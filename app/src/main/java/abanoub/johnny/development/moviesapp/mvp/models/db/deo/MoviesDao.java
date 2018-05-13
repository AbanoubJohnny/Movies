package abanoub.johnny.development.moviesapp.mvp.models.db.deo;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM Movie")
    LiveData<List<MovieDetails>> getAll();


    @Query("SELECT * FROM Movie WHERE id=:mId")
    LiveData<MovieDetails> getMovie(int mId);

    @Query("SELECT count(*) FROM Movie")
    int getCount();

    @Query("SELECT * FROM Movie WHERE original_title LIKE :search ")
    public List<MovieDetails> findBranchesWithName(String search);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll( List<MovieDetails> movies);

    @Delete
    void delete(MovieDetails movie);

}
