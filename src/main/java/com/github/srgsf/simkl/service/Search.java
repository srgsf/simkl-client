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

import com.github.srgsf.simkl.model.FileMetadata;
import com.github.srgsf.simkl.model.MediaItemLink;
import com.github.srgsf.simkl.model.Requests;
import com.github.srgsf.simkl.model.StandardMediaObject;
import retrofit2.Call;

import java.util.List;

/**
 * SIMKL Api related to generic search and lookup.
 */
public interface Search {

    /**
     * Get items by Id
     *
     * @param query params
     * @return movie, show or anime.
     */
    Call<List<StandardMediaObject>> getById(Requests.FindById query);

    /**
     * Use it if you have a filename and want to get simkl_id based on it.
     *
     * @param file filename.
     * @return found movie or tv/anime episode
     */
    Call<FileMetadata> findByFile(Requests.FindByFile file);

    /**
     * Use it if you want to find random item based on your filters.
     *
     * @param param filters
     * @return random TV Show, Anime or Movie link.
     */
    Call<MediaItemLink> random(Requests.Random param);
}
