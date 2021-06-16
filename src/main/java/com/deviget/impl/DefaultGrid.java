package com.deviget.impl;

import com.deviget.model.Cell;
import com.deviget.model.CellPosition;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.statepattern.CoveredCell;
import com.deviget.statepattern.UncoveredCell;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultGrid implements Grid {
    private long id;
    private Cell[][] cells;
    private Game game;
    private int coveredCells;
    private int minedCells;
    private int harmlessCells;
    private int uncoveredCells;
    private List<CellPosition> minedPositions = new ArrayList<>();
    private List<CellPosition> redFlaggedPositions = new ArrayList<>();
    private List<CellPosition> questionMakedPositions = new ArrayList<>();
    private List<CellPosition> uncoveredPositions = new ArrayList<>();

    public DefaultGrid(Cell[][] cells, List<CellPosition> minedPositions, Game game) {
        setId(System.currentTimeMillis());
        this.cells = cells;
        this.minedPositions = minedPositions;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public int getRowcount() {
        return cells.length;
    }

    @Override
    public int getColumncount() {
        return cells[0].length;
    }

    @Override
    public int getTotalCells() {
        return cells.length * cells[0].length;
    }

    @Override
    public int getTotalMinedCells() {
        return minedPositions.size();
    }

    @Override
    public int getTotalHarmlessCells() {
        int totalHarmlessCells = 0;
        for (Cell[] cell : getCells()) {
            for (Cell cell1 : cell) {
                if (cell1.getClass().equals(HarmlessCell.class)) {
                    totalHarmlessCells++;
                }
            }
        }
        return totalHarmlessCells;
    }

    @Override
    public int getTotalCoveredCells() {
        return getCoveredPositions().size();
    }

    @Override
    public int getTotalUncoveredCells() {
        return uncoveredPositions.size();
    }

    @JsonIgnore
    @Override
    public Cell getCellAt(int x, int y) {
        return getCells()[x][y];
    }

    @JsonIgnore
    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public List<CellPosition> getMinedPositions() {
        return minedPositions;
    }

    @Override
    public List<CellPosition> getQuestionMarkedPositions() {
        return questionMakedPositions;
    }

    @Override
    public List<CellPosition> getRedFlaggedPositions() {
        return redFlaggedPositions;
    }

    public List<CellPosition> getUncoveredPositions() {
        List<CellPosition> uncoveredPositions = new ArrayList<>();
        for (Cell[] cell : getCells()) {
            for (Cell cell1 : cell) {
                if (cell1.getCellState().getClass().equals(UncoveredCell.class)) {
                    uncoveredPositions.add(cell1.getCellPosition());
                }
            }
        }
        return uncoveredPositions;
    }

    @Override
    public List<CellPosition> getCoveredPositions() {
        List<CellPosition> uncoveredPositions = new ArrayList<>();
        for (Cell[] cell : getCells()) {
            for (Cell cell1 : cell) {
                if (cell1.getCellState().getClass().equals(CoveredCell.class)) {
                    uncoveredPositions.add(cell1.getCellPosition());
                }
            }
        }
        return uncoveredPositions;
    }

    @Override
    public List<CellPosition> getHarmelessPositions() {
        List<CellPosition> harmlessPositions = new ArrayList<>();
        for (Cell[] cell : getCells()) {
            for (Cell cell1 : cell) {
                if (cell1.getClass().equals(HarmlessCell.class)) {
                    harmlessPositions.add(cell1.getCellPosition());
                }
            }
        }
        return harmlessPositions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!Objects.isNull(getCells()) && getCells().length > 0) {
            for (int i = 0; i < getCells().length; i++) {
                for (int j = 0; j < getCells()[i].length; j++) {
                    stringBuilder.append("|" + getCells()[i][j].getClass().getSimpleName().substring(0, 1).toUpperCase());
                }
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public String statesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!Objects.isNull(getCells()) && getCells().length > 0) {
            for (int i = 0; i < getCells().length; i++) {
                for (int j = 0; j < getCells()[i].length; j++) {
                    stringBuilder.append("|" + getCells()[i][j].getCellState().getClass().getSimpleName().substring(0, 1).toUpperCase());
                }
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void coverCell(int x, int y) {
        Cell cell = getCellAt(x, y);
        cell.getCellState().coverCell();
    }

    @Override
    public void uncoverCell(int x, int y) {
        Cell cell = getCellAt(x, y);
        cell.getCellState().uncoverCell();
    }

    @Override
    public void redFlagCell(int x, int y) {
        Cell cell = getCellAt(x, y);
        cell.getCellState().redFlagCell();
    }

    @Override
    public void questionMarkCell(int x, int y) {
        Cell cell = getCellAt(x, y);
        cell.getCellState().questionMarkCell();
    }

}
