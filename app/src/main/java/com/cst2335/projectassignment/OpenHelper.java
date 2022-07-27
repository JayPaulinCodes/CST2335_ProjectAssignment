package com.cst2335.projectassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String FILE_NAME = "TicketMasterSearcher";
    public static final int VERSION = 1;

    public static final String TABLE_NAME = "Favorites";
    public static final String COL_ID = "_id";
    public static final String COL_MESSAGE = "Message";
    public static final String COL_SEND_RECEIVE = "SendOrReceive";

    public OpenHelper(Context context) {
        super(context, FILE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format(
                "Create table %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER );",
                TABLE_NAME,
                COL_ID,
                COL_MESSAGE,
                COL_SEND_RECEIVE
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
