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

import retrofit2.Call;
import retrofit2.http.*;

interface Authentication {

    @FormUrlEncoded
    @POST("token")
    @Auth
    Call<AccessToken> token(@Field("grant_type") String grantType,
                            @Field("code") String code,
                            @Field("client_id") String clientId,
                            @Field("client_secret") String clientSecret,
                            @Field("redirect_uri") String redirectUri);


    @GET("pin/{code}")
    @Auth
    Call<AccessToken> token(@Path("code") String userCode, @Query("client_id") String clientId);


    @GET("pin")
    @Auth
    Call<DeviceCode> deviceCode(@Query("client_id") String clientId, @Query("redirect") String redirectUri);
}
