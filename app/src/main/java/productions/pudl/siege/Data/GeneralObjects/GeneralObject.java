package productions.pudl.siege.Data.GeneralObjects;

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

    public GeneralRanksObject getRanksObject() {
        return ranksObject;
    }

    public void setRanksObject(GeneralRanksObject ranksObject) {
        this.ranksObject = ranksObject;
    }

    public GeneralAliasesObject getAliasesObject() {
        return aliasesObject;
    }

    public void setAliasesObject(GeneralAliasesObject aliasesObject) {
        this.aliasesObject = aliasesObject;
    }
}