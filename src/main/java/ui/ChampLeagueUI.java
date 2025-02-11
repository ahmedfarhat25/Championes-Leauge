package ui;
import java.awt.BorderLayout;
import models.*;
import enums.*;
import services.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import models.Record;
import java.util.List;
public class ChampLeagueUI extends JFrame {
    private ChampLeague league;
    private JTextArea outputArea;

    public ChampLeagueUI(ChampLeague league) {
        this.league = league;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sector League Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton viewTeamsButton = new JButton("View Teams");
        JButton viewGamesButton = new JButton("View Games");
        JButton simulateGameButton = new JButton("Simulate Game");

        buttonPanel.add(viewTeamsButton);
        buttonPanel.add(viewGamesButton);
        buttonPanel.add(simulateGameButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        viewTeamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTeams();
            }
        });

        viewGamesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewGames();
            }
        });

        simulateGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateGame();
            }
        });
    }

    private void viewTeams() {
        outputArea.setText("");
        for (Team team : league.getTeamList()) {
            outputArea.append("Team: " + team.getName() + "\n");
            outputArea.append("Coach: " + team.getCoach().getName() + "\n");
            outputArea.append("Captain: " + team.getCaptain().getName() + "\n");
            outputArea.append("Players:\n");
            for (Player player : team.getPlayers()) {
                outputArea.append(" - " + player.getName() + " (" + player.getPosition() + ")\n");
            }
            outputArea.append("\n");
        }
    }

    private void viewGames() {
        outputArea.setText("");
        league.printGames();
    }

    private void simulateGame() {
        // Example: Simulate a game between the first two teams
        if (league.getTeamList().size() >= 2) {
            Team teamA = league.getTeamList().get(0);
            Team teamB = league.getTeamList().get(1);
            Game game = new Game("Stadium", teamA, teamB);
            game.setScore((int) (Math.random() * 5), (int) (Math.random() * 5));
            league.getGameList().add(game);
            outputArea.setText("Simulated game: " + teamA.getName() + " vs " + teamB.getName() + "\n");
            outputArea.append("Score: " + game.getScore().getA() + " - " + game.getScore().getB() + "\n");
        } else {
            outputArea.setText("Not enough teams to simulate a game.\n");
        }
    }

    public static void main(String[] args) {
        // Create teams and league
        Player deBruyne = new Player("Kevin De Bruyne", "Belgium", 17, Position.CM);
        Player haaland = new Player("Erling Haaland", "Norway", 9, Position.CF);
        Coach guardiola = new Coach("Pep Guardiola", "Spain", AccLevel.HIGH, 15);
        Team manCity = new Team("Manchester City", new Record(0, 0, 0),
                Arrays.asList(deBruyne, haaland), deBruyne, guardiola);

        Player benzema = new Player("Karim Benzema", "France", 9, Position.CF);
        Player vinicius = new Player("Vinicius Jr.", "Brazil", 20, Position.LW);
        Coach ancelotti = new Coach("Carlo Ancelotti", "Italy", AccLevel.HIGH, 20);
        Team realMadrid = new Team("Real Madrid", new Record(0, 0, 0),
                Arrays.asList(benzema, vinicius), benzema, ancelotti);

        ChampLeague league = new ChampLeague(Arrays.asList(manCity, realMadrid), Arrays.asList());

        // Create and show UI
        SwingUtilities.invokeLater(() -> {
            ChampLeagueUI ui = new ChampLeagueUI(league);
            ui.setVisible(true);
        });
    }
}
