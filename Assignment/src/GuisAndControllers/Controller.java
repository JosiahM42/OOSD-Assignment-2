package GuisAndControllers;

import Classes.Match;
import Classes.Sets;
import Classes.TeamRank;
import Classes.TeamStats;
import Classes.Teams;
import Classes.Fixtures;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Controller implements Initializable {
    public Tab adminTab;
    public Tab viewerTab;
    public Tab scoreSheetTab;

    public TextField teamName;
    public Button addTeam;
    public Label addTeamResponse;

    public TextField playerName;
    public ComboBox<Teams> selectTeam;

    public Button registerPlayer;
    public Label registerPlayerResponse;

    public Button generateFixtures;
    public Label fixturesResponse;

    public Button generateTeamStats;
    public Label teamStatsResponse;

    public Button viewResults;
    public Button viewStats;
    public Button viewRank;
    public Button viewPreviousScores;
    public ComboBox<Teams> previousHomeTeam;
    public ComboBox<Teams> previousAwayTeam;
    public Label displayLabel;
    public Button findPreviousMatch;

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

    public Rectangle hideScoreSheet;
    public Label existingMatch;
    public Button findMatch;
    public Button calculateModified;

    public int teamAddCounter = 0;
    public ObservableList<Teams> selectionTeams = FXCollections.observableArrayList();
    public ObservableList<Teams> homeTeams = FXCollections.observableArrayList();
    public ObservableList<Teams> awayTeams = FXCollections.observableArrayList();
    public ArrayList<Match> scoreSheet = new ArrayList<>();
    public ArrayList<String> teamWins = new ArrayList<>();

    public List<Fixtures> matchFixtures = new ArrayList<>();
    public List<String> matches = new ArrayList<>();
    public List<String> stats = new ArrayList<>();
    public List<String> rank = new ArrayList<>();


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////The Admin Tab Code/////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
        Function Name: addTeam
        Arguments: ActionEvent addTeamEvent
        Description: Adds a new team to the selectionTeams
                     observable list. The team's name will
                     depend on the name entered in the
                     teamName textField.
     */


    public void addTeam(ActionEvent addTeamEvent)
    {
        // Creates a new object of type Teams everytime the function is run
        Teams teams =  new Teams();

        // This will equal to the addTeam button if this function is run
        // This action event is used for validation purposes
        Button createTeam = (Button) addTeamEvent.getSource();


        // Checks if the teamName text field is empty
        if (!teamName.getText().isEmpty())
        {
            // Stores the contents of the teamName text field
            String name = teamName.getText();

            // Sets the team name according to what the user has entered
            teams.setTeam(name);

            // Stores the object into an Observable List for use during the program
            selectionTeams.add(teams);

            // Checks if the team entered is already in selectionTeams
            addTeamValidation(createTeam, name);

            // This clears the text field
            teamName.clear();
        }
        else // This is run when the text field is clear
        {
            addTeamResponse.setTextFill(Color.web("#FF0000")); // Sets colour to red
            addTeamResponse.setText("Please enter a team name");
        }

        // Updates the selectTeam ComboBox with the recently updated team
        selectTeam.setItems(selectionTeams);
        selectTeam.getSelectionModel(); // Displays the teams in the dropdown of the comboBox
    }


    /*
        Function Name: addTeamValidation
        Arguments: Button createTeam & String elementToCheck
        Description: If the addTeam button has been pressed,
                     this function will check if there are
                     duplicate teams in the selectionTeams
                     array.
     */


    public void addTeamValidation(Button createTeam, String elementToCheck)
    {
        // Checks if the addTeam Button has been pressed
        if (createTeam == addTeam)
        {
            // Increments teamAddCounter
            teamAddCounter++;

            if (teamAddCounter == 1)
            {
                // Makes sure that at least 1 team has been entered
                addTeamResponse.setText("");
                addTeamResponse.setTextFill(Color.web("#00FF00")); // Sets the colour to Green
                addTeamResponse.setText("Team Has been added");
            }
            else
            {
                // Used to look for duplicate teams
                boolean duplicates = false;
                int dupeCount = 0;

                // Loops through selectionTeams to check if the team entered is already in selectionTeams
                for(Teams teamCheck : selectionTeams)
                {
                    if (teamCheck.getTeam().equals(elementToCheck))
                    {
                        dupeCount++;
                        if (dupeCount > 1) // Checks if there is more than one teams with the same name
                        {
                            duplicates = true;
                            break;
                        }

                    }
                }

                if (duplicates == true)
                {
                    addTeamResponse.setTextFill(Color.web("#FF0000")); // Sets colour to red
                    addTeamResponse.setText("Team has already been added");
                    selectionTeams.remove(selectionTeams.size() - 1); // Removes the last added team
                }
                else
                {
                    addTeamResponse.setText("");
                    addTeamResponse.setTextFill(Color.web("#00FF00"));
                    addTeamResponse.setText("Team Has been added");
                }
            }
        }
    }


    /*
        Function Name: registerNewPlayer
        Arguments: N/A
        Description: If the registerPlayer button is pressed,
                     a new player will be added to the team
                     selected in the selectTeam ComboBox.
     */


    public void registerNewPlayer() {
        addTeamResponse.setVisible(false); // Hides the validation message in the add team box

        String nameOfPlayer = playerName.getText(); // Stores the user input entered in the text field

        for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++) {

            // Checks if the selected team is equal to a team in selectionTeams
            if (selectionTeams.get(numTeams).equals(selectTeam.getValue())) {

                // Checks if the textField is empty
                if (!playerName.getText().isEmpty())
                {
                    // Adds the name of the player to the selected team
                    selectionTeams.get(numTeams).setPlayer(nameOfPlayer);

                    // Clears the text field
                    playerName.clear();
                }

                registerPlayerResponse.setText("");
                registerPlayerResponse.setTextFill(Color.web("#00FF00")); // Sets colour to Green

                // Sets the text and states which team the new player has been added to
                registerPlayerResponse.setText("New player added to " + selectionTeams.get(numTeams).getTeam());

                // This is to clear the selected team
                selectTeam.getSelectionModel().clearSelection();
            }
            // Makes sure that both a player name has been entered and a team has been selected
            else if(playerName.getText().isEmpty() && selectTeam.getSelectionModel().isEmpty())
            {
                registerPlayerResponse.setTextFill(Color.web("#FF0000")); // Sets colour to red
                registerPlayerResponse.setText("Please enter a player name and select a team");
            }
            // This stops the user from continuing without selecting a team
            else if (selectTeam.getSelectionModel().isEmpty())
            {
                registerPlayerResponse.setTextFill(Color.web("#FF0000")); // Sets colour to red
                registerPlayerResponse.setText("Please select a team");
            }

        }
    }


    /*
        Function Name: generateMatches
        Arguments: N/A
        Description: If the generateFixtures button has been
                     pressed, this function will create a
                     fixtures table and fill each of the matches
                     with their teams and scores.
     */


    public void generateMatches()
    {
        registerPlayerResponse.setVisible(false); // Makes the label invisible

        // Clears the matches array everytime the function is run
        // This is to remove all pre-existing match information
        matches.clear();

        for (int aTeam = 0; aTeam < selectionTeams.size(); aTeam++) // Loops through a version of selectionTeams
        {
            for (int bTeam = 0; bTeam < selectionTeams.size(); bTeam++) // Loops through another version of selectionTeams
            {

                if (aTeam != bTeam) // Prevents the possibility of the same team being in the same match
                {
                    // Creates an object of type Fixtures and passes the two instances of the selectionTeams array
                    Fixtures teamMatches = new Fixtures(selectionTeams.get(aTeam).teamName,
                                                        selectionTeams.get(bTeam).teamName);

                    // Creates the fixtures and sets the match's score
                    teamMatches.setTeamFixtures(scoreSheet);

                    // Adds the string version of the fixtures class
                    matches.add(teamMatches.toString());
                }

            }
        }
        fixturesResponse.setText("");
        fixturesResponse.setTextFill(Color.web("#00FF00")); // Sets colour to Green
        fixturesResponse.setText("The fixtures have been generated");
    }


    /*
        Function Name: generateTeamStats
        Arguments: N/A
        Description: If the generateTeamStats button has been
                     pressed, this function will create a stat list
                     for each team consisting of their matches won,
                     matches played and sets won. This function will
                     also update each team's stats depending on if
                     they have had a match recently.
     */


    public void generateTeamStats()
    {
        fixturesResponse.setVisible(false); // makes the label invisible

        // Creates object of type TeamStats and passes in the selectionTeams array
        TeamStats eachTeamsStats = new TeamStats(selectionTeams);

        // Generates the team stats for each of the teams depending on their matches
        eachTeamsStats.setTotalTeamStats();

        // Clears the array to refresh current stats
        stats.clear();
        stats.add(eachTeamsStats.toString());

        teamStatsResponse.setText("");
        teamStatsResponse.setTextFill(Color.web("#00FF00")); // Sets colour to Green
        teamStatsResponse.setText("The fixtures have been generated");
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////The Viewer Tab Code////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
        Function Name: viewStats
        Arguments: N/A
        Description: If the viewStats button has been
                     pressed, this function will output
                     the all the teams and their stats
                     that are stored in the stats array.
     */


    public void viewStats()
    {
        // makes the comboboxes for viewing previous scores invisible
        previousHomeTeam.setVisible(false);
        previousAwayTeam.setVisible(false);
        findPreviousMatch.setVisible(false);

        displayLabel.setText("Team Stats");


        if (outputDisplay.getText().isEmpty())
        {
            // Clears the text area so that if another button was pressed before the viewStats button
            // It would clear the text area
            outputDisplay.clear();

            // Sets the position of the text inside of the text area
            outputDisplay.setStyle("-fx-font-alignment: center");
            outputDisplay.setFont(Font.font(12));

            // Outputs the stats array in string format to the text area
            outputDisplay.setText(String.join("", stats));
        }
        else
        {
            outputDisplay.clear();
            outputDisplay.setStyle("-fx-font-alignment: center");
            outputDisplay.setFont(Font.font(12));
            outputDisplay.setText(String.join("", stats));
        }
    }


    /*
        Function Name: generateTeamRank
        Arguments: N/A
        Description: If the viewRank button has been
                     pressed, this function will create
                     a set of rankings for all the league
                     teams depending on how many sets they
                     have won.
    */


    public void generateTeamRank()
    {
        // Makes the ComboBoxes for viewing previous scores invisible
        previousHomeTeam.setVisible(false);
        previousAwayTeam.setVisible(false);
        findPreviousMatch.setVisible(false);
        teamStatsResponse.setVisible(false);

        // Creates an object of type TeamRank and passes selectionTeams as it's constructor argument
        TeamRank eachTeamsRank = new TeamRank(selectionTeams);

        // Calls the setTeamRankings function from the TeamRank class
        eachTeamsRank.setTeamRankings();

        // Clears the rank array to avoid duplication of the team ranking
        rank.clear();
        // Adds the TeamRank class object to the rank array in string form
        rank.add(eachTeamsRank.toString());

        displayLabel.setText("Team Rankings");

        if (outputDisplay.getText().isEmpty())
        {
            // Clears the text area so that if another button was pressed before the viewStats button
            // It would clear the text area
            outputDisplay.setStyle("-fx-font-alignment: center");
            outputDisplay.setFont(Font.font(12));

            // Outputs the rank array in string format to the text area
            outputDisplay.setText(String.join("", rank));
        }
        else
        {
            outputDisplay.clear();
            outputDisplay.setStyle("-fx-font-alignment: center");
            outputDisplay.setFont(Font.font(12));
            outputDisplay.setText(String.join("", rank));
        }
    }


    /*
        Function Name: viewFixturesResults
        Arguments: N/A
        Description: If the viewResults button has been
                     pressed, this function will output
                     the all the team fixtures that are
                     stored in the matches array.
    */


    public void viewFixturesResults()
    {
        // makes the ComboBoxes for viewing previous scores invisible
        previousHomeTeam.setVisible(false);
        previousAwayTeam.setVisible(false);
        findPreviousMatch.setVisible(false);

        displayLabel.setText("Fixtures & Results");

        if (outputDisplay.getText().isEmpty())
        {
            // Sets the size of the font in the text area
            outputDisplay.setFont(Font.font(15));

            // Displays the fixtures stored in matches
            outputDisplay.setText(String.join("", matches));
        }
        else
        {
            outputDisplay.clear();
            outputDisplay.setFont(Font.font(15));
            outputDisplay.setText(String.join("", matches));
        }
    }


    /*
        Function Name: viewFixturesResults
        Arguments: N/A
        Description: If the viewResults button has been
                     pressed, this function will output
                     the all the team fixtures that are
                     stored in the matches array.
    */


    public void viewPreviousScores()
    {
        // These are ComboBoxes and a button which are used specifically
        // when the viewPreviousScores button is pressed
        previousHomeTeam.setVisible(true);
        previousAwayTeam.setVisible(true);
        findPreviousMatch.setVisible(true);

        displayLabel.setText("Previous match");

        // Clears the text area
        outputDisplay.clear();

        // Fills the ComboBoxes with the teams in selection teams
        previousHomeTeam.setItems(selectionTeams);
        previousAwayTeam.setItems(selectionTeams);
    }


    /*
        Function Name: searchPreviousScores
        Arguments: N/A
        Description: If the findPreviousMatch button
                     has been pressed, this function
                     will check if both teams that
                     get selected, have played a match
                     together and if they have, that
                     match will be displayed.
    */


    public void searchPreviousScores()
    {
        // Stores the values selected in the previous team ComboBoxes
        Teams previousHomeValue = previousHomeTeam.getValue();
        Teams previousAwayValue = previousAwayTeam.getValue();

        for (Match previousMatchTeam : scoreSheet)
        {
            // Checks if the selected combo box value is equal
            // to any home team that has played a match
            if (previousMatchTeam.getHomeTeam().equals(previousHomeValue))
            {
                System.out.println("Match with " + previousHomeValue.toString() + " has been found");

                // Checks if the selected combo box value is equal
                // to any away team that has played a match
                if (previousMatchTeam.getAwayTeam().equals(previousAwayValue))
                {
                    System.out.println("Match with both " + previousHomeValue.toString() + " & " +
                            previousAwayValue.toString() + " has been found");

                    // Checks if there is anything within the output
                    // display before displaying the match scores
                    if (outputDisplay.getText().isEmpty())
                    {
                        outputDisplay.clear();
                        outputDisplay.setStyle("-fx-font-alignment: center");
                        outputDisplay.setFont(Font.font(18));
                        outputDisplay.setText(previousMatchTeam.toString());
                    }
                    else
                    {
                        outputDisplay.setStyle("-fx-font-alignment: center");
                        outputDisplay.setFont(Font.font(18));
                        outputDisplay.setText(previousMatchTeam.toString());
                    }
                }
                else // If the selected away team has not versed the selected home team
                {
                    outputDisplay.setStyle("-fx-font-alignment: center");
                    outputDisplay.setFont(Font.font(18));
                    outputDisplay.setText("This Match has not been played yet. Please try again later");
                }
            }
            else // If the selected home team has not played any team as a home team
            {
                outputDisplay.setStyle("-fx-font-alignment: center");
                outputDisplay.setFont(Font.font(18));
                outputDisplay.setText("This Match has not been played yet. Please try again later");
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////The ScoreSheet Tab Code////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


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

    public int counter = 0;

    public void inputScoreSheet(ActionEvent event)
    {
        Match newMatch = new Match(homeTeamX.getValue(), awayTeamX.getValue(), playerHomeA.getValue(),
                                    playerHomeB.getValue(), playerAwayA.getValue(), playerAwayB.getValue());

        newMatch.inputMatches(singleSetAOne.getText(), singleSetATwo.getText(), singleSetAThree.getText(),
                              singleSetAFour.getText(), singleSetAFive.getText(), singleSetASix.getText(),
                              singleSetBOne.getText(), singleSetBTwo.getText(), singleSetBThree.getText(),
                              singleSetBFour.getText(), singleSetBFive.getText(), singleSetBSix.getText(),
                              doubleSetA.getText(), doubleSetB.getText(), doubleSetC.getText());

        scoreSheet.add(newMatch);

        matchTotal.setText(newMatch.getMatchTotal());

        System.out.println(scoreSheet.toString());
        System.out.println(newMatch.getMatchWins());
        //System.out.println(teamWins);

        Button hasPressed = (Button) event.getSource();
        if (hasPressed == calculateSubmit) {
            counter++;
            if (counter == 1) {
                initialStats();
            }
            else if (counter >= 2)
            {
                updateStats();
            }

        }
    }


    public void appendModifiedScoreSheet()
    {
        removeStats();

        Match newMatch = new Match(homeTeamX.getValue(), awayTeamX.getValue(), playerHomeA.getValue(),
                playerHomeB.getValue(), playerAwayA.getValue(), playerAwayB.getValue());

        newMatch.inputMatches(singleSetAOne.getText(), singleSetATwo.getText(), singleSetAThree.getText(),
                singleSetAFour.getText(), singleSetAFive.getText(), singleSetASix.getText(),
                singleSetBOne.getText(), singleSetBTwo.getText(), singleSetBThree.getText(),
                singleSetBFour.getText(), singleSetBFive.getText(), singleSetBSix.getText(),
                doubleSetA.getText(), doubleSetB.getText(), doubleSetC.getText());

        scoreSheet.add(newMatch);
        matchTotal.setText(newMatch.getMatchTotal());

        List<Integer> allIndex = new ArrayList<>();

        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        updateStats();


        for(int findIndex = 0; findIndex < scoreSheet.size(); findIndex++)
        {
            if(scoreSheet.get(findIndex).getHomeTeam().equals(homeTeamBox) &&
                    scoreSheet.get(findIndex).getAwayTeam().equals(awayTeamBox))
            {
                allIndex.add(findIndex);
            }
        }

        int firstInstance = allIndex.get(0);
        scoreSheet.remove(firstInstance);

        System.out.println("\n");
        System.out.println(scoreSheet.toString());
        System.out.println(newMatch.getMatchWins());
    }


    public void initialStats()
    {
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        for (int stats = 0; stats < selectionTeams.size(); stats++) {
            if (selectionTeams.get(stats).equals(homeTeamBox))
            {
                for (int playStats = 0; playStats < scoreSheet.size(); playStats++)
                {
                    selectionTeams.get(stats).setMatchesPlayed(1);
                    int homeWin = scoreSheet.get(playStats).teamSetWins[0];
                    selectionTeams.get(stats).setSetsWon(homeWin);
                    if (scoreSheet.get(playStats).teamWon() == 1)
                    {
                        selectionTeams.get(stats).setMatchesWon(1);
                    }
                }
            }
            else if (selectionTeams.get(stats).equals(awayTeamBox))
            {
                for (int playStats = 0; playStats < scoreSheet.size(); playStats++)
                {
                    selectionTeams.get(stats).setMatchesPlayed(1);
                    int awayWin = scoreSheet.get(playStats).teamSetWins[1];
                    selectionTeams.get(stats).setSetsWon(awayWin);
                    if (scoreSheet.get(playStats).teamWon() == 0)
                    {
                        selectionTeams.get(stats).setMatchesWon(1);
                    }
                }
            }

        }
    }

    public Boolean hasWon()
    {
        boolean won = false;

        for (int playStats = 0; playStats < scoreSheet.size(); playStats++) {
            if (scoreSheet.get(playStats).teamWon() == 1) {
                won = true;

            }
            else if (scoreSheet.get(playStats).teamWon() == 0)
            {
                won = false;
            }

        }
        return won;
    }

    public int playingTeams(Teams homeOrAway)
    {
        int teamSetWin = 0;
        for (int playStats = 0; playStats < scoreSheet.size(); playStats++)
        {
            if (homeOrAway.equals(homeTeamX.getValue()))
            {
                teamSetWin = scoreSheet.get(playStats).teamSetWins[0];
            }
            else if (homeOrAway.equals(awayTeamX.getValue()))
            {
                teamSetWin = scoreSheet.get(playStats).teamSetWins[1];
            }
        }
        return teamSetWin;
    }

    public void updateStats()
    {
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        for (int stats = 0; stats < selectionTeams.size(); stats++) {
            if (selectionTeams.get(stats).equals(homeTeamBox))
            {
                selectionTeams.get(stats).matchesPlayed = (selectionTeams.get(stats).matchesPlayed + 1);
                if (hasWon())
                {
                    selectionTeams.get(stats).matchesWon++;
                }

                int homeWin = playingTeams(homeTeamBox);
                selectionTeams.get(stats).setSetsWon(selectionTeams.get(stats).setsWon + homeWin);

            }
            else if (selectionTeams.get(stats).equals(awayTeamBox))
            {
                selectionTeams.get(stats).setMatchesPlayed(selectionTeams.get(stats).matchesPlayed + 1);

                if (!hasWon())
                {
                    selectionTeams.get(stats).matchesWon++;
                }

                int awayWin = playingTeams(awayTeamBox);
                selectionTeams.get(stats).setSetsWon(selectionTeams.get(stats).setsWon + awayWin);
            }
        }
    }


    public void removeStats()
    {
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        for(Teams statsToRemove : selectionTeams)
        {
            if (statsToRemove.equals(homeTeamBox))
            {
                statsToRemove.matchesPlayed = (statsToRemove.matchesPlayed - 1);
                if (hasWon())
                {
                    statsToRemove.matchesWon--;
                }

                int homeWin = playingTeams(homeTeamBox);
                statsToRemove.setSetsWon(statsToRemove.setsWon - homeWin);
            }
            else if (statsToRemove.equals(awayTeamBox))
            {
                statsToRemove.matchesPlayed = (statsToRemove.matchesPlayed - 1);
                if (!hasWon())
                {
                    statsToRemove.matchesWon--;
                }

                int awayWin = playingTeams(awayTeamBox);
                statsToRemove.setSetsWon(statsToRemove.setsWon - awayWin);
            }
        }
    }

    public void generateNewScoreSheet()
    {
        findMatch.setVisible(false);
        existingMatch.setVisible(false);
        hideScoreSheet.setVisible(false);
        calculateModified.setVisible(false);
        calculateSubmit.setVisible(true);

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

    public void modifyButton()
    {
        generateNewScoreSheet();
        hideScoreSheet.setVisible(true);
        findMatch.setVisible(true);
    }

    public int findPlayers(Teams comboValue, String find)
    {
        int playerIndex = 0;

        for (int competingPlayer = 0; competingPlayer < selectionTeams.size(); competingPlayer++)
        {
            if (selectionTeams.get(competingPlayer).equals(comboValue))
            {
                for (int eachplayer = 0; eachplayer < selectionTeams.get(competingPlayer).getPlayer().size(); eachplayer++)
                {

                    if (selectionTeams.get(competingPlayer).getPlayer().get(eachplayer).equals(find))
                    {
                        playerIndex = eachplayer;
                        break;
                    }
                }
            }
        }
        return playerIndex;
    }

    // Match alter == modify
    public String obtainGamePoints(Match alter, int setOne, int index)
    {
        String gameScore = "";
        ArrayList<Sets> singleSets = alter.getMatchSinglesSets();
        int homePoint = singleSets.get(setOne).getSetList().get(index).getHomePlayerPoint();
        int awayPoint = singleSets.get(setOne).getSetList().get(index).getAwayPlayerPoint();
        gameScore = homePoint + ":" + awayPoint;

        return gameScore;
    }

    public void modifyScoreSheet()
    {
        existingMatch.setVisible(true);
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        for (int modify = 0; modify < scoreSheet.size(); modify++)
        //for (Match modify : scoreSheet)
        {
            //if (modify.getHomeTeam().equals(homeTeamBox)) {
            if (scoreSheet.get(modify).getHomeTeam().equals(homeTeamBox)) {
                //if (modify.getAwayTeam().equals(awayTeamBox))
                if (scoreSheet.get(modify).getAwayTeam().equals(awayTeamBox))
                {

                    existingMatch.setText("This match can be modified");
                    hideScoreSheet.setVisible(false);
                    calculateSubmit.setVisible(false);
                    calculateModified.setVisible(true);

                    // The players
                    playerHomeA.getSelectionModel().select(findPlayers(homeTeamBox, scoreSheet.get(modify).getHomePlayerA()));
                    playerHomeB.getSelectionModel().select(findPlayers(homeTeamBox, scoreSheet.get(modify).getHomePlayerB()));

                    playerAwayA.getSelectionModel().select(findPlayers(awayTeamBox, scoreSheet.get(modify).getAwayPlayerA()));
                    playerAwayB.getSelectionModel().select(findPlayers(awayTeamBox, scoreSheet.get(modify).getAwayPlayerB()));

                    // Set one
                    singleSetAOne.setText(obtainGamePoints(scoreSheet.get(modify), 0, 0));
                    singleSetATwo.setText(obtainGamePoints(scoreSheet.get(modify), 0, 1));
                    singleSetAThree.setText(obtainGamePoints(scoreSheet.get(modify), 0, 2));

                    //Set two
                    singleSetAFour.setText(obtainGamePoints(scoreSheet.get(modify), 1, 0));
                    singleSetAFive.setText(obtainGamePoints(scoreSheet.get(modify), 1, 1));
                    singleSetASix.setText(obtainGamePoints(scoreSheet.get(modify), 1, 2));

                    // Set fhree
                    singleSetBOne.setText(obtainGamePoints(scoreSheet.get(modify), 2, 0));
                    singleSetBTwo.setText(obtainGamePoints(scoreSheet.get(modify), 2, 1));
                    singleSetBThree.setText(obtainGamePoints(scoreSheet.get(modify), 2, 2));

                    // Set four
                    singleSetBFour.setText(obtainGamePoints(scoreSheet.get(modify), 3, 0));
                    singleSetBFive.setText(obtainGamePoints(scoreSheet.get(modify), 3, 1));
                    singleSetBSix.setText(obtainGamePoints(scoreSheet.get(modify), 3, 2));

                    // Double set
                    doubleSetA.setText(obtainGamePoints(scoreSheet.get(modify), 4, 0));
                    doubleSetB.setText(obtainGamePoints(scoreSheet.get(modify), 4, 1));
                    doubleSetC.setText(obtainGamePoints(scoreSheet.get(modify), 4, 2));

                }
                else
                {
                    existingMatch.setText("This match has not been played, please select another.");
                }
            }
            else
            {
                existingMatch.setText("This match has not been played, please select another.");

            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        selectTeam.setItems(selectionTeams);
//        selectTeam.getSelectionModel();

        homeTeamX.setItems(selectionTeams);
        homeTeamX.getSelectionModel();

        awayTeamX.setItems(selectionTeams);
        awayTeamX.getSelectionModel();

//        homeTeamX.setItems(homeTeams);
//        homeTeamX.getSelectionModel();
//
//        awayTeamX.setItems(awayTeams);
//        awayTeamX.getSelectionModel();

        previousHomeTeam.setVisible(false);
        previousAwayTeam.setVisible(false);
        findPreviousMatch.setVisible(false);
        hideScoreSheet.setVisible(false);
        findMatch.setVisible(false);
        calculateModified.setVisible(false);
        existingMatch.setVisible(false);

        /// the 100 sec timer
        Runnable runnable = ()-> {
            try{
                while(true)
                {
                    TimeUnit.SECONDS.sleep(10);
                    stats.clear();
                    TeamStats eachTeamsStats = new TeamStats(selectionTeams);
                    eachTeamsStats.setTotalTeamStats();
                    stats.add(eachTeamsStats.toString());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread teamStatDelay =  new Thread(runnable);
        teamStatDelay.setDaemon(true);
        teamStatDelay.start();

        // Filton Team
        Teams filton = new Teams();
        filton.setTeam("filton");

        filton.setPlayer("alex");
        filton.setPlayer("brian");

        selectionTeams.add(filton);


        // UWE Team
        Teams uwe = new Teams();
        uwe.setTeam("uwe");

        uwe.setPlayer("jin");
        uwe.setPlayer("julia");
        uwe.setPlayer("stewart");

        selectionTeams.add(uwe);

        //kcc Team
        Teams kcc = new Teams();
        kcc.setTeam("kcc");

        kcc.setPlayer("chris");
        kcc.setPlayer("ryan");

        selectionTeams.add(kcc);

        //page Team
        Teams page = new Teams();
        page.setTeam("page");

        page.setPlayer("peter");
        page.setPlayer("phil");

        selectionTeams.add(page);

        selectTeam.setItems(selectionTeams);
        selectTeam.getSelectionModel();
//
//
//
//        Match filtonVsUwe = new Match(selectionTeams.get(0), awayTeamX.getValue(), playerHomeA.getValue(),
//                playerHomeB.getValue(), playerAwayA.getValue(), playerAwayB.getValue());

//
//        newMatch.inputMatches(singleSetAOne.getText(), singleSetATwo.getText(), singleSetAThree.getText(),
//                singleSetAFour.getText(), singleSetAFive.getText(), singleSetASix.getText(),
//                singleSetBOne.getText(), singleSetBTwo.getText(), singleSetBThree.getText(),
//                singleSetBFour.getText(), singleSetBFive.getText(), singleSetBSix.getText(),
//                doubleSetA.getText(), doubleSetB.getText(), doubleSetC.getText());
//
//        scoreSheet.add(newMatch);

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

