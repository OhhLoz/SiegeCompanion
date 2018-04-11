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
}
