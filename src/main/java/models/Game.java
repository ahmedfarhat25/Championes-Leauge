package models;

public class Game {
    private String location;
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;

    public Game(String location, Team homeTeam, Team awayTeam) {
        this.location = location;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public void setScore(int home, int away) {
        this.homeScore = home;
        this.awayScore = away;
    }

    public Team getWinner() {
        return homeScore > awayScore ? homeTeam : awayTeam;
    }
}
