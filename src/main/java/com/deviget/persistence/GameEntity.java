package com.deviget.persistence;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;;

@Entity
public class GameEntity implements Serializable {

    private Long id;
    private String playerName;
    private Date startedOn;
    private GameStatus gameStatus;
    private Set<CellEntity> cellEntities = new HashSet<>();
    private int rows;
    private int columns;
    private int mined;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Date getStartedOn() {
        return startedOn;
    }

    @Enumerated(EnumType.ORDINAL)
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    public Set<CellEntity> getCellEntities() {
        return cellEntities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setCellEntities(Set<CellEntity> cellEntities) {
        this.cellEntities = cellEntities;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getMined() {
        return mined;
    }

    public void setMined(int mined) {
        this.mined = mined;
    }

}
