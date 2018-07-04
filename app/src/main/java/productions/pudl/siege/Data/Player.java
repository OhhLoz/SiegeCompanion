package productions.pudl.siege.Data;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Player
{
    private String userName;
    private String platformName;
    private String userID;
    private Level levelObj;

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

    public void getUserPicture(ImageView imgView)
    {
        Picasso.get().load("https://ubisoft-avatars.akamaized.net/" + getUserID() + "/default_256_256.png").into(imgView);
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

    private void setLevelObj(Level levelObj)
    {
        this.levelObj = levelObj;
    }

    public String toString()
    {
        String temp = "Player: " + getPlayerName() + ", Platform: " + getPlatform() + ", UserID: " + getUserID();
        return temp;
    }
}
