package productions.pudl.siege.Adapter;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class R6StatsJSONAdapter
{
    protected String userName;
    protected String platformName;
    private RequestQueue mQueue;

    public R6StatsJSONAdapter(String currUserNameSelected, String currPlatformSelected, RequestQueue mQueue)
    {
        setUserName(currUserNameSelected);
        setPlatformName(currPlatformSelected);
        setmQueue(mQueue);
        ParsePlatform();
        ParseGeneral();
        //ParseOperators();
        ParseSeasons();
    }

    private void ParsePlatform()
    {
        switch(getPlatformName())
        {
            case "uPlay":
                setPlatformName("uplay");
                break;
            case "Xbox":
                setPlatformName("xone");
                break;
            case "Playstation":
                setPlatformName("ps4");
        }
    }

    private void ParseGeneral()
    {
        String URL = "https://api.r6stats.com/api/v1/players/"+getUserName()+"?platform="+getPlatformName();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {
                    try
                    {
                        JSONObject playerArray = response.getJSONObject("player");

                        String userName = playerArray.getString("username");
                        String platformName = playerArray.getString("platform");
                        String ubisoftID = playerArray.getString("ubisoft_id");
                        String indexAt = playerArray.getString("indexed_at");
                        String updatedAt = playerArray.getString("updated_at");

                        JSONObject statsArray = playerArray.getJSONObject("stats");
                        JSONObject rankedArray = statsArray.getJSONObject("ranked");

                        boolean rankedHasPlayed = rankedArray.getBoolean("has_played");
                        int rankedWins = rankedArray.getInt("wins");
                        int rankedLosses = rankedArray.getInt("losses");
                        double rankedWLR = rankedArray.getDouble("wlr");
                        int rankedKills = rankedArray.getInt("kills");
                        int rankedDeaths = rankedArray.getInt("deaths");
                        double rankedKD = rankedArray.getDouble("kd");
                        long rankedPlaytime = rankedArray.getLong("playtime");

                        JSONObject casualArray = statsArray.getJSONObject("casual");

                        boolean casualHasPlayed = casualArray.getBoolean("has_played");
                        int casualWins = casualArray.getInt("wins");
                        int casualLosses = casualArray.getInt("losses");
                        double casualWLR = casualArray.getDouble("wlr");
                        int casualKills = casualArray.getInt("kills");
                        int casualDeaths = casualArray.getInt("deaths");
                        double casualKD = casualArray.getDouble("kd");
                        long casualPlaytime = casualArray.getLong("playtime");

                        JSONObject overallArray = statsArray.getJSONObject("overall");

                        int revives = overallArray.getInt("revives");
                        int suicides = overallArray.getInt("suicides");
                        int reinforcements = overallArray.getInt("reinforcements_deployed");
                        int barricades = overallArray.getInt("barricades_built");
                        long steps_moved = overallArray.getLong("steps_moved");
                        int bullets_fired = overallArray.getInt("bullets_fired");
                        int bullets_hit = overallArray.getInt("bullets_hit");
                        int headshots = overallArray.getInt("headshots");
                        int melee_kills = overallArray.getInt("melee_kills");
                        int penetration_kills = overallArray.getInt("penetration_kills");
                        int assists = overallArray.getInt("assists");

                        JSONObject progressionArray = statsArray.getJSONObject("progression");

                        int level = progressionArray.getInt("level");
                        int xp = progressionArray.getInt("xp");

                        // Do something with data i.e store elsewhere
                        // Store in Object
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    error.printStackTrace();
                }
            });

        getmQueue().add(request);
    }

    private void ParseSeasons()
    {
        String URL = "https://api.r6stats.com/api/v1/players/"+getUserName()+"/seasons?platform="+getPlatformName();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONObject seasonsArray = response.getJSONObject("seasons");

                            for (int i = 1; i < 10; i++)
                            {
                                JSONObject season = seasonsArray.getJSONObject(String.valueOf(i));

                                JSONObject apac = season.getJSONObject("apac");

                                int apacWins = apac.getInt("wins");
                                int apacLosses = apac.getInt("losses");
                                int apacAbandons = apac.getInt("abandons");
                                int apacSeason = apac.getInt("season");

                                JSONObject apacRanking = apac.getJSONObject("ranking");

                                double apacRating = apacRanking.getDouble("rating");
                                double apacNextRating = apacRanking.getDouble("next_rating");
                                double apacPrevRating = apacRanking.getDouble("prev_rating");
                                double apacMean = apacRanking.getDouble("mean");
                                int apacStdDev = apacRanking.getInt("stdev");
                                int apacRank = apacRanking.getInt("rank");

                                JSONObject emea = season.getJSONObject("emea");

                                int emeaWins = emea.getInt("wins");
                                int emeaLosses = emea.getInt("losses");
                                int emeaAbandons = emea.getInt("abandons");
                                int emeaSeason = emea.getInt("season");

                                JSONObject emeaRanking = emea.getJSONObject("ranking");

                                double emeaRating = emeaRanking.getDouble("rating");
                                double emeaNextRating = emeaRanking.getDouble("next_rating");
                                double emeaPrevRating = emeaRanking.getDouble("prev_rating");
                                double emeaMean = emeaRanking.getDouble("mean");
                                int emeaStdDev = emeaRanking.getInt("stdev");
                                int emeaRank = emeaRanking.getInt("rank");

                                JSONObject ncsa = season.getJSONObject("ncsa");

                                int ncsaWins = ncsa.getInt("wins");
                                int ncsaLosses = ncsa.getInt("losses");
                                int ncsaAbandons = ncsa.getInt("abandons");
                                int ncsaSeason = ncsa.getInt("season");

                                JSONObject ncsaRanking = ncsa.getJSONObject("ranking");

                                double ncsaRating = ncsaRanking.getDouble("rating");
                                double ncsaNextRating = ncsaRanking.getDouble("next_rating");
                                double ncsaPrevRating = ncsaRanking.getDouble("prev_rating");
                                double ncsaMean = ncsaRanking.getDouble("mean");
                                int ncsaStdDev = ncsaRanking.getInt("stdev");
                                int ncsaRank = ncsaRanking.getInt("rank");

                                // store in another object
                            }
                            // Do something with data i.e store elsewhere
                            // Store in Object
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        error.printStackTrace();
                    }
                });

        getmQueue().add(request);
    }

    public String getPlatformName()
    {
        return this.platformName;
    }

    public void setPlatformName(String platform)
    {
        this.platformName = platform;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String user)
    {
        this.userName = user;
    }

    public RequestQueue getmQueue()
    {
        return mQueue;
    }

    public void setmQueue(RequestQueue mQueue)
    {
        this.mQueue = mQueue;
    }
}

