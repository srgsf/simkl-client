package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import com.github.srgsf.simkl.service.Search;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

class SearchIT {

    static Search api;
    static Simkl client;

    @BeforeAll
    static void setup() {
        client = Utils.initClient();
        api = client.search();
    }

    @Test
    void searchByIdAnime() throws IOException {
        Response<List<StandardMediaObject>> resp = api.getById(new Requests.FindById()
                .simkl(40398L).level(Filters.ExtendedLevel.full)).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void searchByIdTv() throws IOException {
        Response<List<StandardMediaObject>> resp = api.getById(new Requests.FindById().simkl(17465L)
                .level(Filters.ExtendedLevel.full)).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void searchByIdMovie() throws IOException {
        Response<List<StandardMediaObject>> resp = api.getById(new Requests.FindById().imdb("tt0241527")).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void searchMovieByFile() throws IOException {
        Response<FileMetadata> resp =
                api.findByFile(new Requests.FindByFile("The.Godfather.1972.720p.BluRay.x264.mkv")
                ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void searchEpisodeByFile() throws IOException {
        Response<FileMetadata> resp =
                api.findByFile(new Requests.FindByFile("Were.The.Fugawis.S01E01E02.WS.DSR.x264-NY2.mkv")
                ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void searchAnimeEpisodeByFile() throws IOException {
        Response<FileMetadata> resp =
                api.findByFile(new Requests.FindByFile("[TaigaSubs]_Toradora!_(2008)_-_01v2_-_Tiger_and_Dragon_[1280x720_H.264_FLAC][1234ABCD].mkv")
                ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void randomNetflix() throws IOException {
        Response<MediaItemLink> resp = api.random(new Requests.Random().service(Filters.Service.netflix)
                .includeWatched(true)
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void randomSimkl() throws IOException {
        Response<MediaItemLink> resp = api.random(new Requests.Random()
                .service(Filters.Service.simkl)
                .includeWatched(true)
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }
}
