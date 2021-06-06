package com.deviget.exception;

public class BuildGridException extends Exception {
    int rowCount; 
    int columnCount;
    int minedCells;

    public BuildGridException(String message, int rowCount, int columnCount, int minedCells) {
        super(message);
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.minedCells = minedCells;
    }
}
