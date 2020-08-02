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

public class UserStats {

    @Json(name = "total_mins")
    public final Long totalMinutes;

    @Json(name = "tv")
    public final Shows shows;

    @Json(name = "anime")
    public final Shows anime;

    @Json(name = "movies")
    public final Movies movies;

    @Json(name = "watched_last_week")
    public final LastWeek lastWeek;

    @SuppressWarnings("unused")
    UserStats(Long totalMinutes, Shows shows, Shows anime, Movies movies, LastWeek lastWeek) {
        this.totalMinutes = totalMinutes;
        this.shows = shows;
        this.anime = anime;
        this.movies = movies;
        this.lastWeek = lastWeek;
    }

    public static class Movies {
        @Json(name = "total_mins")
        public final Long totalMinutes;

        @Json(name = "plantowatch")
        public final MovieCount unwatched;

        @Json(name = "notinteresting")
        public final MovieCount notInteresting;

        @Json(name = "completed")
        public final MovieCount watched;

        @SuppressWarnings("unused")
        Movies(Long totalMinutes, MovieCount unwatched, MovieCount notInteresting, MovieCount watched) {
            this.totalMinutes = totalMinutes;
            this.unwatched = unwatched;
            this.notInteresting = notInteresting;
            this.watched = watched;
        }
    }

    public static class Shows {
        @Json(name = "total_mins")
        public final Long totalMinutes;

        @Json(name = "watching")
        public final Watching watching;

        @Json(name = "hold")
        public final ShowCount hold;

        @Json(name = "plantowatch")
        public final ShowCount unwatched;

        @Json(name = "notinteresting")
        public final ShowCount notInteresting;

        @Json(name = "completed")
        public final ShowCount watched;

        @SuppressWarnings("unused")
        Shows(Long totalMinutes, Watching watching, ShowCount hold, ShowCount unwatched, ShowCount notInteresting, ShowCount watched) {
            this.totalMinutes = totalMinutes;
            this.watching = watching;
            this.hold = hold;
            this.unwatched = unwatched;
            this.notInteresting = notInteresting;
            this.watched = watched;
        }
    }


    public static class MovieCount {
        @Json(name = "mins")
        public final Long minutes;

        @Json(name = "count")
        public final Integer count;

        @SuppressWarnings("unused")
        MovieCount(Long minutes, Integer count) {
            this.minutes = minutes;
            this.count = count;
        }
    }

    public static class ShowCount {
        @Json(name = "watched_episodes_count")
        public final Integer watched;

        @Json(name = "count")
        public final Integer count;

        @SuppressWarnings("unused")
        ShowCount(Integer watched, Integer count) {
            this.watched = watched;
            this.count = count;
        }
    }

    public static class Watching extends ShowCount {
        @Json(name = "total_episodes_count")
        public final Integer totalCount;

        @Json(name = "left_to_watch_mins")
        public final Long leftMinutes;

        @SuppressWarnings("unused")
        Watching(Integer watched, Integer count, Integer totalCount, Long leftMinutes) {
            super(watched, count);
            this.totalCount = totalCount;
            this.leftMinutes = leftMinutes;
        }
    }


    public static class LastWeek {
        @Json(name = "total_mins")
        public final Long totalMinutes;

        @Json(name = "movies_mins")
        public final Long moviesMinutes;

        @Json(name = "tv_mins")
        public final Long tvMinutes;

        @Json(name = "anime_mins")
        public final Long animeMinutes;

        @SuppressWarnings("unused")
        LastWeek(Long totalMinutes, Long moviesMinutes, Long tvMinutes, Long animeMinutes) {
            this.totalMinutes = totalMinutes;
            this.moviesMinutes = moviesMinutes;
            this.tvMinutes = tvMinutes;
            this.animeMinutes = animeMinutes;
        }
    }
}