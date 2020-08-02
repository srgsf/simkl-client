package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.LastWatchedArt;
import com.github.srgsf.simkl.model.UserInfo;
import com.github.srgsf.simkl.model.UserStats;
import com.github.srgsf.simkl.service.Users;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

class UsersTest {

    static Users api;
    static Simkl client;
    static MockWebServer webServer;

    @BeforeAll
    static void setup() throws IOException {
        webServer = new MockWebServer();
        webServer.start();
        client = Utils.initClient(webServer.url("/").toString());
        api = client.users();
    }

    @AfterAll
    static void tearDown() throws IOException {
        webServer.shutdown();
    }

    @Test
    void lastArt() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/last_art.json")));
        Response<LastWatchedArt> resp = api.lastWatchedArt(51).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertFalse(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void stats() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/user_stats.json")));
        Response<UserStats> resp = api.statistics(51).execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void info() throws IOException, InterruptedException {
        webServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(Utils.readFromFile("json/user_info.json")));
        Response<UserInfo> resp = api.info().execute();
        RecordedRequest req = webServer.takeRequest();
        Assertions.assertTrue(Utils.checkAuthorization(req));
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }
}
