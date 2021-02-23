package Assignment;
public class Reports {
    String competingTeam;
    int matchesWon = 0;
    int matchesPlayed = 0;
    int setsWon = 0;

    public Reports(String competingTeam)
    {
        this.competingTeam = competingTeam;
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
