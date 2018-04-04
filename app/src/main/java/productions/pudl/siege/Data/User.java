package productions.pudl.siege.Data;

public class User
{
    private String userName;
    private String platformName;

    public User(String platformName, String userName)
    {
        setUserName(userName);
        setPlatformName(platformName);
    }

    public String getPlayerName()
    {
        return this.userName;
    }

    public String getPlatform()
    {
        return this.platformName;
    }

    public void setUserName(String user)
    {
        this.userName = user;
    }

    public void setPlatformName(String platform)
    {
        this.platformName = platform;
    }
    
    public boolean checkValidName()
    {
        //check with API using this.userName and this.platformName if valid account exists in R6S
        return false;
    }

    public void getProfilePic()
    {
    }

    public void getCasualStats()
    {
    }

    public void getRankedStats()
    {
    }

    public void getTerroristHuntStats()
    {
    }

    public void getOperatorStats()
    {
    }
}