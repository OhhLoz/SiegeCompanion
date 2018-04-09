package productions.pudl.siege.Data.GeneralObjects;

public class GeneralLastPlayedObject
{
    private int casual;
    private int ranked;
    private String lastPlayed;

    public GeneralLastPlayedObject(int casual, int ranked, String lastPlayed)
    {
        setCasual(casual);
        setRanked(ranked);
        setLastPlayed(lastPlayed);
    }

    private int getCasual() {
        return casual;
    }

    private void setCasual(int casual) {
        this.casual = casual;
    }

    private int getRanked() {
        return ranked;
    }

    private void setRanked(int ranked) {
        this.ranked = ranked;
    }

    private String getLastPlayed() {
        return lastPlayed;
    }

    private void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    @Override
    public String toString()
    {
        return ('"' + "lastplayed" + '"' + ":{" + '"' + "casual" + '"' + ":" + String.valueOf(getCasual()) + "," + '"' + "ranked" + '"' + ":" + String.valueOf(getRanked()) + "," + '"' + "last_played" + '"' + ":" + getLastPlayed() + "}");
    }
}
