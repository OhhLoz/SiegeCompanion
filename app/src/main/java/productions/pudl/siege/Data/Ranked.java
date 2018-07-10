package productions.pudl.siege.Data;

public class Ranked
{

    private String updateTime;
    private double skillMean;
    private int season;
    private String region;
    private String userID;
    private int pastSeasonWins;
    private int pastSeasonLosses;
    private double maxmmr;
    private double mmr;
    private int wins;
    private int losses;
    private int abandons;
    private double standardDeviation;
    private int rank;
    private double nextRankMMR;
    private double prevRankMMR;
    private int maxRank;

    public Ranked(String updateTime, double skillMean, int season, String region, String userID, int pastSeasonWins, int pastSeasonLosses, double maxmmr, double mmr, int wins, int losses, int abandons, double standardDeviation, int rank, double nextRankMMR, double prevRankMMR, int maxRank) {
        setUpdateTime(updateTime);
        setSkillMean(skillMean);
        setSeason(season);
        setRegion(region);
        setUserID(userID);
        setPastSeasonWins(pastSeasonWins);
        setPastSeasonLosses(pastSeasonLosses);
        setMaxmmr(maxmmr);
        setMmr(mmr);
        setWins(wins);
        setLosses(losses);
        setAbandons(abandons);
        setStandardDeviation(standardDeviation);
        setRank(rank);
        setNextRankMMR(nextRankMMR);
        setPrevRankMMR(prevRankMMR);
        setMaxRank(maxRank);
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public double getSkillMean() {
        return skillMean;
    }

    public void setSkillMean(double skillMean) {
        this.skillMean = skillMean;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getPastSeasonWins() {
        return pastSeasonWins;
    }

    public void setPastSeasonWins(int pastSeasonWins) {
        this.pastSeasonWins = pastSeasonWins;
    }

    public int getPastSeasonLosses() {
        return pastSeasonLosses;
    }

    public void setPastSeasonLosses(int pastSeasonLosses) {
        this.pastSeasonLosses = pastSeasonLosses;
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

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getNextRankMMR() {
        return nextRankMMR;
    }

    public void setNextRankMMR(double nextRankMMR) {
        this.nextRankMMR = nextRankMMR;
    }

    public double getPrevRankMMR() {
        return prevRankMMR;
    }

    public void setPrevRankMMR(double prevRankMMR) {
        this.prevRankMMR = prevRankMMR;
    }

    public int getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(int maxRank) {
        this.maxRank = maxRank;
    }

    public String toString()
    {
        String temp = "";
        return temp;
    }
}