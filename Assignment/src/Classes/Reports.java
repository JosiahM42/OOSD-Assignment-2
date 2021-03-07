package Classes;

import javafx.collections.ObservableList;

public class Reports {
    String competingTeam;
    ObservableList<Teams> competingTeams;
    int matchesWon = 0;
    int matchesPlayed = 0;
    int setsWon = 0;

//    public Reports(String competingTeam, int matchesWon, int matchesPlayed, int setsWon)
//    {
//        this.competingTeam = competingTeam;
//        this.matchesWon = matchesWon;
//        this.matchesPlayed = matchesPlayed;
//        this.setsWon = setsWon;
//    }

    public Reports(ObservableList<Teams> competingTeams)
    {
        this.competingTeams = competingTeams;

    }

//    for (int allTeams = 0; allTeams < selectionOfTeams.size(); allTeams++)
//    {
//        this.competingTeam = selectionOfTeams.get(allTeams).teamName;
//        this.matchesWon = selectionOfTeams.get(allTeams).getMatchesWon();
//        this.matchesPlayed = selectionOfTeams.get(allTeams).getMatchesPlayed();
//        this.setsWon = selectionOfTeams.get(allTeams).getSetsWon();
//    }

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
