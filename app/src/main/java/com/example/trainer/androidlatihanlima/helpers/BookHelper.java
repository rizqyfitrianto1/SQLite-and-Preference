package com.example.trainer.androidlatihanlima.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookHelper extends SQLiteOpenHelper {

    final  static String DBNAME = "book.db";
    final static int DBVERSION=1;


    public BookHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querycreate = "CREATE TABLE samplebooks (_id integer primary key autoincrement," +
                "title text," +
                "author text);";

        sqLiteDatabase.execSQL(querycreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String quertdrop = "DROP TABLE IF EXISTS samplebooks";
        sqLiteDatabase.execSQL(quertdrop);
        onCreate(sqLiteDatabase);

    }
}
