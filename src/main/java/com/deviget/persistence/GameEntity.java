package com.deviget.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class GameEntity {

    private Long id;
    private String playerName;
    private Date startedOn;
    private GameStatus gameStatus;
    private Set<CellEntity> cellEntities;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany
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
}
