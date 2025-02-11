package models;
import java.util.List;

public class Team {
    private String name;
    private Record record;
    private List<Player> playerList;
    private Player captain;
    private Coach coach;

    public Team(String name, Record record, List<Player> playerList, Player captain, Coach coach) {
        this.name = name;
        this.record = record;
        this.playerList = playerList;
        this.captain = captain;
        this.coach = coach;
    }

    public String getName() {
        return name;
    }

    public Record getRecord() {
        return record;
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public Player getCaptain() {
        return captain;
    }

    public Coach getCoach() {
        return coach;
    }
}