package productions.pudl.siege.Data.GeneralObjects;

public class GeneralRanksObject
{
    private GeneralRankObject APAC;
    private GeneralRankObject EMEA;
    private GeneralRankObject NCSA;

    public GeneralRanksObject(GeneralRankObject APAC, GeneralRankObject EMEA, GeneralRankObject NCSA)
    {
        setAPAC(APAC);
        setEMEA(EMEA);
        setNCSA(NCSA);
    }

    public GeneralRankObject getAPAC() {
        return APAC;
    }

    public void setAPAC(GeneralRankObject APAC) {
        this.APAC = APAC;
    }

    public GeneralRankObject getEMEA() {
        return EMEA;
    }

    public void setEMEA(GeneralRankObject EMEA) {
        this.EMEA = EMEA;
    }

    public GeneralRankObject getNCSA() {
        return NCSA;
    }

    public void setNCSA(GeneralRankObject NCSA) {
        this.NCSA = NCSA;
    }

    @Override
    public String toString()
    {
        return ('"' + "ranks" + '"' + ":{" + getAPAC().toString() + "," + getEMEA().toString() + "," + getNCSA().toString());
    }
}
