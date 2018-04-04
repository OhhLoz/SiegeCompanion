package productions.pudl.siege.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class R6DBJSONAdapter
{
    protected String userName;
    protected String platformName;
    private RequestQueue mQueue;

    public R6DBJSONAdapter(String currUserNameSelected, String currPlatformSelected, RequestQueue mQueue)
    {
        setUserName(currUserNameSelected);
        setPlatformName(currPlatformSelected);
        setmQueue(mQueue);
        ParsePlatform();
        ParseGeneral();
        //ParseOperators();
        //ParseSeasons();
    }

    private void ParsePlatform()
    {
        switch(getPlatformName())
        {
            case "uPlay":
                setPlatformName("pc");
                break;
            case "Xbox":
                setPlatformName("xbox");
                break;
            case "Playstation":
                setPlatformName("ps4");
        }
    }

    private void ParseGeneral()
    {
        String URL = "https://r6db.com/api/v2/players?name="+getUserName()+"&platform="+getPlatformName();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {
                    try
                    {
                        JSONArray jsonArray = new JSONArray(response);
                        //JSONArray jsonArray = response.getJSONArray(0);
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject currObject = jsonArray.getJSONObject(i);
                            for (int j = 0; j < currObject.length(); j++)
                            {
                                String id = currObject.getString("id");
                                String userId = currObject.getString("userId");
                                String platform = currObject.getString("platform");
                                boolean flair = currObject.getBoolean("flair");
                                int level = currObject.getInt("level");
                                String createdAt = currObject.getString("created_at");
                                String updatedAt = currObject.getString("updated_at");

                                JSONObject lastPlayed = currObject.getJSONObject("lastPlayed");
                                long casual = lastPlayed.getLong("casual");
                                long ranked = lastPlayed.getLong("ranked");
                                String lastplayed = lastPlayed.getString("last_played");

                                String name = currObject.getString("name");

                                JSONObject ranks = currObject.getJSONObject("ranks");
                                JSONObject apac = ranks.getJSONObject("apac");

                                int apacMMR = apac.getInt("mmr");
                                int apacRank = apac.getInt("rank");

                                JSONObject emea = ranks.getJSONObject("emea");

                                int emeaMMR = apac.getInt("mmr");
                                int emeaRank = apac.getInt("rank");

                                JSONObject ncsa = ranks.getJSONObject("ncsa");

                                int ncsaMMR = apac.getInt("mmr");
                                int ncsaRank = apac.getInt("rank");

                                JSONArray aliases = currObject.getJSONArray("aliases");

                                for (int k = 0; k < aliases.length(); k++)
                                {
                                    JSONObject currAlias = aliases.getJSONObject(k);
                                    for (int l = 0; l < currAlias.length(); l++)
                                    {
                                        String aliasName = currAlias.getString("name");
                                        String aliasCreatedAt = currObject.getString("created_at");

                                        //Add to list of aliases
                                    }
                                }
                            }
                            // Add to list of R6DBUsers
                        }

                        // Do something with data i.e store elsewhere
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

