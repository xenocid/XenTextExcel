package com.xenocid.xentextexcel.model;

import java.util.ArrayList;

/**
 * Created by xenocid on 3/9/15.
 */
public class Spreadsheet {
    private ArrayList<Sheet> sheets;


    public Spreadsheet() {
        super();
        this.sheets = new ArrayList<Sheet>();
        addSheet();
    }

    public Sheet addSheet() {
        Sheet newSheet = new Sheet();
        sheets.add(newSheet);
        return newSheet;
    }

    public Sheet addSheetAtIndex(int index) {
        Sheet newSheet = new Sheet();
        sheets.add(index, newSheet);
        return newSheet;
    }

    public ArrayList<Sheet> getSheets() {
        return sheets;
    }

    public Sheet getSheet(int index) {
        return sheets.get(index);
    }
}
