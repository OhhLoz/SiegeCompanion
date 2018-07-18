package productions.pudl.siege.Data;

public class Operator
{
    private String name;
    private String CTU;
    private int kills;
    private int deaths;
    private double kd;
    private int dbno;
    private int headshot;
    private int meleekills;
    private int mostused;
    private int wins;
    private int losses;
    private int played;
    private double wl;
    private int totalxp;
    private int timeplayed;
    private String userID;

    private int special1;
    private int special2;
    private int special3;
    private boolean isSpecial1;
    private boolean isSpecial2;
    private boolean isSpecial3;
    private String special1Desc;
    private String special2Desc;
    private String special3Desc;

    public Operator(String userID, String name, String CTU, int kills, int deaths, double kd, int dbno, int headshot, int meleekills, int mostused, int wins, int losses, int played, double wl, int totalxp, int timeplayed)
    {
        setUserID(userID);
        setName(name);
        setCTU(CTU);
        setKills(kills);
        setDeaths(deaths);
        setKd(kd);
        setDbno(dbno);
        setHeadshot(headshot);
        setMeleekills(meleekills);
        setMostused(mostused);
        setWins(wins);
        setLosses(losses);
        setPlayed(played);
        setWl(wl);
        setTotalxp(totalxp);
        setTimeplayed(timeplayed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCTU() {
        return CTU;
    }

    public void setCTU(String CTU) {
        this.CTU = CTU;
    }

    public int getTimeplayed() {
        return timeplayed;
    }

    public void setTimeplayed(int timeplayed) {
        this.timeplayed = timeplayed;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public double getKd() {
        return kd;
    }

    public void setKd(double kd) {
        this.kd = kd;
    }

    public int getDbno() {
        return dbno;
    }

    public void setDbno(int dbno) {
        this.dbno = dbno;
    }

    public int getHeadshot() {
        return headshot;
    }

    public void setHeadshot(int headshot) {
        this.headshot = headshot;
    }

    public int getMeleekills() {
        return meleekills;
    }

    public void setMeleekills(int meleekills) {
        this.meleekills = meleekills;
    }

    public int getMostused() {
        return mostused;
    }

    public void setMostused(int mostused) {
        this.mostused = mostused;
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

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public double getWl() {
        return wl;
    }

    public void setWl(double wl) {
        this.wl = wl;
    }

    public int getTotalxp() {
        return totalxp;
    }

    public void setTotalxp(int totalxp) {
        this.totalxp = totalxp;
    }
}
