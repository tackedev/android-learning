package com.tackedev.coffee_shop.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tackedev.coffee_shop.database.DatabaseOpener;
import com.tackedev.coffee_shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final DatabaseOpener databaseOpener;

    public UserDao(Context context) {
        this.databaseOpener = new DatabaseOpener(context);
    }

    public boolean isExist(User user) {
        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Cursor cusor = db.rawQuery("SELECT email FROM user WHERE email=? AND password=?",
                new String[]{user.getEmail(), user.getPassword()});
        return cusor.getCount() == 1;
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();

        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email, password FROM user", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            result.add(new User(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        return result;
    }

    public void delete(String email) {
        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM user WHERE email=?", new String[]{email});
        cursor.getCount();
    }

}
