package productions.pudl.siege.Data.DetailedObjects;

public class DetailedGameModeObject
{
    private int bestScore;
    private int lost;
    private int played;
    private int won;

    public DetailedGameModeObject(int bestscore, int lost, int played, int won)
    {
        setBestScore(bestscore);
        setLost(lost);
        setPlayed(played);
        setWon(won);
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
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

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }
}
