package productions.pudl.siege.Data;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Player
{
    private String userName;
    private String platformName;
    private String userID;
    private Level levelObj;
    private Stat playerStats;
    private Ranked playerRanked;
    private Operator playerOperator;

    public Player(String userName, String platformName, String userID)
    {
        setUserName(userName);
        setPlatformName(platformName);
        setUserID(userID);
    }

    public Player(String userName, String platformName, String userID, Level levelObj)
    {
        setUserName(userName);
        setPlatformName(platformName);
        setUserID(userID);
        setLevelObj(levelObj);
    }

    public String getPlayerName()
{
    return this.userName;
}

    public String getPlatform()
    {
        return this.platformName;
    }

    public String getUserID()
    {
        return this.userID;
    }

    public Ranked getRankedObj()
    {
        return this.playerRanked;
    }

    public Level getLevelObj()
    {
        return this.levelObj;
    }

    public Stat getStatObj()
    {
        return this.playerStats;
    }

    public void getUserPicture(ImageView imgView)
    {
        Picasso.get().load("https://ubisoft-avatars.akamaized.net/" + getUserID() + "/default_256_256.png").into(imgView);
    }

    public void getUserPicture(ImageView imgView, String userID)
    {
        Picasso.get().load("https://ubisoft-avatars.akamaized.net/" + userID + "/default_256_256.png").into(imgView);
    }

    public void setUserName(String user)
    {
        this.userName = user;
    }

    public void setPlatformName(String platform)
    {
        this.platformName = platform;
    }

    public void setUserID(String ID)
    {
        this.userID = ID;
    }

    public void setLevelObj(Level levelObj)
    {
        this.levelObj = levelObj;
    }

    public void setRankedObj(Ranked rankedObj)
    {
        this.playerRanked = rankedObj;
    }

    public void setStatsObj(Stat statObj)
    {
        this.playerStats = statObj;
    }

    public String toString()
    {
        String temp = "Player: " + getPlayerName() + ", Platform: " + getPlatform() + ", UserID: " + getUserID();
        return temp;
    }
}
