package Classes;

import java.util.ArrayList;

public class Match {

    public Teams homeTeam;
    public Teams awayTeam;
    public String homePlayerA, homePlayerB = "";
    public String awayPlayerA, awayPlayerB = "";
    public String matchTotal = "";
    public int[] teamSetWins = new int[2];

    public ArrayList<Sets> matchSinglesSets = new ArrayList<>();
    public ArrayList<String> matchWins = new ArrayList<>();


    public Match(Teams homeTeam, Teams awayTeam, String homePlayerA, String homePlayerB,
                 String awayPlayerA, String awayPlayerB)
    {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

        this.homePlayerA = homePlayerA;
        this.homePlayerB = homePlayerB;

        this.awayPlayerA = awayPlayerA;
        this.awayPlayerB = awayPlayerB;


        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new DoubleSet());

    }

    public Teams getHomeTeam() {
        return homeTeam;
    }

    public Teams getAwayTeam() {
        return awayTeam;
    }

    public String getHomePlayerA() {
        return homePlayerA;
    }

    public String getHomePlayerB() {
        return homePlayerB;
    }

    public String getAwayPlayerA() {
        return awayPlayerA;
    }

    public String getAwayPlayerB() {
        return awayPlayerB;
    }

    public int[] splitScoresString(String setsTextField)
    {
        String[] scoresStringVersion = setsTextField.split(":");
        int[] scoresIntegerVersion =  new int[2];

        scoresIntegerVersion[0] = Integer.valueOf(scoresStringVersion[0]);
        scoresIntegerVersion[1] = Integer.valueOf(scoresStringVersion[1]);

        return scoresIntegerVersion;
    }

    public void inputMatches(String singleSetOneA, String singleSetTwoA, String singleSetThreeA,
                             String singleSetOneB, String singleSetTwoB, String singleSetThreeB,
                             String singleSetOneC, String singleSetTwoC, String singleSetThreeC,
                             String singleSetOneD, String singleSetTwoD, String singleSetThreeD,
                             String doubleSetOne, String doubleSetTwo, String doubleSetThree)
    {

        matchSinglesSets.get(0).setHomePlayer(homePlayerA);
        matchSinglesSets.get(0).setAwayPlayer(awayPlayerA);
        int[] singleScoreOneA = splitScoresString(singleSetOneA);
        int[] singleScoreTwoA = splitScoresString(singleSetTwoA);
        int[] singleScoreThreeA = splitScoresString(singleSetThreeA);
        matchSinglesSets.get(0).singleSet(singleScoreOneA[0],singleScoreOneA[1],singleScoreTwoA[0],
                singleScoreTwoA[1], singleScoreThreeA[0], singleScoreThreeA[1]);
        matchWins.add(matchSinglesSets.get(0).calculateSetTotal());

        int home = matchSinglesSets.get(0).getHomeTotalPoint();
        int away = matchSinglesSets.get(0).getAwayTotalPoint();


        matchSinglesSets.get(1).setHomePlayer(homePlayerB);
        matchSinglesSets.get(1).setAwayPlayer(awayPlayerA);
        int[] singleScoreOneB = splitScoresString(singleSetOneB);
        int[] singleScoreTwoB = splitScoresString(singleSetTwoB);
        int[] singleScoreThreeB = splitScoresString(singleSetThreeB);
        matchSinglesSets.get(1).singleSet(singleScoreOneB[0],singleScoreOneB[1],singleScoreTwoB[0],
                singleScoreTwoB[1], singleScoreThreeB[0],singleScoreThreeB[1]);
        matchWins.add(matchSinglesSets.get(1).calculateSetTotal());

        home += matchSinglesSets.get(1).getHomeTotalPoint();
        away += matchSinglesSets.get(1).getAwayTotalPoint();

        matchSinglesSets.get(2).setHomePlayer(homePlayerA);
        matchSinglesSets.get(2).setAwayPlayer(awayPlayerB);
        int[] singleScoreOneC = splitScoresString(singleSetOneC);
        int[] singleScoreTwoC = splitScoresString(singleSetTwoC);
        int[] singleScoreThreeC = splitScoresString(singleSetThreeC);
        matchSinglesSets.get(2).singleSet(singleScoreOneC[0],singleScoreOneC[1],singleScoreTwoC[0],
                singleScoreTwoC[1], singleScoreThreeC[0],singleScoreThreeC[1]);
        matchWins.add(matchSinglesSets.get(2).calculateSetTotal());

        home += matchSinglesSets.get(2).getHomeTotalPoint();
        away += matchSinglesSets.get(2).getAwayTotalPoint();

        matchSinglesSets.get(3).setHomePlayer(homePlayerB);
        matchSinglesSets.get(3).setAwayPlayer(awayPlayerB);
        int[] singleScoreOneD = splitScoresString(singleSetOneD);
        int[] singleScoreTwoD = splitScoresString(singleSetTwoD);
        int[] singleScoreThreeD = splitScoresString(singleSetThreeD);
        matchSinglesSets.get(3).singleSet(singleScoreOneD[0],singleScoreOneD[1],singleScoreTwoD[0],
                singleScoreTwoD[1], singleScoreThreeD[0],singleScoreThreeD[1]);
        matchWins.add(matchSinglesSets.get(3).calculateSetTotal());

        home += matchSinglesSets.get(3).getHomeTotalPoint();
        away += matchSinglesSets.get(3).getAwayTotalPoint();

        int[] doubleScoreOne = splitScoresString(doubleSetOne);
        int[] doubleScoreTwo = splitScoresString(doubleSetTwo);
        int[] doubleScoreThree = splitScoresString(doubleSetThree);
        matchSinglesSets.get(4).singleSet(doubleScoreOne[0],doubleScoreOne[1],doubleScoreTwo[0],
                doubleScoreTwo[1], doubleScoreThree[0],doubleScoreThree[1]);
        matchWins.add(matchSinglesSets.get(4).calculateSetTotal());

        home += matchSinglesSets.get(4).getHomeTotalPoint();
        away += matchSinglesSets.get(4).getAwayTotalPoint();

        matchTotal = home + ":" + away;
        teamSetWins[0] = home;
        teamSetWins[1] = away;

    }

    public int teamWon()
    {
        if (teamSetWins[0] > teamSetWins[1])
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    public ArrayList<Sets> getMatchSinglesSets()
    {
        return matchSinglesSets;
    }

    public String getMatchTotal()
    {
        return matchTotal;
    }

    public ArrayList<String> getMatchWins()
    {
        return matchWins;
    }

    public int[] getTeamSetWins() {
        return teamSetWins;
    }

    public String toString()
    {

        String display = "Match: " + homeTeam + " Vs " + awayTeam;

        for (Sets eachPlayer : matchSinglesSets)
        {
            display += "\n" + eachPlayer.toString() + String.join(",", eachPlayer.getSetList().toString()) + "}";
        }

        return display;
    }


}
