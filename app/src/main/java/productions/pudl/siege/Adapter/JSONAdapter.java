package productions.pudl.siege.Adapter;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class JSONAdapter
{
    protected String userName;
    protected String platformName;
    private RequestQueue mQueue;

    public JSONAdapter(String currUserNameSelected, String currPlatformSelected, RequestQueue mQueue)
    {
        this.userName = currUserNameSelected;
        this.platformName = currPlatformSelected;
        this.mQueue = mQueue;
        JSONParse();
    }

    private void JSONParse()
    {
        String URL = "https://api.r6stats.com/api/v1/players/"+this.userName+"?platform="+this.platformName.toLowerCase(Locale.getDefault());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.v("JSON", "1");
                        try {
                            JSONObject playerArray = response.getJSONObject("player");

                            Log.v("JSON", "2");
                            userName = userName;
                            platformName = platformName;
                            String ubisoftID = playerArray.getString("ubisoft_id");
                            String indexAt = playerArray.getString("indexed_at");
                            String updatedAt = playerArray.getString("updated_at");

                            JSONObject statsArray = playerArray.getJSONObject("stats");
                            Log.v("JSON", "3");

                            JSONObject rankedArray = statsArray.getJSONObject("ranked");
                            Log.v("JSON", "4");
                            boolean rankedHasPlayed = rankedArray.getBoolean("has_played");
                            int rankedWins = rankedArray.getInt("wins");
                            int rankedLosses = rankedArray.getInt("losses");
                            double rankedWLR = rankedArray.getDouble("wlr");
                            int rankedKills = rankedArray.getInt("kills");
                            int rankedDeaths = rankedArray.getInt("deaths");
                            double rankedKD = rankedArray.getDouble("kd");
                            long rankedPlaytime = rankedArray.getLong("playtime");

                            JSONObject casualArray = statsArray.getJSONObject("casual");
                            Log.v("JSON", "5");
                            boolean casualHasPlayed = casualArray.getBoolean("has_played");
                            int casualWins = casualArray.getInt("wins");
                            int casualLosses = casualArray.getInt("losses");
                            double casualWLR = casualArray.getDouble("wlr");
                            int casualKills = casualArray.getInt("kills");
                            int casualDeaths = casualArray.getInt("deaths");
                            double casualKD = casualArray.getDouble("kd");
                            long casualPlaytime = casualArray.getLong("playtime");

                            JSONObject overallArray = statsArray.getJSONObject("overall");
                            Log.v("JSON", "6");
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
                            Log.v("JSON", "7");
                            int level = progressionArray.getInt("level");
                            int xp = progressionArray.getInt("xp");


                            // Do something with data i.e store elsewhere
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        error.printStackTrace();
                    }
                });

        this.mQueue.add(request);
    }

}

