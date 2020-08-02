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

public class LastWatchedArt {
    @Json(name = "id")
    public final Integer id;

    @Json(name = "title")
    public final String title;

    @Json(name = "poster")
    public final String posterPath;

    @Json(name = "fanart")
    public final String fanartUrl;

    @SuppressWarnings("unused")
    LastWatchedArt(Integer id, String title, String posterPath, String fanartUrl) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.fanartUrl = fanartUrl;
    }
}
