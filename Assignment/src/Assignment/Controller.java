package Assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    public Tab adminTab;
    public Tab viewerTab;
    public Tab scoreSheetTab;

    public TextField teamName;
    public Button addTeam;
    public TextField playerName;
    public ComboBox<Teams> selectTeam;

    public Button registerPlayer;
    public Button generateFixtures;
    public Button generateTeamStats;


    public Button viewResults;
    public Button viewStats;
    public Button viewRank;
    public Button viewPreviousScores;
    public TextArea outputDisplay;


    public Button newSheet;
    public Button modifySheet;

    public ComboBox<Teams> homeTeamX;
    public ComboBox<Teams> awayTeamX;

    public ComboBox<String> playerHomeB;
    public ComboBox<String> playerHomeA;
    public ComboBox<String> playerAwayB;
    public ComboBox<String> playerAwayA;


    public TextField singleSetAOne;
    public TextField singleSetATwo;
    public TextField singleSetAThree;

    public TextField singleSetAFive;
    public TextField singleSetAFour;
    public TextField singleSetASix;


    public TextField singleSetBOne;
    public TextField singleSetBTwo;
    public TextField singleSetBThree;

    public TextField singleSetBFour;
    public TextField singleSetBFive;
    public TextField singleSetBSix;


    public TextField doubleSetA;
    public TextField doubleSetB;
    public TextField doubleSetC;

    public TextArea matchTotal;

    public Button calculateSubmit;

    public ObservableList<Teams> selectionTeams = FXCollections.observableArrayList();
    public ObservableList<Teams> homeTeams = FXCollections.observableArrayList();
    public ObservableList<Teams> awayTeams = FXCollections.observableArrayList();
    public ArrayList<Match> scoreSheet = new ArrayList<>();
    public ArrayList<String> teamWins = new ArrayList<>();

    public List<String> matches = new ArrayList<>();
    public List<String> stats = new ArrayList<>();

    //Admin administrator = new Admin();

    public void addTeam()
    {
        Teams teams =  new Teams();

        if (teamName.getText().isEmpty())
        {
            String name = teamName.getText();

            teams.setTeam(name);

            selectionTeams.add(teams);
            homeTeams.add(teams);
            awayTeams.add(teams);
        }
        else
        {
            String name = teamName.getText();

            teams.setTeam(name);

            selectionTeams.add(teams);
            homeTeams.add(teams);
            awayTeams.add(teams);
            teamName.clear();
        }

        selectTeam.setItems(selectionTeams);
        selectTeam.getSelectionModel();
    }

    public void registerNewPlayer() {
        String nameOfPlayer = playerName.getText();

        for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++) {
            if (selectionTeams.get(numTeams).equals(selectTeam.getValue())) {
                if (playerName.getText().isEmpty())
                {
                    selectionTeams.get(numTeams).setPlayer(nameOfPlayer);
                }
                else
                {
                    selectionTeams.get(numTeams).setPlayer(nameOfPlayer);
                }
            }
        }
        playerName.clear();
    }

    public void generateMatches()
    {
        for (int aTeam = 0; aTeam < selectionTeams.size(); aTeam++)
        {
            for (int bTeam = 0; bTeam < selectionTeams.size(); bTeam++)
            {
                if (aTeam != bTeam)
                {
                    Fixtures teamMatches = new Fixtures(selectionTeams.get(aTeam).teamName, selectionTeams.get(bTeam).teamName);
                    teamMatches.setTeamFixtures();
                    matches.add(teamMatches.toString());
                    //System.out.println(matches);
                }

            }
        }
    }

    public void generateTeamStats()
    {
        System.out.println(selectionTeams.size());
        for (int aTeam = 0; aTeam < selectionTeams.size(); aTeam++)
        {
            TeamStats eachTeamsStats = new TeamStats(selectionTeams.get(aTeam).teamName);
            eachTeamsStats.setTotalTeamStats();
            stats.add(eachTeamsStats.toString());
//            System.out.println(eachTeamsStats);
//            System.out.println(eachTeamsStats);
        }
    }

    public void viewFixturesResults()
    {
        if (outputDisplay.getText().isEmpty())
        {
            outputDisplay.setFont(Font.font(20));

            outputDisplay.setText(String.join("", matches));
        }
        else
        {
            outputDisplay.clear();
            outputDisplay.setFont(Font.font(20));
            outputDisplay.setText(String.join("", matches));
        }
    }

    public void viewStats()
    {
        if (outputDisplay.getText().isEmpty())
        {
            outputDisplay.setStyle("-fx-font-alignment: center");
            outputDisplay.setFont(Font.font(12));
            outputDisplay.setText(String.join("\n", stats));
        }
        else
        {
            outputDisplay.clear();
            outputDisplay.setStyle("-fx-font-alignment: center");
            outputDisplay.setFont(Font.font(12));
            outputDisplay.setText(String.join("\n", stats));
        }
    }

    public void playersAtHomeOnChangeEvent(ActionEvent event)
    {
        ComboBox<Teams> teamBox = (ComboBox<Teams>) event.getSource();
        Teams homeTeam = teamBox.getValue();
        for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++) {
            if (selectionTeams.get(numTeams).equals(homeTeam))
            {
                playerHomeA.setItems(selectionTeams.get(numTeams).getPlayer());
                playerHomeB.setItems(selectionTeams.get(numTeams).getPlayer());
                //playerHomeA.getSelectionModel();
                System.out.println("yes");
                break;
            }
        }
    }

    public void playersAwayOnChangeEvent(ActionEvent event)
    {
        ComboBox<Teams> teamBox = (ComboBox<Teams>) event.getSource();
        Teams AwayTeam = teamBox.getValue();
        for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++) {
            if (selectionTeams.get(numTeams).equals(AwayTeam))
            {
                playerAwayA.setItems(selectionTeams.get(numTeams).getPlayer());
                playerAwayB.setItems(selectionTeams.get(numTeams).getPlayer());
                //playerHomeA.getSelectionModel();
                break;
            }
        }
    }

    public void inputScoreSheet()
    {
        Match newMatch = new Match(homeTeamX.getValue(), awayTeamX.getValue(), playerHomeA.getValue(),
                                    playerHomeB.getValue(), playerAwayA.getValue(), playerAwayB.getValue());

        newMatch.inputMatches(singleSetAOne.getText(), singleSetATwo.getText(), singleSetAThree.getText(),
                              singleSetAFour.getText(), singleSetAFive.getText(), singleSetASix.getText(),
                              singleSetBOne.getText(), singleSetBTwo.getText(), singleSetBThree.getText(),
                              singleSetBFour.getText(), singleSetBFive.getText(), singleSetBSix.getText(),
                              doubleSetA.getText(), doubleSetB.getText(), doubleSetC.getText());

        scoreSheet.add(newMatch);
//        for (String wins: newMatch.getMatchWins())
//        teamWins.add(wins);
        matchTotal.setText(newMatch.getMatchTotal());

        System.out.println(scoreSheet.toString());
        System.out.println(newMatch.getMatchWins());
        //System.out.println(teamWins);
    }

    public void generateNewScoreSheet()
    {
        homeTeamX.getSelectionModel().clearSelection();
        awayTeamX.getSelectionModel().clearSelection();

        playerHomeA.valueProperty().set(null);
        playerHomeB.valueProperty().set(null);

        playerAwayA.valueProperty().set(null);
        playerAwayB.valueProperty().set(null);

        singleSetAOne.clear();
        singleSetATwo.clear();
        singleSetAThree.clear();

        singleSetAFour.clear();
        singleSetAFive.clear();
        singleSetASix.clear();

        singleSetBOne.clear();
        singleSetBTwo.clear();
        singleSetBThree.clear();

        singleSetBFour.clear();
        singleSetBFive.clear();
        singleSetBSix.clear();

        doubleSetA.clear();
        doubleSetB.clear();
        doubleSetC.clear();

        matchTotal.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        selectTeam.setItems(selectionTeams);
//        selectTeam.getSelectionModel();

        homeTeamX.setItems(homeTeams);
        homeTeamX.getSelectionModel();

        awayTeamX.setItems(awayTeams);
        awayTeamX.getSelectionModel();
//        //An event listener, this checks what team has been selected in homeTeamX
//        awayTeamX.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Teams>() {
//            @Override
//            public void changed(ObservableValue<? extends Teams> observable, Teams oldValue, Teams newValue) {
//                Teams homeTeamSelection = homeTeamX.getValue();
//
//                if (awayTeamX.getValue().equals(homeTeamSelection))
//                {
//                    observable.removeListener(this);
//                    awayTeamX.getItems().remove(homeTeamSelection);
//                }
//            }
//        });
    }

}

