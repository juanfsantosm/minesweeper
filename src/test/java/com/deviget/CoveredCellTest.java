package com.deviget;

import com.deviget.impl.DefaultGrid;
import com.deviget.impl.HarmlessCell;
import com.deviget.impl.MinedCell;
import com.deviget.model.Cell;
import com.deviget.model.CellPosition;
import com.deviget.model.Grid;
import com.deviget.statepattern.CoveredCell;
import com.deviget.statepattern.UncoveredCell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoveredCellTest {
    Grid grid;

    Cell[][] cells = new Cell[5][5];
    CellPosition[] minedPositions = new CellPosition[]{new CellPosition(1, 3), new CellPosition(4,2)};

    @BeforeEach
    public void before() {
        grid = new DefaultGrid(cells, new ArrayList<>(), null);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new HarmlessCell(grid, i, j);
            }
        }
        
        for (int i = 0; i < minedPositions.length; i++) {
            int minedX = minedPositions[i].getX();
            int minedY = minedPositions[i].getY();
            cells[minedX][minedY] = new MinedCell(grid, minedPositions[i]);
        }
    }

    @Test
    public void testGetAdjacent() {
        Cell cell = cells[1][1];
        List<Cell> adjacents = new CoveredCell(cell).getAdjacent(cell);
        assertTrue(contains(adjacents, 0, 0));
        assertTrue(contains(adjacents, 0, 1));
        assertTrue(contains(adjacents, 0, 2));
        assertTrue(contains(adjacents, 1, 0));
        assertTrue(contains(adjacents, 1, 2));
        assertTrue(contains(adjacents, 2, 0));
        assertTrue(contains(adjacents, 2, 1));
        assertTrue(contains(adjacents, 2, 2));
    }

    @Test
    public void testCountMinedNeighbours(){
        grid.uncoverCell(0, 0);
        // |C|C|C|C|C|      |0|0|1|C|C|
        // |C|C|C|K|C|      |0|0|1|M|C|
        // |C|C|C|C|C| ==>  |0|0|1|C|C|
        // |C|C|C|C|C|      |U|U|U|U|U|
        // |C|C|C|C|C|      |U|U|U|U|U|
        
        assertEquals(0, grid.getCellAt(0,0).getAdjacentMinedCells());
        assertEquals(0, grid.getCellAt(0,1).getAdjacentMinedCells());
        assertEquals(0, grid.getCellAt(1,0).getAdjacentMinedCells());
        // cells in column 2 and rows 0,1,2 have 1 mined neighbour
        assertEquals(1, grid.getCellAt(0,2).getAdjacentMinedCells());
        assertEquals(1, grid.getCellAt(1,2).getAdjacentMinedCells());
        assertEquals(1, grid.getCellAt(2,2).getAdjacentMinedCells());
    }

    @Test
    public void testUncover() {
        grid.uncoverCell(0, 0);
        //
        // |C|C|C|C|C|      |0|0|1|C|C|
        // |C|C|C|K|C|      |0|0|1|M|C|
        // |C|C|C|C|C| ==>  |0|0|1|C|C|
        // |C|C|C|C|C|      |U|U|U|U|U|
        // |C|C|C|C|C|      |U|U|U|U|U|
        System.out.println(((DefaultGrid)grid).statesToString());
        assertTrue(grid.getCellAt(0, 0).getCellState().getClass().equals(UncoveredCell.class));
        assertTrue(grid.getCellAt(0, 1).getCellState().getClass().equals(UncoveredCell.class));
        assertTrue(grid.getCellAt(1, 0).getCellState().getClass().equals(UncoveredCell.class));
        assertTrue(grid.getCellAt(1, 1).getCellState().getClass().equals(UncoveredCell.class));

        assertTrue(grid.getCellAt(1, 3).getCellState().getClass().equals(CoveredCell.class));
        assertTrue(grid.getCellAt(1, 2).getCellState().getClass().equals(CoveredCell.class));
        assertTrue(grid.getCellAt(2, 2).getCellState().getClass().equals(CoveredCell.class));
        assertTrue(grid.getCellAt(1, 3).getCellState().getClass().equals(CoveredCell.class));
    }

    private boolean contains(List<Cell> cells, int x, int y) {
        for (Cell cell : cells) {
            if (cell.getCellPosition().getX() == x && cell.getCellPosition().getY() == y) {
                return true;
            }
        }
        return false;
    }
}
