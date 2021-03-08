package Classes;

import java.util.ArrayList;
import java.util.List;

public class Sets {

    public String homePlayer = "";
    public String awayPlayer = "";
    public int homeTeamPoint = 0;
    public int awayTeamPoint = 0;
    public int homeTotalPoint = 0;
    public int awayTotalPoint = 0;
    public ArrayList<Game> setList = new ArrayList<>();

     /*
        Constructor Name: Sets
        Arguments: N/A
        Description: The Sets class's constructor.
    */

    public Sets() {

    }

    /*
        Function Name: getHomePlayer
        Arguments: N/A
        Description: Returns the home player.
    */

    public String getHomePlayer()
    {
        return homePlayer;
    }

    /*
        Function Name: setHomePlayer
        Arguments: String homePlayer
        Description: Sets the home player.
    */

    public void setHomePlayer(String homePlayer)
    {
        this.homePlayer = homePlayer;
    }

    /*
        Function Name: getAwayPlayer
        Arguments: N/A
        Description: Returns the away player.
    */

    public String getAwayPlayer()
    {
        return awayPlayer;
    }

    /*
        Function Name: setAwayPlayer
        Arguments: String awayPlayer
        Description: Sets the away player.
    */

    public void setAwayPlayer(String awayPlayer)
    {
        this.awayPlayer = awayPlayer;
    }

    /*
        Function Name: getHomeTeamPoint
        Arguments: N/A
        Description: Returns the homeTeamPoint.
    */

    public int getHomeTeamPoint()
    {
        return homeTeamPoint;
    }

    /*
        Function Name: setHomeTeamPoint
        Arguments: int playerPoint
        Description: Sets the home team point.
    */

    public void setHomeTeamPoint(int playerPoint)
    {
        this.homeTeamPoint = playerPoint;
    }

    /*
        Function Name: getAwayTeamPoint
        Arguments: N/A
        Description: Returns the awayTeamPoint.
    */

    public int getAwayTeamPoint()
    {
        return awayTeamPoint;
    }

    /*
        Function Name: setAwayTeamPoint
        Arguments: int playerPoint
        Description: Sets the away team point.
    */

    public void setAwayTeamPoint(int playerPoint)
    {
        this.awayTeamPoint = playerPoint;
    }

    /*
        Function Name: getHomeTotalPoint
        Arguments: N/A
        Description: Returns the homeTotalPoint.
    */

    public int getHomeTotalPoint() {
        return homeTotalPoint;
    }

    /*
        Function Name: getAwayTotalPoint
        Arguments: N/A
        Description: Returns the awayTotalPoint.
    */

    public int getAwayTotalPoint() {
        return awayTotalPoint;
    }

    /*
        Function Name: singleSet
        Arguments: int homeSetPointA, int awaySetPointA, int homeSetPointB,
                   int awaySetPointB, int homeSetPointC, int awaySetPointC
        Description: Creates a set of three games and appends them to an array.
    */

    public void singleSet(int homeSetPointA, int awaySetPointA, int homeSetPointB,
                          int awaySetPointB, int homeSetPointC, int awaySetPointC)
    {
        // Creates an object of type Game
        Game gameOne = new Game();

        // Sets the home point based on the integer value passed
        gameOne.setHomePlayerPoint(homeSetPointA);
        // Sets the away point based on the integer value passed
        gameOne.setAwayPlayerPoint(awaySetPointA);
        // Appends the object to the list of sets
        setList.add(gameOne);

        Game gameTwo = new Game();

        gameTwo.setHomePlayerPoint(homeSetPointB);
        gameTwo.setAwayPlayerPoint(awaySetPointB);
        setList.add(gameTwo);

        Game gameThree = new Game();
        gameThree.setHomePlayerPoint(homeSetPointC);
        gameThree.setAwayPlayerPoint(awaySetPointC);
        setList.add(gameThree);
    }

    /*
        Function Name: getSetList
        Arguments: N/A
        Description: Returns the list of sets.
    */

    public ArrayList<Game> getSetList() {
        return setList;
    }

    /*
        Function Name: calculateSetTotal
        Arguments: N/A
        Description: Calculates the final set total
                     and returns which team won.
    */

    public String calculateSetTotal()
    {
        // Loops through the list of sets
        for (int setGames = 0; setGames < setList.size(); setGames++)
        {
            // Checks which team scored more in each game
            if(setList.get(setGames).teamWin())
            {
                // Home team won
                homeTeamPoint += 1;
            }
            else
            {
                // Away team won
                awayTeamPoint += 1;
            }
        }

        // Checks to see which team won the set
        if (homeTeamPoint == 2 || homeTeamPoint == 3)
        {
            homeTotalPoint = 1;
        }
        else if (awayTeamPoint == 2 || awayTeamPoint == 3)
        {
            awayTotalPoint = 1;
        }

        // Returns who won the set
        if (homeTeamPoint > 1)
        {
            return "Home Win: " + homeTeamPoint ;
        }
        else
        {
            //+ "Home Loss" + homeTeamPoint
            return "Away Win: " + awayTeamPoint;
        }
    }

    /*
        Function Name: toString
        Arguments: N/A
        Description: Returns a string of the Sets class.
    */

    public String toString()
    {
        return "Set{" + homePlayer + " Vs " + awayPlayer + " = ";
    }

}
