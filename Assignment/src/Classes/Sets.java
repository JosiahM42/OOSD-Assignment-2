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
    public List<Game> setList = new ArrayList<>();

    public Sets() {

    }

    public String getHomePlayer()
    {
        return homePlayer;
    }

    public void setHomePlayer(String homePlayer)
    {
        this.homePlayer = homePlayer;
    }

    public String getAwayPlayer()
    {
        return awayPlayer;
    }

    public void setAwayPlayer(String awayPlayer)
    {
        this.awayPlayer = awayPlayer;
    }

    public int getHomeTeamPoint()
    {
        return homeTeamPoint;
    }

    public void setHomeTeamPoint(int playerPoint)
    {
        this.homeTeamPoint = playerPoint;
    }

    public int getAwayTeamPoint()
    {
        return awayTeamPoint;
    }

    public void setAwayTeamPoint(int playerPoint)
    {
        this.awayTeamPoint = playerPoint;
    }

    public int getHomeTotalPoint() {
        return homeTotalPoint;
    }

    public int getAwayTotalPoint() {
        return awayTotalPoint;
    }

    public void singleSet(int homeSetPointA, int awaySetPointA, int homeSetPointB,
                          int awaySetPointB, int homeSetPointC, int awaySetPointC)
    {
        Game gameOne = new Game();
        gameOne.setHomePlayerPoint(homeSetPointA);
        gameOne.setAwayPlayerPoint(awaySetPointA);
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

    public List<Game> getSetList() {
        return setList;
    }

    public String calculateSetTotal()
    {
        for (int setGames = 0; setGames < setList.size(); setGames++)
        {
            if(setList.get(setGames).teamWin())
            {
                homeTeamPoint += 1;
            }
            else
            {
                awayTeamPoint += 1;
            }
        }


        if (homeTeamPoint == 2 || homeTeamPoint == 3)
        {
            homeTotalPoint = 1;
        }
        else if (awayTeamPoint == 2 || awayTeamPoint == 3)
        {
            awayTotalPoint = 1;
        }

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

    public String toString()
    {
        return "Set{" + homePlayer + " Vs " + awayPlayer + " = ";
    }

}
