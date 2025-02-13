package models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<Team> teams;
    private List<Game> games;

    public Group(String name, List<Team> teams) {
        this.name = name;
        this.teams = teams;
        this.games = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Game> getGames() {
        return games;
    }

    public void simulateGroupStage() {
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team homeTeam = teams.get(i);
                Team awayTeam = teams.get(j);

                // Simulate home game
                Game homeGame = new Game(homeTeam.getName() + " Stadium", homeTeam, awayTeam);
                int homeScore = (int) (Math.random() * 5);
                int awayScore = (int) (Math.random() * 5);
                homeGame.setScore(homeScore, awayScore);
                homeTeam.getRecord().update(homeScore, awayScore);
                awayTeam.getRecord().update(awayScore, homeScore);
                games.add(homeGame);

                // Simulate away game
                Game awayGame = new Game(awayTeam.getName() + " Stadium", awayTeam, homeTeam);
                homeScore = (int) (Math.random() * 5);
                awayScore = (int) (Math.random() * 5);
                awayGame.setScore(homeScore, awayScore);
                awayTeam.getRecord().update(homeScore, awayScore);
                homeTeam.getRecord().update(awayScore, homeScore);
                games.add(awayGame);
            }
        }
    }

    public List<Team> getTopTwoTeams() {
        // Sort teams by points (descending order)
        teams.sort((t1, t2) -> t2.getRecord().getPoints() - t1.getRecord().getPoints());
        return teams.subList(0, 2); // Return top 2 teams
    }

    public void simulateMatches() {
        simulateGroupStage();
    }

    public void reset() {
        games.clear();
        for (Team team : teams) {
            team.getRecord().reset(); // Assuming Team's Record class has a reset method
        }
    }

}
