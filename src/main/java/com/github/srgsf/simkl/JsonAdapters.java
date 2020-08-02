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

import com.github.srgsf.simkl.model.WatchStatus;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.TimeZone;

class JsonAdapters {

    static class DateTimeAdapter {

        @ToJson
        String toJson(OffsetDateTime dt) {
            if (dt == null) {
                return null;
            }
            return dt.toString();
        }

        @FromJson
        OffsetDateTime fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return null;
            }
            return OffsetDateTime.parse(s);
        }
    }

    static class DateAdapter {

        @ToJson
        String toJson(LocalDate dt) {
            if (dt == null) {
                return null;
            }
            return dt.toString();
        }

        @FromJson
        LocalDate fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return null;
            }
            return LocalDate.parse(s);
        }
    }

    static class TimeAdapter {
        private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("hh:mm a");

        @ToJson
        String toJson(LocalTime t) {
            if (t == null) {
                return null;
            }
            return t.format(fmt);
        }

        @FromJson
        LocalTime fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return null;
            }
            return LocalTime.parse(s, fmt);
        }
    }

    static class TimeZoneAdapter {
        @ToJson
        String toJson(TimeZone tz) {
            if (tz == null) {
                return null;
            }
            return tz.getID();
        }

        @FromJson
        TimeZone fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return null;
            }
            return TimeZone.getTimeZone(s);
        }
    }

    static class DayOfWeekAdapter {
        @ToJson
        String toJson(DayOfWeek dow) {
            if (dow == null) {
                return null;
            }
            return dow.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        }

        @FromJson
        DayOfWeek fromJson(String s) {
            if (s == null || s.trim().length() == 0) {
                return null;
            }
            return DayOfWeek.valueOf(s.toUpperCase());
        }
    }

    static class WatchStatusAdapter {
        @ToJson
        public String toJson(WatchStatus.Result r) {
            if (r == null) {
                return null;
            }
            return r.value;
        }

        @FromJson
        public WatchStatus.Result fromJson(JsonReader reader) throws IOException {
            switch (reader.peek()) {
                case NULL:
                    reader.nextNull();
                    return null;
                case BOOLEAN:
                    return reader.nextBoolean() ? WatchStatus.Result.yes : WatchStatus.Result.no;
                case STRING: {
                    String val = reader.nextString();
                    for (WatchStatus.Result r : WatchStatus.Result.values()) {
                        if (val.equals(r.value)) {
                            return r;
                        }
                    }
                    throw new JsonDataException("Unknown watch status result: " + val);
                }
                default:
                    throw new JsonDataException("Unknown watch status type: " + reader.peek());
            }
        }
    }

}
