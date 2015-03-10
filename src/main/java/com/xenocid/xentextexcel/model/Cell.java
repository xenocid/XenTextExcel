package com.xenocid.xentextexcel.model;

/**
 * Created by xenocid on 3/8/15.
 */
public class Cell {
    private CellValue value;

    public Cell() {
        super();
        this.value = new CellValue();
    }

    public Cell(CellValue value) {
        super();
        this.value = value;
    }

    public CellValue getValue() {
        return value;
    }

    public void setValue(CellValue value) {
        this.value = value;
    }

    public void setValue(int intValue) {
        this.value = new CellValue(intValue);
    }

    public void setValue(double dblValue) {
        this.value = new CellValue(dblValue);
    }

    public void setValue(String strValue) {
        this.value = new CellValue(strValue);
    }
}
