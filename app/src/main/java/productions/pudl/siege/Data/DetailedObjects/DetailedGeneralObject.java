package productions.pudl.siege.Data.DetailedObjects;

public class DetailedGeneralObject
{
    private int assists;
    private int blindKills;
    private int bulletsFired;
    private int bulletsHit;
    private int dbno;
    private int dbnoAssists;
    private int deaths;
    private int gadgetsDestroyed;
    private int headshot;
    private int hostageDefense;
    private int hostageRescue;
    private int kills;
    private int lost;
    private int meleekills;
    private int penetrationKills;
    private int played;
    private int rappelBreaches;
    private int revives;
    private int revivesDenied;
    private int serverAggression;
    private int serverDefender;
    private int serversHacked;
    private int suicides;
    private int timePlayed;
    private int won;

    public DetailedGeneralObject(int assists, int blindKills, int bulletsFired, int bulletsHit, int dbno, int dbnoAssists, int deaths, int gadgetsDestroyed, int headshot, int hostageDefense, int hostageRescue, int kills, int lost, int meleekills, int penetrationKills, int played, int rappelBreaches, int revives, int revivesDenied, int serverAggression, int serverDefender, int serversHacked, int suicides, int timePlayed, int won)
    {
        setAssists(assists);
        setBlindKills(blindKills);
        setBulletsFired(bulletsFired);
        setBulletsHit(bulletsHit);
        setDbno(dbno);
        setDboAssists(dbnoAssists);
        setDeaths(deaths);
        setGadgetsDestroyed(gadgetsDestroyed);
        setHeadshot(headshot);
        setHostageDefense(hostageDefense);
        setHostageRescue(hostageRescue);
        setKills(kills);
        setLost(lost);
        setMeleekills(meleekills);
        setPenetrationKills(penetrationKills);
        setPlayed(played);
        setRappelBreaches(rappelBreaches);
        setRevives(revives);
        setRevivesDenied(revivesDenied);
        setServerAggression(serverAggression);
        setServerDefender(serverDefender);
        setServersHacked(serversHacked);
        setSuicides(suicides);
        setTimePlayed(timePlayed);
        setWon(won);
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getBlindKills() {
        return blindKills;
    }

    public void setBlindKills(int blindKills) {
        this.blindKills = blindKills;
    }

    public int getBulletsFired() {
        return bulletsFired;
    }

    public void setBulletsFired(int bulletsFired) {
        this.bulletsFired = bulletsFired;
    }

    public int getBulletsHit() {
        return bulletsHit;
    }

    public void setBulletsHit(int bulletsHit) {
        this.bulletsHit = bulletsHit;
    }

    public int getDbno() {
        return dbno;
    }

    public void setDbno(int dbno) {
        this.dbno = dbno;
    }

    public int getDbnoAssists() {
        return dbnoAssists;
    }

    public void setDboAssists(int dboAssists) {
        this.dbnoAssists = dboAssists;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getGadgetsDestroyed() {
        return gadgetsDestroyed;
    }

    public void setGadgetsDestroyed(int gadgetsDestroyed) {
        this.gadgetsDestroyed = gadgetsDestroyed;
    }

    public int getHeadshot() {
        return headshot;
    }

    public void setHeadshot(int headshot) {
        this.headshot = headshot;
    }

    public int getHostageDefense() {
        return hostageDefense;
    }

    public void setHostageDefense(int hostageDefense) {
        this.hostageDefense = hostageDefense;
    }

    public int getHostageRescue() {
        return hostageRescue;
    }

    public void setHostageRescue(int hostageRescue) {
        this.hostageRescue = hostageRescue;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getMeleekills() {
        return meleekills;
    }

    public void setMeleekills(int meleekills) {
        this.meleekills = meleekills;
    }

    public int getPenetrationKills() {
        return penetrationKills;
    }

    public void setPenetrationKills(int penetrationKills) {
        this.penetrationKills = penetrationKills;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getRappelBreaches() {
        return rappelBreaches;
    }

    public void setRappelBreaches(int rappelBreaches) {
        this.rappelBreaches = rappelBreaches;
    }

    public int getRevives() {
        return revives;
    }

    public void setRevives(int revives) {
        this.revives = revives;
    }

    public int getRevivesDenied() {
        return revivesDenied;
    }

    public void setRevivesDenied(int revivesDenied) {
        this.revivesDenied = revivesDenied;
    }

    public int getServerAggression() {
        return serverAggression;
    }

    public void setServerAggression(int serverAggression) {
        this.serverAggression = serverAggression;
    }

    public int getServerDefender() {
        return serverDefender;
    }

    public void setServerDefender(int serverDefender) {
        this.serverDefender = serverDefender;
    }

    public int getServersHacked() {
        return serversHacked;
    }

    public void setServersHacked(int serversHacked) {
        this.serversHacked = serversHacked;
    }

    public int getSuicides() {
        return suicides;
    }

    public void setSuicides(int suicides) {
        this.suicides = suicides;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }
}
