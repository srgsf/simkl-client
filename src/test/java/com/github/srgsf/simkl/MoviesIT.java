package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.FoundMovie;
import com.github.srgsf.simkl.model.Movie;
import com.github.srgsf.simkl.service.Movies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

class MoviesIT {

    static Movies api;
    static Simkl client;

    @BeforeAll
    static void setup() {
        client = Utils.initClient();
        api = client.movies();
    }

    @Test
    void summary() throws IOException {
        Response<Movie.Summary> resp = api.summary("tt1201607").execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void summaryFull() throws IOException {
        Response<Movie.Detail> resp = api.summaryFull(54112L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void find() throws IOException {
        Response<List<FoundMovie.Summary>> resp = api.find("Harry Potter").execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void findFull() throws IOException {
        Response<List<FoundMovie.Detail>> resp = api.findFull("Harry Potter").execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }
}
