package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Teams {
    public String teamName = "";

    public ObservableList<String> playersList = FXCollections.observableArrayList();

    // The team stats
    public int matchesPlayed = 0;
    public int matchesWon = 0;
    public int setsWon = 0;

    /*
        Constructor Name: Teams
        Arguments: N/A
        Description: The team class's constructor.
    */

    public Teams()
    {

    }

    /*
        Function Name: setTeam
        Arguments: String teamsName
        Description: Sets the team's name.
    */

    public void setTeam(String teamsName)
    {
        this.teamName = teamsName;

    }

    /*
        Function Name: getTeam
        Arguments: N/A
        Description: Returns the team's name.
    */

    public String getTeam()
    {
        return teamName;
    }

    /*
        Function Name: setPlayer
        Arguments: String player
        Description: Adds a new player to the team.
    */

    public void setPlayer(String player)
    {
        this.playersList.add(player);
    }

    /*
        Function Name: getPlayer
        Arguments: N/A
        Description: Returns the list of players.
    */

    public ObservableList<String> getPlayer()
    {
        return playersList;
    }

     /*
        Function Name: getMatchesPlayed
        Arguments: N/A
        Description: Returns the team's matches played.
    */

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /*
        Function Name: setMatchesPlayed
        Arguments: int matchesPlayed
        Description: Sets the matches played.
    */

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    /*
        Function Name: getMatchesWon
        Arguments: N/A
        Description: Returns the team's matches won.
    */

    public int getMatchesWon() {
        return matchesWon;
    }

    /*
        Function Name: setMatchesWon
        Arguments: int matchesWon
        Description: Sets the matches won.
    */

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    /*
        Function Name: getSetsWon
        Arguments: N/A
        Description: Returns the matches won.
    */

    public int getSetsWon() {
        return setsWon;
    }

    /*
        Function Name: setSetsWon
        Arguments: int setsWon
        Description: Sets the sets won.
    */

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    /*
        Function Name: toString
        Arguments: N/A
        Description: Returns a string of the teams class.
    */

    public String toString()
    {
        return "Team: " + String.join("", this.teamName);
    }
}
