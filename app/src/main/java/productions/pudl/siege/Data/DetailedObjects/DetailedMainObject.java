package productions.pudl.siege.Data.DetailedObjects;

import java.util.ArrayList;

import productions.pudl.siege.Data.GeneralObjects.GeneralAliasesObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralLastPlayedObject;

public class DetailedMainObject
{
    private String id;
    private String userID;
    private String platform;
    private int level;
    private String createdAt;
    private String updatedAt;
    private GeneralLastPlayedObject lastPlayedObject;
    private String name;
    private DetailedRanksObject ranksObject;
    private ArrayList<DetailedRanksObject> prevRanksObject;
    private DetailedStatsObject stats;
    private DetailedPlacementsObject placements;
    // Ignore 'Progressions' for now as its not needed in version 1 of the app
    private GeneralAliasesObject aliasesObject;
    private int serverTime;
    private int updateTime;


    public DetailedMainObject(String id, String userID, String platform, int level, String createdAt, String updatedAt, GeneralLastPlayedObject lastPlayedObject, String name, DetailedRanksObject ranksObject, ArrayList<DetailedRanksObject> prevRanksObject, DetailedStatsObject stats, DetailedPlacementsObject placements, GeneralAliasesObject aliasesObject, int serverTime, int updateTime) {
        setId(id);
        setUserID(userID);
        setPlatform(platform);
        setLevel(level);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setLastPlayedObject(lastPlayedObject);
        setName(name);
        setRanksObject(ranksObject);
        setPrevRanksObject(prevRanksObject);
        setStats(stats);
        setPlacements(placements);
        setAliasesObject(aliasesObject);
        setServerTime(serverTime);
        setUpdateTime(updateTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GeneralLastPlayedObject getLastPlayedObject() {
        return lastPlayedObject;
    }

    public void setLastPlayedObject(GeneralLastPlayedObject lastPlayedObject) {
        this.lastPlayedObject = lastPlayedObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DetailedRanksObject getRanksObject() {
        return ranksObject;
    }

    public void setRanksObject(DetailedRanksObject ranksObject) {
        this.ranksObject = ranksObject;
    }

    public ArrayList<DetailedRanksObject> getPrevRanksObject() {
        return prevRanksObject;
    }

    public void setPrevRanksObject(ArrayList<DetailedRanksObject> prevRanksObject) {
        this.prevRanksObject = prevRanksObject;
    }

    public DetailedStatsObject getStats() {
        return stats;
    }

    public void setStats(DetailedStatsObject stats) {
        this.stats = stats;
    }

    public DetailedPlacementsObject getPlacements() {
        return placements;
    }

    public void setPlacements(DetailedPlacementsObject placements) {
        this.placements = placements;
    }

    public GeneralAliasesObject getAliasesObject() {
        return aliasesObject;
    }

    public void setAliasesObject(GeneralAliasesObject aliasesObject) {
        this.aliasesObject = aliasesObject;
    }

    public int getServerTime() {
        return serverTime;
    }

    public void setServerTime(int serverTime) {
        this.serverTime = serverTime;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }
}
