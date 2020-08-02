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

public class FilteredAnime {

    @Json(name = "title")
    public final String title;

    @Json(name = "year")
    public final Integer year;

    @Json(name = "date")
    public final OffsetDateTime date;

    @Json(name = "poster")
    public final String posterUrl;

    @Json(name = "fanart")
    public final String fanartUrl;

    @Json(name = "url")
    public final String url;

    @Json(name = "anime_type")
    public final String animeType;

    @Json(name = "rank")
    public final Integer rank;

    @Json(name = "ratings")
    public final Ratings ratings;

    @Json(name = "ids")
    public final SimklId ids;

    @SuppressWarnings("unused")
    FilteredAnime(String title, Integer year, OffsetDateTime date, String posterUrl, String fanartUrl, String url,
                  String animeType, Integer rank, Ratings ratings, SimklId ids) {
        this.title = title;
        this.year = year;
        this.date = date;
        this.posterUrl = posterUrl;
        this.fanartUrl = fanartUrl;
        this.url = url;
        this.animeType = animeType;
        this.rank = rank;
        this.ratings = ratings;
        this.ids = ids;
    }
}
