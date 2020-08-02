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

public class SimklId {
    @Json(name = "simkl")
    private final Long simkl;
    @Json(name = "simkl_id")
    private final Long simklId;
    @Json(name = "slug")
    public final String slug;

    public Long simkl() {
        return simkl == null ? simklId : simkl;
    }

    @SuppressWarnings("unused")
    SimklId(Long simkl, Long simklId, String slug) {
        this.simkl = simkl;
        this.simklId = simklId;
        this.slug = slug;
    }
}
