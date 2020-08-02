package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import com.github.srgsf.simkl.service.Shows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static com.github.srgsf.simkl.model.Filters.SeriesType.anime;
import static com.github.srgsf.simkl.model.Filters.SeriesType.tv;

class ShowsIT {

    static Shows api;
    static Simkl client;

    @BeforeAll
    static void setup() {
        client = Utils.initClient();
        api = client.shows();
    }


    @Test
    void summaryTv() throws IOException {
        Response<Show.Summary> resp = api.summary(tv, 17465L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void summaryTvFull() throws IOException {
        Response<Show.Detail> resp = api.summaryFull(tv, 17465L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void summaryAnime() throws IOException {
        Response<Show.Summary> resp = api.summary(anime, 40398L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void summaryAnimeFull() throws IOException {
        Response<Show.Detail> resp = api.summaryFull(anime, 40398L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesTv() throws IOException {
        Response<List<Episode.Summary>> resp = api.episodes(tv, 17465L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesTvFull() throws IOException {
        Response<List<Episode.Detail>> resp = api.episodesFull(tv, 17465L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesAnime() throws IOException {
        Response<List<Episode.Summary>> resp = api.episodes(anime, 39687L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void episodesAnimeFull() throws IOException {
        Response<List<Episode.Detail>> resp = api.episodesFull(anime, 39687L).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void findShow() throws IOException {
        Response<List<FoundShow.Summary>> resp = api.find(Filters.SeriesType.tv, "Game of Thrones").execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void findShowFull() throws IOException {
        Response<List<FoundShow.Detail>> resp = api.findFull(Filters.SeriesType.tv, "Game of Thrones").execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void findAnime() throws IOException {
        Response<List<FoundShow.Summary>> resp = api.find(Filters.SeriesType.anime, "Hunter x Hunter: Greed Island").execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void findAnimeFull() throws IOException {
        Response<List<FoundShow.Detail>> resp = api.findFull(Filters.SeriesType.anime, "Hunter x Hunter: Greed Island").execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void bestTv() throws IOException {
        Response<List<BestShow>> resp = api.best(BestShow.Filter.watched,
                BestShow.Type.series).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void bestAnime() throws IOException {
        Response<List<BestAnime>> resp = api.best(BestAnime.Filter.all,
                BestAnime.Type.all).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void premieresTv() throws IOException {
        Response<List<TvPremiere>> resp = api.premieres(TvPremiere.Param.newOne).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void premieresAnime() throws IOException {
        Response<List<AnimePremiere>> resp = api.premieres(AnimePremiere.Param.soon).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }


    @Test
    void filterTv() throws IOException {
        Response<List<FilteredShow>> resp = api.filter(new Requests.FilterShow()
                .genre(Filters.Show.Genre.documentary)
                .sort(Filters.Sort.asc)
                .yearDecade(1980)
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void filterAnime() throws IOException {
        Response<List<FilteredAnime>> resp = api.filter(new Requests.FilterAnime()
                .genre(Filters.Anime.Genre.angst)
                .sort(Filters.Sort.asc)
                .yearDecade(2000)
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNotNull(resp.body());
    }

    @Test
    void filterNotFound() throws IOException {
        Response<List<FilteredShow>> resp = api.filter(new Requests.FilterShow()
                .genre(Filters.Show.Genre.documentary)
                .sort(Filters.Sort.asc)
                .yearDecade(1910)
        ).execute();
        Assertions.assertTrue(resp.isSuccessful());
        Assertions.assertNull(resp.body());
    }
}
