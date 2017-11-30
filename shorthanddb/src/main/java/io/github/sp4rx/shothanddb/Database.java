package io.github.sp4rx.shothanddb;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suvajit on 30/11/17.
 */

public class Database {
    public synchronized static boolean deleteAllTables(SQLiteDatabase db) {
        // query to obtain the names of all tables in your database
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        List<String> tables = new ArrayList<>();

        // iterate over the result set, adding every table name to a list
        while (c.moveToNext()) {
            tables.add(c.getString(0));
        }

        //  Drop on every table name
        for (String table : tables) {
            String dropQuery = "DROP TABLE IF EXISTS " + table;
            try {
                db.execSQL(dropQuery);
            } catch (SQLException e) {
                if (Logger.isDebuggable)
                    e.printStackTrace();
                return false;
            }
        }
        c.close();
        return true;
    }
}
