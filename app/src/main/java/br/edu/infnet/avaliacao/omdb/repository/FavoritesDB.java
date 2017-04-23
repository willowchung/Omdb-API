package br.edu.infnet.avaliacao.omdb.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.infnet.avaliacao.omdb.entidades.Movie;
import br.edu.infnet.avaliacao.omdb.repository.helper.DBConstants;
import br.edu.infnet.avaliacao.omdb.repository.helper.DBHelper;

public class FavoritesDB {
    private static FavoritesDB sInstance;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    /**
     *
     * @param context
     */
    public FavoritesDB(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public static synchronized FavoritesDB getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new FavoritesDB(context.getApplicationContext());
        }
        return sInstance;
    }

    public long createMovie(Movie movie, String userId){
        ContentValues values = new ContentValues();
        values.put(DBConstants.FAVORITES_IMDB_ID, movie.getImdbID());
        values.put(DBConstants.FAVORITES_USER_ID, userId);
        values.put(DBConstants.FAVORITES_TITLE, movie.getTitle());
        values.put(DBConstants.FAVORITES_YEAR, movie.getYear());
        values.put(DBConstants.FAVORITES_POSTER, movie.getPoster());
        return database.insert(DBConstants.FAVORITES_TABLE, null, values);
    }

    public boolean isFavorite(String imdbId, String userId) {
        Cursor cursor = database.query(DBConstants.FAVORITES_TABLE, new String[]{DBConstants.FAVORITES_IMDB_ID}, DBConstants.FAVORITES_IMDB_ID + "= ? AND " + DBConstants.FAVORITES_USER_ID + "= ?" , new String[] {imdbId, userId}, null, null, null);
        return cursor.getCount() > 0;
    }

    public ArrayList<Movie> getMovies(String searchText, String userId){
        String search = DBConstants.FAVORITES_USER_ID + "= ?";
        ArrayList<String> param = new ArrayList<>();

        param.add(userId);

        if(searchText != null && !searchText.trim().isEmpty()) {
            search = " AND " + DBConstants.FAVORITES_TITLE + " like ?";
            param.add("%" + searchText + "%");
        }

        Cursor cursor = database.query(DBConstants.FAVORITES_TABLE, null, search, param.toArray(new String[0]), null, null, null);

        ArrayList<Movie> list = new ArrayList<Movie>();

        while(cursor.moveToNext()){
            Movie movie=new Movie();
            movie.setImdbID(cursor.getString(1));
            movie.setTitle(cursor.getString(3));
            movie.setYear(cursor.getString(4));
            movie.setPoster(cursor.getString(5));
            list.add(movie);
        }

        cursor.close();
        return list;
    }

    public long deleteMovie(String imdbId, String userId){
        return database.delete(DBConstants.FAVORITES_TABLE, DBConstants.FAVORITES_IMDB_ID + "= ? AND " + DBConstants.FAVORITES_USER_ID + "= ?" , new String[] {imdbId, userId});
    }
}
