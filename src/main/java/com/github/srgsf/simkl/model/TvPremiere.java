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

public class TvPremiere {

    public enum Param {
        newOne("new"),
        soon("soon");

        public final String value;

        Param(String value) {
            this.value = value;
        }
    }

    public enum Type {
        all,
        entertainment,
        documentaries,
        animation("animation-filter");

        public final String value;

        Type(String value) {
            this.value = value;
        }

        Type() {
            value = name();
        }
    }

    @Json(name = "title")
    public final String title;

    @Json(name = "year")
    public final Integer year;

    @Json(name = "date")
    public final OffsetDateTime premiereDate;

    @Json(name = "poster")
    public final String posterUrl;

    @Json(name = "url")
    public final String url;

    @Json(name = "rank")
    public final Integer rank;

    @Json(name = "ratings")
    public final Ratings ratings;

    @Json(name = "ids")
    public final SimklId ids;

    @SuppressWarnings("unused")
    TvPremiere(String title, Integer year, OffsetDateTime premiereDate, String posterUrl, String url, Integer rank,
               Ratings ratings, SimklId ids) {
        this.title = title;
        this.year = year;
        this.premiereDate = premiereDate;
        this.posterUrl = posterUrl;
        this.url = url;
        this.rank = rank;
        this.ratings = ratings;
        this.ids = ids;
    }
}
