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

import java.util.ArrayList;
import java.util.List;

public class WatchListSyncStatus {

    @Json(name = "added")
    public final SyncList added;

    @Json(name = "not_found")
    public final SyncList notFound;


    @SuppressWarnings("unused")
    WatchListSyncStatus(SyncList added, SyncList notFound) {
        this.added = added;
        this.notFound = notFound;
    }

    public static class SyncList {
        @Json(name = "movies")
        public final List<WatchListMediaObject> movies = new ArrayList<>();

        @Json(name = "shows")
        public final List<WatchListMediaObject> shows = new ArrayList<>();
    }

    public static class WatchListMediaObject {
        @Json(name = "to")
        public final WatchList.Name list;
        @Json(name = "title")
        public final String title;

        @Json(name = "year")
        public final Integer year;

        @Json(name = "ids")
        public final Ids ids;

        @SuppressWarnings("unused")
        WatchListMediaObject(WatchList.Name list, String title, Integer year, Ids ids) {
            this.list = list;
            this.title = title;
            this.year = year;
            this.ids = ids;
        }
    }
}
