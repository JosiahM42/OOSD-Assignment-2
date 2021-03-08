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
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
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

    public Rectangle hideScoreSheet;
    public Label scoreSheetResponse;
    public Button findMatch;
    public Button createScoreSheet;

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

    public Button calculateModified;

    public int teamAddCounter = 0;
    public int scoreSheetCounter = 0;
    public ObservableList<Teams> selectionTeams = FXCollections.observableArrayList();
    public ArrayList<Match> scoreSheet = new ArrayList<>();

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
        addTeamResponse.setVisible(true);
        registerPlayerResponse.setVisible(false);
        fixturesResponse.setVisible(false);
        teamStatsResponse.setVisible(false);

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
        // Hides the validation messages
        addTeamResponse.setVisible(false);
        registerPlayerResponse.setVisible(true);
        fixturesResponse.setVisible(false);
        teamStatsResponse.setVisible(false);

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

                    registerPlayerResponse.setText("");
                    registerPlayerResponse.setTextFill(Color.web("#00FF00")); // Sets colour to Green

                    // Sets the text and states which team the new player has been added to
                    registerPlayerResponse.setText("New player added to " + selectionTeams.get(numTeams).getTeam());

                    // This is to clear the selected team
                    selectTeam.getSelectionModel().clearSelection();
                    break;
                }
                else if (playerName.getText().isEmpty())
                {
                    registerPlayerResponse.setTextFill(Color.web("#FF0000")); // Sets colour to red
                    registerPlayerResponse.setText("Please enter a player name");
                }

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
        // Hides the validation messages
        addTeamResponse.setVisible(false);
        registerPlayerResponse.setVisible(false);
        fixturesResponse.setVisible(true);
        teamStatsResponse.setVisible(false);


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
        // Hides the validation messages
        addTeamResponse.setVisible(false);
        registerPlayerResponse.setVisible(false);
        fixturesResponse.setVisible(false);
        teamStatsResponse.setVisible(true); // makes the label invisible

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
        teamStatsResponse.setVisible(false);

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


    public boolean searchPreviousScores()
    {
        // Stores the values selected in the previous team ComboBoxes
        Teams previousHomeValue = previousHomeTeam.getValue();
        Teams previousAwayValue = previousAwayTeam.getValue();

        // Checks if the user has selected the same team in both combo boxes
        if (previousHomeValue.equals(previousAwayValue) || previousAwayValue.equals(previousHomeValue))
        {
            if (scoreSheet.isEmpty())
            {
                outputDisplay.setFont(Font.font(18));
                outputDisplay.setText("This Match cannot be played because team cannot play itself");
            }
            else
            {
                outputDisplay.setFont(Font.font(18));
                outputDisplay.setText("A team cannot play itself");
            }

            return false;
        }

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
                        //outputDisplay.clear();
                        outputDisplay.setStyle("-fx-font-alignment: center");
                        outputDisplay.setFont(Font.font(18));
                        outputDisplay.setText(previousMatchTeam.toString());
                        return true;
                    }
                    else
                    {
                        outputDisplay.setStyle("-fx-font-alignment: center");
                        outputDisplay.setFont(Font.font(18));
                        outputDisplay.setText(previousMatchTeam.toString());
                        return true;
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
        return true;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////The ScoreSheet Tab Code////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
        Function Name: generateNewScoreSheet
        Arguments: N/A
        Description: Allows the user to select
                     both an away team and a home
                     team. This function also clears
                     the whole score sheet.
    */


    public void generateNewScoreSheet()
    {
        // Hides and makes objects visible
        scoreSheetResponse.setVisible(true); // Makes a Label visible
        hideScoreSheet.setVisible(true); // Makes a rectangle visible that hides the score sheet
        calculateSubmit.setVisible(true); // Makes the submit button visible
        createScoreSheet.setVisible(true); // Makes a Button visible
        findMatch.setVisible(false); // Makes a Button invisible
        calculateModified.setVisible(false); // Makes a Button invisible

        // Friendly user message
        scoreSheetResponse.setText("Please select a home and away team");

        // Clears the selected team from the value section of the combo boxes
        homeTeamX.getSelectionModel().clearSelection();
        awayTeamX.getSelectionModel().clearSelection();

        // Deselects the last selected player
        playerHomeA.valueProperty().set(null);
        playerHomeB.valueProperty().set(null);

        playerAwayA.valueProperty().set(null);
        playerAwayB.valueProperty().set(null);

        // Clears all the text fields on the score sheet
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

        // Clears the previous match total
        matchTotal.clear();
    }


    /*
        Function Name: competeCheck
        Arguments: N/A
        Description: This is a validation function that
                     checks if the user has selected a
                     correct pair of teams, so that they
                     meet a certain criteria.
    */


    public boolean competeCheck()
    {
        // This is run if the user clicks the createScoreSheet button before selecting a home team or an away team
        if (homeTeamX.getSelectionModel().isEmpty() && awayTeamX.getSelectionModel().isEmpty())
        {
            scoreSheetResponse.setText("Please select a home team and an away team");

            // This will end the function
            return false;
        }
        if (homeTeamX.getSelectionModel().isEmpty()) // This will make sure that the user enters both teams
        {
            scoreSheetResponse.setText("Please select a home team");
            return false;
        }
        else if (awayTeamX.getSelectionModel().isEmpty()) // This will also make sure that the user enters both teams
        {
            scoreSheetResponse.setText("Please select an away team");
            return false;
        }


        // Stores the selected teams
        Teams homeTeamSelection = homeTeamX.getValue();
        Teams awayTeamSelection = awayTeamX.getValue();

        // Checks if the user has selected the same team in both combo boxes
        if (homeTeamSelection.equals(awayTeamSelection) || awayTeamSelection.equals(homeTeamSelection))
        {
            scoreSheetResponse.setText("A team cannot play itself");
            // Keeps the score sheet hidden
            hideScoreSheet.setVisible(true);
            return false;
        }


        // This will be for the first team created and is used to prevent errors when searching the scoreSheet array
        if (scoreSheet.isEmpty())
        {
            scoreSheetResponse.setText("This fixture has not been played");

            // This will remove the large rectangle covering the score sheet
            hideScoreSheet.setVisible(false);
        }
        else // This will check if the teams entered have already played each other or not
        {
            for (Match playedCheck : scoreSheet) {
                // This checks if the selected home team and the away team have not played each other
                if (!playedCheck.getHomeTeam().equals(homeTeamSelection) ||
                        !playedCheck.getAwayTeam().equals(awayTeamSelection))
                {


                    System.out.println("Match with " + playedCheck.getHomeTeam() + " and "
                            + playedCheck.getAwayTeam() +" has not been found");

                    scoreSheetResponse.setText("This fixture has not been played");
                    hideScoreSheet.setVisible(false);

                }

                // This checks if the selected home team and the away team have played each other
               else if (playedCheck.getHomeTeam().equals(homeTeamSelection) &&
                        playedCheck.getAwayTeam().equals(awayTeamSelection))
               {
                    scoreSheetResponse.setText("This fixture has been played, please choose another");
                    hideScoreSheet.setVisible(true);
               }

            }
        }
        return true;
    }


     /*
        Function Name: modifyButton
        Arguments: N/A
        Description: This is just a function that sets up the
                     program for match entry modification.
    */


    public void modifyButton()
    {
        // Calls the new sheet function to clear the score sheet if it is filled out
        generateNewScoreSheet();

        hideScoreSheet.setVisible(true); // Makes the score sheet hidden
        findMatch.setVisible(true); // Makes a button visible
        scoreSheetResponse.setVisible(true); // Makes a label visible
        createScoreSheet.setVisible(false); // makes the previous button invisible

        scoreSheetResponse.setText("Please select a home and away team");
    }


    /*
        Function Name: findPlayers
        Arguments: Teams comboValue & String find
        Description: Finds the players that played
                     the match originally.
    */


    public int findPlayers(Teams comboValue, String find)
    {
        int playerIndex = 0; // Used to store the index of the player in the ComboBox

        for (int competingPlayer = 0; competingPlayer < selectionTeams.size(); competingPlayer++)
        {
            // Checks if the selected team is in selection teams
            if (selectionTeams.get(competingPlayer).equals(comboValue))
            {
                // Loops through the selected team's list of players
                for (int eachplayer = 0; eachplayer < selectionTeams.get(competingPlayer).getPlayer().size(); eachplayer++)
                {
                    // Checks if the player in the team is equal to the one that played in the match
                    if (selectionTeams.get(competingPlayer).getPlayer().get(eachplayer).equals(find))
                    {
                        // Saves the index of that player
                        playerIndex = eachplayer;
                        break;
                    }
                }
            }
        }
        return playerIndex;
    }


    /*
        Function Name: obtainGamePoints
        Arguments: Match alter, int setOne & int index
        Description: Converts both team's game scores
                     into a string to create that
                     game's score.
    */


    public String obtainGamePoints(Match alter, int setOne, int index)
    {
        // Will store the final game score
        String gameScore = "";

        // A list of games and sets in each match
        ArrayList<Sets> singleSets = alter.getMatchSinglesSets();

        // The point that each team scored in each match
        int homePoint = singleSets.get(setOne).getSetList().get(index).getHomePlayerPoint();
        int awayPoint = singleSets.get(setOne).getSetList().get(index).getAwayPlayerPoint();

        // The concatenation of both team point
        gameScore = homePoint + ":" + awayPoint;

        return gameScore;
    }


    /*
        Function Name: modifyScoreSheet
        Arguments: N/A
        Description: Pulls all the data from the scoreSheet
                     array and populates the score sheet in
                     the gui with previous match information
                     that can be altered and saved.
    */


    public boolean modifyScoreSheet()
    {
        // This is run if the user clicks the createScoreSheet button before selecting a home team or an away team
        if (homeTeamX.getSelectionModel().isEmpty() && awayTeamX.getSelectionModel().isEmpty())
        {
            scoreSheetResponse.setText("Please select a home team and an away team to be modified");
            return false;
        }

        if (homeTeamX.getSelectionModel().isEmpty()) // This will make sure that the user enters both teams
        {
            scoreSheetResponse.setText("Please select a home team");
            return false;
        }
        else if (awayTeamX.getSelectionModel().isEmpty()) // This will also make sure that the user enters both teams
        {
            scoreSheetResponse.setText("Please select an away team");
            return false;
        }

        //existingMatch.setVisible(true);
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        // Checks if the user has selected the same team in both combo boxes
        if (homeTeamBox.equals(awayTeamBox) || awayTeamBox.equals(homeTeamBox))
        {
            scoreSheetResponse.setText("This match cannot be modified because it cannot be played");
            // Keeps the score sheet hidden
            hideScoreSheet.setVisible(true);
            return false;
        }


        if (scoreSheet.isEmpty()) // Checks if there are any matches in the scoreSheet array
        {
            scoreSheetResponse.setText("Please create a score sheet before modifying");
        }
        else
            {
            for (int modify = 0; modify < scoreSheet.size(); modify++)
            {
                if (scoreSheet.get(modify).getHomeTeam().equals(homeTeamBox))  // Checks if the selected home team has played at home
                {
                    if (scoreSheet.get(modify).getAwayTeam().equals(awayTeamBox))  // Checks if the selected away team has played in that same match
                    {
                        scoreSheetResponse.setText("This match can be modified");

                        hideScoreSheet.setVisible(false); // This makes the white box covering the score sheet invisible
                        calculateSubmit.setVisible(false); // Makes the submit button for new sheet invisible
                        calculateModified.setVisible(true); // Makes the submit button for modify sheet visible

                        // Selects the home players according to the index value generated in the find players function
                        playerHomeA.getSelectionModel().select(findPlayers(homeTeamBox, scoreSheet.get(modify).getHomePlayerA()));
                        playerHomeB.getSelectionModel().select(findPlayers(homeTeamBox, scoreSheet.get(modify).getHomePlayerB()));

                        // Selects the away players according to the index value generated in the find players function
                        playerAwayA.getSelectionModel().select(findPlayers(awayTeamBox, scoreSheet.get(modify).getAwayPlayerA()));
                        playerAwayB.getSelectionModel().select(findPlayers(awayTeamBox, scoreSheet.get(modify).getAwayPlayerB()));

                        // Sets the first three text fields according to the final score for each game
                        singleSetAOne.setText(obtainGamePoints(scoreSheet.get(modify), 0, 0));
                        singleSetATwo.setText(obtainGamePoints(scoreSheet.get(modify), 0, 1));
                        singleSetAThree.setText(obtainGamePoints(scoreSheet.get(modify), 0, 2));

                        // Sets the second three text fields according to the final score for each game
                        singleSetAFour.setText(obtainGamePoints(scoreSheet.get(modify), 1, 0));
                        singleSetAFive.setText(obtainGamePoints(scoreSheet.get(modify), 1, 1));
                        singleSetASix.setText(obtainGamePoints(scoreSheet.get(modify), 1, 2));

                        // Sets the third three text fields according to the final score for each game
                        singleSetBOne.setText(obtainGamePoints(scoreSheet.get(modify), 2, 0));
                        singleSetBTwo.setText(obtainGamePoints(scoreSheet.get(modify), 2, 1));
                        singleSetBThree.setText(obtainGamePoints(scoreSheet.get(modify), 2, 2));

                        // Sets the fourth three text fields according to the final score for each game
                        singleSetBFour.setText(obtainGamePoints(scoreSheet.get(modify), 3, 0));
                        singleSetBFive.setText(obtainGamePoints(scoreSheet.get(modify), 3, 1));
                        singleSetBSix.setText(obtainGamePoints(scoreSheet.get(modify), 3, 2));

                        // // Sets the fifth double set text fields according to the final score for each game
                        doubleSetA.setText(obtainGamePoints(scoreSheet.get(modify), 4, 0));
                        doubleSetB.setText(obtainGamePoints(scoreSheet.get(modify), 4, 1));
                        doubleSetC.setText(obtainGamePoints(scoreSheet.get(modify), 4, 2));

                    }
                    // Checks if the away team selected has not competed in this match
                    else if (!scoreSheet.get(modify).getAwayTeam().equals(awayTeamBox)) {
                        scoreSheetResponse.setText("This match has not been played, please select another.");
                    }
                }
            }
        }
        return true;
    }

   /*
        Function Name: playersAtHomeOnChangeEvent
        Arguments: N/A
        Description: Sets the values in the home players
                     combo box depending on what team has
                     been selected.
    */


    public void homePlayerForModification()
    {
        // Stores the selected home team
        Teams homeTeam = homeTeamX.getValue();

        for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++) {

            // Checks to find the exact team to get the players from
            if (selectionTeams.get(numTeams).equals(homeTeam))
            {
                // Sets the player's combo boxes to the players that were originally selected
                playerHomeA.setItems(selectionTeams.get(numTeams).getPlayer());
                playerHomeB.setItems(selectionTeams.get(numTeams).getPlayer());
                break;
            }
        }
    }


    /*
        Function Name: playersAwayOnChangeEvent
        Arguments: N/A
        Description: Sets the values in the away players
                     combo box depending on what team has
                     been selected.
    */


    public void awayPlayerForModification()
    {
        // Stores the selected away team
        Teams AwayTeam = awayTeamX.getValue();

        for (int numTeams = 0; numTeams < selectionTeams.size(); numTeams++) {

            // Checks to find the exact team to get the players from
            if (selectionTeams.get(numTeams).equals(AwayTeam))
            {
                // Sets the player's combo boxes to the players that were originally selected
                playerAwayA.setItems(selectionTeams.get(numTeams).getPlayer());
                playerAwayB.setItems(selectionTeams.get(numTeams).getPlayer());
                break;
            }
        }
    }

    /*
        Function Name: textFieldValidation
        Arguments: N/A
        Description: Turns all the text boxes red
                     if they are empty. This is for
                     validation purposes.
    */

    public void textFieldValidation()
    {
        // checks if any of the text fields are empty
        if (singleSetAOne.getText().isEmpty() || singleSetATwo.getText().isEmpty() ||
                singleSetAThree.getText().isEmpty() || singleSetAFour.getText().isEmpty() ||
                singleSetAFive.getText().isEmpty() || singleSetASix.getText().isEmpty() ||
                singleSetBOne.getText().isEmpty() || singleSetBTwo.getText().isEmpty() ||
                singleSetBThree.getText().isEmpty() || singleSetBFour.getText().isEmpty() ||
                singleSetBFive.getText().isEmpty() || singleSetBSix.getText().isEmpty() ||
                doubleSetA.getText().isEmpty() || doubleSetB.getText().isEmpty() || doubleSetC.getText().isEmpty())
        {
            scoreSheetResponse.setText("All fields must be filled");

            // Turns all the text fields red
            singleSetAOne.setStyle("-fx-control-inner-background: red;");
            singleSetATwo.setStyle("-fx-control-inner-background: red;");
            singleSetAThree.setStyle("-fx-control-inner-background: red;");

            singleSetAFour.setStyle("-fx-control-inner-background: red;");
            singleSetAFive.setStyle("-fx-control-inner-background: red;");
            singleSetASix.setStyle("-fx-control-inner-background: red;");

            singleSetBOne.setStyle("-fx-control-inner-background: red;");
            singleSetBTwo.setStyle("-fx-control-inner-background: red;");
            singleSetBThree.setStyle("-fx-control-inner-background: red;");

            singleSetBFour.setStyle("-fx-control-inner-background: red;");
            singleSetBFive.setStyle("-fx-control-inner-background: red;");
            singleSetBSix.setStyle("-fx-control-inner-background: red;");

            doubleSetA.setStyle("-fx-control-inner-background: red;");
            doubleSetB.setStyle("-fx-control-inner-background: red;");
            doubleSetC.setStyle("-fx-control-inner-background: red;");
        }
        // Checks if the user has entered a score in the wrong format e.g. 11:b or a:0
        // The regular expression is checking if the text is not equal to:
        // an integer between 0 and 11
        // a colon (:)
        // an integer between 0 and 11
        else if(!singleSetAOne.getText().matches("(\\d{0,11})\\W(\\d{0,11})") || !singleSetATwo.getText().matches("(\\d{0,11})\\W(\\d{0,11})") ||
                !singleSetAThree.getText().matches("(\\d{0,11})\\W(\\d{0,11})") || !singleSetAFour.getText().matches("(\\d{0,11})\\W(\\d{0,11})") ||
                !singleSetAFive.getText().matches("(\\d{0,11})\\W(\\d{0,11})") || !singleSetASix.getText().matches("(\\d{0,11})\\W(\\d{0,11})") ||
                !singleSetBOne.getText().matches("(\\d{0,11})\\W(\\d{0,11})") || !singleSetBTwo.getText().matches("(\\d{0,11})\\W(\\d{0,11})") ||
                !singleSetBThree.getText().matches("(\\d{0,11})\\W(\\d{0,11})") || !singleSetBFour.getText().matches("(\\d{0,11})\\W(\\d{0,11})") ||
                !singleSetBFive.getText().matches("(\\d{0,11})\\W(\\d{0,11})") || !singleSetBSix.getText().matches("(\\d{0,11})\\W(\\d{0,11})") ||
                !doubleSetA.getText().matches("(\\d{0,11})\\W(\\d{0,11})") || !doubleSetB.getText().matches("(\\d{0,11})\\W(\\d{0,11})")
                || !doubleSetC.getText().matches("(\\d{0,11})\\W(\\d{0,11})"))
        {
            scoreSheetResponse.setText("The input must be integer 0 - 11 : 0 - 11");
            singleSetAOne.setStyle("-fx-control-inner-background: red;");
            singleSetATwo.setStyle("-fx-control-inner-background: red;");
            singleSetAThree.setStyle("-fx-control-inner-background: red;");

            singleSetAFour.setStyle("-fx-control-inner-background: red;");
            singleSetAFive.setStyle("-fx-control-inner-background: red;");
            singleSetASix.setStyle("-fx-control-inner-background: red;");

            singleSetBOne.setStyle("-fx-control-inner-background: red;");
            singleSetBTwo.setStyle("-fx-control-inner-background: red;");
            singleSetBThree.setStyle("-fx-control-inner-background: red;");

            singleSetBFour.setStyle("-fx-control-inner-background: red;");
            singleSetBFive.setStyle("-fx-control-inner-background: red;");
            singleSetBSix.setStyle("-fx-control-inner-background: red;");

            doubleSetA.setStyle("-fx-control-inner-background: red;");
            doubleSetB.setStyle("-fx-control-inner-background: red;");
            doubleSetC.setStyle("-fx-control-inner-background: red;");
        }

    }


    /*
        Function Name: textFieldReversal
        Arguments: N/A
        Description: Reverts the text fields back to
                     normal if one or more of the fields
                     is empty.
    */


    public void textFieldReversal()
    {
        scoreSheetResponse.setText("");

        // Turns all the text fields white
        singleSetAOne.setStyle("-fx-control-inner-background: white;");
        singleSetATwo.setStyle("-fx-control-inner-background: white;");
        singleSetAThree.setStyle("-fx-control-inner-background: white;");

        singleSetAFour.setStyle("-fx-control-inner-background: white;");
        singleSetAFive.setStyle("-fx-control-inner-background: white;");
        singleSetASix.setStyle("-fx-control-inner-background: white;");

        singleSetBOne.setStyle("-fx-control-inner-background: white;");
        singleSetBTwo.setStyle("-fx-control-inner-background: white;");
        singleSetBThree.setStyle("-fx-control-inner-background: white;");

        singleSetBFour.setStyle("-fx-control-inner-background: white;");
        singleSetBFive.setStyle("-fx-control-inner-background: white;");
        singleSetBSix.setStyle("-fx-control-inner-background: white;");

        doubleSetA.setStyle("-fx-control-inner-background: white;");
        doubleSetB.setStyle("-fx-control-inner-background: white;");
        doubleSetC.setStyle("-fx-control-inner-background: white;");
    }


    /*
        Function Name: inputScoreSheet
        Arguments: ActionEvent event
        Description: Gets all the data from the
                     text fields and combo boxes
                     on the score sheet and stores
                     them in an array.
    */


    public void inputScoreSheet()
    {
        // Creates an object of type Match and passes the teams and players selected as arguments for the constructor
        Match newMatch = new Match(homeTeamX.getValue(), awayTeamX.getValue(), playerHomeA.getValue(),
                                    playerHomeB.getValue(), playerAwayA.getValue(), playerAwayB.getValue());

        // A try and catch statement to avoid producing an error when a user does not enter a score in all
        // the text fields
        try
        {
            // Calls Match's inputMatches function and passes all the scores within the text fields as arguments
            newMatch.inputMatches(singleSetAOne.getText(), singleSetATwo.getText(), singleSetAThree.getText(),
                    singleSetAFour.getText(), singleSetAFive.getText(), singleSetASix.getText(),
                    singleSetBOne.getText(), singleSetBTwo.getText(), singleSetBThree.getText(),
                    singleSetBFour.getText(), singleSetBFive.getText(), singleSetBSix.getText(),
                    doubleSetA.getText(), doubleSetB.getText(), doubleSetC.getText());

            // Appends the object to an array
            scoreSheet.add(newMatch);

            // Outputs the final match score depending on the match played
            matchTotal.setText(newMatch.getMatchTotal());

            System.out.println(scoreSheet.toString());
            System.out.println(newMatch.getMatchWins());

            // Adds the stats to the teams
            updateStats();

        }
        catch (NumberFormatException numException) // The exception to catch
        {
            // If this exception occurs then this function will be called
            textFieldValidation();
        }


    }


    /*
        Function Name: appendModifiedScoreSheet
        Arguments: N/A
        Description: This function allows the user to modify
                     scores from the text fields and combo boxes
                     on the score sheet and stores them in an array.
    */


    public void appendModifiedScoreSheet()
    {
        // Calls the removeStats function to remove the stats that were inputted the first time that the match
        // was played. This is just so that the newly modified match's stats can be added
        removeStats();

        // Creates an object of type Match and passes the teams and players selected as arguments for the constructor
        Match newMatch = new Match(homeTeamX.getValue(), awayTeamX.getValue(), playerHomeA.getValue(),
                playerHomeB.getValue(), playerAwayA.getValue(), playerAwayB.getValue());
        try {
            // Calls Match's inputMatches function and passes all the scores within the text fields as arguments
            newMatch.inputMatches(singleSetAOne.getText(), singleSetATwo.getText(), singleSetAThree.getText(),
                    singleSetAFour.getText(), singleSetAFive.getText(), singleSetASix.getText(),
                    singleSetBOne.getText(), singleSetBTwo.getText(), singleSetBThree.getText(),
                    singleSetBFour.getText(), singleSetBFive.getText(), singleSetBSix.getText(),
                    doubleSetA.getText(), doubleSetB.getText(), doubleSetC.getText());

            // Appends the newly modified match to the scoreSheet array
            scoreSheet.add(newMatch);

            // Outputs the final match score depending on the match played
            matchTotal.setText(newMatch.getMatchTotal());

            // A list to store all the indexes of all the occurrences of the modified match
            // This includes the initial score sheet
            List<Integer> allIndex = new ArrayList<>();

            // Gets the values in both teams ComboBoxes
            Teams homeTeamBox = homeTeamX.getValue();
            Teams awayTeamBox = awayTeamX.getValue();

            // Adds the new stats of the modified game
            updateStats();

            // Loops through the scoreSheet array to find any occurrence of a match with the same teams
            for (int findIndex = 0; findIndex < scoreSheet.size(); findIndex++) {

                // Checks if another match exists with the same teams as the modified match
                if (scoreSheet.get(findIndex).getHomeTeam().equals(homeTeamBox) &&
                        scoreSheet.get(findIndex).getAwayTeam().equals(awayTeamBox)) {
                    // If such a match is found, it's index will be added to the array
                    allIndex.add(findIndex);
                }
            }

            // Since the match would have been found first, the first index is removed
            int firstInstance = allIndex.get(0);
            scoreSheet.remove(firstInstance);

            System.out.println("\n");
            System.out.println(scoreSheet.toString());
            System.out.println(newMatch.getMatchWins());
        }
        catch (NumberFormatException numException) // The exception to catch
        {
            // If this exception occurs then this function will be called
            textFieldValidation();
        }

    }


     /*
        Function Name: initialStats
        Arguments: N/A
        Description: This function adds each team's
                     stats to their respective stat
                     variables. The function gets the
                     stats from the matches via the
                     scoreSheet array.
    */


    public void initialStats()
    {
        // Stores the teams that have been selected in the combo box
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        // Loops through the array of teams
        for (int stats = 0; stats < selectionTeams.size(); stats++) {

            // Checks if the selected home team is in the list of teams
            if (selectionTeams.get(stats).equals(homeTeamBox))
            {
                // loop through the list of matches
                for (int playStats = 0; playStats < scoreSheet.size(); playStats++)
                {
                    // Adds one to the selected team's matchesPlayed variable
                    selectionTeams.get(stats).setMatchesPlayed(1);

                    // Stores the home wins for the whole match
                    int homeWin = scoreSheet.get(playStats).teamSetWins[0];

                    // Adds the total set wins to the selected team's setsWon variable
                    selectionTeams.get(stats).setSetsWon(homeWin);

                    // Checks if the home team won the game
                    if (scoreSheet.get(playStats).teamWon() == 1)
                    {
                        // If so add one to the selected team's matchesWon variable
                        selectionTeams.get(stats).setMatchesWon(1);
                    }
                }
            }
            else if (selectionTeams.get(stats).equals(awayTeamBox))
            {
                // loop through the list of matches
                for (int playStats = 0; playStats < scoreSheet.size(); playStats++)
                {
                    // Adds one to the selected team's matchesPlayed variable
                    selectionTeams.get(stats).setMatchesPlayed(1);

                    // Stores the away wins for the whole match
                    int awayWin = scoreSheet.get(playStats).teamSetWins[1];

                    // Adds the total set wins to the selected team's setsWon variable
                    selectionTeams.get(stats).setSetsWon(awayWin);

                    // Checks if the away team won the game
                    if (scoreSheet.get(playStats).teamWon() == 0)
                    {
                        // If so add one to the selected team's matchesWon variable
                        selectionTeams.get(stats).setMatchesWon(1);
                    }
                }
            }

        }
    }


    /*
        Function Name: hasWon
        Arguments: N/A
        Description: This function checks which team
                     won the match depending on their
                     final score.
    */


    public boolean hasWon()
    {
        // A value to return
        boolean won = false;

        // Loops through all the matches
        for (int playStats = 0; playStats < scoreSheet.size(); playStats++) {

            // If this is true, then the home team won the match
            if (scoreSheet.get(playStats).teamWon() == 1) {
                won = true;

            }
            // If this is true, then the away team won the match
            else if (scoreSheet.get(playStats).teamWon() == 0)
            {
                won = false;
            }

        }
        return won;
    }



    /*
        Function Name: playingTeams
        Arguments: Teams homeOrAway
        Description: This function checks which team
                     was passed as an argument and
                     depending on what the value was
                     it will return that team's set wins.
    */


    public int playingTeams(Teams homeOrAway)
    {
        // Used to temporarily store the selected team's set wins
        int teamSetWin = 0;

        // Loops through the list of matches
        for (int playStats = 0; playStats < scoreSheet.size(); playStats++)
        {
            // Checks if the argument passed is equal to the value within homeTeamX
            if (homeOrAway.equals(homeTeamX.getValue()))
            {
                // Gets the set wins from the match
                teamSetWin = scoreSheet.get(playStats).teamSetWins[0];
            }
            // Checks if the argument passed is equal to the value within awayTeamX
            else if (homeOrAway.equals(awayTeamX.getValue()))
            {
                // Gets the set wins from the match
                teamSetWin = scoreSheet.get(playStats).teamSetWins[1];
            }
        }
        return teamSetWin;
    }


    /*
        Function Name: updateStats
        Arguments: N/A
        Description: If a new score sheet is made
                     then the new stats will be added
                     by this function.
    */


    public void updateStats()
    {
        // Stores the teams selected
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        // Loops through the list of teams
        for (int stats = 0; stats < selectionTeams.size(); stats++) {

            // Checks if the selected home team is in the list of teams
            if (selectionTeams.get(stats).equals(homeTeamBox))
            {
                // Adds 1 to the selected home team's matchPlayed function
                selectionTeams.get(stats).matchesPlayed = (selectionTeams.get(stats).matchesPlayed + 1);

                // If hasWon is equal to true (If home team won the match)
                if (hasWon())
                {
                    // Adds one to the home team's matchesWon
                    selectionTeams.get(stats).matchesWon++;
                }

                // Stores the home wins for the whole match
                int homeWin = playingTeams(homeTeamBox);

                // Adds the total set wins to the selected team's setsWon variable
                selectionTeams.get(stats).setSetsWon(selectionTeams.get(stats).setsWon + homeWin);

            }
            // Checks if the selected away team is in the list of teams
            else if (selectionTeams.get(stats).equals(awayTeamBox))
            {
                // Adds 1 to the selected away team's matchPlayed function
                selectionTeams.get(stats).setMatchesPlayed(selectionTeams.get(stats).matchesPlayed + 1);

                // If hasWon is equal to false (If away team won the match)
                if (!hasWon())
                {
                    // Adds one to the away team's matchesWon
                    selectionTeams.get(stats).matchesWon++;
                }

                // Stores the away wins for the whole match
                int awayWin = playingTeams(awayTeamBox);

                // Adds the total set wins to the selected team's setsWon variable
                selectionTeams.get(stats).setSetsWon(selectionTeams.get(stats).setsWon + awayWin);
            }
        }
    }


    /*
        Function Name: removeStats
        Arguments: N/A
        Description: If a match is being modified
                     then the previous match stats
                     are removed by this function.
    */


    public void removeStats()
    {
        // Stores the teams selected
        Teams homeTeamBox = homeTeamX.getValue();
        Teams awayTeamBox = awayTeamX.getValue();

        // Loops through the lists of teams
        for(Teams statsToRemove : selectionTeams)
        {
            // Checks if the selected home team is a team in the list of teams
            if (statsToRemove.equals(homeTeamBox))
            {
                // Subtracts one from the selected teams matchesPlayed
                statsToRemove.matchesPlayed = (statsToRemove.matchesPlayed - 1);

                // If the home team won the match
                if (hasWon())
                {
                    // Subtracts one from the team's matchesWon
                    statsToRemove.matchesWon--;
                }

                // Stores the home wins for the whole match
                int homeWin = playingTeams(homeTeamBox);

                // Adds the total set wins to the selected team's setsWon variable
                statsToRemove.setSetsWon(statsToRemove.setsWon - homeWin);
            }
            // Checks if the selected away team is a team in the list of teams
            else if (statsToRemove.equals(awayTeamBox))
            {
                // Subtracts one from the selected teams matchesPlayed
                statsToRemove.matchesPlayed = (statsToRemove.matchesPlayed - 1);

                // If the away team won the match
                if (!hasWon())
                {
                    // Subtracts one from the team's matchesWon
                    statsToRemove.matchesWon--;
                }

                // Stores the away wins for the whole match
                int awayWin = playingTeams(awayTeamBox);

                // Adds the total set wins to the selected team's setsWon variable
                statsToRemove.setSetsWon(statsToRemove.setsWon - awayWin);
            }
        }
    }

    public int playingTestTeams(Teams homeOrAway)
    {
        // Used to temporarily store the selected team's set wins
        int teamSetWin = 0;

        // Loops through the list of matches
        for (int playStats = 0; playStats < scoreSheet.size(); playStats++)
        {
            // Checks if the argument passed is equal to the value of homeOrAway
            if (homeOrAway.equals(selectionTeams.get(0)) || homeOrAway.equals(selectionTeams.get(1)))
            {
                // Gets the set wins from the match
                teamSetWin = scoreSheet.get(playStats).teamSetWins[0];
            }
            // Checks if the argument passed is equal to the value of homeOrAway
            else if (homeOrAway.equals(selectionTeams.get(1)) || homeOrAway.equals(selectionTeams.get(3)))
            {
                // Gets the set wins from the match
                teamSetWin = scoreSheet.get(playStats).teamSetWins[1];
            }
        }
        return teamSetWin;
    }


    public void testTeamStats(Teams homeTeam, Teams awayTeam)
    {
        // Loops through the list of teams
        for (int stats = 0; stats < selectionTeams.size(); stats++) {

            // Checks if the selected home team is in the list of teams
            if (selectionTeams.get(stats).equals(homeTeam))
            {
                // Adds 1 to the selected home team's matchPlayed function
                selectionTeams.get(stats).matchesPlayed = (selectionTeams.get(stats).matchesPlayed + 1);


                // If hasWon is equal to true (If home team won the match)
                if (hasWon())
                {
                    // Adds one to the home team's matchesWon
                    selectionTeams.get(stats).matchesWon++;
                }

                // Stores the home wins for the whole match
                int homeWin = playingTestTeams(homeTeam);

                // Adds the total set wins to the selected team's setsWon variable
                selectionTeams.get(stats).setSetsWon(selectionTeams.get(stats).setsWon + homeWin);

                if (homeTeam == selectionTeams.get(1))
                {
                    selectionTeams.get(stats).setsWon = selectionTeams.get(stats).setsWon - 1;
                }
            }
            // Checks if the selected away team is in the list of teams
            else if (selectionTeams.get(stats).equals(awayTeam))
            {
                // Adds 1 to the selected away team's matchPlayed function
                selectionTeams.get(stats).setMatchesPlayed(selectionTeams.get(stats).matchesPlayed + 1);

                // If hasWon is equal to false (If away team won the match)
                if (!hasWon())
                {
                    // Adds one to the away team's matchesWon
                    selectionTeams.get(stats).matchesWon++;
                }

                // Stores the away wins for the whole match
                int awayWin = playingTestTeams(awayTeam);

                // Adds the total set wins to the selected team's setsWon variable
                selectionTeams.get(stats).setSetsWon(selectionTeams.get(stats).setsWon + awayWin);
            }
        }
    }


    /*
        Function Name: initialize
        Arguments: URL location & ResourceBundle resources
        Description: The code inside this function will either
                     run continuously whilst the program is running
                     or run at the beginning of the program.
    */


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Adds values to the combo Box
        homeTeamX.setItems(selectionTeams);
        // Allows for selection of values
        homeTeamX.getSelectionModel();

        // Adds values to the combo Box
        awayTeamX.setItems(selectionTeams);
        // Allows for selection of values
        awayTeamX.getSelectionModel();

        // Hides the team drop down combo Boxes and button on the view page
        previousHomeTeam.setVisible(false);
        previousAwayTeam.setVisible(false);
        findPreviousMatch.setVisible(false);

        // Hides buttons and labels
        hideScoreSheet.setVisible(true);
        findMatch.setVisible(false);
        calculateModified.setVisible(false);
        scoreSheetResponse.setVisible(true);
        createScoreSheet.setVisible(false);

        scoreSheetResponse.setText("Please select either new sheet or modify sheet");

        /// the 100 sec timer
        // Creates a runnable
        Runnable runnable = ()-> {

            try{
                while(true) // continuously runs until the program is closed
                {
                    TimeUnit.SECONDS.sleep(100); // Waits 100 seconds
                    stats.clear(); // Clears the stats every time
                    TeamStats eachTeamsStats = new TeamStats(selectionTeams); // Creates a new object
                    // Generates the team stats for each of the teams depending on their matches
                    eachTeamsStats.setTotalTeamStats();
                    // Adds the class to an array so that the view button can output the stats
                    stats.add(eachTeamsStats.toString());
                }
            } catch (InterruptedException e) { // If the program is ended this avoids an error
                // Outputs the stack trace
                e.printStackTrace();
            }
        };

        Thread teamStatDelay =  new Thread(runnable); // Creates a thread
        teamStatDelay.setDaemon(true); // Prevents the JVM from closing and abandoning daemon threads
        teamStatDelay.start(); // Starts the 100 second timer

        // Filton Team
        Teams filton = new Teams();

        // Adds a new team
        filton.setTeam("filton");

        // Adds a new player to the team
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


        // Creates a new match object for the teams filton and uwe
        Match filtonVsUwe = new Match(selectionTeams.get(0), selectionTeams.get(1), selectionTeams.get(0).getPlayer().get(0),
                selectionTeams.get(0).getPlayer().get(1), selectionTeams.get(1).getPlayer().get(0), selectionTeams.get(1).getPlayer().get(1));

        // Creates a new scoresheet
        filtonVsUwe.inputMatches("11:2", "3:11", "11:5",
                "11:9", "11:1", "11:1",
                "1:11", "5:11", "11:6",
                "11:2", "3:11", "11:5",
                "0:11", "1:11", "2:11");

        // Adds new match to scoreSheet array
        scoreSheet.add(filtonVsUwe);

        testTeamStats(selectionTeams.get(0), selectionTeams.get(1));


        // Creates a new match object for the teams uwe and page
        Match uweVsPage = new Match(selectionTeams.get(1), selectionTeams.get(3), selectionTeams.get(1).getPlayer().get(0),
                selectionTeams.get(1).getPlayer().get(1), selectionTeams.get(3).getPlayer().get(0), selectionTeams.get(3).getPlayer().get(1));


        uweVsPage.inputMatches("11:2", "3:11", "11:5",
                "11:9", "11:1", "11:1",
                "1:11", "5:11", "11:6",
                "11:2", "3:11", "11:5",
                "0:11", "1:11", "2:11");

        scoreSheet.add(uweVsPage);

        testTeamStats(selectionTeams.get(1), selectionTeams.get(3));
    }

}
