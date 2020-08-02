package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import com.github.srgsf.simkl.service.Sync;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SyncIT {

    static Sync api;
    static Simkl client;

    @BeforeAll
    static void setup() {
        client = Utils.initClient();
        api = client.sync();
    }

    @Test
    void lastActivities() throws IOException {
        Response<LastActivities> resp = api.lastActivities().execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void allItems() throws IOException {
        Response<WatchList> resp = api.getAllItems(new Requests.WatchList().watchEpisodeDates(true)
                .level(Filters.ExtendedLevel.full)
                .nextWatchInfo(true)
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void addToHistoryNotFound() throws IOException {
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852xxxdd")))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691111111L)))
                .addEpisodes(new Requests.Episode(new Requests.Ids().simkl(34363322222L)));
        Response<SyncStatus> resp = api.addToHistory(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Order(1)
    @Test
    void addToHistory() throws IOException {
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)))
                .addEpisodes(new Requests.Episode(new Requests.Ids().simkl(343633L)));
        Response<SyncStatus> resp = api.addToHistory(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Order(2)
    @Test
    void removeFromHistory() throws IOException {
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)))
                .addEpisodes(new Requests.Episode(new Requests.Ids().simkl(343633L)));
        Response<SyncStatus> resp = api.removeFromHistory(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Order(3)
    @Test
    void addUserRating() throws IOException {
        Requests.SyncList list = new Requests.SyncList();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).userRating(8).ratedAt(OffsetDateTime.now()))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)).userRating(6).ratedAt(OffsetDateTime.now()));

        Response<SyncStatus> resp = api.addRatings(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Order(4)
    @Test
    void userRatings() throws IOException {
        Response<WatchList> resp = api.getUserRatings(new Requests.Rating()
                .level(Filters.ExtendedLevel.full)
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Order(5)
    @Test
    void removeUserRatings() throws IOException {
        Requests.SyncList list = new Requests.SyncList();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).userRating(8).ratedAt(OffsetDateTime.now()))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)).userRating(6).ratedAt(OffsetDateTime.now()));
        Response<SyncStatus> resp = api.removeRatings(list
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Disabled("doesn't work using example from website")
    @Order(6)
    @Test
    void addToCollection() throws IOException {
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")));
        Response<SyncStatus> resp = api.addToCollection(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Disabled("doesn't work using example from website")
    @Order(7)
    @Test
    void removeFromCollection() throws IOException {
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).collectedAt(OffsetDateTime.now()));
        Response<SyncStatus> resp = api.removeFromCollection(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Order(8)
    @Test
    void addToWatchList() throws IOException {
        Requests.SyncList list = new Requests.SyncList();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).list(com.github.srgsf.simkl.model.WatchList.Name.hold))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)).list(com.github.srgsf.simkl.model.WatchList.Name.watching));
        Response<WatchListSyncStatus> resp = api.addToWatchList(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Order(9)
    @Test
    void isWatching() throws IOException {
        ArrayList<Requests.WatchStatus> list = new ArrayList<>();
        list.add(new Requests.WatchStatus().imdb("tt0181852"));
        list.add(new Requests.WatchStatus().simkl(13692L));
        list.add(new Requests.WatchStatus().simkl(10000000L));
        Response<List<WatchStatus>> resp = api.isWatching(list).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Order(10)
    @Test
    void checkinMovie() throws IOException {
        Response<Checkin> resp =
                api.checkin(new Checkin(
                        new Checkin.Movie(new Requests.Ids().imdb("tt0181852"))
                )).execute();
        if (resp.isSuccessful()) {
            Assertions.assertNotNull(resp.body());
        } else {
            Assertions.assertEquals(HttpURLConnection.HTTP_CONFLICT, resp.code());
        }
    }

    @Order(11)
    @Test
    void checkinEpisode() throws IOException {
        Response<Checkin> resp =
                api.checkin(new Checkin(
                        new Checkin.Episode(new Requests.Ids().simkl(1278828L), 1, 1)
                )).execute();
        if (resp.isSuccessful()) {
            Assertions.assertNotNull(resp.body());
        } else {
            Assertions.assertEquals(HttpURLConnection.HTTP_CONFLICT, resp.code());
        }
    }
}
