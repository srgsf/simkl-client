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

class EmbeddedIds<T extends EmbeddedIds<T>> {
    @Json(name = "hulu")
    public Long hulu;

    @Json(name = "netflix")
    public Long netflix;

    @Json(name = "mal")
    public Long mal;

    @Json(name = "tvdb")
    public Long tvdb;

    @Json(name = "tmdb")
    public Long tmdb;

    @Json(name = "imdb")
    public String imdb;

    @Json(name = "anidb")
    public Long anidb;

    @Json(name = "simkl")
    public Long simkl;

    @Json(name = "type")
    public Filters.TmdbType type;

    @SuppressWarnings("unchecked")
    public T hulu(Long hulu) {
        this.hulu = hulu;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T netflix(Long netflix) {
        this.netflix = netflix;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T mal(Long mal) {
        this.mal = mal;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T tvdb(Long tvdb) {
        this.tvdb = tvdb;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T tmdb(Long tmdb, Filters.TmdbType type) {
        this.tmdb = tmdb;
        this.type = type;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T imdb(String imdb) {
        this.imdb = imdb;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T anidb(Long anidb) {
        this.anidb = anidb;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T simkl(Long simkl) {
        this.simkl = simkl;
        return (T) this;
    }
}
