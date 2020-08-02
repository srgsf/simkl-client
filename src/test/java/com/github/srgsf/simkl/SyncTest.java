package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import com.github.srgsf.simkl.service.Sync;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


class SyncTest {

    static Sync api;
    static Simkl client;
    static MockWebServer webServer;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        client = Utils.initClient(webServer.url("/").toString());
        api = client.sync();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void lastActivities() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/last_activities.json")));
        Response<LastActivities> resp = api.lastActivities().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void allItems() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/all_items.json")));
        Response<WatchList> resp = api.getAllItems(new Requests.WatchList().watchEpisodeDates(true)
                .level(Filters.ExtendedLevel.full)
                .nextWatchInfo(true)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void addToHistoryNotFound() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/show_summary.json")));
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852xxxdd")))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691111111L)))
                .addEpisodes(new Requests.Episode(new Requests.Ids().simkl(34363322222L)));
        Response<SyncStatus> resp = api.addToHistory(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void addToHistory() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/sync_status_added.json")));
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)))
                .addEpisodes(new Requests.Episode(new Requests.Ids().simkl(343633L)));
        Response<SyncStatus> resp = api.addToHistory(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void removeFromHistory() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/sync_status_removed.json")));
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)))
                .addEpisodes(new Requests.Episode(new Requests.Ids().simkl(343633L)));
        Response<SyncStatus> resp = api.removeFromHistory(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void addUserRating() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/sync_status_added.json")));
        Requests.SyncList list = new Requests.SyncList();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).userRating(8).ratedAt(OffsetDateTime.now()))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)).userRating(6).ratedAt(OffsetDateTime.now()));

        Response<SyncStatus> resp = api.addRatings(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void userRatings() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/user_ratings.json")));
        Response<WatchList> resp = api.getUserRatings(new Requests.Rating()
                .level(Filters.ExtendedLevel.full)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void removeUserRatings() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/sync_status_removed.json")));
        Requests.SyncList list = new Requests.SyncList();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).userRating(8).ratedAt(OffsetDateTime.now()))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)).userRating(6).ratedAt(OffsetDateTime.now()));
        Response<SyncStatus> resp = api.removeRatings(list
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void addToCollection() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/sync_status_added.json")));
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")));
        Response<SyncStatus> resp = api.addToCollection(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void removeFromCollection() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/sync_status_removed.json")));
        Requests.SyncListExt list = new Requests.SyncListExt();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).collectedAt(OffsetDateTime.now()));
        Response<SyncStatus> resp = api.removeFromCollection(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void addToWatchList() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/wl_sync_status.json")));
        Requests.SyncList list = new Requests.SyncList();
        list.addMovies(new Requests.Movie(new Requests.Ids().imdb("tt0181852")).list(WatchList.Name.hold))
                .addShows(new Requests.Show(new Requests.Ids().simkl(13691L)).list(WatchList.Name.watching));
        Response<WatchListSyncStatus> resp = api.addToWatchList(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void isWatching() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/is_watching.json")));
        ArrayList<Requests.WatchStatus> list = new ArrayList<>();
        list.add(new Requests.WatchStatus().imdb("tt0181852"));
        list.add(new Requests.WatchStatus().simkl(13692L));
        list.add(new Requests.WatchStatus().simkl(10000000L));
        Response<List<WatchStatus>> resp = api.isWatching(list).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void checkinMovie() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/checkin_movie.json")));
        Response<Checkin> resp =
                api.checkin(new Checkin(
                        new Checkin.Movie(new Requests.Ids().imdb("tt0181852"))
                )).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void checkinEpisode() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/checkin_episode.json")));
        Response<Checkin> resp =
                api.checkin(new Checkin(
                        new Checkin.Episode(new Requests.Ids().simkl(1278828L), 1, 1)
                )).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }
}
