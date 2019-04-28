package com.app.foodapp.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddressDBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "address.db";
        private static final int DATABASE_VERSION = 1;

        public AddressDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String SQL_CREATE_ADDRESS_TABLE = "CREATE TABLE " + AddressContract.AddressEntry.TABLE_NAME + " ("
                    + AddressContract.AddressEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + AddressContract.AddressEntry.COLUMN_CITY_NAME + " TEXT NOT NULL, "
                    + AddressContract.AddressEntry.COLUMN_STREET_NAME + " TEXT NOT NULL, "
                    + AddressContract.AddressEntry.COLUMN_HOUSE_NUMBEBR + " TEXT NOT NULL);";

            db.execSQL(SQL_CREATE_ADDRESS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
}



