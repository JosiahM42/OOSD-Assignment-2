package Classes;

public class Game {
    public int homePlayerPoint = 0;
    public int awayPlayerPoint = 0;
    // game win
    // game indivdual
    //set = 3
    //match all sets

    public Game()
    {

    }

    public int getHomePlayerPoint()
    {
        return homePlayerPoint;
    }

    public void setHomePlayerPoint(int homePoints)
    {
        this.homePlayerPoint = homePoints;
    }

    public int getAwayPlayerPoint()
    {
        return awayPlayerPoint;
    }

    public void setAwayPlayerPoint(int awayPoints)
    {
        this.awayPlayerPoint = awayPoints;
    }

    public boolean teamWin ()
    {
        if (homePlayerPoint > awayPlayerPoint)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return homePlayerPoint + ":" + awayPlayerPoint;
    }
}
