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

public class FoundMovie {

    public static class Summary {

        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "poster")
        public final String posterUrl;

        @Json(name = "ids")
        public final SimklId ids;

        @SuppressWarnings("unused")
        Summary(String title, Integer year, String posterUrl, SimklId ids) {
            this.title = title;
            this.year = year;
            this.posterUrl = posterUrl;
            this.ids = ids;
        }
    }

    public static class Detail extends Summary {

        @Json(name = "rank")
        public final Integer rank;

        @Json(name = "url")
        public final String url;

        @Json(name = "ratings")
        public final Ratings ratings;

        @Json(name = "all_titles")
        public final List<String> allTitles = new ArrayList<>();


        @SuppressWarnings("unused")
        public Detail(String title, Integer year, String posterUrl, SimklId ids, Integer rank, String url, Ratings ratings) {
            super(title, year, posterUrl, ids);
            this.rank = rank;
            this.url = url;
            this.ratings = ratings;
        }
    }
}
