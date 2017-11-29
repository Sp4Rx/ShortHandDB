package io.github.sp4rx.shothanddb;

import java.util.Arrays;

/**
 * Created by suvajit on 28/11/17.
 */

public class Schema {
    private String columnName;
    private Constraint[] constraints;
    private DataType dataType;

    public Schema(String columnName, DataType dataType) {
        this.columnName = columnName;
        this.dataType = dataType;
    }

    public Schema(String columnName, Constraint[] constraints, DataType dataType) {
        this.columnName = columnName;
        this.constraints = constraints;
        this.dataType = dataType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Constraint[] getConstraints() {
        return constraints;
    }

    public String getConstraintsString() {
        StringBuilder constraints = new StringBuilder();
        if (this.constraints != null) {
            for (Constraint constraint : this.constraints) {
                constraints.append(constraint.getValue()).append(" ");
            }
        }
        return constraints.toString();
    }

    public void setConstraints(Constraint[] constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        return "Schema{" +
                "columnName='" + columnName + '\'' +
                ", constraints=" + Arrays.toString(constraints) +
                ", dataType=" + dataType +
                '}';
    }
}
