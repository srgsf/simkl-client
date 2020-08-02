/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import com.github.srgsf.simkl.service.*;
import retrofit2.Call;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

class ApiImpl implements Search, Shows, Movies, Sync, Users {

    private final SimklApi api;


    ApiImpl(SimklApi api) {
        this.api = api;
    }

    @Override
    public Call<List<StandardMediaObject>> getById(Requests.FindById r) {
        return api.getById(
                r.simkl,
                r.hulu,
                r.netflix,
                r.mal,
                r.tvdb,
                r.tmdb,
                r.imdb,
                r.anidb,
                r.crunchyRollId,
                r.type,
                r.title,
                r.year,
                r.level);
    }


    @Override
    public Call<FileMetadata> findByFile(Requests.FindByFile file) {
        return api.findByFile(file);
    }

    @Override
    public Call<MediaItemLink> random(Requests.Random param) {
        return param.includeWatched ?
                api.randomAll(param.service,
                        param.type,
                        param.genre,
                        param.rating.from,
                        param.rating.to,
                        param.year.from,
                        param.year.to,
                        param.limit)
                :
                api.random(param.service,
                        param.type,
                        param.genre,
                        param.rating.from,
                        param.rating.to,
                        param.year.from,
                        param.year.to,
                        param.limit);
    }


    // shows


    @Override
    public Call<Show.Detail> summaryFull(Filters.SeriesType type, String imdb) {
        return api.summary(type, imdb, Filters.ExtendedLevel.full);
    }

    @Override
    public Call<Show.Summary> summary(Filters.SeriesType type, String imdb) {
        return api.summary(type, imdb);
    }

    @Override
    public Call<Show.Detail> summaryFull(Filters.SeriesType type, long simkl) {
        return summaryFull(type, String.valueOf(simkl));
    }

    @Override
    public Call<Show.Summary> summary(Filters.SeriesType type, long simkl) {
        return summary(type, String.valueOf(simkl));
    }

    @Override
    public Call<List<Episode.Detail>> episodesFull(Filters.SeriesType type, long simkl) {
        return api.episodes(type, simkl, Filters.ExtendedLevel.full);
    }

    @Override
    public Call<List<Episode.Summary>> episodes(Filters.SeriesType type, long simkl) {
        return api.episodes(type, simkl);
    }

    @Override
    public Call<List<TvPremiere>> premieres(TvPremiere.Param param, TvPremiere.Type type) {
        return premieres(param, type, null, null);
    }

    @Override
    public Call<List<TvPremiere>> premieres(TvPremiere.Param param) {
        return premieres(param, null);
    }

    @Override
    public Call<List<TvPremiere>> premieres(TvPremiere.Param param, Integer page, Integer limit) {
        return premieres(param, null, page, limit);
    }


    @Override
    public Call<List<TvPremiere>> premieres(TvPremiere.Param param, TvPremiere.Type type, Integer page, Integer limit) {
        return api.premieres(param.value, Optional.ofNullable(type).map(t -> t.value).orElse(null),
                page, limit);
    }

    @Override
    public Call<List<AnimePremiere>> premieres(AnimePremiere.Param param, AnimePremiere.Type type) {
        return premieres(param, type, null, null);
    }

    @Override
    public Call<List<AnimePremiere>> premieres(AnimePremiere.Param param) {
        return premieres(param, null);
    }

    @Override
    public Call<List<AnimePremiere>> premieres(AnimePremiere.Param param, Integer page, Integer limit) {
        return premieres(param, null, page, limit);
    }


    @Override
    public Call<List<AnimePremiere>> premieres(AnimePremiere.Param param, AnimePremiere.Type type, Integer page, Integer limit) {
        return api.premieres(param.value, type, page, limit);
    }


    @Override
    public Call<List<BestShow>> best(BestShow.Filter filter, BestShow.Type type) {
        return api.best(filter, type);
    }

    @Override
    public Call<List<BestShow>> best(BestShow.Filter filter) {
        return best(filter, null);
    }

    @Override
    public Call<List<BestAnime>> best(BestAnime.Filter filter, BestAnime.Type type) {
        return api.best(filter, type);
    }

    @Override
    public Call<List<BestAnime>> best(BestAnime.Filter filter) {
        return best(filter, null);
    }


    @Override
    public Call<List<FilteredAnime>> filter(Requests.FilterAnime filter) {
        return api.filter(filter.genre.value, filter.type, filter.network.value, filter.year,
                filter.sort.value,
                filter.page, filter.limit);
    }

    @Override
    public Call<List<FilteredShow>> filter(Requests.FilterShow filter) {
        return api.filter(filter.genre.value, filter.type.value, filter.country.value, filter.network.value,
                filter.year,
                filter.sort.value,
                filter.page, filter.limit);
    }


    @Override
    public Call<List<FoundShow.Summary>> find(Filters.SeriesType type, String query) {
        return find(type, query, null, null);
    }

    @Override
    public Call<List<FoundShow.Detail>> findFull(Filters.SeriesType type, String query) {
        return findFull(type, query, null, null);
    }

