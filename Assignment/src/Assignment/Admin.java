package Assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    public Teams team = new Teams();
    //public ObservableList<Teams> teamNameList = FXCollections.observableArrayList();
    //public List<Teams> teamsList = new ArrayList<>();

    public Admin()
    {

    }

    public void setTeam(String teamsName)
    {
        team.setTeam(teamsName);
        //teamNameList.add(team);
        //this.team.add(teamsName);

    }

//    public void setTeamsList()
//    {
//        teamsList.add(team);
//    }

//    public ObservableList<Teams> getTeam()
//    {
//        return teamNameList;
//        //return team;
//    }

    public void setPlayer(String player)
    {
        team.setPlayer(player);
        //team.setPlayers();
    }
//    {
//        this.teamPlayer.setPlayer(teamPlayer);
//    }
//
//    public List<String> getPlayer()
//    {
//        return teamPlayer.getPlayer();
//    }

//    public void setPlayerTeam(String teamss)
//    {
//        this.teamPlayer.setPlayerTeam(teamss);
//        // team.players.addAll(teamPlayer.player);
//        //String teamTodadd = teamsList.contains(teamss);
//
//
//    }
}
