package com.xenocid.xentextexcel.model;

import javafx.scene.control.Cell;

/**
 * Created by xenocid on 3/8/15.
 */
public class CellValue {
    public enum CellValueType {
        CVT_NONE,
        CVT_FORMULA,
        CVT_INTEGER,
        CVT_DOUBLE,
        CVT_STRING
    };

    private CellValueType   type;
    private int             intValue;
    private double          doubleValue;
    private String          stringValue;

    public CellValue() {
        super();
        reset();
    }

    public CellValue(int intValue) {
        super();
        reset();
        this.type = CellValueType.CVT_INTEGER;
        this.intValue = intValue;
    }

    public CellValue(double doubleValue) {
        super();
        reset();
        this.type = CellValueType.CVT_DOUBLE;
        this.doubleValue = doubleValue;
    }

    public CellValue(String stringValue) {
        super();
        reset();
        this.type = CellValueType.CVT_STRING;
        this.stringValue = stringValue;
    }

    public CellValue(CellValue other) {
        super();
        reset();
        this.type = other.type;
        this.intValue = other.intValue;
        this.doubleValue = other.doubleValue;
        this.stringValue = other.stringValue;
    }

    public CellValueType getType() { return this.type; }
    public int getIntValue() { return this.intValue; }
    public double getDoubleValue() { return this.doubleValue; }
    public String getStringValue() { return this.stringValue; }
    public String getFormulaValue() { return this.stringValue; }

    public void setType(CellValueType type) {
        this.type = type;
    }

    public void setIntValue(int intValue) {
        setType(CellValueType.CVT_INTEGER);
        this.intValue = intValue;
    }

    public void setDoubleValue(double doubleValue) {
        setType(CellValueType.CVT_DOUBLE);
        this.doubleValue = doubleValue;
    }

    public void setStringValue(String stringValue) {
        setType(CellValueType.CVT_STRING);
        this.stringValue = stringValue;
    }

    public void setFormulaValue(String formula) {
        setType(CellValueType.CVT_FORMULA);
        this.stringValue = formula;
    }

    private void reset() {
        this.type = CellValueType.CVT_NONE;
        this.intValue = 0;
        this.doubleValue = 0.0f;
        this.stringValue = "";
    }
}
