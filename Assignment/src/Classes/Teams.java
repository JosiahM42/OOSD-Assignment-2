package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Teams {
    // public ObservableList<String> team = FXCollections.observableArrayList();
    public String teamName = "";
    //public ArrayList<String> playersList = new ArrayList<>();
    //public List<String> playersList = new ArrayList<>();
    public ObservableList<String> playersList = FXCollections.observableArrayList();
    public int matchesPlayed = 0;
    public int matchesWon = 0;
    public int setsWon = 0;

    public Teams() //String name
    {
//        teamName =  name;
    }

    public void setTeam(String teamsName)
    {
        this.teamName = teamsName;
        // team.add(teamsName);
        // this.team.add(teamsName);

    }

    public String getTeam()
    {
        return teamName;
        //return team;
    }

    public void setPlayer(String player)
    {
        this.playersList.add(player);
    }

    public ObservableList<String> getPlayer()
    {
        return playersList;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public String toString()
    {
        String output = "Team: " + String.join("", this.teamName);
//        for (String eachPlayer : playersList)
//        {
//            output += "\n" + "Player:" + eachPlayer;
//        }
        return output;
    }
}
