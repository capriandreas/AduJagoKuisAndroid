package com.example.nicenicenice.projectpam.controller;

/**
 * Created by capri on 5/13/2017.
 */

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.nicenicenice.projectpam.model.ModelHasilLeaderboard;
import com.example.nicenicenice.projectpam.sqlite.DBHelper;


public class ControllerLeaderboard {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String TABLE_NAME = "leaderboard";
    public static final String ID = "id";
    public static final String NAMA_USER = "nama_user";
    public static final String POINT = "point";
    public static final String STATUS = "status";

    public static final String CREATE_LEADERBOARD = "CREATE TABLE "+TABLE_NAME+" "+ "("+ID+" integer primary key, "+ NAMA_USER+" TEXT, "+ POINT+" integer, "+ STATUS+" TEXT)";

    private String[] TABLE_COLUMNS = {ID, NAMA_USER, POINT, STATUS };

    public ControllerLeaderboard(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public void deleteData (){
        database.delete(TABLE_NAME, null, null);
    }
    public void insertData(int id, String nama_user, int point, String status){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAMA_USER, nama_user);
        contentValues.put(POINT, point);
        contentValues.put(STATUS, status);
        database.insert(TABLE_NAME, null, contentValues);
    }
    public ArrayList<ModelHasilLeaderboard> getData() {
        ArrayList<ModelHasilLeaderboard> allData = new
                ArrayList<ModelHasilLeaderboard>();
        Cursor cursor = null;
        cursor = database.query(TABLE_NAME, TABLE_COLUMNS, null, null,
                null, null,
                ID + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allData.add(parseData(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return allData;
    }
    private ModelHasilLeaderboard parseData(Cursor cursor){
        ModelHasilLeaderboard curData = new ModelHasilLeaderboard();
        curData.setId(cursor.getInt(0));
        curData.setNama_user(cursor.getString(1));
        curData.setPoint(cursor.getInt(2));
        curData.setStatus(cursor.getString(3));
        return curData;
    }
}

