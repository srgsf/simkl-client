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

public class FileMetadata {
    public enum Type {
        episode, movie
    }

    @Json(name = "type")
    public final Type type;

    @Json(name = "movie")
    public final MediaObject movie;

    @Json(name = "show")
    public final MediaObject show;

    @Json(name = "episode")
    public final Episode episode;

    @SuppressWarnings("unused")
    FileMetadata(Type type, MediaObject movie, MediaObject show, Episode episode) {
        this.type = type;
        this.movie = movie;
        this.show = show;
        this.episode = episode;
    }

    public static class Episode {
        @Json(name = "title")
        public final String title;

        @Json(name = "season")
        public final Integer season;

        @Json(name = "episode")
        public final Integer episode;

        @Json(name = "multipart")
        public final Boolean multipart;

        @Json(name = "ids")
        public final SimklId ids;

        @SuppressWarnings("unused")
        Episode(String title, Integer season, Integer episode, Boolean multipart, Ids ids) {
            this.title = title;
            this.season = season;
            this.episode = episode;
            this.multipart = multipart;
            this.ids = ids;
        }
    }

    public static class MediaObject {
        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "ids")
        public final Ids ids;

        @SuppressWarnings("unused")
        MediaObject(String title, Integer year, Ids ids) {
            this.title = title;
            this.year = year;
            this.ids = ids;
        }
    }
}
