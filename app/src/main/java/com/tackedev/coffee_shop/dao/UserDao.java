package com.tackedev.coffee_shop.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tackedev.coffee_shop.database.DatabaseOpener;
import com.tackedev.coffee_shop.model.User;

public class UserDao {

    private DatabaseOpener databaseOpener;

    public UserDao(Context context) {
        this.databaseOpener = new DatabaseOpener(context);
    }

    public boolean isExist(User user) {
        SQLiteDatabase db = databaseOpener.getReadableDatabase();
        Cursor cusor = db.rawQuery("SELECT email FROM user WHERE email=? AND password=?",
                new String[]{user.getEmail(), user.getPassword()});
        return cusor.getCount() == 1;
    }

}
