package abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = {"id"}, tableName = "Movie")
public class MovieDetails {


    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    private Boolean adult = false;
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdrop_path = "";
    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    private Integer budge = 0;
    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    private String homepage = "";
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Integer id = 0;
    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    private String imdb_id = "";
    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    private String original_language = "";
    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    private String original_title = "";
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    private String overview = "";
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    private double popularity = 0.0;
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    @Nullable
    private String poster_path = "";
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    private String release_date = "";
    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    private Integer revenue  =0 ;
    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    private Integer runtime = 0;
    @SerializedName("status")
    @ColumnInfo(name = "status")
    private String status = "";
    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    private String tagline = "";
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title = "";
    @SerializedName("video")
    @ColumnInfo(name = "video")
    private Boolean video = false;
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private double vote_average  =0.0;
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    private Integer vote_count = 0;

    public MovieDetails(){}

    public MovieDetails(Boolean adult, String backdrop_path, Integer budge, String homepage, Integer id, String imdb_id, String original_language, String original_title,
                        String overview, double popularity, String poster_path,String release_date, Integer revenue, Integer runtime,
                        String status, String tagline, String title, Boolean video, double vote_average, Integer vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.budge = budge;
        this.homepage = homepage;
        this.id = id;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    public Integer getBudge() {
        return budge;
    }

    public void setBudge(Integer budge) {
        this.budge = budge;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Nullable
    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(@Nullable String poster_path) {
        this.poster_path = poster_path;
    }


    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }
}
