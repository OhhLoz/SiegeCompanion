package productions.pudl.siege.Data.GeneralObjects;

public class GeneralObject
{
    private String userName;
    private String platformName;
    private String ubisoftID;
    private String indexedAt;
    private String updatedAt;
    private GeneralStatObject statObject;

    public GeneralObject(String userName, String platform, String ubisoftID, String indexedAt, String updatedAt, GeneralStatObject statObject)
    {
        setUserName(userName);
        setPlatformName(platform);
        setUbisoftID(ubisoftID);
        setIndexedAt(indexedAt);
        setUpdatedAt(updatedAt);
        setStatObject(statObject);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getUbisoftID() {
        return ubisoftID;
    }

    public void setUbisoftID(String ubisoftID) {
        this.ubisoftID = ubisoftID;
    }

    public String getIndexedAt() {
        return indexedAt;
    }

    public void setIndexedAt(String indexedAt) {
        this.indexedAt = indexedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public GeneralStatObject getStatObject() {
        return statObject;
    }

    public void setStatObject(GeneralStatObject statObject) {
        this.statObject = statObject;
    }
}