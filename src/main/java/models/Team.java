package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private Record record;
    private List<Player> players;
    private Player captain;
    private Coach coach;

    // Full constructor
    public Team(String name, Record record, List<Player> players, Player captain, Coach coach) {
        this.name = name;
        this.record = record;
        this.players = players;
        this.captain = captain;
        this.coach = coach;
    }

    // Overloaded constructor (if players, captain, and coach are optional)
    public Team(String name, Record record) {
        this(name, record, new ArrayList<>(), null, null);
    }

    public String getName() {
        return name;
    }

    public Record getRecord() {
        return record;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCaptain() {
        return captain;
    }

    public Coach getCoach() {
        return coach;
    }
}
