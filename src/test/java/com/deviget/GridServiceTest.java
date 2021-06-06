package com.deviget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import com.deviget.exception.BuildGridException;
import com.deviget.impl.DefaultGridService;
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
        Grid grid = gridService.buildGrid(5, 5, 5);

        assertEquals(25, grid.getTotalCells());
        assertEquals(5, grid.getTotalMinedCells());
        assertEquals(20, grid.getTotalHarmlessCells());
        assertEquals(25, grid.getTotalCoveredCells());
        assertEquals(0, grid.getTotalUncoveredCells());
    }
}
