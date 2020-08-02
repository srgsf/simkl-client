package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import com.github.srgsf.simkl.service.Shows;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static com.github.srgsf.simkl.model.Filters.SeriesType.anime;
import static com.github.srgsf.simkl.model.Filters.SeriesType.tv;

class ShowsTest {

    static Shows api;
    static Simkl client;

    static MockWebServer webServer;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        client = Utils.initClient(webServer.url("/").toString());
        api = client.shows();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }


    @Test
    void summaryTv() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/show_summary.json")));
        Response<Show.Summary> resp = api.summary(tv, 17465L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void summaryTvFull() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/show_detail.json")));
        Response<Show.Detail> resp = api.summaryFull(tv, 17465L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void summaryAnime() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/anime_summary.json")));
        Response<Show.Summary> resp = api.summary(anime, 40398L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void summaryAnimeFull() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/anime_detail.json")));
        Response<Show.Detail> resp = api.summaryFull(anime, 40398L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesTv() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/episodes_tv_summary.json")));
        Response<List<Episode.Summary>> resp = api.episodes(tv, 17465L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesTvFull() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/episodes_tv_detail.json")));
        Response<List<Episode.Detail>> resp = api.episodesFull(tv, 17465L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesAnime() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/episodes_anime_summary.json")));
        Response<List<Episode.Summary>> resp = api.episodes(anime, 39687L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesAnimeFull() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/episodes_anime_detail.json")));
        Response<List<Episode.Detail>> resp = api.episodesFull(anime, 39687L).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void findShow() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/found_show_summary.json")));
        Response<List<FoundShow.Summary>> resp = api.find(Filters.SeriesType.tv, "Game of Thrones").execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void findShowFull() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/found_show_detail.json")));
        Response<List<FoundShow.Detail>> resp = api.findFull(Filters.SeriesType.tv, "Game of Thrones").execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void findAnime() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/found_anime_summary.json")));
        Response<List<FoundShow.Summary>> resp =
                api.find(Filters.SeriesType.anime, "Hunter x Hunter: Greed Island").execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void findAnimeFull() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/found_anime_detail.json")));
        Response<List<FoundShow.Detail>> resp = api.findFull(
                Filters.SeriesType.anime, "Hunter x Hunter: Greed Island").execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void bestTv() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/best_tv.json")));
        Response<List<BestShow>> resp = api.best(BestShow.Filter.watched,
                BestShow.Type.series).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void bestAnime() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/best_anime.json")));
        Response<List<BestAnime>> resp = api.best(BestAnime.Filter.all,
                BestAnime.Type.all).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void premieresTv() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/premieres_tv.json")));
        Response<List<TvPremiere>> resp = api.premieres(TvPremiere.Param.newOne).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void premieresAnime() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/premieres_anime.json")));
        Response<List<AnimePremiere>> resp = api.premieres(AnimePremiere.Param.soon).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void filterTv() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/filter_tv.json")));
        Response<List<FilteredShow>> resp = api.filter(new Requests.FilterShow()
                .genre(Filters.Show.Genre.documentary)
                .sort(Filters.Sort.asc)
                .yearDecade(1980)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void filterAnime() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/filter_anime.json")));
        Response<List<FilteredAnime>> resp = api.filter(new Requests.FilterAnime()
                .genre(Filters.Anime.Genre.angst)
                .sort(Filters.Sort.asc)
                .yearDecade(2000)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }
}
