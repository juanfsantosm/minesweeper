package com.deviget.controller;

import com.deviget.NewGameRequest;
import com.deviget.data.Global;
import com.deviget.exception.BuildGridException;
import com.deviget.impl.DefaultGame;
import com.deviget.impl.MinedCell;
import com.deviget.model.Cell;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.persistence.CellEntity;
import com.deviget.persistence.CellStatus;
import com.deviget.persistence.CellType;
import com.deviget.persistence.GameEntity;
import com.deviget.persistence.GameRepository;
import com.deviget.persistence.GameStatus;
import com.service.GridService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Controller("/games")
public class GameController {
    @Inject
    Global data;

    @Inject
    GridService gridService;

    @Inject
    GameRepository gameRepository;

    @Post(produces = MediaType.APPLICATION_JSON, value = "/new", consumes = MediaType.APPLICATION_JSON)
    public Grid startGame(NewGameRequest newGameRequest) {        
        Grid grid = null;
        try {
            Game game = new DefaultGame();
            grid = gridService.buildGrid(game, newGameRequest.getRows(), newGameRequest.getColumns(), newGameRequest.getMinedCells());
            game.setGrid(grid);
            data.runningGames.add(game);

            GameEntity gameEntity = new GameEntity();
            gameEntity.setGameStatus(GameStatus.CREATED);
            gameEntity.setPlayerName(newGameRequest.getPlayerId());
            gameEntity.setStartedOn(new Date());
            
            for (int i = 0; i < game.getGrid().getCells().length; i++) {
                for (int j = 0; j < game.getGrid().getCells()[i].length; j++) {
                    Cell cell = game.getGrid().getCells()[i][j];
                    CellEntity cellEntity = new CellEntity();
                    cellEntity.setGameEntity(gameEntity);
                    cellEntity.setX(cell.getCellPosition().getX());
                    cellEntity.setY(cell.getCellPosition().getY());
                    cellEntity.setStatus(CellStatus.COVERED);
                    cellEntity.setType(MinedCell.class.isAssignableFrom(cell.getClass()) ?  CellType.MINED : CellType.HARMLESS );
                }
            }

            gameRepository.save(gameEntity);

        } catch (BuildGridException e) {
            e.printStackTrace();
        }
        return grid;
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/list/{playerId}")
    public List<Game> listGames(@PathVariable("playerId") String playerId) {
        return data.runningGames.stream().filter(r -> r.getPlayerId().equalsIgnoreCase(playerId)).collect(Collectors.toList());
    }

    /**
     * <p>
     * Find and respond with all saved games.
     * </p>
     * @return
     */
    @Get(produces = MediaType.APPLICATION_JSON, value = "/listsaved")
    public Iterable<GameEntity> listGames() {
        return gameRepository.findAll();
    }

    public Game loadGame(long id) {
        return null;
    }

}
