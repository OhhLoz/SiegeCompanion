package productions.pudl.siege.Data.DetailedObjects;

public class DetailedRanksObject
{
    private DetailedRankObject APAC;
    private DetailedRankObject EMEA;
    private DetailedRankObject NCSA;
    private int season;

    public DetailedRanksObject(DetailedRankObject APAC, DetailedRankObject EMEA, DetailedRankObject NCSA, int season)
    {
        setAPAC(APAC);
        setEMEA(EMEA);
        setNCSA(NCSA);
        setSeason(season);
    }

    public DetailedRankObject getAPAC() {
        return APAC;
    }

    public void setAPAC(DetailedRankObject APAC) {
        this.APAC = APAC;
    }

    public DetailedRankObject getEMEA() {
        return EMEA;
    }

    public void setEMEA(DetailedRankObject EMEA) {
        this.EMEA = EMEA;
    }

    public DetailedRankObject getNCSA() {
        return NCSA;
    }

    public void setNCSA(DetailedRankObject NCSA) {
        this.NCSA = NCSA;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getMaxRank()
    {
        int apacRank = APAC.getRank();
        int ncsaRank = NCSA.getRank();
        int emeaRank = EMEA.getRank();

        int max = Math.max(apacRank, ncsaRank);
        if(max > ncsaRank)
            max = Math.max(apacRank, emeaRank);
        else
            max = Math.max(ncsaRank, emeaRank);

        return max;
    }

    public String getRankString(int rank)
    {
        switch(rank)
        {
            case 0:
                return "Unranked";
            case 1:
                return "Copper 4";
            case 2:
                return "Copper 3";
            case 3:
                return "Copper 2";
            case 4:
                return "Copper 1";
            case 5:
                return "Bronze 4";
            case 6:
                return "Bronze 3";
            case 7:
                return "Bronze 2";
            case 8:
                return "Bronze 1";
            case 9:
                return "Silver 4";
            case 10:
                return "Silver 3";
            case 11:
                return "Silver 2";
            case 12:
                return "Silver 1";
            case 13:
                return "Gold 4";
            case 14:
                return "Gold 3";
            case 15:
                return "Gold 2";
            case 16:
                return "Gold 1";
            case 17:
                return "Platinum 3";
            case 18:
                return "Platinum 2";
            case 19:
                return "Platinum 1";
            case 20:
                return "Diamond";
        }
        return "Unranked";
    }
}
