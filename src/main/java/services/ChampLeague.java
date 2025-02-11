package services;
import models.Team;
import models.Game;
import java.util.List;

public class ChampLeague {
    private List<Team> teamList;
    private List<Game> gameList;

    public ChampLeague(List<Team> teamList, List<Game> gameList) {
        this.teamList = teamList;
        this.gameList = gameList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public Team getTeam(String name) {
        for (Team team : teamList) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }

    public Game getGame(Team a, Team b) {
        for (Game game : gameList) {
            if ((game.getTeamA().equals(a) && game.getTeamB().equals(b)) ||
                    (game.getTeamA().equals(b) && game.getTeamB().equals(a))) {
                return game;
            }
        }
        return null;
    }

    public void printGames() {
        for (Game game : gameList) {
            System.out.println("Game at " + game.getLocation() + " between " +
                    game.getTeamA().getName() + " and " + game.getTeamB().getName() +
                    " - Score: " + game.getScore().getA() + ":" + game.getScore().getB());
        }
    }
}