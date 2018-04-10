package productions.pudl.siege.Data.GeneralObjects;

import java.util.ArrayList;

public class GeneralObject
{
    private String id;
    private String userID;
    private String platform;
    private int level;
    private String createdAt;
    private String updatedAt;
    private GeneralLastPlayedObject lastPlayedObject;
    private String name;
    private GeneralRanksObject ranksObject;
    private GeneralAliasesObject aliasesObject;

    public GeneralObject(String id, String userID, String platform, int level, String createdAt, String updatedAt, GeneralLastPlayedObject lastPlayedObject, String name, GeneralRanksObject ranksObject, GeneralAliasesObject aliasesObject)
    {
        setId(id);
        setUserID(userID);
        setPlatform(platform);
        setLevel(level);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setLastPlayedObject(lastPlayedObject);
        setName(name);
        setRanksObject(ranksObject);
        setAliasesObject(aliasesObject);
    }

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private String getUserID() {
        return userID;
    }

    private void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPlatform() {
        return platform;
    }

    private void setPlatform(String platform) {
        this.platform = platform;
    }

    private int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    private String getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    private String getUpdatedAt() {
        return updatedAt;
    }

    private void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    private GeneralLastPlayedObject getLastPlayedObject() {
        return lastPlayedObject;
    }

    private void setLastPlayedObject(GeneralLastPlayedObject lastPlayedObject) {
        this.lastPlayedObject = lastPlayedObject;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private GeneralRanksObject getRanksObject() {
        return ranksObject;
    }

    private void setRanksObject(GeneralRanksObject ranksObject) {
        this.ranksObject = ranksObject;
    }

    private GeneralAliasesObject getAliasesObject() {
        return aliasesObject;
    }

    private void setAliasesObject(GeneralAliasesObject aliasesObject) {
        this.aliasesObject = aliasesObject;
    }

    @Override
    public String toString()
    {
        return ("{" + '"' + "id" + '"' + ":" + '"' + getId() + '"' + "," + '"' + "userId" + '"' + ":" + '"' + getUserID() + '"' + "," + '"' + "platform" + '"' + ":" + '"' + getPlatform() + '"' + "," + '"' + "level" + '"' + ":" + '"' + String.valueOf(getLevel()) + '"' + "," + '"' + "created_at" + '"' + ":" + '"' + getCreatedAt() + '"' + "," + '"' + "updated_at" + '"' + ":" + '"' + getUpdatedAt() + '"' + "," + getLastPlayedObject().toString() + "," + '"' + "name" + '"' + ":" + '"' + getName() + '"' + "," + getRanksObject().toString() + "," + getAliasesObject().toString() + "}");
    }
}