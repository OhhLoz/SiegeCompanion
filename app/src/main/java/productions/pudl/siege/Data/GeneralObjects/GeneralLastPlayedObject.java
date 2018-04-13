package productions.pudl.siege.Data.GeneralObjects;

public class GeneralLastPlayedObject
{
    private String casual;
    private String ranked;
    private String lastPlayed;

    public GeneralLastPlayedObject(String casual, String ranked, String lastPlayed)
    {
        setCasual(casual);
        setRanked(ranked);
        setLastPlayed(lastPlayed);
    }

    private String getCasual() {
        return casual;
    }

    private void setCasual(String casual) {
        this.casual = casual;
    }

    private String getRanked() {
        return ranked;
    }

    private void setRanked(String ranked) {
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
        return ('"' + "lastplayed" + '"' + ":{" + '"' + "casual" + '"' + ":" + getCasual() + "," + '"' + "ranked" + '"' + ":" + getRanked() + "," + '"' + "last_played" + '"' + ":" + getLastPlayed() + "}");
    }
}
