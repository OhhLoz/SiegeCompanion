package productions.pudl.siege.Data.R6TabObjects;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SearchObject
{
    private String id;
    private String userID;
    private String platform;
    private int level;
    private String name;
    private int mmr;
    private int rank;
    private double kd;

    public SearchObject(String id, String userID, String platform, int level, String name, int mmr, int rank, double kd)
    {
        setId(id);
        setUserID(userID);
        setPlatform(platform);
        setLevel(level);
        setName(name);
        this.kd = kd;
        this.mmr = mmr;
        this.rank = rank;
    }

    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
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

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }


    public void getUserPicture(ImageView imgView)
    {
        Picasso.get().load("https://ubisoft-avatars.akamaized.net/" + getUserID() + "/default_256_256.png").into(imgView);
    }

    @Override
    public String toString()
    {
        return ("{" + '"' + "id" + '"' + ":" + '"' + getId() + '"' + "," + '"' + "userId" + '"' + ":" + '"' + getUserID() + '"' + "," + '"' + "platform" + '"' + ":" + '"' + getPlatform() + '"' + "," + '"' + "level" + '"' + ":" + '"' + getLevel() + '"' + "," + '"' + "name" + '"' + ":" + '"' + getName() + '"' + "," + '"' + "mmr" + '"' + ":" + '"' + mmr + '"' + "," + '"' + "rank" + '"' + ":" + '"' + rank + '"' + "," + '"' + "kd" + '"' + ":" + '"' + kd + '"' + "}");
    }
}