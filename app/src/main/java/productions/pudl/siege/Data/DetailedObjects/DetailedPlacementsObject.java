package productions.pudl.siege.Data.DetailedObjects;

public class DetailedPlacementsObject
{
    private int global;
    private int APAC;
    private int EMEA;
    private int NCSA;

    public DetailedPlacementsObject(int global, int APAC, int EMEA, int NCSA)
    {
        setGlobal(global);
        setAPAC(APAC);
        setEMEA(EMEA);
        setNCSA(NCSA);
    }

    public int getGlobal() {
        return global;
    }

    public void setGlobal(int global) {
        this.global = global;
    }

    public int getAPAC() {
        return APAC;
    }

    public void setAPAC(int APAC) {
        this.APAC = APAC;
    }

    public int getEMEA() {
        return EMEA;
    }

    public void setEMEA(int EMEA) {
        this.EMEA = EMEA;
    }

    public int getNCSA() {
        return NCSA;
    }

    public void setNCSA(int NCSA) {
        this.NCSA = NCSA;
    }
}
