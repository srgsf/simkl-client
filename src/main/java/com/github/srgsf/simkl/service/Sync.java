/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.simkl.service;

import com.github.srgsf.simkl.model.*;
import retrofit2.Call;

import java.util.List;

public interface Sync {

    /**
     * When syncing it's best to check user's last activity first. Then you'll be able to sync only the watchlists
     * that have changed since the last sync.
     *
     * @return last activities
     */
    Call<LastActivities> lastActivities();

    /**
     * Returns all items that user has in his watchlist
     *
     * @param watch filter
     * @return all items that user has in his watchlist
     */

    Call<WatchList> getAllItems(Requests.WatchList watch);

    /**
     * Add items to the user's watched history.
     *
     * @param list objects to add.
     * @return operation status
     */
    Call<SyncStatus> addToHistory(Requests.SyncListExt list);

    /**
     * Remove the items from the user's watched history.
     *
     * @param list objects to add.
     * @return operation status
     */
    Call<SyncStatus> removeFromHistory(Requests.SyncListExt list);

    /**
     * This should be tied to a user manual action.
     * The item will be as watching on the site and if the time has elapsed it will switch to completed.
     *
     * @param checkin episode or movie.
     * @return result or http 409 if checkin is in progress.
     */
    Call<Checkin> checkin(Checkin checkin);

    /**
     * Returns all rated items in user's lists.
     *
     * @return all rated items in user's lists
     */
    Call<WatchList> getUserRatings();

    /**
     * Returns all rated items in user's lists filtered.
     *
     * @param param filters
     * @return rated items in user's lists
     */
    Call<WatchList> getUserRatings(Requests.Rating param);

    /**
     * Rate the movies or tv shows/anime.
     *
     * @param list movies or tv shows/anime
     * @return operation status
     */
    Call<SyncStatus> addRatings(Requests.SyncList list);

    /**
     * Any movies or tv shows passed here will be unrated
     *
     * @param list movies or tv shows/anime
     * @return operation status
     */
    Call<SyncStatus> removeRatings(Requests.SyncList list);

    /**
     * Add items to a user's Simkl lists.
     *
     * @param list movies and/or shows
     * @return operation status
     */
    Call<WatchListSyncStatus> addToWatchList(Requests.SyncList list);

    /**
     * If you want to add something to a user's collection use this method. Accepts shows, season and movies.
     * If seasons skipped then all seasons for the show will be collected otherwise only specified seasons.
     * Items collected in the past should send collected_at UTC datetime.
     * If you send item second time then it will be updated with new values.
     *
     * @param list movies and/or shows
     * @return operation status
     */
    Call<SyncStatus> addToCollection(Requests.SyncListExt list);

    /**
     * Using this method you can remove one or more items from a user's collection.
     *
     * @param list movies and/or shows
     * @return operation status
     */
    Call<SyncStatus> removeFromCollection(Requests.SyncListExt list);

    /**
     * Use this method if you want to see which shows or episodes user has seen.
     * You can pass different ids and get their appropriate statuses in a user's list.
     *
     * @param list filter
     * @return status
     */
    Call<List<WatchStatus>> isWatching(List<Requests.WatchStatus> list);

}
