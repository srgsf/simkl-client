/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.simkl.auth;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * OAuth 2.0 authentication client. Supports web application and limited device flows.
 *
 * @author srgsf
 * @since 0.1
 */
public class SimklAuthClient {

    /**
     * Error message that is returned on token polling request.
     */
    public static final String ERR_AUTHORIZATION_PENDING = "Authorization pending";
    /**
     * Error message that is returned on token polling request.
     */
    public static final String ERR_SLOW_DOWN = "Slow down";

    /**
     * OAuth 2.0 grant type.
     */
    public enum GrantType {
        authorization_code, device_code
    }

    /**
     * <a href="https://simkl.com">simkl.com</a> OAuth 2.0 base url.
     */
    public static final String AUTH_URL = "https://api.simkl.com/oauth/";


    private Authentication authentication;
    private final Retrofit retrofit;
    private final OkHttpClient httpClient;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;

    private Converter<ResponseBody, ErrResponse> converter;

    /**
     * Base url that is used with current instance.
     */
    public final HttpUrl baseUrl;

    /**
     * Builder is used to configure and create instances of {@link SimklAuthClient}.
     * To reconfigure call {@link SimklAuthClient#newBuilder()}
     *
     * @author srgsf
     * @since 0.1
     */
    public static class Builder {
        private String url;
        private OkHttpClient client;
        private Retrofit retrofit;
        private String clientId;
        private String clientSecret;
        private String redirectUri;

        public Builder() {
        }

        Builder(SimklAuthClient client) {
            url = client.baseUrl.toString();
            this.client = client.httpClient;
            retrofit = client.retrofit;
            clientId = client.clientId;
            clientSecret = client.clientSecret;
        }

        public Builder baseUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder retrofit(Retrofit retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * @param clientId    The client ID you received from Simkl when you registered.
         * @param secret      The client secret you received from Simkl when you registered.
         * @param redirectUri The redirect uri for client ID registered in Simkl.
         * @return {@link Builder}
         */
        public Builder clientCredentials(String clientId, String secret, String redirectUri) {
            this.clientId = clientId;
            clientSecret = secret;
            this.redirectUri = redirectUri;
            return this;
        }

        /**
         * Creates {@link SimklAuthClient} instance.
         *
         * @return {@link SimklAuthClient}
         * @throws IllegalStateException if API client credentials are not provided.
         */
        public SimklAuthClient build() {
            final HttpUrl baseUrl = HttpUrl.get(url == null ? AUTH_URL : url);
            if (clientId == null || clientSecret == null) {
                throw new IllegalStateException("client credentials must be provided.");
            }
            Retrofit.Builder retrofitBuilder = retrofit == null ? new Retrofit.Builder() :
                    retrofit.newBuilder();
            retrofitBuilder.baseUrl(baseUrl);


            if (retrofitBuilder.converterFactories().stream().noneMatch(c -> c instanceof MoshiConverterFactory)) {
                retrofitBuilder.addConverterFactory(MoshiConverterFactory.create());
            }

            OkHttpClient.Builder clientBuilder = client == null ? new OkHttpClient.Builder() : client.newBuilder();

            if (clientBuilder.interceptors().stream().noneMatch(i -> i instanceof AuthInterceptor)) {
                clientBuilder.addInterceptor(new AuthInterceptor());
            }
            client = clientBuilder.build();
            retrofitBuilder.client(client);
            return new SimklAuthClient(baseUrl, clientId, clientSecret, redirectUri, client, retrofitBuilder.build());
        }
    }

    private SimklAuthClient(HttpUrl baseUrl,
                            String clientId,
                            String clientSecret,
                            String redirectUri,
                            OkHttpClient httpClient,
                            Retrofit retrofit) {
        this.httpClient = httpClient;
        this.retrofit = retrofit;
        this.baseUrl = baseUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    private Authentication authentication() {
        if (authentication == null) {
            authentication = retrofit.create(Authentication.class);
        }
        return authentication;
    }

    /**
     * Retrieves OAuth 2.0 access token.
     *
     * @param type type of authorization request.
     * @param code code received as a result of authorization request. (Either authorization code or user code)
     * @return OAuth 2.0 access token.
     */
    public Call<AccessToken> accessToken(GrantType type, String code) {
        switch (type) {
            case device_code:
                return authentication().token(code, clientId);
            case authorization_code:
                return authentication().token(
                        "authorization_code",
                        code,
                        clientId,
                        clientSecret,
                        redirectUri);
            default:
                throw new UnsupportedOperationException("Unknown grant type");
        }
    }

    /**
     * Requests new device code.
     *
     * @return device code for polling Simkl authorization server

     */
    public Call<DeviceCode> deviceCode() {
        return authentication().deviceCode(clientId, redirectUri);
    }

    /**
     * Helper method to parse error response that contains error message.
     *
     * @param errorBody error body.
     * @return parsed error.
     * @throws IOException in case of i/o failure.
     */
    public String error(ResponseBody errorBody) throws IOException {
        if (converter == null) {
            converter = retrofit.responseBodyConverter(ErrResponse.class, new Annotation[0]);
        }
        return Optional.ofNullable(converter.convert(errorBody)).map(r -> r.message == null ? r.error : r.message)
                .orElse(null);
    }

    /**
     * Use this if you need to reconfigure auth client.
     *
     * @return copy of {@link Builder} that was used to create current auth client instance.
     */
    public Builder newBuilder() {
        return new Builder(this);
    }

    /**
     * Helper method for authorization request generation.
     *
     * @param clientId    Api client id.
     * @param redirectUri Api redirect uri.
     * @param state       An unguessable random string. It is used to protect against cross-site request forgery attacks.
     * @return state request url.
     */
    public static HttpUrl authorizationRequestUrl(String clientId, String redirectUri, String state) {
        return HttpUrl.get("https://simkl.com/oauth/authorize").newBuilder()
                .addQueryParameter("response_type", "code")
                .addQueryParameter("client_id", clientId)
                .addQueryParameter("redirect_uri", redirectUri)
                .addQueryParameter("state", state).build();

    }
}