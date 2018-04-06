package productions.pudl.siege.Data.GeneralObjects;

public class GeneralRankObject
{
    String region;
    double mmr;
    int rank;

    public GeneralRankObject(String region, double mmr, int rank)
    {
        setRegion(region);
        setMMR(mmr);
        setRank(rank);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getMMR() {
        return mmr;
    }

    public void setMMR(double mmr) {
        this.mmr = mmr;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
