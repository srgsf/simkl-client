package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import com.github.srgsf.simkl.service.Search;
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

class SearchTest {

    static Search api;
    static Simkl client;
    static MockWebServer webServer;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        client = Utils.initClient(webServer.url("/").toString());
        api = client.search();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void searchByIdAnime() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/get_by_id_anime.json")));
        Response<List<StandardMediaObject>> resp = api.getById(new Requests.FindById()
                .simkl(40398L).level(Filters.ExtendedLevel.full)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void searchByIdTv() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/get_by_id_tv.json")));
        Response<List<StandardMediaObject>> resp = api.getById(new Requests.FindById().simkl(17465L)
                .level(Filters.ExtendedLevel.full)).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void searchByIdMovie() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/get_by_id_movie.json")));
        Response<List<StandardMediaObject>> resp = api.getById(new Requests.FindById().imdb("tt0241527")).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void searchMovieByFile() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/get_by_file_movie.json")));
        Response<FileMetadata> resp =
                api.findByFile(new Requests.FindByFile("The.Godfather.1972.720p.BluRay.x264.mkv")
                ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void searchEpisodeByFile() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/get_by_file_episode.json")));
        Response<FileMetadata> resp =
                api.findByFile(new Requests.FindByFile("Were.The.Fugawis.S01E01E02.WS.DSR.x264-NY2.mkv")
                ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void searchAnimeEpisodeByFile() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/get_by_file_anime.json")));
        Response<FileMetadata> resp =
                api.findByFile(new Requests.FindByFile("[TaigaSubs]_Toradora!_(2008)_-_01v2_-_Tiger_and_Dragon_[1280x720_H.264_FLAC][1234ABCD].mkv")
                ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void randomNetflix() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/random_netflix.json")));
        Response<MediaItemLink> resp = api.random(new Requests.Random().service(Filters.Service.netflix)
                .includeWatched(true)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void randomSimkl() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/random_simkl.json")));
        Response<MediaItemLink> resp = api.random(new Requests.Random()
                .service(Filters.Service.simkl)
                .includeWatched(true)
        ).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }
}
