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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    public static class Summary {
        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "type")
        public final Filters.TmdbType type;

        @Json(name = "ids")
        protected final Ids ids;

        public SimklId ids() {
            return ids;
        }

        @SuppressWarnings("unused")
        Summary(String title, Integer year, Filters.TmdbType type, Ids ids) {
            this.title = title;
            this.year = year;
            this.type = type;
            this.ids = ids;
        }
    }

    public static class Release {
        @Json(name = "type")
        public final Integer type;

        @Json(name = "release_date")
        public final LocalDate date;

        @SuppressWarnings("unused")
        Release(Integer type, LocalDate date) {
            this.type = type;
            this.date = date;
        }
    }

    public static class Releases {
        @Json(name = "iso_3166_1")
        public final String country;

        @Json(name = "results")
        public final List<Release> releases = new ArrayList<>();

        @SuppressWarnings("unused")
        Releases(String country) {
            this.country = country;
        }
    }

    public static class Detail extends Summary {

        @Json(name = "released")
        public final LocalDate released;

        @Json(name = "director")
        public final String director;

        @Json(name = "budget")
        public final Long budget;

        @Json(name = "revenue")
        public final Long revenue;

        @Json(name = "rank")
        public final Integer rank;

        @Json(name = "poster")
        public final String posterUrl;

        @Json(name = "fanart")
        public final String fanartUrl;

        @Json(name = "runtime")
        public final Integer runtime;

        @Json(name = "certification")
        public final String certification;

        @Json(name = "overview")
        public final String overview;

        @Json(name = "ratings")
        public final Ratings ratings;

        @Json(name = "country")
        public final String country;

        @Json(name = "trailers")
        public final List<Trailer> trailers = new ArrayList<>();

        @Json(name = "genres")
        public final List<String> genres = new ArrayList<>();

        @Json(name = "release_dates")
        public final List<Releases> releases = new ArrayList<>();

        @Override
        public Ids ids() {
            return ids;
        }

        @SuppressWarnings("unused")
        Detail(String title, Integer year, Filters.TmdbType type, Ids ids, LocalDate released, String director,
               Long budget, Long revenue, Integer rank, String posterUrl, String fanartUrl,
               Integer runtime, String certification, String overview, Ratings ratings, String country) {
            super(title, year, type, ids);
            this.released = released;
            this.director = director;
            this.budget = budget;
            this.revenue = revenue;
            this.rank = rank;
            this.posterUrl = posterUrl;
            this.fanartUrl = fanartUrl;
            this.runtime = runtime;
            this.certification = certification;
            this.overview = overview;
            this.ratings = ratings;
            this.country = country;
        }
    }
}
