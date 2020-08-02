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

public class Checkin {

    @Json(name = "movie")
    public final Movie movie;

    @Json(name = "show")
    public final Episode show;

    public Checkin(Movie movie) {
        this.movie = movie;
        show = null;
    }

    public Checkin(Episode episode) {
        this.show = episode;
        movie = null;
    }

    public static class Movie extends Requests.StandardMediaObject<Movie> {
        public Movie(Requests.Ids ids) {
            super(ids);
        }
    }

    public static class Episode extends Requests.StandardMediaObject<Episode> {
        @Json(name = "episode")
        public final RefId episode;

        public Episode(Requests.Ids showIds, Integer season, Integer number) {
            super(showIds);
            episode = new RefId(season, number);
        }
    }

    public static class RefId {
        @Json(name = "season")
        public final Integer season;
        @Json(name = "number")
        public final Integer episode;

        public RefId(Integer season, Integer episode) {
            this.season = season;
            this.episode = episode;
        }
    }
}
