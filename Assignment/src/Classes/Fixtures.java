package Classes;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Fixtures {

    public String teamA = "";
    public String teamB = "";

    public List<String> teamFixtures = new ArrayList<>();

    /*
        Constructor Name: Fixtures
        Arguments: String teamA & String teamB
        Description: The Fixtures class's constructor.
    */

    public Fixtures(String teamA, String teamB)
    {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    /*
        Function Name: setTeamFixtures
        Arguments: ArrayList<Match> results
        Description: Sets match fixtures and sets
                     all fixtures to NP (Not Played)
                     and if the match has been played,
                     it will update with the exact score.
    */

    public void setTeamFixtures(ArrayList<Match> results)
    {
        String matchSet = teamA + " Home Vs " + teamB + " Away = ";

        // Checks if the match has been played
        if (hasBeenPlayed(results) == true)
        {
            // If so add the match score to the end of it
            matchSet += playedResult(results);
        }
        else
        {
            // By default this will be appended to the end
            // but it will update in runtime
            matchSet += "NP";
        }

        // Adds the final match fixture to the array
        teamFixtures.add(matchSet);

    }

    /*
        Function Name: getTeamFixtures
        Arguments: N/A
        Description: Returns the array of fixtures.
    */

    public List<String> getTeamFixtures()
    {
        return teamFixtures;
    }

    /*
        Function Name: playedResult
        Arguments: ArrayList<Match> results
        Description: Returns the match total.
    */

    public String playedResult(ArrayList<Match> results)
    {
        // Used to store the match total
        String finalMatchScore = "";

        // Loops through the array of matches
        for (Match playedMatch : results)
        {
            // Checks if the home team and the away team are equal
            // to the home and away teams created in the constructor
            if (playedMatch.getHomeTeam().getTeam().equals(teamA)
                    && playedMatch.getAwayTeam().getTeam().equals(teamB))
            {
                // Appends the match total to the final match score variable
                finalMatchScore = playedMatch.getMatchTotal();
            }

        }
        return finalMatchScore;
    }

    /*
        Function Name: hasBeenPlayed
        Arguments: ArrayList<Match> results
        Description: Returns if the match has been
                     played or not.
    */

    public boolean hasBeenPlayed(ArrayList<Match> results)
    {
        boolean beenPlayed = false;

        // Checks if the list of matches is empty
        if (results.isEmpty())
        {
            // This will return false
            return beenPlayed;
        }
        else
        {
            // Loops through the list of matches
            for (Match playedMatch : results)
            {
                // Checks if the home team and the away team are equal
                // to the home and away teams created in the constructor
                if (playedMatch.getHomeTeam().getTeam().equals(teamA)
                        && playedMatch.getAwayTeam().getTeam().equals(teamB))
                {
                    // If the match has been played
                    beenPlayed = true;
                }
            }
        }
        return beenPlayed;
    }

    /*
        Function Name: toString
        Arguments: N/A
        Description: Returns a string of the Fixtures class.
    */

    public String toString()
    {
        String output = "";
        for (String teams : teamFixtures)
        {
            output = teams + "\n";
        }

        return output;
    }
}
