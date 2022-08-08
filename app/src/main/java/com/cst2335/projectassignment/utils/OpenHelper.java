package com.cst2335.projectassignment.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class OpenHelper extends SQLiteOpenHelper {
    public static final String FILE_NAME = "TicketQuery";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "FAVORITES";
    public static final String COL_ID = "_id";
    public static final String COL_EVENT_ID = "eventId";
    public static final String COL_IS_EVENT_FAVORITE = "isFavorite";

    // TODO: Add JavaDoc Comment
    public OpenHelper(Context context) {
        super(context, FILE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format(
                "Create table %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER );",
                TABLE_NAME,
                COL_ID,
                COL_EVENT_ID,
                COL_IS_EVENT_FAVORITE
        );

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format(
                "Drop table if exists %s",
                TABLE_NAME
        );

        db.execSQL(query);

        this.onCreate(db);
    }



}
