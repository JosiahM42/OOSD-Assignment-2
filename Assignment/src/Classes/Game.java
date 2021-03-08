package Classes;

public class Game {
    public int homePlayerPoint = 0;
    public int awayPlayerPoint = 0;

    /*
        Constructor Name: Game
        Arguments: N/A
        Description: The Game class's constructor.
    */

    public Game()
    {

    }

    /*
        Function Name: getHomePlayerPoint
        Arguments: N/A
        Description: Returns the home player point for the individual game.
    */

    public int getHomePlayerPoint()
    {
        return homePlayerPoint;
    }

    /*
        Function Name: setHomePlayerPoint
        Arguments: int homePoints
        Description: Sets the homePlayerPoint
                     based on the argument passed.
    */

    public void setHomePlayerPoint(int homePoints)
    {
        this.homePlayerPoint = homePoints;
    }

    /*
        Function Name: getAwayPlayerPoint
        Arguments: N/A
        Description: Returns the away player point for the individual game.
    */

    public int getAwayPlayerPoint()
    {
        return awayPlayerPoint;
    }

    /*
        Function Name: setAwayPlayerPoint
        Arguments: int awayPoints
        Description: Sets the awayPlayerPoint
                     based on the argument passed.
    */

    public void setAwayPlayerPoint(int awayPoints)
    {
        this.awayPlayerPoint = awayPoints;
    }

    /*
        Function Name: teamWin
        Arguments: N/A
        Description: Returns the wining player based
                     on the game score.
    */

    public boolean teamWin ()
    {
        // Checks if the home player has scored more points than the away player
        if (homePlayerPoint > awayPlayerPoint)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
        Function Name: toString
        Arguments: N/A
        Description: Returns a string of the Game class.
    */

    public String toString()
    {
        return homePlayerPoint + ":" + awayPlayerPoint;
    }
}
