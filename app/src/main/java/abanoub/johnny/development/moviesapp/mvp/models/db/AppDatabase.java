package abanoub.johnny.development.moviesapp.mvp.models.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import abanoub.johnny.development.moviesapp.mvp.models.db.deo.MoviesDao;
import abanoub.johnny.development.moviesapp.mvp.models.entity.response.moviedetails.MovieDetails;

@Database(entities = {MovieDetails.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}