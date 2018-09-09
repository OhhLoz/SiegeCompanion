package productions.pudl.siege.Data;

public class Stat
{
    private int generalWins;
    private int generalLost;
    private double generalWL;
    private int generalAssists;
    private int generalPlayed;
    private int generalKills;
    private int generalDeaths;
    private double generalKD;
    private int generalLevel;
    private int generalTimePlayed;
    private String generalTimePlayedStr;

    private int casualWon;
    private int casualLost;
    private double casualWL;
    private int casualPlayed;
    private int casualKills;
    private int casualDeaths;
    private double casualKD;
    private int casualTimePlayed;
    private String casualTimePlayedStr;

    private int rankedWon;
    private int rankedLost;
    private double rankedWL;
    private int rankedPlayed;
    private int rankedKills;
    private int rankedDeaths;
    private double rankedKD;
    private int rankedTimePlayed;
    private String rankedTimePlayedStr;

    private int weaponHeadshots;
    private double weaponHSHit;
    private double weaponHSKill;
    private int weaponBulletHit;
    private int weaponBulletFired;
    private double weaponAccuracy;
    private int weaponMeleeKills;
    private int weaponBlindKills;
    private int weaponPenetrations;
    private int weaponDBNO;
    private int weaponDBNOAssists;

    private int miscBarricade;
    private int miscDistanceTravelled;
    private int miscGadgetDestroyed;
    private int miscRappelBreach;
    private int miscReinforcementDeploy;
    private int miscRevives;
    private int miscRevivesDenied;
    private int miscSuicides;

    private int secureBestScore;
    private int secureWins;
    private int secureLosses;
    private double secureWL;
    private int securePlayed;
    private int secureTimePlayed;

    private int hostageBestScore;
    private int hostageWins;
    private int hostageLosses;
    private double hostageWL;
    private int hostagePlayed;
    private int hostageTimePlayed;

    private int bombBestScore;
    private int bombWins;
    private int bombLosses;
    private double bombWL;
    private int bombPlayed;
    private int bombTimePlayed;

    public Stat(int casualWon, int casualLost, int casualPlayed, int casualKills, int casualDeaths, int casualTimePlayed, int rankedWon, int rankedLost, int rankedPlayed, int rankedKills, int rankedDeaths, int rankedTimePlayed)
    {
        setCasualWon(casualWon);
        setCasualLost(casualLost);
        setCasualPlayed(casualPlayed);
        setCasualKills(casualKills);
        setCasualDeaths(casualDeaths);
        setCasualTimePlayed(casualTimePlayed);
        this.casualTimePlayedStr = calculateTimeplayed(casualTimePlayed);
        this.casualKD = calculateKD(casualKills, casualDeaths);
        this.casualWL = calculateWL(casualWon, casualPlayed);
        setRankedWon(rankedWon);
        setRankedLost(rankedLost);
        setRankedPlayed(rankedPlayed);
        setRankedKills(rankedKills);
        setRankedDeaths(rankedDeaths);
        setRankedTimePlayed(rankedTimePlayed);
        this.rankedTimePlayedStr = calculateTimeplayed(rankedTimePlayed);
        this.rankedKD = calculateKD(rankedKills,rankedDeaths);
        this.rankedWL = calculateWL(rankedWon, rankedPlayed);
    }

