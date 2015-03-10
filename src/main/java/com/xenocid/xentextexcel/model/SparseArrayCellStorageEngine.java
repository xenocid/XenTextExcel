package com.xenocid.xentextexcel.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xenocid on 3/9/15.
 */
public class SparseArrayCellStorageEngine implements CellStorageEngine {

    class CellsRow {
        private HashMap<Integer, Cell> cells;

        public CellsRow() {
            super();
            this.cells = new HashMap<Integer, Cell>();
        }

        public Cell getCellAtColumn(int column) {
            Integer columnKey = Integer.valueOf(column);
            if (cells.containsKey(columnKey)) {
                return cells.get(Integer.valueOf(column));
            } else {
                return setCellAtColumn(column, new CellValue());
            }
        }

        public Cell setCellAtColumn(int column, CellValue cellValue) {
            Integer key = Integer.valueOf(column);
            Cell cell;
            if (cells.containsKey(key)) {
                cell = cells.get(key);
                cell.setValue(cellValue);
            } else {
                cell = new Cell(cellValue);
                cells.put(key, cell);
            }
            return cell;
        }

        public void shiftCellsToRightAfter(int column, int by) {
            SortedSet<Integer> keys = new TreeSet<Integer>( cells
                    .keySet()
                    .stream()
                    .filter(key -> key.intValue() >= column)
                    .collect(Collectors.toSet()));
            for(Iterator<Integer> it = ((TreeSet)keys).descendingIterator(); it.hasNext();) {
                Integer key = it.next();
                cells.put(Integer.valueOf(key.intValue() + by), cells.remove(key));
            }
        }
    }

    private HashMap<Integer, CellsRow> rows;
    private int maxRowIndex;
    private int maxColumnIndex;

    public SparseArrayCellStorageEngine() {
        super();
        this.rows = new HashMap<Integer, CellsRow>();
    }

    public int getNumberOfActualCells() {
        return 0;
    }

    @Override
    public Cell getCell(int rowIndex, int columnIndex) {
        return getRowInternal(rowIndex).getCellAtColumn(columnIndex);
    }

    @Override
    public void setCell(int row, int column, CellValue cellValue) {

    }

    @Override
    public ArrayList<Cell> getRow(int row) {
        return null;
    }

    @Override
    public ArrayList<Cell> getColumn(int column) {
        return null;
    }

    @Override
    public void insertRowBefore(int row) {
        shiftRows(row);
        CellsRow newRow = new CellsRow();
        rows.put(row - 1, newRow);
    }

    @Override
    public void insertRowAfter(int row) {
        shiftRows(row + 1);
        CellsRow newRow = new CellsRow();
        rows.put(row + 1, newRow);
    }

    @Override
    public void insertColumnBefore(int column) {
        shiftColumnsRight(column);
    }

    @Override
    public void insertColumnAfter(int column) {
        shiftColumnsRight(column + 1);
    }

    @Override
    public int getRows() {
        return maxRowIndex;
    }

    @Override
    public int getColumns() {
        return maxColumnIndex;
    }

    private boolean isCellExists(int row, int column) {
        return false;
    }

    private void shiftRows(int fromRow) {
        shiftRows(fromRow, 1);
    }

    private void shiftRows(int fromRow, int by) {
        SortedSet<Integer> keys = new TreeSet<Integer>( rows
                .keySet()
                .stream()
                .filter(key -> key.intValue() >= fromRow)
                .collect(Collectors.toSet()));
        for(Iterator<Integer> it = ((TreeSet)keys).descendingIterator(); it.hasNext();) {
            Integer key = it.next();
            rows.put(Integer.valueOf(key.intValue() + by), rows.remove(key));
        }
    }

    private void shiftColumnsRight(int column) {
        shiftColumnsRight(column, 1);
    }

    private void shiftColumnsRight(int column, int by) {
        for(Iterator<CellsRow> it = rows.values().iterator(); it.hasNext();) {
            CellsRow row = it.next();
            row.shiftCellsToRightAfter(column, by);
        }
    }

    private CellsRow getRowInternal(int rowIndex) {
        Integer rowKey = Integer.valueOf(rowIndex);
        CellsRow row = null;

        if (rows.containsKey(rowKey)) {
            row = rows.get(rowKey);
        } else {
            row = new CellsRow();
            rows.put(rowKey, row);
        }
        return row;
    }
}
