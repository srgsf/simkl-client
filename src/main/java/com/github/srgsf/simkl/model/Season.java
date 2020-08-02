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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Season {

    @Json(name = "number")
    public final int number;

    @Json(name = "watched_at")
    public OffsetDateTime watchedAt;

    @Json(name = "episodes")
    public final List<Number> episodes = new ArrayList<>();

    public Season(int number) {
        this.number = number;
    }

    public Season(int number, Collection<Number> episodes) {
        this.number = number;
        this.episodes.addAll(episodes);
    }

    public Season watchedAt(OffsetDateTime watchedAt) {
        this.watchedAt = watchedAt;
        return this;
    }

    public Season addEpisodes(int... episodes) {
        for (int i : episodes) {
            this.episodes.add(new Number(i));
        }
        return this;
    }


    public Season addEpisodes(Collection<Integer> episodes) {
        for (Integer i : episodes) {
            this.episodes.add(new Number(i));
        }
        return this;
    }

    public static class Number {
        @Json(name = "number")
        public final int number;

        public Number(int number) {
            this.number = number;
        }
    }
}
