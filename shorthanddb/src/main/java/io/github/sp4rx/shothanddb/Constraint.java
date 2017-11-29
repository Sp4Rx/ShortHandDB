package io.github.sp4rx.shothanddb;

/**
 * Created by suvajit on 28/11/17.
 */

public enum Constraint {
    PRIMARY_KEY("PRIMARY KEY"), NOT_NULL("NOT NULL"), UNIQUE("UNIQUE"), FOREIGN_KEY("FOREIGN KEY");

    private String value;

    Constraint(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}