package Assignment;

import java.util.ArrayList;
import java.util.List;

public class TeamStats extends Reports{

    List<String> totalTeamStats = new ArrayList<>();

    public TeamStats (String competingTeam)
    {
        super(competingTeam);
    }

    public void setTotalTeamStats()
    {
        String statsForTeam = competingTeam + ": Matches Played: " + matchesPlayed + ", Matches Won: "
                + matchesWon + ", Sets Won: " + setsWon;

        totalTeamStats.add(statsForTeam);
    }

    public List<String> getTotalTeamStats()
    {
        return totalTeamStats;
    }

    public String toString()
    {
        return String.join("", totalTeamStats);
    }
}
