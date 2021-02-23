package Assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Match {

    public ObservableList<String> leagueTeams = FXCollections.observableArrayList();
    public ObservableList<String> leaguePlayers = FXCollections.observableArrayList();

    public Match()
    {

    }

    // home team and away team cannot equal the same thing
    public void setLeagueTeams(ObservableList<Teams> teamsList)
    {
        for (int playTeams = 0; playTeams < teamsList.size(); playTeams++)
        {
            leagueTeams.add(teamsList.get(playTeams).teamName);
        }
    }

    public ObservableList<String> getLeagueTeams()
    {
        return leagueTeams;
    }

//    public String toString()
//    {
//        return "Team: " + String.join("", this.leagueTeams);
//    }


}
