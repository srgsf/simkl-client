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

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Invocation;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

class ApiInterceptor implements Interceptor {

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String API_KEY_HEADER = "simkl-api-key";

    private static final String AUTH_SCHEME = "Bearer";


    private final String apiKey;
    private final Supplier<String> tokenProvider;

    public ApiInterceptor(String apiKey, Supplier<String> tokenProvider) {
        this.apiKey = apiKey;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Api api = Optional.ofNullable(request.tag(Invocation.class)).map(Invocation::method)
                .map(method -> method.getAnnotation(Api.class)).orElse(null);

        if (api == null) {
            return chain.proceed(request);
        }

        Request.Builder reqBuilder = request.newBuilder();

        if (request.header(HEADER_CONTENT_TYPE) == null) {
            reqBuilder.addHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON);
        }

        if (request.header(ACCEPT_HEADER) == null) {
            reqBuilder.addHeader(ACCEPT_HEADER, APPLICATION_JSON);
        }

        if (request.header(API_KEY_HEADER) == null) {
            reqBuilder.addHeader(API_KEY_HEADER, apiKey);
        }

        if (api.secured()) {
            reqBuilder.header(AUTHORIZATION_HEADER, String.join(" ", AUTH_SCHEME, tokenProvider.get()));
        }

        return chain.proceed(reqBuilder.build());
    }


}
