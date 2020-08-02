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

public class Episode {
    public enum Type {
        episode, special
    }

    public static class Summary {

        @Json(name = "season")
        public final Integer season;

        @Json(name = "episode")
        public final Integer episode;

        @Json(name = "type")
        public final Type type;

        @Json(name = "aired")
        public final Boolean aired;

        @Json(name = "img")
        public final String posterUrl;

        @Json(name = "ids")
        public final SimklId ids;

        @SuppressWarnings("unused")
        Summary(Integer season, Integer episode, Type type, Boolean aired, String posterUrl, SimklId ids) {
            this.season = season;
            this.episode = episode;
            this.type = type;
            this.aired = aired;
            this.posterUrl = posterUrl;
            this.ids = ids;
        }
    }

    public static class Detail extends Summary {

        @Json(name = "description")
        public final String description;

        @Json(name = "date")
        public final OffsetDateTime date;


        @SuppressWarnings("unused")
        Detail(Integer season, Integer episode, Type type, Boolean aired, String posterUrl, SimklId ids, String description, OffsetDateTime date) {
            super(season, episode, type, aired, posterUrl, ids);
            this.description = description;
            this.date = date;
        }
    }
}
