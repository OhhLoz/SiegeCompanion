package productions.pudl.siege;

public class User
{
    private String userName;
    private String platformName;

    public User(String platformName, String userName)
    {
        this.userName = userName;
        this.platformName = platformName;
    }

    public String getPlayerName()
    {
        return this.userName;
    }

    public String getPlatform()
    {
        return this.platformName;
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