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

/**
 * SIMKL Api related to tv shows and anime.
 */
public interface Shows {

    /**
     * Get detail about show.
     *
     * @param type type of series.
     * @param imdb imdb id
     * @return a single detail info of the tv show or anime.
     */
    Call<Show.Detail> summaryFull(Filters.SeriesType type, String imdb);

    /**
     * Get summary about show.
     *
     * @param type type of series.
     * @param imdb imdb id
     * @return a single summary info of the tv show or anime.
     */
    Call<Show.Summary> summary(Filters.SeriesType type, String imdb);

    /**
     * Get detail about show.
     *
     * @param type  type of series.
     * @param simkl simkl id
     * @return a single detail info of the tv show or anime.
     */
    Call<Show.Detail> summaryFull(Filters.SeriesType type, long simkl);

    /**
     * Get summary about show.
     *
     * @param type  type of series.
     * @param simkl simkl id
     * @return a single summary info of the tv show or anime.
     */
    Call<Show.Summary> summary(Filters.SeriesType type, long simkl);

    /**
     * Get Tv Show episodes
     *
     * @param type  type of series.
     * @param simkl simkl id
     * @return an array of all episode summaries of the TV Show or anime
     */
    Call<List<Episode.Summary>> episodes(Filters.SeriesType type, long simkl);

    /**
     * Get Tv Show episodes
     *
     * @param type  type of series.
     * @param simkl simkl id
     * @return an array of all episode details of the TV Show or anime
     */
    Call<List<Episode.Detail>> episodesFull(Filters.SeriesType type, long simkl);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @param type  type
     * @return an array of latest premieres.
     */
    Call<List<AnimePremiere>> premieres(AnimePremiere.Param param, AnimePremiere.Type type);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @param type  type
     * @param page  page number to load
     * @param limit items on a page
     * @return an array of latest premieres.
     */
    Call<List<AnimePremiere>> premieres(AnimePremiere.Param param, AnimePremiere.Type type, Integer page, Integer limit);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @return an array of latest premieres.
     */
    Call<List<AnimePremiere>> premieres(AnimePremiere.Param param);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @param page  page number to load
     * @param limit items on a page
     * @return an array of latest premieres.
     */
    Call<List<AnimePremiere>> premieres(AnimePremiere.Param param, Integer page, Integer limit);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @param type  type
     * @return an array of latest premieres.
     */
    Call<List<TvPremiere>> premieres(TvPremiere.Param param, TvPremiere.Type type);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @param type  type
     * @param page  page number to load
     * @param limit items on a page
     * @return an array of latest premieres.
     */
    Call<List<TvPremiere>> premieres(TvPremiere.Param param, TvPremiere.Type type, Integer page, Integer limit);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @return an array of latest premieres.
     */
    Call<List<TvPremiere>> premieres(TvPremiere.Param param);

    /**
     * Get latest premieres.
     *
     * @param param filter
     * @param page  page number to load
     * @param limit items on a page
     * @return an array of latest premieres.
     */
    Call<List<TvPremiere>> premieres(TvPremiere.Param param, Integer page, Integer limit);

    /**
     * Get Best of all TV Shows.
     *
     * @param filter filter
     * @param type   type
     * @return an array of the best tv shows.
     */
    Call<List<BestShow>> best(BestShow.Filter filter, BestShow.Type type);

    /**
     * Get Best of all TV Shows.
     *
     * @param filter filter
     * @return an array of the best tv shows.
     */
    Call<List<BestShow>> best(BestShow.Filter filter);

    /**
     * Get Best of all Anime.
     *
     * @param filter filter
     * @param type   type
     * @return an array of the best anime.
     */
    Call<List<BestAnime>> best(BestAnime.Filter filter, BestAnime.Type type);

    /**
     * Get Best of all Anime.
     *
     * @param filter filter
     * @return an array of the best anime.
     */
    Call<List<BestAnime>> best(BestAnime.Filter filter);

    /**
     * Get Shows filtered by genre, years, etc...
     *
     * @param filter search filter
     * @return an array of all found TV Shows
     */
    Call<List<FilteredShow>> filter(Requests.FilterShow filter);

    /**
     * Get Anime filtered by genre, years, etc...
     *
     * @param filter search filter
     * @return an array of all found Anime
     */
    Call<List<FilteredAnime>> filter(Requests.FilterAnime filter);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param type  tv show or anime
     * @param query title
     * @return array of matched summaries
     */
    Call<List<FoundShow.Summary>> find(Filters.SeriesType type, String query);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param type  tv show or anime
     * @param query title
     * @return array of matched details
     */
    Call<List<FoundShow.Detail>> findFull(Filters.SeriesType type, String query);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param type  tv show or anime
     * @param query title
     * @param page  page number to load
     * @param limit items on a page
     * @return array of matched details
     */
    Call<List<FoundShow.Summary>> find(Filters.SeriesType type, String query, Integer page, Integer limit);

    /**
     * Search items by title, sorted by relevance (what people search most).
     *
     * @param type  tv show or anime
     * @param query title
     * @param page  page number to load
     * @param limit items on a page
     * @return array of matched summaries
     */
    Call<List<FoundShow.Detail>> findFull(Filters.SeriesType type, String query, Integer page, Integer limit);

}
