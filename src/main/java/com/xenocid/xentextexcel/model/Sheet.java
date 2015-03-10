package com.xenocid.xentextexcel.model;

/**
 * Created by xenocid on 3/9/15.
 */
public class Sheet {
    private CellStorageEngine cells;

    public Sheet() {
        super();
        this.cells = new SparseArrayCellStorageEngine();
    }

    public int getRows() {
        return cells.getRows();
    }

    public int getColumns() {
        return cells.getColumns();
    }

    public Cell getCell(int row, int column) {
        return cells.getCell(row, column);
    }

    public Cell getCell(int[] coordinates) {
        return getCell(coordinates[0], coordinates[1]);
    }

    public Cell getCell(String cellRefStr) {
        return getCell(CellReference.stringToCoordinates(cellRefStr));
    }

    public Cell setCell(String cellRefStr, int intValue) {
        Cell cell = getCell(cellRefStr);
        cell.setValue(intValue);
        return cell;
    }

    public Cell setCell(String cellRefStr, double dblValue) {
        Cell cell = getCell(cellRefStr);
        cell.setValue(dblValue);
        return cell;
    }

    public Cell setCell(String cellRefStr, String strValue) {
        Cell cell = getCell(cellRefStr);
        cell.setValue(strValue);
        return cell;
    }

    public void insertRowAfter(int rowIndex) {
        cells.insertRowAfter(rowIndex);
    }

    public void insertRowBefore(int rowIndex) {
        cells.insertRowBefore(rowIndex);
    }

    public void insertColumnBefore(int columnIndex) {
        cells.insertColumnBefore(columnIndex);
    }

    public void insertColumnAfter(int columnIndex) {
        cells.insertColumnAfter(columnIndex);
    }
}
