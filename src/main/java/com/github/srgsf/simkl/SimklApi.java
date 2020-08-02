/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.simkl;

import com.github.srgsf.simkl.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.OffsetDateTime;
import java.util.List;

interface SimklApi {

    @GET("search/id")
    @Api
    Call<List<StandardMediaObject>> getById(@Query("simkl") Long simklId,
                                            @Query("hulu") Long huluId,
                                            @Query("netflix") Long netflixId,
                                            @Query("mal") Long malId,
                                            @Query("tvdb") Long tvdbId,
                                            @Query("tmdb") Long tmdbId,
                                            @Query("imdb") String imdbId,
                                            @Query("anidb") Long anidbId,
                                            @Query("crunchyroll") Long crunchyRollId,
                                            @Query("type") Filters.TmdbType type,
                                            @Query("title") String title,
                                            @Query("year") String year,
                                            @Query("extended") Filters.ExtendedLevel level);


    @POST("search/file")
    @Api
    Call<FileMetadata> findByFile(@Body Requests.FindByFile file);

    @GET("search/random")
    @Api
    Call<MediaItemLink> randomAll(@Query("service") Filters.Service service,
                                  @Query("type") String type,
                                  @Query("genre") String genre,
                                  @Query("rating_from") Integer ratingFrom,
                                  @Query("rating_to") Integer ratingTo,
                                  @Query("year_from") Integer yearFrom,
                                  @Query("year_to") Integer yearTo,
                                  @Query("limit") Integer limit);

    @GET("search/random")
    @Api(secured = true)
    Call<MediaItemLink> random(@Query("service") Filters.Service service,
                               @Query("type") String type,
                               @Query("genre") String genre,
                               @Query("rating_from") Integer ratingFrom,
                               @Query("rating_to") Integer ratingTo,
                               @Query("year_from") Integer yearFrom,
                               @Query("year_to") Integer yearTo,
                               @Query("limit") Integer limit);


    @GET("{type}/{id}")
    @Api
    Call<Show.Summary> summary(@Path("type") Filters.SeriesType type, @Path("id") String id);

    @GET("{type}/{id}")
    @Api
    Call<Show.Detail> summary(@Path("type") Filters.SeriesType type, @Path("id") String id, @Query("extended") Filters.ExtendedLevel level);

    @GET("movies/{id}")
    @Api
    Call<Movie.Summary> summary(@Path("id") String id);

    @GET("movies/{id}")
    @Api
    Call<Movie.Detail> summary(@Path("id") String id, @Query("extended") Filters.ExtendedLevel level);


    @GET("{type}/episodes/{id}")
    @Api
    Call<List<Episode.Detail>> episodes(@Path("type") Filters.SeriesType type, @Path("id") Long id, @Query("extended") Filters.ExtendedLevel level);

    @GET("{type}/episodes/{id}")
    @Api
    Call<List<Episode.Summary>> episodes(@Path("type") Filters.SeriesType type, @Path("id") Long id);


    @GET("anime/premieres/{param}")
    @Api
    Call<List<AnimePremiere>> premieres(@Path("param") String param,
                                        @Query("type") AnimePremiere.Type type,
                                        @Query("page") Integer page,
                                        @Query("limit") Integer limit);

    @GET("tv/premieres/{param}")
    @Api
    Call<List<TvPremiere>> premieres(@Path("param") String param,
                                     @Query("type") String type,
                                     @Query("page") Integer page,
                                     @Query("limit") Integer limit);


    @GET("anime/best/{filter}")
    @Api
    Call<List<BestAnime>> best(@Path("filter") BestAnime.Filter filter,
                               @Query("type") BestAnime.Type type);

    @GET("tv/best/{filter}")
    @Api
    Call<List<BestShow>> best(@Path("filter") BestShow.Filter filter,
                              @Query("type") BestShow.Type type);


    @GET("anime/genres/{genre}/{type}/{network}/{year}/{sort}")
    @Api
    Call<List<FilteredAnime>> filter(@Path("genre") String genre,
                                     @Path("type") Filters.Anime.Type type,
                                     @Path("network") String network,
                                     @Path("year") String year,
                                     @Path("sort") String sort,
                                     @Query("page") Integer page,
                                     @Query("limit") Integer limit);

    @GET("tv/genres/{genre}/{type}/{country}/{network}/{year}/{sort}")
    @Api
    Call<List<FilteredShow>> filter(@Path("genre") String genre,
                                    @Path("type") String type,
                                    @Path("country") String country,
                                    @Path("network") String network,
                                    @Path("year") String year,
                                    @Path("sort") String sort,
                                    @Query("page") Integer page,
                                    @Query("limit") Integer limit);


    @GET("search/{type}")
    @Api
    Call<List<FoundShow.Summary>> findShows(@Path("type") Filters.SeriesType type,
                                            @Query("q") String query,
                                            @Query("page") Integer page,
                                            @Query("limit") Integer limit);


