package productions.pudl.siege.Adapter;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import productions.pudl.siege.Data.DetailedObjects.DetailedGameModeObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedGeneralObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedMainObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedOperatorObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedOperatorsObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedPlacementsObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedRankObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedRanksObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedStatObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedStatsObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasesObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralLastPlayedObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRankObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRanksObject;
import productions.pudl.siege.Data.R6TabObjects.SearchObject;
import productions.pudl.siege.Fragment.Search.Search;

public class R6TabAPIAdapter {
    private static RequestQueue mQueue;
    private static ArrayList<SearchObject> searchResult = new ArrayList<>();
    static private HashMap<String, String> platformMap = new HashMap<String, String>(){{
        put("PC", "uplay");
        put("Xbox", "xbl");
        put("PS4", "psn");
    }};

    public R6TabAPIAdapter(RequestQueue mQueue)
    {
    }

    public static void searchPlayer(Context context, String platformName, String userName, final VolleyResponseListener listener)
    {
        String platform = platformMap.get(platformName);
        mQueue = Volley.newRequestQueue(context);
        String URL = "https://r6tab.com/api/search.php?platform="+ platform + "&search=" + userName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.v("JSONObject", response.toString());
                            //ArrayList<GeneralObject> generalObjectArrayList = new ArrayList<>();

                            if(response.getInt("totalresults") <= 0)
                                Log.v("R6TabAPI", "No search results found");

                            JSONArray responseArr = response.getJSONArray("results");

                            for (int i = 0; i < responseArr.length(); i++)
                            {
                                JSONObject currObject = responseArr.getJSONObject(i);

                                String id = currObject.getString("p_id");
                                String userId = currObject.getString("p_user");
                                String name = currObject.getString("p_name");
                                String platform = currObject.getString("p_platform");
                                int level = currObject.getInt("p_level");

                                int mmr = currObject.getInt("p_currentmmr");
                                int rank = currObject.getInt("p_currentrank");
                                double kd = currObject.getDouble("kd");

                                SearchObject tempObj = new SearchObject(id, userId, platform, level, name, mmr, rank, kd);
                                Log.v("SearchResult", tempObj.toString());
                                searchResult.add(tempObj);
                            }
                            Log.v("JSON", "Reached end of parsing!");
                            if(listener != null)
                                listener.onResponse();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("x-app-id", "175980a6-8ce2-449c-976b-e8cd9c05d86b");
                return headers;
            }
        };

        mQueue.add(request);
    }

    public static ArrayList<SearchObject> getSearchResults() {
        return searchResult;
    }

}

