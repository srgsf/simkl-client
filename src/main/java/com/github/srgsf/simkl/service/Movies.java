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

import com.github.srgsf.simkl.model.FoundMovie;
import com.github.srgsf.simkl.model.Movie;
import retrofit2.Call;

import java.util.List;

/**
 * SIMKL Api related to movie infos.
 */
public interface Movies {
    /**
     * Get detail info about the movie.
     *
     * @param imdb imdb id
     * @return a single full detail info about the movie
     */
    Call<Movie.Detail> summaryFull(String imdb);

    /**
     * Get detail info about the movie.
     *
     * @param imdb imdb id
     * @return a single summary detail info about the movie
     */
    Call<Movie.Summary> summary(String imdb);

    /**
     * Get detail info about the movie.
     *
     * @param simkl simkl id
     * @return a single full detail info about the movie
     */
    Call<Movie.Detail> summaryFull(long simkl);

    /**
     * Get detail info about the movie.
     *
     * @param simkl simkl id
     * @return a single summary detail info about the movie
     */
    Call<Movie.Summary> summary(long simkl);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param query title
     * @return movie details
     */
    Call<List<FoundMovie.Detail>> findFull(String query);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param query title
     * @return movie summaries
     */
    Call<List<FoundMovie.Summary>> find(String query);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param query title
     * @param page  page to load
     * @param limit items on a page
     * @return movie summaries
     */
    Call<List<FoundMovie.Summary>> find(String query, Integer page, Integer limit);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param query title
     * @param page  page to load
     * @param limit items on a page
     * @return movie details
     */
    Call<List<FoundMovie.Detail>> findFull(String query, Integer page, Integer limit);
}
