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

public class Ids extends SimklId {

    @Json(name = "hulu")
    public final String hulu;

    @Json(name = "netflix")
    public final String netflix;

    @Json(name = "mal")
    public final String mal;

    @Json(name = "tvdb")
    public final String tvdb;

    @Json(name = "tmdb")
    public final String tmdb;

    @Json(name = "tmdbtv")
    public final String tmdbtv;

    @Json(name = "imdb")
    public final String imdb;

    @Json(name = "anidb")
    public final String anidb;

    @Json(name = "crunchyroll")
    public final String crunchyRoll;

    @Json(name = "fb")
    public final String fb;

    @Json(name = "instagram")
    public final String instagram;

    @Json(name = "tw")
    public final String tw;

    @Json(name = "tvdbslug")
    public final String tvdbslug;

    @Json(name = "traktslug")
    public final String traktslug;

    @Json(name = "zap2it")
    public final String zap2it;

    @Json(name = "offen")
    public final String offen;

    @Json(name = "ann")
    public final String ann;

    @Json(name = "anfo")
    public final String anfo;

    @Json(name = "wikien")
    public final String wikien;

    @Json(name = "offjp")
    public final String offjp;

    @Json(name = "moviedb")
    public final String moviedb;

    @Json(name = "wikizh")
    public final String wikizh;

    @Json(name = "wikiko")
    public final String wikiko;

    @Json(name = "anison")
    public final String anison;

    @Json(name = "schel")
    public final String schel;

    @SuppressWarnings("unused")
    Ids(Long simkl, Long simklId, String slug, String hulu, String netflix, String mal, String tvdb, String tmdb,
        String tmdbtv, String imdb, String anidb, String crunchyRoll, String fb, String instagram, String tw,
        String tvdbslug, String traktslug, String zap2it, String offen, String ann, String anfo, String wikien,
        String offjp, String moviedb, String wikizh, String wikiko, String anison, String schel) {
        super(simkl, simklId, slug);
        this.hulu = hulu;
        this.netflix = netflix;
        this.mal = mal;
        this.tvdb = tvdb;
        this.tmdb = tmdb;
        this.tmdbtv = tmdbtv;
        this.imdb = imdb;
        this.anidb = anidb;
        this.crunchyRoll = crunchyRoll;
        this.fb = fb;
        this.instagram = instagram;
        this.tw = tw;
        this.tvdbslug = tvdbslug;
        this.traktslug = traktslug;
        this.zap2it = zap2it;
        this.offen = offen;
        this.ann = ann;
        this.anfo = anfo;
        this.wikien = wikien;
        this.offjp = offjp;
        this.moviedb = moviedb;
        this.wikizh = wikizh;
        this.wikiko = wikiko;
        this.anison = anison;
        this.schel = schel;
    }
}