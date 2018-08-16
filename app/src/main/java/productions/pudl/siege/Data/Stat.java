package productions.pudl.siege.Data;

public class Stat
{
    private int casualWon;
    private int casualLost;
    private int casualPlayed;
    private int casualKills;
    private int casualDeaths;
    private int casualTimePlayed;
    private int rankedWon;
    private int rankedLost;
    private int rankedPlayed;
    private int rankedKills;
    private int rankedDeaths;
    private int rankedTimePlayed;

    public Stat(int casualWon, int casualLost, int casualPlayed, int casualKills, int casualDeaths, int casualTimePlayed, int rankedWon, int rankedLost, int rankedPlayed, int rankedKills, int rankedDeaths, int rankedTimePlayed) {
        setCasualWon(casualWon);
        setCasualLost(casualLost);
        setCasualPlayed(casualPlayed);
        setCasualKills(casualKills);
        setCasualDeaths(casualDeaths);
        setCasualTimePlayed(casualTimePlayed);
        setRankedWon(rankedWon);
        setRankedLost(rankedLost);
        setRankedPlayed(rankedPlayed);
        setRankedKills(rankedKills);
        setRankedDeaths(rankedDeaths);
        setRankedTimePlayed(rankedTimePlayed);
    }

    public int getCasualWon() {
        return casualWon;
    }

    public void setCasualWon(int casualWon) {
        this.casualWon = casualWon;
    }

    public int getCasualLost() {
        return casualLost;
    }

    public void setCasualLost(int casualLost) {
        this.casualLost = casualLost;
    }

    public int getCasualPlayed() {
        return casualPlayed;
    }

    public void setCasualPlayed(int casualPlayed) {
        this.casualPlayed = casualPlayed;
    }

    public int getCasualKills() {
        return casualKills;
    }

    public void setCasualKills(int casualKills) {
        this.casualKills = casualKills;
    }

    public int getCasualDeaths() {
        return casualDeaths;
    }

    public void setCasualDeaths(int casualDeaths) {
        this.casualDeaths = casualDeaths;
    }

    public int getCasualTimePlayed() {
        return casualTimePlayed;
    }

    public void setCasualTimePlayed(int casualTimePlayed) {
        this.casualTimePlayed = casualTimePlayed;
    }

    public int getRankedWon() {
        return rankedWon;
    }

    public void setRankedWon(int rankedWon) {
        this.rankedWon = rankedWon;
    }

    public int getRankedLost() {
        return rankedLost;
    }

    public void setRankedLost(int rankedLost) {
        this.rankedLost = rankedLost;
    }

    public int getRankedPlayed() {
        return rankedPlayed;
    }

    public void setRankedPlayed(int rankedPlayed) {
        this.rankedPlayed = rankedPlayed;
    }

    public int getRankedKills() {
        return rankedKills;
    }

    public void setRankedKills(int rankedKills) {
        this.rankedKills = rankedKills;
    }

    public int getRankedDeaths() {
        return rankedDeaths;
    }

    public void setRankedDeaths(int rankedDeaths) {
        this.rankedDeaths = rankedDeaths;
    }

    public int getRankedTimePlayed() {
        return rankedTimePlayed;
    }

    public void setRankedTimePlayed(int rankedTimePlayed) {
        this.rankedTimePlayed = rankedTimePlayed;
    }
    public String toString()
    {
        String temp = "Casual: " + getCasualKills() + " Kills, " + getCasualDeaths() + " Deaths, " + getCasualWon() + " Wins, " + getCasualLost() + " Losses, " + getCasualPlayed() + " Played, " + getCasualTimePlayed() + " Time Played \n" + "Ranked: " + getRankedKills() + " Kills, " + getRankedDeaths() + " Deaths, " + getRankedWon() + " Wins, " + getRankedLost() + " Losses, " + getRankedPlayed() + " Played, " + getRankedTimePlayed() + " Time Played ";
        return temp;
    }
}
