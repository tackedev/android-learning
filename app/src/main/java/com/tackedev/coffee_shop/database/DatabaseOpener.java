package com.tackedev.coffee_shop.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseOpener extends SQLiteOpenHelper {

    public DatabaseOpener(@Nullable Context context) {
        super(context, "CoffeeShop", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE user(email Text primary key not null, password Text)";
        db.execSQL(createUserTableQuery);
        db.execSQL("INSERT INTO user VALUES('kylq@gmail.com', 'Admin@123')");
        db.execSQL("INSERT INTO user VALUES('ky.le@gmail.com', 'Admin@123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS ADMIN");
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
    }

}
