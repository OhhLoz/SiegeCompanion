package productions.pudl.siege.Adapter;

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
    }

    private void JSONParse()
    {
        String URL = "https://api.r6stats.com/api/v1/players/"+this.userName+"?platform="+this.platformName.toLowerCase(Locale.getDefault());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONObject playerArray = response.getJSONObject("player");
                            
                            for (int i = 0; i < playerArray.length(); i++)
                            { 
                                userName = userName;
                                platformName = platformName;
                                String ubisoftID = playerArray.getString("ubisoft_id");
                                String indexAt = playerArray.getString("indexed_at");
                                String updatedAt = playerArray.getString("updated_at");
                                JSONObject statsArray = playerArray.getJSONObject("stats");

                                for (int j = 0; j < statsArray.length(); j++)
                                {
                                    JSONObject rankedArray = statsArray.getJSONObject("ranked");
                                    JSONObject casualArray = statsArray.getJSONObject("casual");
                                    JSONObject overallArray = statsArray.getJSONObject("overall");
                                    JSONObject progressionArray = statsArray.getJSONObject("progression");

                                    for (int k = 0; k < rankedArray.length(); k++)
                                    {

                                        boolean rankedHasPlayed = rankedArray.getBoolean("has_played");
                                        int rankedWins = rankedArray.getInt("wins");
                                        int rankedLosses = rankedArray.getInt("losses");
                                        double rankedWLR = rankedArray.getDouble("wlr");
                                        int rankedKills = rankedArray.getInt("kills");
                                        int rankedDeaths = rankedArray.getInt("deaths");
                                        double rankedKD = rankedArray.getDouble("kd");
                                        long rankedPlaytime = rankedArray.getLong("playtime");

                                        boolean casualHasPlayed = casualArray.getBoolean("has_played");
                                        int casualWins = casualArray.getInt("wins");
                                        int casualLosses = casualArray.getInt("losses");
                                        double casualWLR = casualArray.getDouble("wlr");
                                        int casualKills = casualArray.getInt("kills");
                                        int casualDeaths = casualArray.getInt("deaths");
                                        double casualKD = casualArray.getDouble("kd");
                                        long casualPlaytime = casualArray.getLong("playtime");
                                    }
                                    for (int l = 0; l < overallArray.length(); l++)
                                    {
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
                                    }
                                    for(int m = 0; m < progressionArray.length(); m++)
                                    {
                                        int level = progressionArray.getInt("level");
                                        int xp = progressionArray.getInt("xp");
                                    }
                                }
                                // Do something with data i.e store elsewhere
                            }

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

