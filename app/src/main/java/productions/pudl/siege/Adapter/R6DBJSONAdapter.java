package productions.pudl.siege.Adapter;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import productions.pudl.siege.Data.GeneralObjects.GeneralAliasObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasesObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralLastPlayedObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRankObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRanksObject;
import productions.pudl.siege.Fragment.Search;

public class R6DBJSONAdapter
{
    protected String userName;
    protected String platformName;
    private Search search;
    private RequestQueue mQueue;
    private ArrayList<GeneralObject> searchResult = new ArrayList<>();

    public R6DBJSONAdapter(Search search, String currUserNameSelected, String currPlatformSelected, RequestQueue mQueue)
    {
        this.search = search;
        setUserName(currUserNameSelected);
        setPlatformName(currPlatformSelected);
        setmQueue(mQueue);
        //ParsePlatform();
        //ParseGeneral();
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

    public void ParseGeneral()
    {
        String URL = "https://r6db.com/api/v2/players?name="+getUserName()+"&platform="+getPlatformName();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response)
                {
                    try
                    {
                        searchResult.clear();
                        Log.v("JSONArray", response.toString());
                        //ArrayList<GeneralObject> generalObjectArrayList = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject currObject = response.getJSONObject(i);

                            String id = currObject.getString("id");
                            String userId = currObject.getString("userId");
                            String platform = currObject.getString("platform");
                            int level = currObject.getInt("level");
                            String createdAt = currObject.getString("created_at");
                            String updatedAt = currObject.getString("updated_at");

                            JSONObject lastPlayed = currObject.getJSONObject("lastPlayed");
                            String casual = lastPlayed.getString("casual");
                            String ranked = lastPlayed.getString("ranked");
                            String lastplayed = lastPlayed.getString("last_played");

                            GeneralLastPlayedObject lastPlayedObject = new GeneralLastPlayedObject(casual, ranked, lastplayed);

                            String name = currObject.getString("name");

                            JSONObject ranks = currObject.getJSONObject("ranks");
                            JSONObject apac = ranks.getJSONObject("apac");

                            int apacMMR = apac.getInt("mmr");
                            int apacRank = apac.getInt("rank");

                            GeneralRankObject apacObject = new GeneralRankObject("apac", apacMMR, apacRank);

                            JSONObject emea = ranks.getJSONObject("emea");

                            int emeaMMR = emea.getInt("mmr");
                            int emeaRank = emea.getInt("rank");

                            GeneralRankObject emeaObject = new GeneralRankObject("emea", emeaMMR, emeaRank);

                            JSONObject ncsa = ranks.getJSONObject("ncsa");

                            int ncsaMMR = ncsa.getInt("mmr");
                            int ncsaRank = ncsa.getInt("rank");

                            GeneralRankObject ncsaObject = new GeneralRankObject("ncsa", ncsaMMR, ncsaRank);

                            GeneralRanksObject ranksObject = new GeneralRanksObject(apacObject, emeaObject, ncsaObject);

                            JSONArray aliases = currObject.getJSONArray("aliases");

                            ArrayList<GeneralAliasObject> aliasArrayList = new ArrayList<>();
                            for (int j = 0; j < aliases.length(); j++)
                            {
                                JSONObject currAlias = aliases.getJSONObject(j);

                                String aliasName = currAlias.getString("name");
                                String aliasCreatedAt = currObject.getString("created_at");

                                aliasArrayList.add(new GeneralAliasObject(aliasName, aliasCreatedAt));
                                //Add to list of aliases
                            }

                            GeneralAliasesObject generalAliasesObject = new GeneralAliasesObject(aliasArrayList);

                            GeneralObject generalObject = new GeneralObject(id, userId, platform, level, createdAt, updatedAt, lastPlayedObject, name, ranksObject, generalAliasesObject);
                            searchResult.add(generalObject);
                            Log.v("JSONArrayResult", generalObject.toString());
                        }
                        Log.v("JSON", "Reached end of parsing!");

                        search.updateListView(searchResult);
                        // Add to list of R6DBUsers
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
            }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("x-app-id", "175980a6-8ce2-449c-976b-e8cd9c05d86b");
                return headers;
            }};

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

    private RequestQueue getmQueue()
    {
        return mQueue;
    }

    private void setmQueue(RequestQueue mQueue)
    {
        this.mQueue = mQueue;
    }

    public ArrayList<GeneralObject> getSearchResults()
    {
        return this.searchResult;
    }
}

