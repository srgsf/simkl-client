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

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class Show {
    public enum Type {
        show, anime
    }

    public static class Summary {
        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "type")
        public final Type type;

        @Json(name = "ids")
        protected final Ids ids;

        public SimklId ids() {
            return ids;
        }

        @SuppressWarnings("unused")
        Summary(String title, Integer year, Type type, Ids ids) {
            this.title = title;
            this.year = year;
            this.type = type;
            this.ids = ids;
        }
    }


    public static class Detail extends Summary {

        @Json(name = "en_title")
        public final String enTitle;

        @Json(name = "alt_titles")
        public final List<AltTitle> altTitles = new ArrayList<>();

        @Json(name = "rank")
        public final Integer rank;

        @Json(name = "poster")
        public final String posterUrl;

        @Json(name = "fanart")
        public final String fanartUrl;

        @Json(name = "first_aired")
        public final OffsetDateTime firstAired;

        @Json(name = "airs")
        public final AirTime airs;

        @Json(name = "runtime")
        public final Integer runtime;

        @Json(name = "certification")
        public final String certification;

        @Json(name = "overview")
        public final String overview;

        @Json(name = "genres")
        public final List<String> genres = new ArrayList<>();

        @Json(name = "country")
        public final String country;

        @Json(name = "total_episodes")
        public final Integer episodes;

        @Json(name = "status")
        public final String status;

        @Json(name = "network")
        public final String network;

        @Json(name = "anime_type")
        public final String animeType;

        @Json(name = "ratings")
        public final Ratings ratings;

        @Json(name = "trailers")
        public final List<Trailer> trailers = new ArrayList<>();

        @Json(name = "users_recommendations")
        public final List<Recommendation> recommendations = new ArrayList<>();

        @Json(name = "relations")
        public final List<Relation> relations = new ArrayList<>();

        public Ids ids() {
            return ids;
        }

        @SuppressWarnings("unused")
        Detail(String title, Integer year, Type type, Ids ids, String enTitle, Integer rank, String posterUrl,
               String fanartUrl, OffsetDateTime firstAired, AirTime airs, Integer runtime, String certification,
               String overview, String country, Integer episodes, String status, String network,
               String animeType, Ratings ratings) {
            super(title, year, type, ids);
            this.enTitle = enTitle;
            this.rank = rank;
            this.posterUrl = posterUrl;
            this.fanartUrl = fanartUrl;
            this.firstAired = firstAired;
            this.airs = airs;
            this.runtime = runtime;
            this.certification = certification;
            this.overview = overview;
            this.country = country;
            this.episodes = episodes;
            this.status = status;
            this.network = network;
            this.animeType = animeType;
            this.ratings = ratings;
        }
    }


    public static class AirTime {
        @Json(name = "day")
        public final DayOfWeek day;
        @Json(name = "time")
        public final LocalTime time;
        @Json(name = "timezone")
        public final TimeZone timezone;

        @SuppressWarnings("unused")
        AirTime(DayOfWeek day, LocalTime time, TimeZone timezone) {
            this.day = day;
            this.time = time;
            this.timezone = timezone;
        }
    }


    static class Other {
        @Json(name = "title")
        public final String title;

        @Json(name = "en_title")
        public final String enTitle;

        @Json(name = "anime_type")
        public final String animeType;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "poster")
        public final String posterUrl;

        @Json(name = "ids")
        public final SimklId ids;

        Other(String title, String enTitle, String animeType, Integer year, String posterUrl, SimklId ids) {
            this.title = title;
            this.enTitle = enTitle;
            this.animeType = animeType;
            this.year = year;
            this.posterUrl = posterUrl;
            this.ids = ids;
        }
    }


    public static class Recommendation extends Other {

        @Json(name = "users_percent")
        public final String usersPercent;

        @Json(name = "users_count")
        public final Integer usersCount;

        @SuppressWarnings("unused")
        Recommendation(String title, String enTitle, String animeType, Integer year, String posterUrl, SimklId ids,
                       String usersPercent, Integer usersCount) {
            super(title, enTitle, animeType, year, posterUrl, ids);
            this.usersPercent = usersPercent;
            this.usersCount = usersCount;
        }
    }

    public static class Relation extends Other {

        @Json(name = "relation_type")
        public final String relationType;

        @Json(name = "is_direct")
        public final Boolean direct;

        @SuppressWarnings("unused")
        Relation(String title, String enTitle, String animeType, Integer year, String posterUrl, SimklId ids,
                 String relationType, Boolean direct) {
            super(title, enTitle, animeType, year, posterUrl, ids);
            this.relationType = relationType;
            this.direct = direct;
        }
    }

    public static class AltTitle {
        @Json(name = "name")
        public final String name;

        @Json(name = "lang")
        public final Integer lang;

        @Json(name = "type")
        public final String type;

        @SuppressWarnings("unused")
        AltTitle(String name, Integer lang, String type) {
            this.name = name;
            this.lang = lang;
            this.type = type;
        }
    }

}
