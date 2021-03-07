package Classes;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamRank extends Reports {

    public List<String> teamRankings = new ArrayList<>();

//    public TeamRank(String competingTeam, int matchesWon, int matchesPlayed, int setsWon)
//    {
//        super(competingTeam, matchesWon, matchesPlayed, setsWon);
//    }

    public TeamRank (ObservableList<Teams> competingTeams)
    {
        super(competingTeams);
    }

//    public void setTeamRankings()
//    {
//        String statsForTeam = competingTeam + ": Matches Played: " + matchesPlayed + ", Matches Won: "
//                + matchesWon + ", Sets Won: " + setsWon;
//
//        teamRankings.add(statsForTeam);
//    }


    public void setTeamRankings()
    {
        Collections.sort(competingTeams, new Comparator<Teams>(){
            @Override
            public int compare(Teams o1, Teams o2) {
                if (o1.getSetsWon() == o2.getSetsWon())
                    return 0;
                return o1.getSetsWon() > o2.getSetsWon() ? -1 : 1;
            }
        });

        for (Teams stats : competingTeams)
        {
            String statsForTeam = stats.teamName + ": Matches Played: " + stats.getMatchesPlayed() + ", Matches Won: "
                    + stats.getMatchesWon() + ", Sets Won: " + stats.getSetsWon();
            teamRankings.add(statsForTeam);
        }
    }

    public List<String> getTeamRankings()
    {
        return teamRankings;
    }

    public String toString()
    {
        String output = "" ;
        for (String ranking : teamRankings)
        {
            output += ranking + "\n" ;
        }
        return output;
        //return String.join("", teamRankings);
    }

}
