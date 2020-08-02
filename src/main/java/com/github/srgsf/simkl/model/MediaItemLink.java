/*
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.srgsf.simkl.model;

import com.squareup.moshi.Json;

public class MediaItemLink {
    @Json(name = "simkl_id")
    public final Long simklId;
    @Json(name = "simkl_url")
    public final String simklUrl;

    @Json(name = "netflix_id")
    public final String netfilxId;
    @Json(name = "netflix_url")
    public final String netfilxUrl;


    @SuppressWarnings("unused")
    MediaItemLink(Long simklId, String simklUrl, String netfilxId, String netfilxUrl) {
        this.simklId = simklId;
        this.simklUrl = simklUrl;
        this.netfilxId = netfilxId;
        this.netfilxUrl = netfilxUrl;
    }
}
