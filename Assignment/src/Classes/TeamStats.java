package Classes;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TeamStats extends Reports {

    List<String> totalTeamStats = new ArrayList<>();

    /*
        Constructor Name: TeamStats
        Arguments: ObservableList<Teams> competingTeams
        Description: The TeamStats class's constructor.
    */

    public TeamStats (ObservableList<Teams> competingTeams)
    {
        super(competingTeams);
    }

    /*
        Function Name: setTotalTeamStats
        Arguments: N/A
        Description: Creates a string of each team's
                     stats.
    */

    public void setTotalTeamStats()
    {
        // Loops through the teams array
        for (Teams stats : competingTeams)
        {
            // Gets the teams variables
            String statsForTeam = stats.teamName + ": Matches Played: " + stats.getMatchesPlayed() + ", Matches Won: "
                    + stats.getMatchesWon() + ", Sets Won: " + stats.getSetsWon();

            // Adds the list of team stats
            totalTeamStats.add(statsForTeam);
        }
    }

    /*
        Function Name: getTotalTeamStats
        Arguments: N/A
        Description: Returns the totalTeamStats array.
    */

    public List<String> getTotalTeamStats()
    {
        return totalTeamStats;
    }

    /*
        Function Name: toString
        Arguments: N/A
        Description: Returns a string of the TeamStats class.
    */

    public String toString()
    {
        String output = "" ;

        // Loops through the totalTeamStats array
        for (String stats : totalTeamStats)
        {
            // Returns each of the team's stats one by one
            output += stats + "\n" ;
        }
        return output;
    }
}
