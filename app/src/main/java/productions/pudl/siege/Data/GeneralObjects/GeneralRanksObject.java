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

    private GeneralRankObject getAPAC() {
        return APAC;
    }

    private void setAPAC(GeneralRankObject APAC) {
        this.APAC = APAC;
    }

    private GeneralRankObject getEMEA() {
        return EMEA;
    }

    private void setEMEA(GeneralRankObject EMEA) {
        this.EMEA = EMEA;
    }

    private GeneralRankObject getNCSA() {
        return NCSA;
    }

    private void setNCSA(GeneralRankObject NCSA) {
        this.NCSA = NCSA;
    }

    @Override
    public String toString()
    {
        return ('"' + "ranks" + '"' + ":{" + getAPAC().toString() + "," + getEMEA().toString() + "," + getNCSA().toString() + "}");
    }
}
