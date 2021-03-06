package com.deviget.statepattern;

import java.util.ArrayList;
import java.util.List;

import com.deviget.impl.MinedCell;
import com.deviget.model.Cell;
import com.deviget.model.CellPosition;
import com.deviget.model.CellState;
import com.deviget.model.Grid;
import com.deviget.persistence.GameStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CoveredCell implements CellState {

    private Cell cell;

    public CoveredCell(Cell cell) {
        this.cell = cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @JsonIgnore
    public Cell getCell() {
        return cell;
    }

    @Override
    public void coverCell() {
        // do nothing
    }

    @Override
    public void uncoverCell() {
        if (MinedCell.class.isAssignableFrom(getCell().getClass())) {
            // end of game
            getCell().getGrid().getGame().end(GameStatus.LOST);
        } else {
            getCell().setCellState(new UncoveredCell(cell));
            uncoverAdjacent(cell);
            for (CellPosition cp : getCell().getGrid().getHarmelessPositions()) {
                if (!getCell().getGrid().getCellAt(cp.getX(), cp.getY()).getCellState().getClass().equals(UncoveredCell.class)) {
                    return;
                }
            }

            getCell().getGrid().getGame().end(GameStatus.WON);
        }
    }

    /**
     * <p>
     * Uncover adjacent cells if and only if they are in UncoveredState.
     * If at least one neighbour cell is mined, stop the uncovering.
     * </p>
     *
     * @param cell
     */
    private void uncoverAdjacent(Cell cell) {
        // calculate adjacent harmless cells!
        List<Cell> adjacent = getAdjacent(cell);

        int adjacentMinedCells = 0;

        for (Cell cell1 : adjacent) {
            if (MinedCell.class.equals(cell1.getClass())) {
                adjacentMinedCells ++;
            }
        }

        cell.setAdjacentMinedCells(adjacentMinedCells);

        // stop discovering adjacent cells once we found a mined neighbour
        if (0 < adjacentMinedCells) {
            return;
        }

        for (Cell cell1 : adjacent) {
            System.out.println(cell1.getCellPosition() + " -> " + cell1.getCellState().getClass().getSimpleName());
            if (CoveredCell.class.equals(cell1.getCellState().getClass())) {
                cell1.getCellState().uncoverCell();
            }
        }
    }

    /**
     * <p>
     * Find adjacent 8 Cells.
     * </p>
     *
     * @param cell
     * @return
     */
    public List<Cell> getAdjacent(Cell cell) {
        Grid grid = cell.getGrid();
        List<Cell> cells = new ArrayList<>();

        int x = cell.getCellPosition().getX();
        int y = cell.getCellPosition().getY();
        int maxX = cell.getGrid().getRowcount();
        int maxY = cell.getGrid().getColumncount();

        if (x - 1 >= 0 && y - 1 >= 0) {
            Cell northWest = grid.getCellAt(x - 1, y - 1);
            cells.add(northWest);
        }
        if (y - 1 >= 0) {
            Cell north = grid.getCellAt(x, y - 1);
            cells.add(north);
        }
        if (x + 1 < maxX && y - 1 >= 0) {
            Cell northEast = grid.getCellAt(x + 1, y - 1);
            cells.add(northEast);
        }
        if (x - 1 >= 0) {
            Cell west = grid.getCellAt(x - 1, y);
            cells.add(west);
        }
        if (x + 1 < maxX) {
            Cell east = grid.getCellAt(x + 1, y);
            cells.add(east);
        }
        if (x - 1 >= 0 && y + 1 < maxY) {
            Cell southWest = grid.getCellAt(x - 1, y + 1);
            cells.add(southWest);
        }
        if (y + 1 < maxY) {
            Cell south = grid.getCellAt(x, y + 1);
            cells.add(south);
        }
        if (x + 1 < maxX && y + 1 < maxY) {
            Cell southEast = grid.getCellAt(x + 1, y + 1);
            cells.add(southEast);
        }

        return cells;
    }

    @Override
    public void redFlagCell() {
        // TODO Auto-generated method stub
    }

    @Override
    public void questionMarkCell() {
        // TODO Auto-generated method stub
    }

}
