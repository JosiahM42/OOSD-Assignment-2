package Classes;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//public class TeamRank extends Reports {
public class TeamRank extends TeamStats {

    public List<String> teamRankings = new ArrayList<>();

    /*
        Constructor Name: TeamRank
        Arguments: ObservableList<Teams> competingTeams
        Description: The TeamRank class's constructor.
    */

    public TeamRank (ObservableList<Teams> competingTeams)
    {
        super(competingTeams);
    }

    /*
        Function Name: setTeamRankings
        Arguments: N/A
        Description: Sorts the competingTeams array
                     by how many sets each team has won.
                     The function then creates a string of
                     the team stat rankings.
    */

    public void setTeamRankings()
    {
        // Sorts the competingTeams array by how many sets each team has won
        Collections.sort(competingTeams, new Comparator<Teams>(){
            @Override
            public int compare(Teams o1, Teams o2) {
                // Check if the first team is equal to the second
                // if it is the sort skips it
                if (o1.getSetsWon() == o2.getSetsWon())
                    return 0;
                // Returns a sorted list of teams that are sorted from high to low
                return o1.getSetsWon() > o2.getSetsWon() ? -1 : 1;
            }
        });

        // Loops through list of teams
        for (Teams stats : competingTeams)
        {
            // Puts each team's name and stats into a string format
            String statsForTeam = stats.teamName + ": Matches Played: " + stats.getMatchesPlayed() + ", Matches Won: "
                    + stats.getMatchesWon() + ", Sets Won: " + stats.getSetsWon();

            // Stores that string into an array
            teamRankings.add(statsForTeam);
        }
    }

    /*
        Function Name: getTeamRankings
        Arguments: N/A
        Description: Returns the teamRankings array.
    */

    public List<String> getTeamRankings()
    {
        return teamRankings;
    }

    /*
        Function Name: toString
        Arguments: N/A
        Description: Returns a string of the TeamRank class.
    */

    public String toString()
    {
        String output = "" ;

        // Loops through the teamRankings array
        for (String ranking : teamRankings)
        {
            // Returns each of the team's ranked stats one by one
            output += ranking + "\n" ;
        }
        return output;
    }

}
