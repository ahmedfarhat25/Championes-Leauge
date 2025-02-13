package models;

public class Record {
    private int wins;
    private int draws;
    private int losses;
    private int goalsFor;
    private int goalsAgainst;

    public Record(int wins, int draws, int losses) {
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
    }

    public void update(int goalsFor, int goalsAgainst) {
        this.goalsFor += goalsFor;
        this.goalsAgainst += goalsAgainst;
        if (goalsFor > goalsAgainst) {
            wins++;
        } else if (goalsFor < goalsAgainst) {
            losses++;
        } else {
            draws++;
        }
    }

    public int getPoints() {
        return (wins * 3) + draws;
    }

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    @Override
    public String toString() {
        return "W: " + wins + " D: " + draws + " L: " + losses +
                " GF: " + goalsFor + " GA: " + goalsAgainst +
                " GD: " + getGoalDifference() + " Pts: " + getPoints();
    }

    public void reset() {
        wins = 0;
        draws = 0;
        losses = 0;
        goalsFor = 0;
        goalsAgainst = 0;
    }
}
