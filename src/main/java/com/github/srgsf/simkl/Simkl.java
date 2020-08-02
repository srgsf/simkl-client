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

import com.github.srgsf.simkl.service.*;
import com.squareup.moshi.Moshi;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.util.function.Supplier;

public class Simkl {
    /**
     * Default API url.
     */
    public static final String API_URL = "https://api.simkl.com/";

    private final Retrofit retrofit;
    private final OkHttpClient httpClient;
    private final Supplier<String> accessTokenProvider;
    private final HttpUrl baseUrl;
    private final String clientId;
    private ApiImpl apiImpl;

    /**
     * Builder is used to configure and create instances of {@link Simkl}.
     * To reconfigure call {@link Simkl#newBuilder()}
     *
     * @author srgsf
     * @since 0.1
     */
    public static class Builder {
        private String url;
        private OkHttpClient client;
        private Retrofit retrofit;
        private Supplier<String> accessTokenProvider;
        private String clientId;

        public Builder() {

        }

        Builder(Simkl old) {
            url = old.baseUrl.toString();
            client = old.httpClient;
            retrofit = old.retrofit;
            accessTokenProvider = old.accessTokenProvider;
            clientId = old.clientId;
        }

        /**
         * Use custom url for Api.
         * {@link Simkl#API_URL} is used by default.
         *
         * @param url custom url
         * @return {@link Builder}
         */
        public Builder baseUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets shared retrofit instance. Use this to save resources and improve performance.
         *
         * @param retrofit shared retrofit instance.
         * @return {@link Builder}
         */
        public Builder retrofit(Retrofit retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        /**
         * Sets shared okHttp client instance. Use this to save resources and improve performance.
         *
         * @param client shared okHttp client instance
         * @return {@link Builder}
         */
        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }


        /**
         * Sets OAuth 2.0 authorization token provider.
         *
         * @param provider access token provider
         * @return {@link Builder}
         */
        public Builder tokenProvider(Supplier<String> provider) {
            this.accessTokenProvider = provider;
            return this;
        }

        /**
         * Sets OAuth 2.0 client id.
         *
         * @param clientId The client ID you received from Simkl when you registered.
         * @return {@link Builder}
         */
        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        /**
         * Builds new instance of {@link Simkl}.
         *
         * @return new instance of {@link Simkl}
         */
        public Simkl build() {
            if (accessTokenProvider == null) {
                throw new IllegalStateException("access token provider must be set");
            }
            if (clientId == null) {
                throw new IllegalStateException("client id must be set");
            }
            final HttpUrl baseUrl = HttpUrl.get(url == null ? API_URL : url);
            OkHttpClient.Builder clientBuilder = client == null ? new OkHttpClient.Builder() : client.newBuilder();

            clientBuilder.interceptors().removeIf(i -> i instanceof ApiInterceptor);
            clientBuilder.addInterceptor(new ApiInterceptor(clientId, accessTokenProvider));

            Retrofit.Builder retrofitBuilder = retrofit == null ? new Retrofit.Builder() :
                    retrofit.newBuilder();
            retrofitBuilder.baseUrl(baseUrl);
            retrofitBuilder.client(clientBuilder.build());

            retrofitBuilder.converterFactories().removeIf(c -> c instanceof MoshiConverterFactory);
            Moshi moshi = new Moshi.Builder()
                    .add(new JsonAdapters.DateTimeAdapter())
                    .add(new JsonAdapters.TimeZoneAdapter())
                    .add(new JsonAdapters.WatchStatusAdapter())
                    .add(new JsonAdapters.DateAdapter())
                    .add(new JsonAdapters.TimeAdapter())
                    .add(new JsonAdapters.DayOfWeekAdapter())
                    .build();
            retrofitBuilder.addConverterFactory(MoshiConverterFactory.create(moshi));
            return new Simkl(baseUrl, client, retrofitBuilder.build(), accessTokenProvider, clientId);
        }
    }

    private Simkl(HttpUrl baseUrl,
                  OkHttpClient httpClient,
                  Retrofit retrofit,
                  Supplier<String> accessTokenProvider,
                  String clientId) {
        this.baseUrl = baseUrl;
        this.retrofit = retrofit;
        this.accessTokenProvider = accessTokenProvider;
        this.httpClient = httpClient;
        this.clientId = clientId;
    }

    /**
     * API base url that is configured for this instance.
     *
     * @return API base url.
     */
    public HttpUrl getBaseUrl() {
        return baseUrl;
    }

    /**
     * Returns copy of {@link Builder} that was used to create current instance.
     * Use this to reconfigure {@link Simkl}.
     *
     * @return {@link Builder}.
     */
    public Builder newBuilder() {
        return new Builder(this);
    }

    /**
     * Creates search Api
     *
     * @return {@link Search}
     */
    public Search search() {
        return apiImpl();
    }

    /**
     * Creates Shows Api
     *
     * @return {@link Shows}
     */
    public Shows shows() {
        return apiImpl();
    }


    /**
     * Creates Users Api.
     *
     * @return {@link Users} api implementation.
     */
    public Users users() {
        return apiImpl();
    }

    /**
     * Creates Movies Api
     *
     * @return {@link Movies}
     */
    public Movies movies() {
        return apiImpl();
    }

    /**
     * Creates Sync Api
     *
     * @return {@link Sync}
     */
    public Sync sync() {
        return apiImpl();
    }

    private ApiImpl apiImpl() {
        synchronized (this) {
            if (apiImpl == null) {
                apiImpl = new ApiImpl(retrofit.create(SimklApi.class));
            }
        }
        return apiImpl;
    }
}
