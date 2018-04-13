package productions.pudl.siege.Data.DetailedObjects;

public class DetailedOperatorObject
{
    private int won;
    private int lost;
    private int kills;
    private int deaths;
    private int timePlayed;
    private String name;
    private String CTU;

    public DetailedOperatorObject(int won, int lost, int kills, int deaths, int timePlayed, String name, String CTU)
    {
        setWon(won);
        setLost(lost);
        setKills(kills);
        setDeaths(deaths);
        setTimePlayed(timePlayed);
        setName(name);
        setCTU(CTU);
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCTU() {
        return CTU;
    }

    public void setCTU(String CTU) {
        this.CTU = CTU;
    }
}
