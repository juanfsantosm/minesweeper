package com.deviget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.inject.Inject;

import com.deviget.exception.BuildGridException;
import com.deviget.model.Grid;
import com.service.GridService;

import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
public class GridServiceTest {

    @Inject
    GridService gridService;
    
    @Test
    public void testBuildGrid() throws BuildGridException {
        Grid grid = gridService.buildGrid(6, 5, 5);

        assertEquals(30, grid.getTotalCells());
        assertEquals(5, grid.getTotalMinedCells());
        assertEquals(25, grid.getTotalHarmlessCells());
        assertEquals(30, grid.getTotalCoveredCells());
        assertEquals(0, grid.getTotalUncoveredCells());
        assertEquals(6, grid.getRowcount());
        assertEquals(5, grid.getColumncount());
    }

    @Test
    public void testInvalidNumberMinedCells() {
        assertThrows(BuildGridException.class, () -> {
            Grid grid = gridService.buildGrid(3, 3, 20);
        });
    }

    @Test
    public void testCoverCell() throws BuildGridException{
        Grid grid = gridService.buildGrid(6, 5, 5);

        gridService.uncoverCellAt(grid, 2, 2);

        
    } 
}
