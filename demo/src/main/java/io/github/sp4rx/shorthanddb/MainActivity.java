package io.github.sp4rx.shorthanddb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.sp4rx.shothanddb.Constraint;
import io.github.sp4rx.shothanddb.DataType;
import io.github.sp4rx.shothanddb.Schema;
import io.github.sp4rx.shothanddb.ShortHandSchema;
import io.github.sp4rx.shothanddb.Table;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //User table
    private static final String TABLE_USER = "user";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_EMAIL = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Method 1
        Map<String, DataType> tableStructure = new HashMap<>();
        tableStructure.put(KEY_USER_EMAIL, DataType.TEXT);
        tableStructure.put(KEY_USER_ID, DataType.INTEGER);

        Log.d(TAG, Table.generateSql(TABLE_USER, tableStructure));

        //Method 2
        ShortHandSchema shortHandSchema = () -> {
            ArrayList<Schema> schemas = new ArrayList<>();
            schemas.add(new Schema(KEY_USER_ID, DataType.INTEGER, new Constraint[]{Constraint.PRIMARY_KEY, Constraint.UNIQUE} ));
            schemas.add(new Schema(KEY_USER_EMAIL, DataType.TEXT));
            return schemas;
        };

        Log.d(TAG, Table.generateSql(TABLE_USER, shortHandSchema));

        DbHelper.initDb(this);
        System.out.println( DbHelper.getInfo(this));
    }
}
