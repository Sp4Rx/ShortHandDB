package io.github.sp4rx.shorthanddb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.sp4rx.shothanddb.Constraint;
import io.github.sp4rx.shothanddb.DataType;
import io.github.sp4rx.shothanddb.Database;
import io.github.sp4rx.shothanddb.Schema;
import io.github.sp4rx.shothanddb.ShortHandSchema;
import io.github.sp4rx.shothanddb.Table;

/**
 * Created by suvajit on 23/11/17.
 */


public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "qr_login";
    private static final int VERSION = 4;

    private static final String TAG = "DbHelper";
    private static DbHelper mInstance;

    //User table
    private static final String TABLE_USER = "user";
    private static final String TABLE_USER_2 = "user_two";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_EMAIL = "user_email";

    private DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Method 3
        Map<String, DataType> tableStructure = new HashMap<>();
        tableStructure.put(KEY_USER_EMAIL, DataType.TEXT);
        tableStructure.put(KEY_USER_ID, DataType.INTEGER);

        Table.create(TABLE_USER, tableStructure, db);

        //Method 4
        ShortHandSchema shortHandSchema = new ShortHandSchema() {
            @Override
            public ArrayList<Schema> getSchema() {
                ArrayList<Schema> schemas = new ArrayList<>();
                schemas.add(new Schema(KEY_USER_ID, new Constraint[]{Constraint.PRIMARY_KEY, Constraint.UNIQUE}, DataType.INTEGER));
                schemas.add(new Schema(KEY_USER_EMAIL, DataType.TEXT));
                return schemas;
            }
        };
        Table.create(TABLE_USER_2, shortHandSchema, db);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Database.deleteAllTables(db);
        onCreate(db);
    }


    static synchronized DbHelper getHelper(Context context) {
        if (mInstance == null) {
            mInstance = new DbHelper(context);
        }
        return mInstance;
    }

    public static String getInfo(Context context) {
        String info = "";
        SQLiteDatabase db = getHelper(context).getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, null, null, null, null, null);
        if (cursor.getCount() > 1) {
            cursor.moveToFirst();
            info = cursor.getString(cursor.getColumnIndex(KEY_USER_EMAIL));
        }

        cursor.close();
        return info;
    }

    public static void initDb(Context context) {
        SQLiteDatabase db = getHelper(context).getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USER_ID, 1);
        contentValues.put(KEY_USER_EMAIL, "aa@aa.com");
        db.insert(TABLE_USER, null, contentValues);

    }

}
