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

import com.squareup.moshi.Json;

/**
 * OAuth 2.0 limited device flow response.
 *
 * @author srgsf
 * @since 0.1
 */
public final class DeviceCode {

    @Json(name = "user_code")
    public final String userCode;

    @Json(name = "verification_url")
    public final String verificationUrl;

    @Json(name = "expires_in")
    public final Long expiresIn;

    @Json(name = "interval")
    public final Integer interval;

    @SuppressWarnings("unused")
    DeviceCode(String userCode, String verificationUrl, Long expiresIn, Integer interval) {
        this.userCode = userCode;
        this.verificationUrl = verificationUrl;
        this.expiresIn = expiresIn;
        this.interval = interval;
    }
}