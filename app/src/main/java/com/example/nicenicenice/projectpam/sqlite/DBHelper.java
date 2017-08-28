package com.example.nicenicenice.projectpam.sqlite;

/**
 * Created by capri on 5/13/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import com.example.nicenicenice.projectpam.controller.ControllerLeaderboard;

public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "leaderboard.db";
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(ControllerLeaderboard.CREATE_LEADERBOARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
    }

}