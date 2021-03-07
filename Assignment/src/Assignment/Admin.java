package Assignment;

import Classes.Fixtures;
import Classes.Teams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    //public Teams team = new Teams();
    public List<Teams> teamList = new ArrayList<>();
//    public ObservableList<Teams> teamList = FXCollections.observableArrayList();
    public List<Teams> homeTeams = new ArrayList<>();
    public List<Teams> awayTeams = new ArrayList<>();
    //public List<Teams> teamsList = new ArrayList<>();

    public List<Fixtures> matches = new ArrayList<>();
    public List<String> stats = new ArrayList<>();

    public Admin()
    {

    }

    public void addTeam(String teamsName)
    {
        Teams team = new Teams();
        team.setTeam(teamsName);
        teamList.add(team);
        homeTeams.add(team);
        awayTeams.add(team);
    }

    public List<Teams> getTeam()
    {
        return teamList;
        //return team;
    }


    public void registerNewPlayer(String player) {
        for (int playersTeams = 0; playersTeams < teamList.size(); playersTeams++) {
            teamList.get(playersTeams).setPlayer(player);
        }
    }

//    public void generateMatches()
//    {
//        for (int aTeam = 0; aTeam < teamList.size(); aTeam++)
//        {
//            for (int bTeam = 0; bTeam < teamList.size(); bTeam++)
//            {
//                if (aTeam != bTeam)
//                {
//                    Fixtures teamMatches = new Fixtures(teamList.get(aTeam).teamName, teamList.get(bTeam).teamName);
//                    teamMatches.setTeamFixtures();
//                    matches.add(teamMatches);
//                    //System.out.println(matches);
//                }
//
//            }
//        }
//    }

    public List<Fixtures> getMatches() {
        return matches;
    }

    //    public void setPlayer(String player)
//    {
//        //team.setPlayer(player);
//        for (int playersTeams = 0; playersTeams < teamList.size(); playersTeams++) {
//            teamList.get(playersTeams).setPlayer(player);
//        }
//        //team.setPlayers();
//    }
//    {
//        this.teamPlayer.setPlayer(teamPlayer);
//    }
//
    public ObservableList<String> getPlayer()
    {
        ObservableList<String> playersList = FXCollections.observableArrayList();
        for (int playersTeams = 0; playersTeams < teamList.size(); playersTeams++) {
            playersList = teamList.get(playersTeams).getPlayer();
        }
        return playersList;
    }

//    public void setPlayerTeam(String teamss)
//    {
//        this.teamPlayer.setPlayerTeam(teamss);
//        // team.players.addAll(teamPlayer.player);
//        //String teamTodadd = teamsList.contains(teamss);
//
//
//    }

    public String toString()
    {
        String output = "";
        for (int playersTeams = 0; playersTeams < teamList.size(); playersTeams++) {
            output = "Team: " + String.join("", teamList.get(playersTeams).teamName);

        }
        return output;
    }
}