    @Override
    public Call<List<FoundShow.Summary>> find(Filters.SeriesType type, String query, Integer page, Integer limit) {
        return api.findShows(type, query, page, limit);
    }

    @Override
    public Call<List<FoundShow.Detail>> findFull(Filters.SeriesType type, String query, Integer page, Integer limit) {
        return api.findShows(type, query, Filters.ExtendedLevel.full, page, limit);
    }

    @Override
    public Call<List<FoundMovie.Summary>> find(String query) {
        return find(query, null, null);
    }

    @Override
    public Call<List<FoundMovie.Detail>> findFull(String query) {
        return findFull(query, null, null);
    }

    @Override
    public Call<List<FoundMovie.Summary>> find(String query, Integer page, Integer limit) {
        return api.findMovies(query, page, limit);
    }

    @Override
    public Call<List<FoundMovie.Detail>> findFull(String query, Integer page, Integer limit) {
        return api.findMovies(query, Filters.ExtendedLevel.full, page, limit);
    }

    @Override
    public Call<Movie.Detail> summaryFull(String imdbId) {
        return api.summary(imdbId, Filters.ExtendedLevel.full);
    }

    @Override
    public Call<Movie.Summary> summary(String imdbId) {
        return api.summary(imdbId);
    }

    @Override
    public Call<Movie.Detail> summaryFull(long simkl) {
        return summaryFull(String.valueOf(simkl));
    }

    @Override
    public Call<Movie.Summary> summary(long simkl) {
        return api.summary(String.valueOf(simkl));
    }


    @Override
    public Call<LastWatchedArt> lastWatchedArt(int userId) {
        return api.lastWatchedArt(userId);
    }

    @Override
    public Call<UserInfo> info() {
        return api.user();
    }

    @Override
    public Call<UserStats> statistics(int userId) {
        return api.stats(userId);
    }


    @Override
    public Call<LastActivities> lastActivities() {
        return api.lastActivities();
    }

    @Override
    public Call<WatchList> getAllItems(Requests.WatchList watch) {
        OffsetDateTime dt = null;
        if (watch.updatedFrom != null) {
            dt = watch.updatedFrom.withOffsetSameInstant(ZoneOffset.UTC);
        }

        String returnEpisodeDates = watch.watchEpisodeDates ? "true" : null;
        String nextWatchInfo = watch.nextWatchInfo ? "true" : null;


        if (watch.type == null && watch.status == null) {
            return api.getAll(
                    watch.level,
                    returnEpisodeDates,
                    nextWatchInfo,
                    dt);
        }

        if (!(watch.type == null || watch.status == null)) {
            return api.getAll(
                    watch.type,
                    watch.status,
                    watch.level,
                    returnEpisodeDates,
                    nextWatchInfo,
                    dt);
        }

        if (watch.type == null) {
            return api.getAll(
                    watch.status,
                    watch.level,
                    returnEpisodeDates,
                    nextWatchInfo,
                    dt);
        }

        return api.getAll(
                watch.type,
                watch.level,
                returnEpisodeDates,
                nextWatchInfo,
                dt);
    }

    @Override
    public Call<WatchList> getUserRatings() {
        return api.getUserRatings(null, null);
    }

    @Override
    public Call<WatchList> getUserRatings(Requests.Rating param) {
        OffsetDateTime dt = null;
        if (param.after != null) {
            dt = param.after.withOffsetSameInstant(ZoneOffset.UTC);
        }
        if (param.type == null && param.rating == null) {
            return api.getUserRatings(dt, param.level);
        }
        if (!(param.type == null || param.rating == null)) {
            return api.getUserRatings(param.type, param.rating, dt, param.level);
        }

        if (param.type == null) {
            return api.getUserRatings(param.rating, dt, param.level);
        }
        return api.getUserRatings(param.type, dt, param.level);
    }

    @Override
    public Call<Checkin> checkin(Checkin checkin) {
        return api.checkin(checkin);
    }

    @Override
    public Call<SyncStatus> addToHistory(Requests.SyncListExt list) {
        return api.addToHistory(list);
    }

    @Override
    public Call<SyncStatus> removeFromHistory(Requests.SyncListExt list) {
        return api.removeFromHistory(list);
    }

    @Override
    public Call<SyncStatus> addRatings(Requests.SyncList list) {
        return api.addRatings(list);
    }

    @Override
    public Call<SyncStatus> removeRatings(Requests.SyncList list) {
        return api.removeRatings(list);
    }

    @Override
    public Call<WatchListSyncStatus> addToWatchList(Requests.SyncList list) {
        return api.addToWatchList(list);
    }

    @Override
    public Call<SyncStatus> addToCollection(Requests.SyncListExt list) {
        return api.addToCollection(list);
    }


    @Override
    public Call<SyncStatus> removeFromCollection(Requests.SyncListExt list) {
        return api.removeFromCollection(list);
    }

    @Override
    public Call<List<WatchStatus>> isWatching(List<Requests.WatchStatus> list) {
        return api.isWatching(list);
    }
}