    @GET("search/{type}")
    @Api
    Call<List<FoundShow.Detail>> findShows(@Path("type") Filters.SeriesType type,
                                           @Query("q") String query,
                                           @Query("extended") Filters.ExtendedLevel level,
                                           @Query("page") Integer page,
                                           @Query("limit") Integer limit);

    @GET("search/movie")
    @Api
    Call<List<FoundMovie.Summary>> findMovies(@Query("q") String query,
                                              @Query("page") Integer page,
                                              @Query("limit") Integer limit);

    @GET("search/movie")
    @Api
    Call<List<FoundMovie.Detail>> findMovies(@Query("q") String query,
                                             @Query("extended") Filters.ExtendedLevel level,
                                             @Query("page") Integer page,
                                             @Query("limit") Integer limit);


    @GET("users/{user_id}/stats")
    @Api(secured = true)
    Call<UserStats> stats(@Path("user_id") int userId);

    @GET("users/settings")
    @Api(secured = true)
    Call<UserInfo> user();

    @GET("users/recently-watched-background/{user_id}")
    @Api
    Call<LastWatchedArt> lastWatchedArt(@Path("user_id") int userId);

    @GET("sync/activities")
    @Api(secured = true)
    Call<LastActivities> lastActivities();

    @GET("sync/all-items/{type}/{status}")
    @Api(secured = true)
    Call<WatchList> getAll(@Path("type") Requests.WatchList.Type type, @Path("status") WatchList.Name status,
                           @Query("extended") Filters.ExtendedLevel level,
                           @Query("episode_watched_at") String episodeDates,
                           @Query("next_watch_info") String nextWatchInfo,
                           @Query("date_from") OffsetDateTime dt);

    @GET("sync/all-items/{type}")
    @Api(secured = true)
    Call<WatchList> getAll(@Path("type") Requests.WatchList.Type type,
                           @Query("extended") Filters.ExtendedLevel level,
                           @Query("episode_watched_at") String episodeDates,
                           @Query("next_watch_info") String nextWatchInfo,
                           @Query("date_from") OffsetDateTime dt);

    @GET("sync/all-items/{status}")
    @Api(secured = true)
    Call<WatchList> getAll(@Path("status") WatchList.Name status,
                           @Query("extended") Filters.ExtendedLevel level,
                           @Query("episode_watched_at") String episodeDates,
                           @Query("next_watch_info") String nextWatchInfo,
                           @Query("date_from") OffsetDateTime dt);

    @GET("sync/all-items")
    @Api(secured = true)
    Call<WatchList> getAll(@Query("extended") Filters.ExtendedLevel level,
                           @Query("episode_watched_at") String episodeDates,
                           @Query("next_watch_info") String nextWatchInfo,
                           @Query("date_from") OffsetDateTime dt);


    @GET("sync/ratings/{type}")
    @Api(secured = true)
    Call<WatchList> getUserRatings(@Path("type") Requests.WatchList.Type type,
                                   @Query("date_from") OffsetDateTime after,
                                   @Query("extended") Filters.ExtendedLevel level);

    @GET("sync/ratings/{type}/{rating}")
    @Api(secured = true)
    Call<WatchList> getUserRatings(@Path("type") Requests.WatchList.Type type,
                                   @Path("rating") Integer rating,
                                   @Query("date_from") OffsetDateTime after,
                                   @Query("extended") Filters.ExtendedLevel level);

    @GET("sync/ratings/{rating}")
    @Api(secured = true)
    Call<WatchList> getUserRatings(@Path("rating") Integer rating,
                                   @Query("date_from") OffsetDateTime after,
                                   @Query("extended") Filters.ExtendedLevel level);

    @GET("sync/ratings")
    @Api(secured = true)
    Call<WatchList> getUserRatings(@Query("date_from") OffsetDateTime after,
                                   @Query("extended") Filters.ExtendedLevel level);

    @POST("checkin")
    @Api(secured = true)
    Call<Checkin> checkin(@Body Checkin checkin);

    @POST("sync/collection/remove")
    @Api(secured = true)
    Call<SyncStatus> removeFromCollection(@Body Requests.SyncListExt list);

    @POST("sync/collection")
    @Api(secured = true)
    Call<SyncStatus> addToCollection(@Body Requests.SyncListExt list);

    @POST("sync/add-to-list")
    @Api(secured = true)
    Call<WatchListSyncStatus> addToWatchList(@Body Requests.SyncList list);

    @POST("sync/ratings/remove")
    @Api(secured = true)
    Call<SyncStatus> removeRatings(@Body Requests.SyncList list);

    @POST("sync/ratings")
    @Api(secured = true)
    Call<SyncStatus> addRatings(@Body Requests.SyncList list);

    @POST("sync/history/remove")
    @Api(secured = true)
    Call<SyncStatus> removeFromHistory(@Body Requests.SyncListExt list);

    @POST("sync/history")
    @Api(secured = true)
    Call<SyncStatus> addToHistory(@Body Requests.SyncListExt list);

    @POST("sync/watched")
    @Api(secured = true)
    Call<List<WatchStatus>> isWatching(@Body List<Requests.WatchStatus> list);
}
