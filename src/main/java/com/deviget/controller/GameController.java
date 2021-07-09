package com.deviget.controller;

import com.deviget.NewGameRequest;
import com.deviget.data.Global;
import com.deviget.exception.BuildGridException;
import com.deviget.impl.DefaultGame;
import com.deviget.impl.MinedCell;
import com.deviget.model.Cell;
import com.deviget.model.Game;
import com.deviget.model.Grid;
import com.deviget.model.Player;
import com.deviget.persistence.CellEntity;
import com.deviget.persistence.CellStatus;
import com.deviget.persistence.CellType;
import com.deviget.persistence.GameEntity;
import com.deviget.persistence.GameRepository;
import com.deviget.persistence.GameStatus;
import com.service.GridService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameStatus(GameStatus.CREATED);
        gameEntity.setPlayerName(newGameRequest.getPlayerId());
        gameEntity.setStartedOn(new Date());
        gameEntity.setRows(newGameRequest.getRows());
        gameEntity.setColumns(newGameRequest.getColumns());
        gameEntity.setMined(newGameRequest.getMinedCells());
        gameRepository.save(gameEntity);
        return grid;
    }

    @Get(produces = MediaType.APPLICATION_JSON, value = "/list/{playerId}")
    public List<Game> listGames(@PathVariable("playerId") String playerId) {
        return data.runningGames.stream().filter(r -> r.getPlayerId().equalsIgnoreCase(playerId))
                .collect(Collectors.toList());
    }

    /**
     * <p>
     * Find and respond with all saved games.
     * </p>
     * 
     * @return
     */
    @Get(produces = MediaType.APPLICATION_JSON, value = "/listsaved")
    public List<GameEntity> listGames() {
        List<GameEntity> all = new ArrayList<>();
        Iterable<GameEntity> iterable = gameRepository.findAll();
        Iterator<GameEntity> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            GameEntity next = iterator.next();
            all.add(next);
            System.out.println(next.getId());
        }

        return all;
    }

    @Get(value = "/load/{id}")
    public Game loadGame(long id) {
        GameEntity entity = gameRepository.findById(id).get();
        Game game = new DefaultGame(id);
        int ixof = data.runningGames.indexOf(new DefaultGame(id));

        System.out.println("Entity.status " + entity.getGameStatus());

        Grid grid = null;

        if (0 <= ixof) {
            game = data.runningGames.get(ixof);
            return game;
        } else {

            switch (entity.getGameStatus()) {
                case CREATED: {
                    try {
                        grid = gridService.buildGrid(game, entity.getRows(), entity.getColumns(), entity.getMined());
                    } catch (BuildGridException e) {
                        e.printStackTrace();
                    }

                    game.setGrid(grid);

                    for (int i = 0; i < game.getGrid().getCells().length; i++) {
                        for (int j = 0; j < game.getGrid().getCells()[i].length; j++) {
                            Cell cell = game.getGrid().getCells()[i][j];
                            CellEntity cellEntity = new CellEntity();
                            cellEntity.setX(cell.getCellPosition().getX());
                            cellEntity.setY(cell.getCellPosition().getY());
                            cellEntity.setStatus(CellStatus.COVERED);
                            cellEntity.setType(MinedCell.class.isAssignableFrom(cell.getClass()) ? CellType.MINED
                                    : CellType.HARMLESS);

                            entity.getCellEntities().add(cellEntity);
                        }
                    }

                    game.setState(GameStatus.PLAYING);
                    data.runningGames.add(game);

                    break;
                }
                case PLAYING:
                case PAUSED: {
                    grid = gridService.loadGrid(entity);
                    break;
                }
                case WON:
                case LOST:
                default:
            }
        }

        game.setGrid(grid);

        return game;
    }

    @Delete(value = "/{id}", produces = MediaType.TEXT_PLAIN)
    public String delete(@PathVariable("id") long id) {
        gameRepository.deleteById(id);
        return "ok";
    }

    // Grid related paths

    @Post(produces = MediaType.APPLICATION_JSON, value = "/{gameId}")
    public Game getGrid(@PathVariable("gameId") long gameId) {
        return data.runningGames.get(data.runningGames.indexOf(new DefaultGame(gameId)));
    }

    @Post(produces = MediaType.APPLICATION_JSON, value = "/{gameId}/uncover/{x}/{y}")
    public Game uncoverCell(@PathVariable("gameId") long gameId, @PathVariable("x") int x, @PathVariable("y") int y) {
        Game game = data.runningGames.get(data.runningGames.indexOf(new DefaultGame(gameId)));
        gridService.uncoverCellAt(game.getGrid(), x, y);
        return game;
    }

    @Post(produces = MediaType.APPLICATION_JSON, value = "/{gameId}/questionmark/{x}/{y}")
    public Game questionmarkCell(@PathVariable("gameId") long gameId, @PathVariable("x") int x,
            @PathVariable("y") int y) {
        Game game = data.runningGames.get(data.runningGames.indexOf(new DefaultGame(gameId)));
        gridService.questionMarkCellAt(game.getGrid(), x, y);
        return game;
    }

    @Post(produces = MediaType.APPLICATION_JSON, value = "/{gameId}/redflag/{x}/{y}")
    public Game redflagCell(@PathVariable("gameId") long gameId, @PathVariable("x") int x, @PathVariable("y") int y) {
        Game game = data.runningGames.get(data.runningGames.indexOf(new DefaultGame(gameId)));
        gridService.redFlagCellAt(game.getGrid(), x, y);
        return game;
    }

}
