package br.edu.infnet.avaliacao.omdb.repository.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cliente on 08/04/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MoviesIMDB";

    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " +
            DBConstants.FAVORITES_TABLE + " ( " +
            DBConstants.FAVORITES_ID + "integer primary key, " +
            DBConstants.FAVORITES_IMDB_ID + " text not null," +
            DBConstants.FAVORITES_USER_ID + " text not null," +
            DBConstants.FAVORITES_TITLE + " text not null," +
            DBConstants.FAVORITES_YEAR + " text not null," +
            DBConstants.FAVORITES_POSTER + " text not null);";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
    }
}