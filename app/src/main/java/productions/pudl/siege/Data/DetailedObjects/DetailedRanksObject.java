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
                return "Copper IV";
            case 2:
                return "Copper III";
            case 3:
                return "Copper II";
            case 4:
                return "Copper I";
            case 5:
                return "Bronze IV";
            case 6:
                return "Bronze III";
            case 7:
                return "Bronze II";
            case 8:
                return "Bronze I";
            case 9:
                return "Silver IV";
            case 10:
                return "Silver III";
            case 11:
                return "Silver II";
            case 12:
                return "Silver I";
            case 13:
                return "Gold IV";
            case 14:
                return "Gold III";
            case 15:
                return "Gold II";
            case 16:
                return "Gold I";
            case 17:
                return "Platinum III";
            case 18:
                return "Platinum II";
            case 19:
                return "Platinum I";
            case 20:
                return "Diamond";
        }
        return "Unranked";
    }
}
