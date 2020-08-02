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

public class WatchStatus extends EmbeddedIds<WatchStatus> {

    public enum Result {
        yes("true"),
        no("false"),
        notFound("not_found");

        public final String value;

        Result(String value) {
            this.value = value;
        }
    }

    @Json(name = "result")
    public final Result result;

    @Json(name = "list")
    public final WatchList.Name list;

    @Json(name = "watched")
    public final OffsetDateTime watched;

    @SuppressWarnings("unused")
    WatchStatus(Filters.TmdbType type, Result result, WatchList.Name list, OffsetDateTime watched, Long hulu, Long netflix, Long mal,
                Long tvdb, Long tmdb, String imdb, Long anidb, Long simkl) {
        this.type = type;
        this.result = result;
        this.list = list;
        this.watched = watched;
    }
}
