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

    /*
        Constructor Name: Match
        Arguments: Teams homeTeam, Teams awayTeam, String homePlayerA,
                   String homePlayerB, String awayPlayerA, String awayPlayerB
        Description: The Match class's constructor.
    */

    public Match(Teams homeTeam, Teams awayTeam, String homePlayerA, String homePlayerB,
                 String awayPlayerA, String awayPlayerB)
    {
        // Sets the home team and away team
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

        // Sets the home and away players
        this.homePlayerA = homePlayerA;
        this.homePlayerB = homePlayerB;

        this.awayPlayerA = awayPlayerA;
        this.awayPlayerB = awayPlayerB;


        // Adds objects to the match array for easy assignment
        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new Sets());
        matchSinglesSets.add(new DoubleSet());

    }

    /*
        Function Name: getHomeTeam
        Arguments: N/A
        Description: Returns the home team.
    */

    public Teams getHomeTeam() {
        return homeTeam;
    }

    /*
        Function Name: getAwayTeam
        Arguments: N/A
        Description: Returns the away team.
    */

    public Teams getAwayTeam() {
        return awayTeam;
    }

    /*
        Function Name: getHomePlayerA
        Arguments: N/A
        Description: Returns the first home player.
    */

    public String getHomePlayerA() {
        return homePlayerA;
    }

    /*
        Function Name: getHomePlayerB
        Arguments: N/A
        Description: Returns the second home player.
    */

    public String getHomePlayerB() {
        return homePlayerB;
    }

    /*
        Function Name: getAwayPlayerA
        Arguments: N/A
        Description: Returns the first away player.
    */

    public String getAwayPlayerA() {
        return awayPlayerA;
    }

    /*
        Function Name: getAwayPlayerB
        Arguments: N/A
        Description: Returns the second away player.
    */

    public String getAwayPlayerB() {
        return awayPlayerB;
    }

    /*
        Function Name: splitScoresString
        Arguments: String setsTextField
        Description: Converts a string into
                     a list of integers.
    */

    public int[] splitScoresString(String setsTextField)
    {
        // Stores the list of integers as a string divided by a colon
        String[] scoresStringVersion = setsTextField.split(":");

        // Used to store the end list of integers
        int[] scoresIntegerVersion =  new int[2];

        // Converts the string values in the string array into integers
        // The first element will be used as the home team point
        // The second element will be used as the away team point
        scoresIntegerVersion[0] = Integer.valueOf(scoresStringVersion[0]);
        scoresIntegerVersion[1] = Integer.valueOf(scoresStringVersion[1]);

        return scoresIntegerVersion;
    }

    /*
        Function Name: inputMatches
        Arguments: String singleSetOneA, String singleSetTwoA, String singleSetThreeA,
                   String singleSetOneB, String singleSetTwoB, String singleSetThreeB,
                   String singleSetOneC, String singleSetTwoC, String singleSetThreeC,
                   String singleSetOneD, String singleSetTwoD, String singleSetThreeD,
                   String doubleSetOne, String doubleSetTwo, String doubleSetThree
        Description: Uses four sets objects and a double set object
                     to create a match.
    */

    public void inputMatches(String singleSetOneA, String singleSetTwoA, String singleSetThreeA,
                             String singleSetOneB, String singleSetTwoB, String singleSetThreeB,
                             String singleSetOneC, String singleSetTwoC, String singleSetThreeC,
                             String singleSetOneD, String singleSetTwoD, String singleSetThreeD,
                             String doubleSetOne, String doubleSetTwo, String doubleSetThree)
    {

        // Sets the first sets object's home and away players
        matchSinglesSets.get(0).setHomePlayer(homePlayerA);
        matchSinglesSets.get(0).setAwayPlayer(awayPlayerA);

        // Converts the string version of the score into integers and stores the returned array
        int[] singleScoreOneA = splitScoresString(singleSetOneA);
        int[] singleScoreTwoA = splitScoresString(singleSetTwoA);
        int[] singleScoreThreeA = splitScoresString(singleSetThreeA);

        // Passes the home and away scores stored in the arrays above as arguments for Sets' singleSet function
        matchSinglesSets.get(0).singleSet(singleScoreOneA[0],singleScoreOneA[1],singleScoreTwoA[0],
                singleScoreTwoA[1], singleScoreThreeA[0], singleScoreThreeA[1]);

        // Appends the final set winner to the match wins array
        matchWins.add(matchSinglesSets.get(0).calculateSetTotal());

        // Used to store the final match score
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

        // Stores the final match score in a string format
        matchTotal = home + ":" + away;

        // Stores the total team wins
        teamSetWins[0] = home;
        teamSetWins[1] = away;

    }

    /*
        Function Name: teamWon
        Arguments: N/A
        Description: Returns the wining team based
                     on the final match score.
    */

    public int teamWon()
    {
        // Check if home team match score is more than the away team match score
        if (teamSetWins[0] > teamSetWins[1])
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /*
        Function Name: getMatchSinglesSets
        Arguments: N/A
        Description: Returns the list of sets .
    */

    public ArrayList<Sets> getMatchSinglesSets()
    {
        return matchSinglesSets;
    }

    /*
        Function Name: getMatchTotal
        Arguments: N/A
        Description: Returns the match total.
    */

    public String getMatchTotal()
    {
        return matchTotal;
    }

    /*
        Function Name: getMatchWins
        Arguments: N/A
        Description: Returns the match wins.
    */

    public ArrayList<String> getMatchWins()
    {
        return matchWins;
    }

    /*
        Function Name: getTeamSetWins
        Arguments: N/A
        Description: Returns the set wins.
    */

    public int[] getTeamSetWins() {
        return teamSetWins;
    }

    /*
        Function Name: toString
        Arguments: N/A
        Description: Returns a string of the Match class.
    */

    public String toString()
    {
        // This will be returned first
        String display = "Match: " + homeTeam + " Vs " + awayTeam;

        // Then this will loop
        for (Sets eachPlayer : matchSinglesSets)
        {
            // Every set will be be displayed one by one
            display += "\n" + eachPlayer.toString() +
                    eachPlayer.getSetList().toString().replaceAll("(^\\[|\\]$)", "") + "}";
        }
        // Finally, the match score will be displayed at the end
        return display + "\nMatch Score: " + getMatchTotal();
    }


}
