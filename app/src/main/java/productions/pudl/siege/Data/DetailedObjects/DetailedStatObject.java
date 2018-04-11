package productions.pudl.siege.Data.DetailedObjects;

public class DetailedStatObject
{
    private int deaths;
    private int kills;
    private int lost;
    private int played;
    private int timePlayed;
    private int won;

    public DetailedStatObject(int deaths, int kills, int lost, int played, int timePlayed, int won)
    {
        setDeaths(deaths);
        setKills(kills);
        setLost(lost);
        setPlayed(played);
        setTimePlayed(timePlayed);
        setWon(won);
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }
}
