package abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(primaryKeys = {"id"}, tableName = "Movie")
public class MovieDetails implements Serializable {

    @Ignore
    private int status_code;
    @Ignore
    private String status_message;
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    private Boolean adult;
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;
    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    private Integer budge;
    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    private String homepage;
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Integer id;
    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    private String imdbID;
    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    private String originalLanguage = "";
    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    private String originalTitle;
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    private String overview;
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    private double popularity;
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    @Nullable
    private String posterPath;
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    private String releaseDate;
    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    private Integer revenue;
    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    private Integer runtime;
    @SerializedName("status")
    @ColumnInfo(name = "status")
    private String status;
    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    private String tagline;
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("video")
    @ColumnInfo(name = "video")
    private Boolean video;
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    private Integer voteCount;

    public MovieDetails(){}
    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
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

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(@Nullable String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
