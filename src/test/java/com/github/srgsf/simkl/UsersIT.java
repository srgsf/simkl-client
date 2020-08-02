package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.LastWatchedArt;
import com.github.srgsf.simkl.model.UserInfo;
import com.github.srgsf.simkl.model.UserStats;
import com.github.srgsf.simkl.service.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

class UsersIT {

    static Users api;
    static Simkl client;

    @BeforeAll
    static void setup() {
        client = Utils.initClient();
        api = client.users();
    }

    @Test
    void lastArt() throws IOException {
        Response<LastWatchedArt> resp = api.lastWatchedArt(51).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void stats() throws IOException {
        Response<UserStats> resp = api.statistics(51).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void info() throws IOException {
        Response<UserInfo> resp = api.info().execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }
}