    public Stat(int generalKills, int generalDeaths, int generalAssists, int generalWins, int generalLosses, int generalPlayed, int generalLevel, int generalTimeplayed, int casualWon, int casualLost, int casualPlayed, int casualKills, int casualDeaths, int casualTimePlayed, int rankedWon, int rankedLost, int rankedPlayed, int rankedKills, int rankedDeaths, int rankedTimePlayed, int weaponHeadshots, int weaponBulletHit, int weaponBulletFired, int weaponBlindKills, int weaponMeleeKills, int weaponPenetrations, int weaponDBNO, int weaponDBNOAssists, int miscBarricade, int miscDistanceTravelled, int miscGadgetDestroyed, int miscRappelBreach, int miscReinforcementDeploy, int miscRevives, int miscRevivesDenied, int miscSuicides,  int secureBestScore, int secureWins, int secureLosses, int securePlayed, int secureTimePlayed, int hostageBestScore, int hostageWins, int hostageLosses, int hostagePlayed, int hostageTimePlayed, int bombBestScore, int bombWins, int bombLosses, int bombPlayed, int bombTimePlayed)
    {
        this.generalKills = generalKills;
        this.generalDeaths = generalDeaths;
        this.generalKD = calculateKD(generalKills, generalDeaths);
        this.generalWins = generalWins;
        this.generalLost = generalLosses;
        this.generalPlayed = generalPlayed;
        this.generalWL = calculateWL(generalWins, generalPlayed);
        this.generalAssists = generalAssists;
        this.generalLevel = generalLevel;
        this.generalTimePlayed = generalTimeplayed;
        this.generalTimePlayedStr = calculateTimeplayed(generalTimePlayed);

        setCasualWon(casualWon);
        setCasualLost(casualLost);
        setCasualPlayed(casualPlayed);
        setCasualKills(casualKills);
        setCasualDeaths(casualDeaths);
        setCasualTimePlayed(casualTimePlayed);
        this.casualTimePlayedStr = calculateTimeplayed(casualTimePlayed);
        this.casualKD = calculateKD(casualKills, casualDeaths);
        this.casualWL = calculateWL(casualWon, casualPlayed);

        setRankedWon(rankedWon);
        setRankedLost(rankedLost);
        setRankedPlayed(rankedPlayed);
        setRankedKills(rankedKills);
        setRankedDeaths(rankedDeaths);
        setRankedTimePlayed(rankedTimePlayed);
        this.rankedTimePlayedStr = calculateTimeplayed(rankedTimePlayed);
        this.rankedKD = calculateKD(rankedKills,rankedDeaths);
        this.rankedWL = calculateWL(rankedWon, rankedPlayed);

        this.weaponHeadshots = weaponHeadshots;
        this.weaponHSHit = calculateWL(weaponHeadshots, weaponBulletHit);
        this.weaponHSKill = calculateWL(weaponHeadshots, generalKills);
        this.weaponBulletHit = weaponBulletHit;
        this.weaponBulletFired = weaponBulletFired;
        this.weaponAccuracy = calculateWL(weaponBulletHit, weaponBulletFired);
        this.weaponBlindKills = weaponBlindKills;
        this.weaponMeleeKills = weaponMeleeKills;
        this.weaponPenetrations = weaponPenetrations;
        this.weaponDBNO = weaponDBNO;
        this.weaponDBNOAssists = weaponDBNOAssists;

        this.miscBarricade = miscBarricade;
        this.miscDistanceTravelled = miscDistanceTravelled;
        this.miscGadgetDestroyed = miscGadgetDestroyed;
        this.miscRappelBreach = miscRappelBreach;
        this.miscReinforcementDeploy = miscReinforcementDeploy;
        this.miscRevives = miscRevives;
        this.miscRevivesDenied = miscRevivesDenied;
        this.miscSuicides = miscSuicides;

        this.secureBestScore = secureBestScore;
        this.secureWins = secureWins;
        this.secureLosses = secureLosses;
        this.secureWL = calculateWL(secureWins, securePlayed);
        this.securePlayed = securePlayed;
        this.secureTimePlayed = secureTimePlayed;

        this.hostageBestScore = hostageBestScore;
        this.hostageWins = hostageWins;
        this.hostageLosses = hostageLosses;
        this.hostageWL = calculateWL(hostageWins, hostagePlayed);
        this.hostagePlayed = hostagePlayed;
        this.hostageTimePlayed = hostageTimePlayed;

        this.bombBestScore = bombBestScore;
        this.bombWins = bombWins;
        this.bombLosses = bombLosses;
        this.bombWL = calculateWL(bombWins, bombPlayed);
        this.bombPlayed = bombPlayed;
        this.bombTimePlayed = bombTimePlayed;
    }

    public double calculateKD(int kills, int deaths)
    {
        double tempKD = (double) kills / (double) deaths;
        tempKD = (double) Math.round(tempKD * 1000d) / 1000d;
        return tempKD;
    }

    public double calculateWL(int wins, int played)
    {
        double tempWL = ((double) wins / (double) played) * 100;
        tempWL = (double) Math.round(tempWL * 100d) / 100d;
        return tempWL;
    }

    public String calculateTimeplayed(int timeplayed)
    {
        int seconds = timeplayed;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = (seconds % 3600) % 60;
        StringBuilder timePlayed = new StringBuilder();
        if (hours != 0)
            timePlayed.append(String.valueOf(hours) + "H ");
        if (minutes != 0)
            timePlayed.append(String.valueOf(minutes) + "M");
        if (hours == 0)
            timePlayed.append(" " + String.valueOf(seconds) + "S");
        return timePlayed.toString();
    }


