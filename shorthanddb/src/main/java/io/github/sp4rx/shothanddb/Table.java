package io.github.sp4rx.shothanddb;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Map;

/**
 * Created by suvajit on 23/11/17.
 */

public final class Table {
    private static final String TAG = "Table";

//    public synchronized static boolean generateSql(String tableName, Map<String, DataType> tableStructure, SQLiteDatabase db) {
//        //Create table
//        StringBuilder createTableSql = new StringBuilder(" CREATE TABLE IF NOT EXISTS " +
//                tableName + " ( ");
//        int count = 0;
//        for (String columnName : tableStructure.keySet()) {
//            if (count == tableStructure.size() - 1) {
//                createTableSql.append(columnName).append(" ").append(tableStructure.get(columnName).getValue()).append(" )");
//            } else {
//                createTableSql.append(columnName).append(" ").append(tableStructure.get(columnName).getValue()).append(", ");
//            }
//            count++;
//        }
//
//        Log.d(TAG, "Generated generateSql table SQL: " + createTableSql);
//        try {
//            db.execSQL(createTableSql.toString());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }

    //Initialize column values
//        ContentValues contentValues = new ContentValues();
//        for (TablePojo tablePojo : tableStructure) {
//            switch (tablePojo.getValue()) {
//                case INTEGER:
//                    contentValues.put(tablePojo.getColumnName(), (Integer) tablePojo.getValue());
//                    break;
//                case TEXT:
//                    contentValues.put(tablePojo.getColumnName(), (String) tablePojo.getValue());
//                    break;
//            }
//        }
//
//        try {
//            db.insertOrThrow(tableName, null, contentValues);
//        } catch (SQLException e) {
//            if (BuildConfig.DEBUG)
//                e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }


    public synchronized static String generateSql(String tableName, Map<String, DataType> tableStructure) {
        //Check if null parameters
        if (tableName == null)
            throw new NullPointerException("tableName returned null value");
        if (tableStructure == null)
            throw new NullPointerException("tableStructure returned null value");

        //Create table sql
        StringBuilder createTableSql = new StringBuilder(" CREATE TABLE IF NOT EXISTS " +
                tableName + " ( ");
        int count = 0;
        for (String columnName : tableStructure.keySet()) {
            if (count == tableStructure.size() - 1) {
                createTableSql.append(columnName).append(" ").append(tableStructure.get(columnName).getValue()).append(" )");
            } else {
                createTableSql.append(columnName).append(" ").append(tableStructure.get(columnName).getValue()).append(", ");
            }
            count++;
        }

        return createTableSql.toString();
    }


    public synchronized static String generateSql(String tableName, ShortHandSchema shortHandSchema) {
        //Check if null parameters
        if (shortHandSchema.getSchema() == null)
            throw new NullPointerException("getSchema from " + ShortHandSchema.class.getCanonicalName() + " returned null value");
        if (tableName == null)
            throw new NullPointerException("tableName returned null value");


        //Create table sql
        StringBuilder createTableSql = new StringBuilder(" CREATE TABLE IF NOT EXISTS " +
                tableName + " ( ");
        int count = 0;
        for (Schema schema : shortHandSchema.getSchema()) {
            if (count == shortHandSchema.getSchema().size() - 1) {
                createTableSql.append(schema.getColumnName()).append(" ").append(schema.getConstraintsString()).append(" ").append(schema.getDataType().getValue()).append(" )");
            } else {
                createTableSql.append(schema.getColumnName()).append(" ").append(schema.getConstraintsString()).append(" ").append(schema.getDataType().getValue()).append(", ");
            }
            count++;
        }

        return createTableSql.toString();
    }

    public synchronized static boolean create(String tableName, Map<String, DataType> tableStructure, SQLiteDatabase db) {
        //Check if null parameters
        if (tableName == null)
            throw new NullPointerException("tableName returned null value");
        if (tableStructure == null)
            throw new NullPointerException("tableStructure returned null value");

        //Create table sql
        StringBuilder createTableSql = new StringBuilder(" CREATE TABLE IF NOT EXISTS " +
                tableName + " ( ");
        int count = 0;
        for (String columnName : tableStructure.keySet()) {
            if (count == tableStructure.size() - 1) {
                createTableSql.append(columnName).append(" ").append(tableStructure.get(columnName).getValue()).append(" )");
            } else {
                createTableSql.append(columnName).append(" ").append(tableStructure.get(columnName).getValue()).append(", ");
            }
            count++;
        }

        Logger.i(TAG, "SQL: " + createTableSql);

        try {
            db.execSQL(createTableSql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public synchronized static boolean create(String tableName, ShortHandSchema shortHandSchema, SQLiteDatabase db) {
        //Check if null parameters
        if (shortHandSchema.getSchema() == null)
            throw new NullPointerException("getSchema from " + ShortHandSchema.class.getCanonicalName() + " returned null value");
        if (tableName == null)
            throw new NullPointerException("tableName returned null value");

        //Create table sql
        StringBuilder createTableSql = new StringBuilder(" CREATE TABLE IF NOT EXISTS " +
                tableName + " ( ");
        int count = 0;
        for (Schema schema : shortHandSchema.getSchema()) {
            if (count == shortHandSchema.getSchema().size() - 1) {
                createTableSql.append(schema.getColumnName()).append(" ").append(schema.getConstraintsString()).append(" ").append(schema.getDataType().getValue()).append(" )");
            } else {
                createTableSql.append(schema.getColumnName()).append(" ").append(schema.getConstraintsString()).append(" ").append(schema.getDataType().getValue()).append(", ");
            }
            count++;
        }

        try {
            db.execSQL(createTableSql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
