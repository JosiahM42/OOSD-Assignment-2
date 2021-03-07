package Classes;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TeamStats extends Reports {

    List<String> totalTeamStats = new ArrayList<>();

//    public TeamStats (String competingTeam, int matchesWon, int matchesPlayed, int setsWon)
//    {
//        super(competingTeam, matchesWon, matchesPlayed, setsWon);
//    }

    public TeamStats (ObservableList<Teams> competingTeams)
    {
        super(competingTeams);
    }

//    public void setTotalTeamStats()
//    {
//        String statsForTeam = competingTeam + ": Matches Played: " + matchesPlayed + ", Matches Won: "
//                + matchesWon + ", Sets Won: " + setsWon;
//
//        totalTeamStats.add(statsForTeam);
//    }

    public void setTotalTeamStats()
    {
        for (Teams stats : competingTeams)
        {
            String statsForTeam = stats.teamName + ": Matches Played: " + stats.getMatchesPlayed() + ", Matches Won: "
                    + stats.getMatchesWon() + ", Sets Won: " + stats.getSetsWon();
            totalTeamStats.add(statsForTeam);
        }
    }


    public List<String> getTotalTeamStats()
    {
        return totalTeamStats;
    }

    public String toString()
    {
        String output = "" ;
        for (String stats : totalTeamStats)
        {
            output += stats + "\n" ;
        }
        return output;
        // return String.join("", totalTeamStats);
    }
}
