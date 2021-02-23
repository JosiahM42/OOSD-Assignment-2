package Assignment;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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

    public Button calculateSubmit;

    public ObservableList<Teams> selectionTeams = FXCollections.observableArrayList();
    public ObservableList<Teams> homeTeams = FXCollections.observableArrayList();
    public ObservableList<Teams> awayTeams = FXCollections.observableArrayList();

    public List<String> matches = new ArrayList<>();
    public List<String> stats = new ArrayList<>();

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

    public String generateMatches()
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
        return "";
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

        // System.out.println(listOfTeams.get(0).getPlayer());

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

    public void playersOnChangeEvent(ActionEvent event)
    {
        ComboBox<Teams> teamBox = (ComboBox<Teams>) event.getSource();
        Teams homeTeam = teamBox.getValue();
        for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++) {
            if (selectionTeams.get(numTeams).equals(homeTeam))
            {
                playerHomeA.setItems(selectionTeams.get(numTeams).getPlayer());
                //playerHomeA.getSelectionModel();
                System.out.println("yes");
                break;
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectTeam.setItems(selectionTeams);
        selectTeam.getSelectionModel();

        homeTeamX.setItems(homeTeams);
        //homeTeamX.setItems(selectionTeams);
        homeTeamX.getSelectionModel();

        awayTeamX.setItems(awayTeams);
//        awayTeamX.getItems().remove(homeTeamX.getValue());
        //awayTeamX.getSelectionModel();

        //An event listener, this checks what team has been selected in homeTeamX
        awayTeamX.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Teams>() {
            @Override
            public void changed(ObservableValue<? extends Teams> observable, Teams oldValue, Teams newValue) {
                Teams homeTeamSelection = homeTeamX.getValue();

                if (awayTeamX.getValue().equals(homeTeamSelection))
                {
                    observable.removeListener(this);
                    System.out.println("working");
                    awayTeamX.getItems().remove(homeTeamSelection);
                }
                else
                {
                    System.out.println("not working");
                }
            }
        });

//        playerHomeA.setItems(homePlayers);
//        //playerHomeA.getSelectionModel();
//
//        playerHomeA.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//                for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++)
//                {
//                    if (selectionTeams.contains(homeTeamX.getValue()))
//                    {
//                        System.out.println("working");
//                        playerHomeA.getItems().add(selectionTeams.get(numTeams).getPlayerString());
////                        homePlayers.add(selectionTeams.get(1).getPlayerString());
//                        break;
//                    }
//
////                    playerHomeA.getItems().add(homePlayers);
//                }
////                System.out.println(homePlayers);
//            }
//        });


//        playerHomeA.setItems();
//        playerHomeA.getSelectionModel();
//
//        playerAwayA.setItems(selectAwayPlayers());
//        playerAwayA.getSelectionModel();

    }

}

