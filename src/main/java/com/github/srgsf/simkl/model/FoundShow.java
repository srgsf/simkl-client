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

public class FoundShow {

    public static class Summary {

        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "type")
        public final String animeType;

        @Json(name = "poster")
        public final String posterUrl;

        @Json(name = "ids")
        public final SimklId ids;

        @SuppressWarnings("unused")
        Summary(String title, Integer year, String animeType, String posterUrl, SimklId ids) {
            this.title = title;
            this.year = year;
            this.animeType = animeType;
            this.posterUrl = posterUrl;
            this.ids = ids;
        }
    }


    public static class Detail extends Summary {

        @Json(name = "url")
        public final String url;

        @Json(name = "ep_count")
        public final Integer episodesNum;

        @Json(name = "rank")
        public final Integer rank;

        @Json(name = "status")
        public final String status;

        @Json(name = "ratings")
        public final Ratings ratings;

        @Json(name = "all_titles")
        public final List<String> allTitles = new ArrayList<>();

        @SuppressWarnings("unused")
        Detail(String title, Integer year, String animeType, String posterUrl, SimklId ids, String url,
               Integer episodesNum, Integer rank, String status, Ratings ratings) {
            super(title, year, animeType, posterUrl, ids);
            this.url = url;
            this.episodesNum = episodesNum;
            this.rank = rank;
            this.status = status;
            this.ratings = ratings;
        }
    }
}
