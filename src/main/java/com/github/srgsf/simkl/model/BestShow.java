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

public class BestShow {

    public enum Filter {
        year, month, all, voted, watched
    }

    public enum Type {
        series, documentary, entertainment, animation
    }

    @Json(name = "title")
    public final String title;

    @Json(name = "year")
    public final Integer year;

    @Json(name = "poster")
    public final String posterUrl;

    @Json(name = "url")
    public final String url;

    @Json(name = "watched")
    public final Integer watched;

    @Json(name = "votes")
    public final Integer voted;

    @Json(name = "ratings")
    public final Ratings ratings;

    @Json(name = "ids")
    public final SimklId ids;

    @SuppressWarnings("unused")
    BestShow(String title, Integer year, String posterUrl, String url, Integer watched, Integer voted,
             Ratings ratings, SimklId ids) {
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
        this.url = url;
        this.watched = watched;
        this.voted = voted;
        this.ratings = ratings;
        this.ids = ids;
    }
}
