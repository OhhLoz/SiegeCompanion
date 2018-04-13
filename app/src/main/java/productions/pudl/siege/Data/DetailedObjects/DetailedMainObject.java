package productions.pudl.siege.Data.DetailedObjects;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
    private String serverTime;
    private String updateTime;


    public DetailedMainObject(String id, String userID, String platform, int level, String createdAt, String updatedAt, GeneralLastPlayedObject lastPlayedObject, String name, DetailedRanksObject ranksObject, ArrayList<DetailedRanksObject> prevRanksObject, DetailedStatsObject stats, DetailedPlacementsObject placements, GeneralAliasesObject aliasesObject, String serverTime, String updateTime) {
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

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void getUserPicture(ImageView imgView)
    {
        Picasso.get().load("https://ubisoft-avatars.akamaized.net/" + getUserID() + "/default_146_146.png").into(imgView);
    }
}
