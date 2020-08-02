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

import com.github.srgsf.simkl.model.LastWatchedArt;
import com.github.srgsf.simkl.model.UserInfo;
import com.github.srgsf.simkl.model.UserStats;
import retrofit2.Call;

/**
 * User's data API
 */
public interface Users {

    /**
     * Get images for user's last watched TV Show or Movie. Useful for example to create an app that will
     * update user's wallpaper on computer or phone
     *
     * @param userId public user id
     * @return {@link LastWatchedArt}
     */
    Call<LastWatchedArt> lastWatchedArt(int userId);

    /**
     * Retrieves user's settings.
     *
     * @return user settings.
     */
    Call<UserInfo> info();

    /**
     * Get all info about user's watched movies, tv shows, anime and episodes.
     *
     * @param userId public user id
     * @return {@link UserStats}
     */
    Call<UserStats> statistics(int userId);
}
