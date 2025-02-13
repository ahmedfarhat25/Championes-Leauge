package services;
import models.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChampLeague {
    private List<Group> groups;
    private List<Team> roundOf16Teams;
    private List<Team> quarterFinalTeams;
    private List<Team> semiFinalTeams;
    private List<Team> finalists;
    private Team winner;

    public ChampLeague(List<Group> groups) {
        this.groups = groups;
        this.roundOf16Teams = new ArrayList<>();
        this.quarterFinalTeams = new ArrayList<>();
        this.semiFinalTeams = new ArrayList<>();
        this.finalists = new ArrayList<>();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void simulateGroupStage() {
        for (Group group : groups) {
            group.simulateMatches();
            roundOf16Teams.addAll(group.getTopTwoTeams());
        }
    }

    public List<Team> getRoundOf16Teams() {
        return roundOf16Teams;
    }

    public List<Team> getQuarterFinalTeams() {
        return quarterFinalTeams;
    }

    public List<Team> getSemiFinalTeams() {
        return semiFinalTeams;
    }

    public List<Team> getFinalists() {
        return finalists;
    }

    public void simulateKnockoutStage() {
        quarterFinalTeams = simulateRound(roundOf16Teams);
        semiFinalTeams = simulateRound(quarterFinalTeams);
        finalists = simulateRound(semiFinalTeams);
        winner = simulateRound(finalists).get(0);
    }

    private List<Team> simulateRound(List<Team> teams) {
        List<Team> winners = new ArrayList<>();
        Collections.shuffle(teams);
        for (int i = 0; i < teams.size(); i += 2) {
            winners.add(teams.get(i)); // Simulating a win for team[i]
        }
        return winners;
    }

    public Team getWinner() {
        return winner;
    }

    public void reset() {
        roundOf16Teams.clear();
        quarterFinalTeams.clear();
        semiFinalTeams.clear();
        finalists.clear();
        winner = null;

        for (Group group : groups) {
            group.reset(); // Assuming Group class has a reset method
        }
    }
}
