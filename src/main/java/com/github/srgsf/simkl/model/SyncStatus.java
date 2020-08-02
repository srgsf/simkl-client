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

import java.util.ArrayList;
import java.util.List;

public class SyncStatus {
    @Json(name = "added")
    public final Counter added;

    @Json(name = "updated")
    public final Counter updated;

    @Json(name = "existing")
    public final Counter existing;

    @Json(name = "deleted")
    public final Counter deleted;

    @Json(name = "not_found")
    public final NotFound notFound;

    @SuppressWarnings("unused")
    SyncStatus(Counter added, Counter updated, Counter existing, Counter deleted, NotFound notFound) {
        this.added = added;
        this.updated = updated;
        this.existing = existing;
        this.deleted = deleted;
        this.notFound = notFound;
    }

    public static class Counter {
        @Json(name = "movies")
        public final Integer movies;
        @Json(name = "shows")
        public final Integer shows;
        @Json(name = "episodes")
        public final Integer episodes;

        @SuppressWarnings("unused")
        Counter(Integer movies, Integer shows, Integer episodes) {
            this.movies = movies;
            this.shows = shows;
            this.episodes = episodes;
        }
    }

    public static class NotFound {
        @Json(name = "movies")
        public final List<MediaObject> movies = new ArrayList<>();

        @Json(name = "shows")
        public final List<MediaObject> shows = new ArrayList<>();

        @Json(name = "seasons")
        public final List<MediaObject> seasons = new ArrayList<>();

        @Json(name = "episodes")
        public final List<MediaObject> episodes = new ArrayList<>();
    }

    public static class MediaObject {
        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "ids")
        public final Ids ids;

        @SuppressWarnings("unused")
        MediaObject(String title, Integer year, Ids ids) {
            this.title = title;
            this.year = year;
            this.ids = ids;
        }
    }
}
