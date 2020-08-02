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

import com.squareup.moshi.JsonReader;
import okhttp3.*;
import okio.BufferedSource;
import retrofit2.Invocation;

import java.io.IOException;
import java.util.Optional;

/**
 * Internal interceptor that helps with api specific handling.
 *
 * @author srgsf
 * @since 0.1
 */
class AuthInterceptor implements Interceptor {

    private static final String RESULT_FIELD = "result";
    private static final String OK = "OK";
    private static final String APPLICATION_JSON = "application/json";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";


    /**
     * Create new instance.
     */
    AuthInterceptor() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!Optional.ofNullable(request.tag(Invocation.class)).map(Invocation::method)
                .map(method -> method.getAnnotation(Auth.class)).isPresent()) {
            return chain.proceed(request);
        }

        if (request.header(HEADER_CONTENT_TYPE) == null) {
            request = request.newBuilder().addHeader(HEADER_CONTENT_TYPE, APPLICATION_JSON).build();
        }

        Response response = chain.proceed(request);

        if (!response.isSuccessful()) {
            return response;
        }

        Optional<ResponseBody> body = Optional.ofNullable(response.body());

        if (!body.map(ResponseBody::contentType).map(MediaType::toString)
                .filter(mt -> mt.contains(APPLICATION_JSON)).isPresent()) {
            return response;
        }

        BufferedSource source = body.map(ResponseBody::source).get();
        source.request(Long.MAX_VALUE);
        JsonReader reader = JsonReader.of(source.getBuffer().clone());
        if (!(reader.hasNext() || reader.peek() == JsonReader.Token.BEGIN_OBJECT)) {
            return response;
        }
        reader.beginObject();
        if (reader.peek() != JsonReader.Token.NAME) {
            return response;
        }
        int level = 0;
        while (reader.hasNext()) {
            JsonReader.Token token = reader.peek();
            switch (token) {
                case BEGIN_OBJECT:
                    reader.beginObject();
                    ++level;
                    break;
                case END_OBJECT:
                    reader.endObject();
                    --level;
                    break;
                case NAME:
                    String name = reader.nextName();
                    if (level == 0 && RESULT_FIELD.equals(name)) {
                        String res = reader.nextString();
                        if (OK.equals(res)) {
                            return response;
                        }
                        return response.newBuilder().code(418).build();
                    }
                    break;
                default:
                    reader.skipValue();
            }
        }
        return response;
    }
}