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

import java.time.OffsetDateTime;
import java.util.TimeZone;

public class UserInfo {

    @Json(name = "user")
    public final User user;

    @Json(name = "account")
    public final Account account;

    @Json(name = "connections")
    public final Connections connections;


    @SuppressWarnings("unused")
    UserInfo(User user, Account account, Connections connections) {
        this.user = user;
        this.account = account;
        this.connections = connections;
    }

    public static class User {

        @Json(name = "name")
        public final String name;

        @Json(name = "joined_at")
        public final OffsetDateTime joined;

        @Json(name = "gender")
        public final String gender;

        @Json(name = "avatar")
        public final String avatar;

        @Json(name = "bio")
        public final String bio;

        @Json(name = "loc")
        public final String location;

        @Json(name = "age")
        public final String age;

        @SuppressWarnings("unused")
        User(String name, OffsetDateTime joined, String gender, String avatar, String bio, String location, String age) {
            this.name = name;
            this.joined = joined;
            this.gender = gender;
            this.avatar = avatar;
            this.bio = bio;
            this.location = location;
            this.age = age;
        }
    }

    public static class Account {
        @Json(name = "id")
        public final Integer id;

        @Json(name = "type")
        public final String type;

        @Json(name = "timezone")
        public final TimeZone timeZone;

        @SuppressWarnings("unused")
        Account(Integer id, String type, TimeZone timeZone) {
            this.id = id;
            this.type = type;
            this.timeZone = timeZone;
        }
    }

    public static class Connections {
        @Json(name = "facebook")
        public final Boolean facebook;

        @SuppressWarnings("unused")
        Connections(Boolean facebook) {
            this.facebook = facebook;
        }
    }
}
