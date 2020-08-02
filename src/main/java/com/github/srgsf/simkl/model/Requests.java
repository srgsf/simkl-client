/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.simkl.model;

import com.squareup.moshi.Json;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Requests {

    public static class FindById extends EmbeddedIds<FindById> {
        public Filters.ExtendedLevel level;

        public String title;
        public String year;
        public Long crunchyRollId;

        public FindById crunchyRollId(Long crunchyRollId) {
            this.crunchyRollId = crunchyRollId;
            return this;
        }

        public FindById level(Filters.ExtendedLevel level) {
            this.level = level;
            return this;
        }

        public FindById title(String title) {
            this.title = title;
            return this;
        }

        public FindById year(String year) {
            this.year = year;
            return this;
        }
    }

    public static class FindByFile {
        @Json(name = "file")
        public final String fileName;
        @Json(name = "part")
        public Integer part;
        @Json(name = "hash")
        public String hash;

        public FindByFile(String fileName) {
            this.fileName = fileName;
        }

        public FindByFile part(Integer part) {
            this.part = part;
            return this;
        }

        public FindByFile hash(String hash) {
            this.hash = hash;
            return this;
        }
    }

    public static class Random {
        public final Range year = new Range();
        public final Range rating = new Range();

        public Integer limit;

        public Filters.Service service;
        public String type;
        public String genre;
        public boolean includeWatched;

        public Random limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Random service(Filters.Service service) {
            this.service = service;
            return this;
        }

        public Random type(Filters.SeriesType type) {
            this.type = type.name();
            return this;
        }

        public Random movie() {
            this.type = "movie";
            return this;
        }

        public Random genre(Filters.Show.Genre genre) {
            this.genre = genre.value;
            return this;
        }

        public Random genre(Filters.Anime.Genre genre) {
            this.genre = genre.value;
            return this;
        }

        public Random genre(Filters.Movie.Genre genre) {
            this.genre = genre.value;
            movie();
            return this;
        }

        public Random includeWatched(boolean includeWatched) {
            this.includeWatched = includeWatched;
            return this;
        }
    }

    abstract static class Filter<T extends Paged<T>> extends Paged<T> {
        public Filters.Sort sort = Filters.Sort.rank;
        public String year = "2010s";

        @SuppressWarnings("unchecked")
        public T sort(Filters.Sort sort) {
            this.sort = sort;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T yearExact(int year) {
            this.year = String.valueOf(year);
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T yearDecade(int year) {
            this.year = String.join("", String.valueOf(year), "s");
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T yearFilter(Filters.Year year) {
            this.year = year.name();
            return (T) this;
        }
    }

    public static class FilterAnime extends Filter<FilterAnime> {
        public Filters.Anime.Genre genre = Filters.Anime.Genre.all;
        public Filters.Anime.Type type = Filters.Anime.Type.all;
        public Filters.Anime.Network network = Filters.Anime.Network.all;

        public FilterAnime genre(Filters.Anime.Genre genre) {
            this.genre = genre;
            return this;
        }

        public FilterAnime type(Filters.Anime.Type type) {
            this.type = type;
            return this;
        }

        public FilterAnime network(Filters.Anime.Network network) {
            this.network = network;
            return this;
        }
    }

    public static class FilterShow extends Filter<FilterShow> {
        public Filters.Show.Genre genre = Filters.Show.Genre.all;
        public Filters.Show.Type type = Filters.Show.Type.shows;
        public Filters.Show.Network network = Filters.Show.Network.all;
        public Filters.Show.Country country = Filters.Show.Country.us;

        public FilterShow genre(Filters.Show.Genre genre) {
            this.genre = genre;
            return this;
        }

        public FilterShow type(Filters.Show.Type type) {
            this.type = type;
            return this;
        }

        public FilterShow network(Filters.Show.Network network) {
            this.network = network;
            return this;
        }

        public FilterShow country(Filters.Show.Country country) {
            this.country = country;
            return this;
        }
    }

    public static class Rating {
        public WatchList.Type type;
        public Integer rating;
        public OffsetDateTime after;
        public Filters.ExtendedLevel level;

        public Rating type(WatchList.Type type) {
            this.type = type;
            return this;
        }

        public Rating rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Rating after(OffsetDateTime after) {
            this.after = after;
            return this;
        }

        public Rating level(Filters.ExtendedLevel level) {
            this.level = level;
            return this;
        }
    }

    public static class WatchStatus extends EmbeddedIds<WatchStatus> {

        @Json(name = "title")
        public String title;

        @Json(name = "year")
        public Integer year;

        @Json(name = "season")
        public Integer season;

        @Json(name = "episode")
        public Integer episode;

        public WatchStatus title(String title) {
            this.title = title;
            return this;
        }

        public WatchStatus year(Integer year) {
            this.year = year;
            return this;
        }

        public WatchStatus season(Integer season) {
            this.season = season;
            return this;
        }

        public WatchStatus episode(Integer episode) {
            this.episode = episode;
            return this;
        }
    }

    abstract static class StandardMediaObject<T extends StandardMediaObject<T>> extends MediaObjectId {

        @Json(name = "title")
        public String title;

        @Json(name = "year")
        public Integer year;

        StandardMediaObject(Requests.Ids ids) {
            super(ids);
        }

        @SuppressWarnings("unchecked")
        public T title(String title) {
            this.title = title;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T year(Integer year) {
            this.year = year;
            return (T) this;
        }
    }

    public static class WatchList {
        public enum Type {
            shows, movies, anime
        }

        public Type type;
        public com.github.srgsf.simkl.model.WatchList.Name status;
        public Filters.ExtendedLevel level;
        public boolean watchEpisodeDates;
        public boolean nextWatchInfo;
        public OffsetDateTime updatedFrom;

        public WatchList type(Type type) {
            this.type = type;
            return this;
        }

        public WatchList status(com.github.srgsf.simkl.model.WatchList.Name status) {
            this.status = status;
            return this;
        }

        public WatchList level(Filters.ExtendedLevel level) {
            this.level = level;
            return this;
        }

        public WatchList watchEpisodeDates(boolean watchEpisodeDates) {
            this.watchEpisodeDates = watchEpisodeDates;
            return this;
        }

        public WatchList updatedFrom(OffsetDateTime updatedFrom) {
            this.updatedFrom = updatedFrom;
            return this;
        }

        public WatchList nextWatchInfo(boolean nextWatchInfo) {
            this.nextWatchInfo = nextWatchInfo;
            return this;
        }
    }

    abstract static class MediaObjectId {

        @Json(name = "ids")
        public final Requests.Ids ids;

        MediaObjectId(Requests.Ids ids) {
            this.ids = ids;
        }

    }

    public static class Ids extends EmbeddedIds<Ids> {
        @Json(name = "crunchyroll")
        public Long crunchyRollId;

        public Ids crunchyRollId(Long crunchyRollId) {
            this.crunchyRollId = crunchyRollId;
            return this;
        }
    }

    public static class Show extends SyncMediaObject<Show> {

        @Json(name = "seasons")
        public List<Season> seasons;

        public Show(Ids ids) {
            super(ids);
        }

        public Show seasons(List<Season> seasons) {
            this.seasons = seasons;
            return this;
        }
    }

    public static class Movie extends SyncMediaObject<Movie> {
        public Movie(Ids ids) {
            super(ids);
        }
    }

    public static class Episode extends MediaObjectId {

        @Json(name = "watched_at")
        public OffsetDateTime watchedAt;

        public Episode(Ids ids) {
            super(ids);
        }

        public Episode watchedAt(OffsetDateTime watchedAt) {
            this.watchedAt = watchedAt;
            return this;
        }
    }

    abstract static class SyncMediaObject<T extends SyncMediaObject<T>> extends StandardMediaObject<T> {

        @Json(name = "watched_at")
        public OffsetDateTime watchedAt;

        @Json(name = "memo")
        public String memo;

        @Json(name = "rating")
        public Integer userRating;

        @Json(name = "rated_at")
        public OffsetDateTime ratedAt;

        @Json(name = "to")
        public com.github.srgsf.simkl.model.WatchList.Name list;

        @Json(name = "collected_at")
        public OffsetDateTime collectedAt;

        SyncMediaObject(Ids ids) {
            super(ids);
        }

        @SuppressWarnings("unchecked")
        public T watchedAt(OffsetDateTime watchedAt) {
            this.watchedAt = watchedAt;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T memo(String memo) {
            this.memo = memo;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T userRating(Integer userRating) {
            this.userRating = userRating;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T ratedAt(OffsetDateTime ratedAt) {
            this.ratedAt = ratedAt;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T list(com.github.srgsf.simkl.model.WatchList.Name list) {
            this.list = list;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T collectedAt(OffsetDateTime collectedAt) {
            this.collectedAt = collectedAt;
            return (T) this;
        }
    }

    abstract static class BaseSyncList<T extends BaseSyncList<T>> {
        @Json(name = "movies")
        public final List<Movie> movies = new ArrayList<>();

        @Json(name = "shows")
        public final List<Show> shows = new ArrayList<>();

        @SuppressWarnings("unchecked")
        public T addMovies(Movie... items) {
            movies.addAll(Arrays.asList(items));
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T addMovies(Collection<? extends Movie> items) {
            movies.addAll(items);
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T addShows(Collection<? extends Show> items) {
            shows.addAll(items);
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T addShows(Show... items) {
            shows.addAll(Arrays.asList(items));
            return (T) this;
        }
    }

    public static class SyncList extends BaseSyncList<SyncList> {

    }

    public static class SyncListExt extends BaseSyncList<SyncListExt> {

        @Json(name = "episodes")
        public final List<Episode> episodes = new ArrayList<>();


        public SyncListExt addEpisodes(Collection<Episode> items) {
            episodes.addAll(items);
            return this;
        }

        public SyncListExt addEpisodes(Episode... items) {
            episodes.addAll(Arrays.asList(items));
            return this;
        }

    }
}
