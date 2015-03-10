package com.xenocid.xentextexcel.model;

import java.util.ArrayList;

/**
 * Created by xenocid on 3/9/15.
 */
public interface CellStorageEngine {
    public Cell getCell(int row, int column);
    public void setCell(int row, int column, CellValue cellValue);
    public ArrayList<Cell> getRow(int row);
    public ArrayList<Cell> getColumn(int column);
    public void insertRowBefore(int row);
    public void insertRowAfter(int row);
    public void insertColumnBefore(int column);
    public void insertColumnAfter(int column);
    public int  getRows();
    public int  getColumns();
}
