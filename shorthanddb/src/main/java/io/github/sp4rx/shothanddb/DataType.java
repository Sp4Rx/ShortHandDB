package io.github.sp4rx.shothanddb;

/**
 * Created by suvajit on 23/11/17.
 */

public enum DataType {
    TEXT("TEXT"), INTEGER("INTEGER"), REAL("REAL"), BLOB("BLOB");

    private String value;

    DataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
