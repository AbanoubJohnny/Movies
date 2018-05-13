package abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviespage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesPage<T>{

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("results")
    @Expose
    public T results = null;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
}
