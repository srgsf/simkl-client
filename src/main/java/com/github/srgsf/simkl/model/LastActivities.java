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

public class LastActivities {

    @Json(name = "all")
    public final OffsetDateTime all;

    @Json(name = "tv_shows")
    public final Series shows;

    @Json(name = "anime")
    public final Series anime;

    @Json(name = "movies")
    public final Movies movies;

    @SuppressWarnings("unused")
    LastActivities(OffsetDateTime all, Series shows, Series anime, Movies movies) {
        this.all = all;
        this.shows = shows;
        this.anime = anime;
        this.movies = movies;
    }

    public static class Movies {
        @Json(name = "all")
        public final OffsetDateTime all;

        @Json(name = "rated_at")
        public final OffsetDateTime rated_at;

        @Json(name = "plantowatch")
        public final OffsetDateTime plantowatch;

        @Json(name = "completed")
        public final OffsetDateTime completed;

        @Json(name = "notinteresting")
        public final OffsetDateTime notinteresting;

        @Json(name = "removed_from_list")
        public final OffsetDateTime removedFromList;

        @SuppressWarnings("unused")
        Movies(OffsetDateTime all, OffsetDateTime rated_at,
               OffsetDateTime plantowatch, OffsetDateTime completed,
               OffsetDateTime notinteresting, OffsetDateTime removedFromList) {
            this.all = all;
            this.rated_at = rated_at;
            this.plantowatch = plantowatch;
            this.completed = completed;
            this.notinteresting = notinteresting;
            this.removedFromList = removedFromList;
        }
    }

    public static class Series extends Movies {
        @Json(name = "watching")
        public final OffsetDateTime watching;
        @Json(name = "hold")
        public final OffsetDateTime hold;

        @SuppressWarnings("unused")
        Series(OffsetDateTime all, OffsetDateTime rated_at, OffsetDateTime plantowatch,
               OffsetDateTime completed, OffsetDateTime notinteresting, OffsetDateTime removedFromList,
               OffsetDateTime watching, OffsetDateTime hold) {
            super(all, rated_at, plantowatch, completed, notinteresting, removedFromList);
            this.watching = watching;
            this.hold = hold;
        }
    }
}
