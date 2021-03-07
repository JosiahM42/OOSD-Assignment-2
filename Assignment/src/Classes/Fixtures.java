package Classes;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Fixtures {

    public String teamA = "";
    public String teamB = "";

    public List<String> teamFixtures = new ArrayList<>();

    public Fixtures(String teamA, String teamB)
    {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public void setTeamFixtures(ArrayList<Match> results)
    {
        String matchSet = teamA + " Home Vs " + teamB + " Away = ";

        if (hasBeenPlayed(results) == true)
        {
            matchSet += playedResult(results);
        }
        else
        {
            matchSet += "NP";
        }

        teamFixtures.add(matchSet);

    }

    public List<String> getTeamFixtures()
    {
        return teamFixtures;
    }

    public String playedResult(ArrayList<Match> results)
    {
        String finalMatchScore = "";
        for (Match playedMatch : results)
        {
            if (playedMatch.getHomeTeam().getTeam().equals(teamA)
                    && playedMatch.getAwayTeam().getTeam().equals(teamB))
            {
                finalMatchScore = playedMatch.getMatchTotal();
            }

        }
        return finalMatchScore;
    }

    public boolean hasBeenPlayed(ArrayList<Match> results)
    {
        boolean beenPlayed = false;
        if (results.isEmpty())
        {
            return beenPlayed;
        }
        else
        {
            for (Match playedMatch : results)
            {
                if (playedMatch.getHomeTeam().getTeam().equals(teamA)
                        && playedMatch.getAwayTeam().getTeam().equals(teamB))
                {
                    beenPlayed = true;
                }
            }
        }
        return beenPlayed;
    }


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
