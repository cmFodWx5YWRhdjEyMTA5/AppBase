package com.haijun.shop.util.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @anthor haijun
 * @project name: Healthy
 * @class name：com.amsu.healthy.utils.database
 * @time 7/5/2017 9:20 AM
 * @describe
 */

public class SqliteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = SqliteOpenHelper.class.getSimpleName();

    public SqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate is calles");
        db.execSQL(getCreateTableSql("data"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"onUpgrade is calles");

        if(newVersion>oldVersion) {
            //db.execSQL("ALTER TABLE uploadreport ADD lf1 TEXT;");
        }
    }


    private String getCreateTableSql(String tableName){
        String OFFLINE_RECORD_CREATE = "create table if not exists "+tableName+"("
                + "fileName STRING)";

        return OFFLINE_RECORD_CREATE;
    }

}

