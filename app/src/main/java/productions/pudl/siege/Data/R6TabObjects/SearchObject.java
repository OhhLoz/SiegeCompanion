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

    public SearchObject(String id, String userID, String platform, int level, String name, int mmr, int rank, double kd) {
        this.id = id;
        this.userID = userID;
        this.platform = platform;
        this.level = level;
        this.name = name;
        this.mmr = mmr;
        this.rank = rank;
        this.kd = kd;
    }

    private String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getPlatform() {
        return platform;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
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