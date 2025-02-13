package ui;

import models.*;
import services.*;
import models.Record;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class ChampLeagueUI extends JFrame {
    private ChampLeague championsLeague;
    private JTextArea outputArea;
    private JPanel groupsPanel;
    private JPanel knockoutPanel;

    public ChampLeagueUI(ChampLeague championsLeague) {
        this.championsLeague = championsLeague;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("UEFA Champions League Simulator");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Control Panel
        JPanel controlPanel = new JPanel();
        JButton simulateGroupsButton = new JButton("Simulate Group Stage");
        JButton simulateKoButton = new JButton("Simulate Knockout Stage");
        JButton showWinnerButton = new JButton("Show Winner");
        JButton resetButton = new JButton("Reset");

        controlPanel.add(simulateGroupsButton);
        controlPanel.add(simulateKoButton);
        controlPanel.add(showWinnerButton);
        controlPanel.add(resetButton);
        add(controlPanel, BorderLayout.NORTH);

        // Main content panel with tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Groups panel
        groupsPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        initializeGroupsDisplay();
        tabbedPane.addTab("Group Stage", new JScrollPane(groupsPanel));

        // Knockout stage panel
        knockoutPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        tabbedPane.addTab("Knockout Stage", new JScrollPane(knockoutPanel));

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        add(tabbedPane, BorderLayout.CENTER);

        // Button actions
        simulateGroupsButton.addActionListener(this::simulateGroupStage);
        simulateKoButton.addActionListener(this::simulateKnockoutStage);
        showWinnerButton.addActionListener(this::showWinner);
        resetButton.addActionListener(this::resetTournament);
    }

    private void initializeGroupsDisplay() {
        groupsPanel.removeAll();
        for (Group group : championsLeague.getGroups()) {
            JPanel groupPanel = new JPanel(new BorderLayout());
            groupPanel.setBorder(BorderFactory.createTitledBorder(group.getName()));

            String[] columnNames = {"Team", "Points", "GD"};
            Object[][] data = new Object[4][3];

            List<Team> sortedTeams = new ArrayList<>(group.getTeams());
            sortedTeams.sort(Comparator.comparingInt(t -> -t.getRecord().getPoints()));

            for (int i = 0; i < 4; i++) {
                Team team = sortedTeams.get(i);
                data[i][0] = team.getName();
                data[i][1] = team.getRecord().getPoints();
                data[i][2] = team.getRecord().getGoalDifference();
            }

            JTable table = new JTable(data, columnNames);
            table.setEnabled(false);
            groupPanel.add(new JScrollPane(table), BorderLayout.CENTER);
            groupsPanel.add(groupPanel);
        }
        groupsPanel.revalidate();
        groupsPanel.repaint();
    }

    private void updateKnockoutDisplay() {
        knockoutPanel.removeAll();

        // Round of 16
        JPanel round16Panel = createKnockoutRoundPanel("Round of 16",
                championsLeague.getRoundOf16Teams());
        knockoutPanel.add(round16Panel);

        // Quarter-finals
        JPanel quartersPanel = createKnockoutRoundPanel("Quarter-finals",
                championsLeague.getQuarterFinalTeams());
        knockoutPanel.add(quartersPanel);

        // Semi-finals
        JPanel semisPanel = createKnockoutRoundPanel("Semi-finals",
                championsLeague.getSemiFinalTeams());
        knockoutPanel.add(semisPanel);

        // Final
        JPanel finalPanel = createKnockoutRoundPanel("Final",
                championsLeague.getFinalists());
        knockoutPanel.add(finalPanel);

        knockoutPanel.revalidate();
        knockoutPanel.repaint();
    }

    private JPanel createKnockoutRoundPanel(String roundName, List<Team> teams) {
        JPanel panel = new JPanel(new GridLayout(teams.size() / 2, 1));
        panel.setBorder(BorderFactory.createTitledBorder(roundName));

        for (int i = 0; i < teams.size(); i += 2) {
            Team home = teams.get(i);
            Team away = teams.get(i + 1);
            JLabel matchLabel = new JLabel(
                    String.format("%s vs %s", home.getName(), away.getName()));
            panel.add(matchLabel);
        }
        return panel;
    }

    private void simulateGroupStage(ActionEvent e) {
        championsLeague.simulateGroupStage();
        initializeGroupsDisplay();
        outputArea.setText("Group stage matches completed!\n");
    }

    private void simulateKnockoutStage(ActionEvent e) {
        championsLeague.simulateKnockoutStage();
        updateKnockoutDisplay();
        outputArea.append("Knockout stage completed!\n");
    }

    private void showWinner(ActionEvent e) {
        Team winner = championsLeague.getWinner();
        if (winner != null) {
            JOptionPane.showMessageDialog(this,
                    "The Champions League winner is:\n" + winner.getName(),
                    "Tournament Winner",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "The tournament is not completed yet!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void resetTournament(ActionEvent e) {
        championsLeague.reset();
        initializeGroupsDisplay();
        updateKnockoutDisplay();
        outputArea.setText("Tournament reset. Ready for a new simulation.\n");
    }

    public static void main(String[] args) {
        List<Group> groups = initializeGroups();
        ChampLeague championsLeague = new ChampLeague(groups);

        SwingUtilities.invokeLater(() -> {
            ChampLeagueUI gui = new ChampLeagueUI(championsLeague);
            gui.setVisible(true);
        });
    }

    private static List<Group> initializeGroups() {
        List<Group> groups = new ArrayList<>();

        groups.add(new Group("Group A", Arrays.asList(
                createTeam("Bayern Munich"),
                createTeam("Copenhagen"),
                createTeam("Galatasaray"),
                createTeam("Manchester United")
        )));
        groups.add(new Group("Group B", Arrays.asList(
                createTeam("Arsenal"),
                createTeam("PSV Eindhoven"),
                createTeam("Lens"),
                createTeam("Sevilla")
        )));
        groups.add(new Group("Group C", Arrays.asList(
                createTeam("Real Madrid"),
                createTeam("Napoli"),
                createTeam("Braga"),
                createTeam("Union Berlin")
        )));
        groups.add(new Group("Group D", Arrays.asList(
                createTeam("Real Sociedad"),
                createTeam("Inter Milan"),
                createTeam("Benfica"),
                createTeam("Red Bull Salzburg")
        )));
        groups.add(new Group("Group E", Arrays.asList(
                createTeam("Atlético Madrid"),
                createTeam("Lazio"),
                createTeam("Feyenoord"),
                createTeam("Celtic")
        )));
        groups.add(new Group("Group F", Arrays.asList(
                createTeam("Borussia Dortmund"),
                createTeam("Paris Saint-Germain"),
                createTeam("Milan"),
                createTeam("Newcastle United")
        )));
        groups.add(new Group("Group G", Arrays.asList(
                createTeam("Manchester City"),
                createTeam("RB Leipzig"),
                createTeam("Young Boys"),
                createTeam("Red Star Belgrade")
        )));
        groups.add(new Group("Group H", Arrays.asList(
                createTeam("Barcelona"),
                createTeam("Porto"),
                createTeam("Shakhtar Donetsk"),
                createTeam("Antwerp")
        )));

        return groups;
    }

    private static Team createTeam(String name) {
        return new Team(name, new Record(0, 0,0));
    }
}
