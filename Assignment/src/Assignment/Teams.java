package Assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Teams {
    // public ObservableList<String> team = FXCollections.observableArrayList();
    public String teamName = "";
    //public ArrayList<String> playersList = new ArrayList<>();
    public List<String> playersList = new ArrayList<>();

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

    public List<String> getPlayer()
    {
        return playersList;
    }

    public String getPlayerString()
    {
        String display = "";
        for (String eachPlayer : playersList)
        {
            //return "\n" + "Player:" + eachPlayer;
            display += "\n" + "Player:" + eachPlayer;
        }
        //return String.join(",", playersList);
        return display;
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