    public int getWeaponHeadshots() {
        return weaponHeadshots;
    }

    public double getWeaponHSHit() {
        return weaponHSHit;
    }

    public double getWeaponHSKill() {
        return weaponHSKill;
    }

    public int getWeaponBulletHit() {
        return weaponBulletHit;
    }

    public int getWeaponBulletFired() {
        return weaponBulletFired;
    }

    public double getWeaponAccuracy() {
        return weaponAccuracy;
    }

    public int getWeaponMeleeKills() {
        return weaponMeleeKills;
    }

    public int getWeaponPenetrations() {
        return weaponPenetrations;
    }

    public int getGeneralWins() {
        return generalWins;
    }

    public int getGeneralLost() {
        return generalLost;
    }

    public double getGeneralWL() {
        return generalWL;
    }

    public int getGeneralAssists() {
        return generalAssists;
    }

    public int getGeneralPlayed() {
        return generalPlayed;
    }

    public int getGeneralKills() {
        return generalKills;
    }

    public int getGeneralDeaths() {
        return generalDeaths;
    }

    public double getGeneralKD() {
        return generalKD;
    }

    public int getGeneralLevel() {
        return generalLevel;
    }

    public int getGeneralTimePlayed() {
        return generalTimePlayed;
    }

    public String getGeneralTimePlayedStr() {
        return generalTimePlayedStr;
    }

    public double getCasualWL() {
        return casualWL;
    }

    public double getCasualKD() {
        return casualKD;
    }

    public String getCasualTimePlayedStr() {
        return casualTimePlayedStr;
    }

    public double getRankedWL() {
        return rankedWL;
    }

    public double getRankedKD() {
        return rankedKD;
    }

    public String getRankedTimePlayedStr() {
        return rankedTimePlayedStr;
    }

    public int getCasualWon() {
        return casualWon;
    }

    public void setCasualWon(int casualWon) {
        this.casualWon = casualWon;
    }

    public int getCasualLost() {
        return casualLost;
    }

    public void setCasualLost(int casualLost) {
        this.casualLost = casualLost;
    }

    public int getCasualPlayed() {
        return casualPlayed;
    }

    public void setCasualPlayed(int casualPlayed) {
        this.casualPlayed = casualPlayed;
    }

    public int getCasualKills() {
        return casualKills;
    }

    public void setCasualKills(int casualKills) {
        this.casualKills = casualKills;
    }

    public int getCasualDeaths() {
        return casualDeaths;
    }

    public void setCasualDeaths(int casualDeaths) {
        this.casualDeaths = casualDeaths;
    }

    public int getCasualTimePlayed() {
        return casualTimePlayed;
    }

    public void setCasualTimePlayed(int casualTimePlayed) {
        this.casualTimePlayed = casualTimePlayed;
    }

    public int getRankedWon() {
        return rankedWon;
    }

    public void setRankedWon(int rankedWon) {
        this.rankedWon = rankedWon;
    }

    public int getRankedLost() {
        return rankedLost;
    }

    public void setRankedLost(int rankedLost) {
        this.rankedLost = rankedLost;
    }

    public int getRankedPlayed() {
        return rankedPlayed;
    }

    public void setRankedPlayed(int rankedPlayed) {
        this.rankedPlayed = rankedPlayed;
    }

    public int getRankedKills() {
        return rankedKills;
    }

    public void setRankedKills(int rankedKills) {
        this.rankedKills = rankedKills;
    }

    public int getRankedDeaths() {
        return rankedDeaths;
    }

    public void setRankedDeaths(int rankedDeaths) {
        this.rankedDeaths = rankedDeaths;
    }

    public int getRankedTimePlayed() {
        return rankedTimePlayed;
    }

    public void setRankedTimePlayed(int rankedTimePlayed) {
        this.rankedTimePlayed = rankedTimePlayed;
    }
    public String toString()
    {
        String temp = "Casual: " + getCasualKills() + " Kills, " + getCasualDeaths() + " Deaths, " + getCasualWon() + " Wins, " + getCasualLost() + " Losses, " + getCasualPlayed() + " Played, " + getCasualTimePlayed() + " Time Played \n" + "Ranked: " + getRankedKills() + " Kills, " + getRankedDeaths() + " Deaths, " + getRankedWon() + " Wins, " + getRankedLost() + " Losses, " + getRankedPlayed() + " Played, " + getRankedTimePlayed() + " Time Played ";
        return temp;
    }
}
