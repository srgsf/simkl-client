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

class Paged<T extends Paged<T>> {

    @Json(name = "page")
    public int page = 1;

    @Json(name = "limit")
    public int limit = 10;

    @SuppressWarnings("unchecked")
    public T page(int page) {
        this.page = page;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T limit(int limit) {
        this.limit = limit;
        return (T) this;
    }
}
