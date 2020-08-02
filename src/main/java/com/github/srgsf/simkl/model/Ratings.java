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

public class Ratings {

    @Json(name = "imdb")
    public final Rating imdb;

    @Json(name = "simkl")
    public final Rating simkl;

    @Json(name = "mal")
    public final Rating mal;

    @SuppressWarnings("unused")
    Ratings(Rating imdb, Rating simkl, Rating mal) {
        this.imdb = imdb;
        this.simkl = simkl;
        this.mal = mal;
    }

    public static class Rating {
        @Json(name = "rating")
        public final Double rating;

        @Json(name = "votes")
        public final Long votes;

        @Json(name = "rank")
        public final Integer rank;


        @SuppressWarnings("unused")
        Rating(Double rating, Long votes, Integer rank) {
            this.rating = rating;
            this.votes = votes;
            this.rank = rank;
        }
    }
}
