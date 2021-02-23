package Assignment;

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

    public void setTeamFixtures()
    {
        String matchSet = teamA + " Home Vs " + teamB + " Away";
        teamFixtures.add(matchSet);
    }

    public List<String> getTeamFixtures()
    {
        return teamFixtures;
    }

    public String toString()
    {
        //String output = String.join("\n", teamFixtures);
        String output = "";
        for (String teams : teamFixtures)
        {
            output = teams + "\n";
        }
        // return String.join("\n", teamFixtures);
        return output;
    }
}
