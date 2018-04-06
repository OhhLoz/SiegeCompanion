package productions.pudl.siege.Data.GeneralObjects;

class GeneralLastPlayedObject
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

    public int getCasual() {
        return casual;
    }

    public void setCasual(int casual) {
        this.casual = casual;
    }

    public int getRanked() {
        return ranked;
    }

    public void setRanked(int ranked) {
        this.ranked = ranked;
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
}
