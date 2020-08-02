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

public class StandardMediaObject {

    public enum Type {
        movie, anime, tv
    }

    @Json(name = "type")
    public final Type type;

    @Json(name = "anime_type")
    public final String animeType;

    @Json(name = "title")
    public final String title;

    @Json(name = "year")
    public final Integer year;

    @Json(name = "poster")
    public final String posterPath;

    @Json(name = "ids")
    public final SimklId ids;

    @SuppressWarnings("unused")
    StandardMediaObject(Type type, String animeType, String title, Integer year, String posterPath, Ids ids) {
        this.type = type;
        this.animeType = animeType;
        this.title = title;
        this.year = year;
        this.posterPath = posterPath;
        this.ids = ids;
    }
}
