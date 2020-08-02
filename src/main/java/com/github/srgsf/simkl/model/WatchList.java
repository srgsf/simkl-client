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
import java.util.List;

public class WatchList {

    public enum Name {
        watching, plantowatch, hold, completed, notinteresting
    }


    @Json(name = "shows")
    public final List<Show> shows = new ArrayList<>();
    @Json(name = "anime")
    public final List<Show> anime = new ArrayList<>();
    @Json(name = "movies")
    public final List<Movie> movies = new ArrayList<>();


    static class BaseItem {
        @Json(name = "last_watched_at")
        public final OffsetDateTime lastWatchedAt;

        @Json(name = "status")
        public final Name status;

        @Json(name = "user_rating")
        public final Integer userRating;

        BaseItem(OffsetDateTime lastWatchedAt, Name status, Integer userRating) {
            this.lastWatchedAt = lastWatchedAt;
            this.status = status;
            this.userRating = userRating;
        }
    }


    public static class Movie extends BaseItem {
        @Json(name = "movie")
        public final MediaObject movie;

        @SuppressWarnings("unused")
        Movie(OffsetDateTime lastWatchedAt, Name status, Integer userRating, MediaObject movie) {
            super(lastWatchedAt, status, userRating);
            this.movie = movie;
        }
    }


    public static class Show extends BaseItem {

        @Json(name = "watched_episodes_count")
        public final Integer watchedNum;

        @Json(name = "total_episodes_count")
        public final Integer totalNum;

        @Json(name = "not_aired_episodes_count")
        public final Integer notAiredNum;

        @Json(name = "last_watched")
        public final String lastWatched;

        @Json(name = "next_to_watch")
        public final String nextToWatch;

        @Json(name = "show")
        public final MediaObject show;

        @Json(name = "seasons")
        public final List<Season> seasons = new ArrayList<>();

        @SuppressWarnings("unused")
        Show(OffsetDateTime lastWatchedAt, Name status, Integer userRating,
             Integer watchedNum, Integer totalNum, Integer notAiredNum, String lastWatched,
             String nextToWatch, MediaObject show) {
            super(lastWatchedAt, status, userRating);
            this.watchedNum = watchedNum;
            this.totalNum = totalNum;
            this.notAiredNum = notAiredNum;
            this.lastWatched = lastWatched;
            this.nextToWatch = nextToWatch;
            this.show = show;
        }
    }

    public static class MediaObject {

        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "poster")
        public final String posterPath;

        @Json(name = "ids")
        public final Ids ids;

        @SuppressWarnings("unused")
        MediaObject(String title, Integer year, String posterPath, Ids ids) {
            this.title = title;
            this.year = year;
            this.posterPath = posterPath;
            this.ids = ids;
        }
    }
}
