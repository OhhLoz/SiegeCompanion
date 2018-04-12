package productions.pudl.siege.Data.GeneralObjects;

public class GeneralRankObject
{
    private String region;
    private double mmr;
    private int rank;

    public GeneralRankObject(String region, double mmr, int rank)
    {
        setRegion(region);
        setMMR(mmr);
        setRank(rank);
    }

    private String getRegion() {
        return region;
    }

    private void setRegion(String region) {
        this.region = region;
    }

    private double getMMR() {
        return mmr;
    }

    private void setMMR(double mmr) {
        this.mmr = mmr;
    }

    private int getRank() {
        return rank;
    }

    private void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString()
    {
        return ('"' + getRegion() + '"' + ":{" + '"' + "mmr" + '"' + ":" + getMMR() + "," + '"' + "rank" + '"' + ":" + getRank() + "}");
    }
}
