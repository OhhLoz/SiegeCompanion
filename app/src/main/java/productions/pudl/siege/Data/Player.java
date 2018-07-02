package productions.pudl.siege.Data;

import android.util.Log;

public class Player
{
    private String userName;
    private String platformName;
    private String userID;

    public Player(String userName, String platformName, String userID)
    {
        setUserName(userName);
        setPlatformName(platformName);
        setUserID(userID);
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

    private void setUserName(String user)
    {
        this.userName = user;
    }

    private void setPlatformName(String platform)
    {
        this.platformName = platform;
    }

    private void setUserID(String ID)
    {
        this.userID = ID;
    }

    public String toString()
    {
        String temp = "Player: " + getPlayerName() + ", Platform: " + getPlatform() + ", UserID: " + getUserID();
        return temp;
    }
}
