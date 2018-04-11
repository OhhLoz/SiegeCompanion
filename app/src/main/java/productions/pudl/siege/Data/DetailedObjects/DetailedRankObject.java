package productions.pudl.siege.Data.DetailedObjects;

public class DetailedRankObject
{
    private String region;

    private int wins;
    private int losses;
    private int abandons;
    private double maxmmr;
    private double mmr;
    private int rank;
    private int maxrank;
    private double skill_mean;
    private double skill_stdev;

    public DetailedRankObject(String region, int wins, int losses, int abandons, double maxmmr, double mmr, int rank, int maxrank, double skill_mean, double skill_stdev)
    {
        setRegion(region);
        setWins(wins);
        setLosses(losses);
        setAbandons(abandons);
        setMaxmmr(maxmmr);
        setMmr(mmr);
        setRank(rank);
        setMaxrank(maxrank);
        setSkill_mean(skill_mean);
        setSkill_stdev(skill_stdev);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getAbandons() {
        return abandons;
    }

    public void setAbandons(int abandons) {
        this.abandons = abandons;
    }

    public double getMaxmmr() {
        return maxmmr;
    }

    public void setMaxmmr(double maxmmr) {
        this.maxmmr = maxmmr;
    }

    public double getMmr() {
        return mmr;
    }

    public void setMmr(double mmr) {
        this.mmr = mmr;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getMaxrank() {
        return maxrank;
    }

    public void setMaxrank(int maxrank) {
        this.maxrank = maxrank;
    }

    public double getSkill_mean() {
        return skill_mean;
    }

    public void setSkill_mean(double skill_mean) {
        this.skill_mean = skill_mean;
    }

    public double getSkill_stdev() {
        return skill_stdev;
    }

    public void setSkill_stdev(double skill_stdev) {
        this.skill_stdev = skill_stdev;
    }
}
