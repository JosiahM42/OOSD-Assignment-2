package Assignment;

import Classes.Teams;
import javafx.collections.ObservableList;

public class Reports {
    ObservableList<Teams> competingTeams;
    int matchesWon = 0;
    int matchesPlayed = 0;
    int setsWon = 0;

    public Reports(ObservableList<Teams> competingTeams)
    {
        this.competingTeams = competingTeams;

    }

    public void setMatchesWon(int matchWon)
    {
        this.matchesWon = matchWon;
    }

    public int getMatchesWon()
    {
        return matchesWon;
    }

    public void setMatchesPlayed(int matchPlayed)
    {
        this.matchesPlayed = matchPlayed;
    }

    public int getMatchesPlayed()
    {
        return matchesPlayed;
    }

    public void setSetsWon(int setWon)
    {
        this.setsWon = setWon;
    }

    public int getSetsWonWon()
    {
        return setsWon;
    }

}
