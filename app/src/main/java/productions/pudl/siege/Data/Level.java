package productions.pudl.siege.Data;

public class Level
{
    private int XP;
    private String userID;
    private int lootboxProbability;
    private int level;

    public Level(int XP, String userID, int lootboxProbability, int level)
    {
        setXP(XP);
        setUserID(userID);
        setLootboxProbability(lootboxProbability);
        setlevel(level);
    }

    private void setXP(int XP)
    {
        this.XP = XP;
    }

    private void setUserID(String ID)
    {
        this.userID = ID;
    }

    private void setLootboxProbability(int lootboxProbability)
    {
        this.lootboxProbability = lootboxProbability;
    }

    private void setlevel(int level)
    {
        this.level = level;
    }

    public int getXP()
    {
        return XP;
    }

    public String getUserID()
    {
        return userID;
    }

    public int getLootboxProbability()
    {
        return lootboxProbability;
    }

    public int getLevel()
    {
        return level;
    }

    public String toString()
    {
        String temp = "userID: " + getUserID() + ", Level: " + getLevel() + ", xp: " + getXP() + ", Lootbox: " + getLootboxProbability();
        return temp;
    }
}
