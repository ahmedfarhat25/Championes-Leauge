package models;

public class Game {
    private String location;
    private Team teamA;
    private Team teamB;
    private Score score;

    public Game(String location, Team teamA, Team teamB) {
        this.location = location;
        this.teamA = teamA;
        this.teamB = teamB;
        this.score = new Score(0, 0);
    }

    public String getLocation() {
        return location;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(int a, int b) {
        this.score = new Score(a, b);
    }

    public static class Score {
        private int a;
        private int b;

        public Score(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }
}