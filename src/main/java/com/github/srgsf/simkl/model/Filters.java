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

public class Filters {

    public enum Sort {
        popularToday("popular-today"),
        popularThisWeek("popular-this-week"),
        popularThisMonth("popular-this-month"),
        rank,
        votes,
        releaseDate("release-date"),
        lastAirDate("last-air-date"),
        asc("a-z"),
        desc("z-a");

        public final String value;

        Sort() {
            value = name();
        }

        Sort(String value) {
            this.value = value;
        }
    }

    public enum Year {
        all("all-years"),
        today("today"),
        thisWeek("this-week"),
        thisMonth("this-month"),
        thisYear("this-year");
        public final String value;

        Year(String value) {
            this.value = value;
        }
    }

    public enum SeriesType {
        tv, anime
    }

    public enum Service {
        netflix, simkl
    }

    public enum TmdbType {
        show, movie
    }

    public enum ExtendedLevel {
        full
    }

    public static class Show {


        public enum PremiereType {
            all, entertainment,
            documentaries,
            animation("animation-filter");

            public final String value;

            PremiereType(String value) {
                this.value = value;
            }

            PremiereType() {
                value = name();
            }
        }

        public enum Type {
            all("all-types"),
            shows("tv-shows"),
            entertainment,
            documentaries,
            animation("animation-filter");

            public final String value;

            Type() {
                value = name();
            }

            Type(String value) {
                this.value = value;
            }
        }

        public enum Country {
            all("all-countries"),
            us,
            gb,
            ca,
            kr,
            jp;

            public final String value;

            Country(String value) {
                this.value = value;
            }

            Country() {
                value = name();
            }
        }

        public enum Genre {
            all,
            actionAndAdventure("action-and-adventure"),
            adventure,
            animation,
            children,
            comedy,
            crime,
            documentary,
            drama,
            family,
            fantasy,
            food,
            gameShow("game-show"),
            homeAndGarden("home-and-garden"),
            horror,
            miniSeries("mini-series"),
            mystery,
            news,
            reality,
            scienceFiction("science-fiction"),
            soap,
            specialInterest("special-interest"),
            sport,
            suspense,
            talkShow("talk-show"),
            thriller,
            travel,
            western;

            public final String value;

            Genre(String value) {
                this.value = value;
            }

            Genre() {
                value = name();
            }
        }

        public enum Network {
            all("all-networks"),
            netflix,
            disney,
            peacock,
            appletv,
            quibi,
            cbs,
            abc,
            fox,
            cw,
            hbo,
            showtime,
            usa,
            syfy,
            tnt,
            fx,
            amc,
            abcfam,
            showcase,
            starz,
            mtv,
            lifetime,
            ae,
            tvland;

            public final String value;

            Network(String value) {
                this.value = value;
            }

            Network() {
                value = name();
            }
        }
    }

    public static class Anime {


        public enum Type {
            all, series, movies, ovas
        }

        public enum Genre {
            action,
            adventure,
            angst,
            anthropomorphism,
            blackmail,
            comedy,
            detective,
            drama,
            ecchi,
            fantasy,
            harem,
            henshin,
            horror,
            romance,
            thriller,
            western,
            all,
            mahouShoujo("mahou-shoujo"),
            sciFi("sci-fi"),
            sexualAbuse("under-one-roof"),
            underOneRoof("under-one-roof");

            public final String value;

            Genre(String value) {
                this.value = value;
            }

            Genre() {
                value = name();
            }
        }

        public enum Network {
            all("all-networks"),
            tvtokyo,
            tokyomx,
            fujitv,
            tokyobroadcastingsystem,
            tvasahi,
            wowow,
            ntv,
            atx,
            ctc,
            nhk,
            mbs,
            animax,
            cartoonnetwork,
            abc;

            public final String value;

            Network(String value) {
                this.value = value;
            }

            Network() {
                value = name();
            }
        }
    }

    public static class Movie {
        public enum Genre {
            action,
            adventure,
            animation,
            comedy,
            crime,
            drama,
            family,
            fantasy,
            foreign,
            history,
            horror,
            music,
            mystery,
            romance,
            scienceFiction("science-fiction"),
            thriller,
            tvMovie("tv-movie"),
            war,
            western;

            public final String value;

            Genre(String value) {
                this.value = value;
            }

            Genre() {
                value = name();
            }
        }
    }

}
