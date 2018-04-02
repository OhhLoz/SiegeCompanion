package productions.pudl.siege;

public class PreviousSearches {
    private String playerName;
    private String platform;

    public PreviousSearches(String platform, String playerName)
    {
        this.playerName = playerName;
        this.platform = platform;
    }

    public String getPlayerName()
    {
        return this.playerName;
    }

    public String getPlatform()
    {
        return this.platform;
    }
}