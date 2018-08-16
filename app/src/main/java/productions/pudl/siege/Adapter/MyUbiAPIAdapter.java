package productions.pudl.siege.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import productions.pudl.siege.Data.Level;
import productions.pudl.siege.Data.Operator;
import productions.pudl.siege.Data.Player;
import productions.pudl.siege.Data.Ranked;
import productions.pudl.siege.Data.Stat;
import productions.pudl.siege.R;


public class MyUbiAPIAdapter
{
    static private String expirationTimeStr = "DEFAULT";
    static private String userName = "DEFAULT";
    static private Date expirationTimeFormatted = null;
    static private RequestQueue mQueue;
    static private MyHeader headers;
    static private ArrayList<Player> playersResult = new ArrayList<>();
    static private ArrayList<Level> levelsResult = new ArrayList<>();
    static private ArrayList<Stat> statsResult = new ArrayList<>();
    static private ArrayList<Ranked> rankedResult = new ArrayList<>();
    static private HashMap<String, Operator> operatorFinalResult = new HashMap<>();
    static private HashMap<String, String> ctuMap = new HashMap<>();
    static private SparseArray<Pair<String, String>> operatorFinalMap = new SparseArray<>();
    static private String[] statArray;

    static public void changeContext(Context context, String credentials)
    {
        mQueue = Volley.newRequestQueue(context);
    }

    static public void create(RequestQueue currQueue, String credentials, final VolleyResponseListener listener)
    {
        populateOperatorMap();
        populateCTUMap();
        headers = new MyHeader(credentials);
        mQueue = currQueue;
        if ((expirationTimeStr.equals("DEFAULT") && expirationTimeFormatted == null) || isExpired())
            loginAuth(listener);
    }

    static public void create(Context context, String credentials, final VolleyResponseListener listener)
    {
        populateOperatorMap();
        populateCTUMap();
        headers = new MyHeader(credentials);
        mQueue = Volley.newRequestQueue(context);
        if ((expirationTimeStr.equals("DEFAULT") && expirationTimeFormatted == null) || isExpired())
            loginAuth(listener);
    }

    static private void populateOperatorMap()
    {
        operatorFinalMap.append(0, new Pair<>("1:1", "Recruit(SAS)"));
        operatorFinalMap.append(1, new Pair<>("1:2", "Recruit(FBI)"));
        operatorFinalMap.append(2, new Pair<>("1:3", "Recruit(GIGN)"));
        operatorFinalMap.append(3, new Pair<>("1:4", "Recruit(GSG9)"));
        operatorFinalMap.append(4, new Pair<>("1:5", "Recruit(Spetsnaz)"));
        operatorFinalMap.append(5, new Pair<>("2:1", "Smoke"));
        operatorFinalMap.append(6, new Pair<>("3:1", "Mute"));
        operatorFinalMap.append(7, new Pair<>("4:1", "Sledge"));
        operatorFinalMap.append(8, new Pair<>("5:1", "Thatcher"));
        operatorFinalMap.append(9, new Pair<>("2:2", "Castle"));
        operatorFinalMap.append(10, new Pair<>("3:2", "Ash"));
        operatorFinalMap.append(11, new Pair<>("4:2", "Pulse"));
        operatorFinalMap.append(12, new Pair<>("5:2", "Thermite"));
        operatorFinalMap.append(13, new Pair<>("2:3", "Doc"));
        operatorFinalMap.append(14, new Pair<>("3:3", "Rook"));
        operatorFinalMap.append(15, new Pair<>("4:3", "Twitch"));
        operatorFinalMap.append(16, new Pair<>("5:3", "Montagne"));
        operatorFinalMap.append(17, new Pair<>("2:4", "Glaz"));
        operatorFinalMap.append(18, new Pair<>("3:4", "Fuze"));
        operatorFinalMap.append(19, new Pair<>("4:4", "Kapkan"));
        operatorFinalMap.append(20, new Pair<>("5:4", "Tachanka"));
        operatorFinalMap.append(21, new Pair<>("2:5", "Blitz"));
        operatorFinalMap.append(22, new Pair<>("3:5", "IQ"));
        operatorFinalMap.append(23, new Pair<>("4:5", "Jäger"));
        operatorFinalMap.append(24, new Pair<>("5:5", "Bandit"));
        operatorFinalMap.append(25, new Pair<>("2:6", "Buck"));
        operatorFinalMap.append(26, new Pair<>("3:6", "Frost"));
        operatorFinalMap.append(27, new Pair<>("2:7", "Blackbeard"));
        operatorFinalMap.append(28, new Pair<>("3:7", "Valkyrie"));
        operatorFinalMap.append(29, new Pair<>("2:8", "Capitão"));
        operatorFinalMap.append(30, new Pair<>("3:8", "Caveira"));
        operatorFinalMap.append(31, new Pair<>("2:9", "Hibana"));
        operatorFinalMap.append(32, new Pair<>("3:9", "Echo"));
        operatorFinalMap.append(33, new Pair<>("2:A", "Jackal"));
        operatorFinalMap.append(34, new Pair<>("3:A", "Mira"));
        operatorFinalMap.append(35, new Pair<>("2:B", "Ying"));
        operatorFinalMap.append(36, new Pair<>("3:B", "Lesion"));
        operatorFinalMap.append(37, new Pair<>("2:C", "Ela"));
        operatorFinalMap.append(38, new Pair<>("2:D", "Zofia"));
        operatorFinalMap.append(39, new Pair<>("3:C", "Dokkaebi"));
        operatorFinalMap.append(40, new Pair<>("3:D", "Vigil"));
        operatorFinalMap.append(41, new Pair<>("3:E", "Lion"));
        operatorFinalMap.append(42, new Pair<>("4:E", "Finka"));
        operatorFinalMap.append(43, new Pair<>("2:F", "Maestro"));
        operatorFinalMap.append(44, new Pair<>("3:F", "Alibi"));
    }

    static private void populateCTUMap()
    {
        ctuMap.put("Sledge", "SAS");
        ctuMap.put("Thatcher", "SAS");
        ctuMap.put("Smoke", "SAS");
        ctuMap.put("Mute", "SAS");
        ctuMap.put("Recruit(SAS)", "SAS");
        ctuMap.put("Castle", "FBISWAT");
        ctuMap.put("Ash", "FBISWAT");
        ctuMap.put("Recruit(FBI)", "FBISWAT");
        ctuMap.put("Thermite", "FBISWAT");
        ctuMap.put("Pulse", "FBISWAT");
        ctuMap.put("Recruit(GIGN)", "GIGN");
        ctuMap.put("Rook", "GIGN");
        ctuMap.put("Doc", "GIGN");
        ctuMap.put("Twitch", "GIGN");
        ctuMap.put("Montagne", "GIGN");
        ctuMap.put("Recruit(Spetsnaz)", "Spetsnaz");
        ctuMap.put("Glaz", "Spetsnaz");
        ctuMap.put("Fuze", "Spetsnaz");
        ctuMap.put("Kapkan", "Spetsnaz");
        ctuMap.put("Tachanka", "Spetsnaz");
        ctuMap.put("Recruit(GSG9)", "GSG9");
        ctuMap.put("Blitz", "GSG9");
        ctuMap.put("Bandit", "GSG9");
        ctuMap.put("Jäger", "GSG9");
        ctuMap.put("IQ", "GSG9");
        ctuMap.put("Buck", "JTF2");
        ctuMap.put("Frost", "JTF2");
        ctuMap.put("Blackbeard", "NAVYSEALS");
        ctuMap.put("Valkyrie", "NAVYSEALS");
        ctuMap.put("Caveira", "BOPE");
        ctuMap.put("Capitão", "BOPE");
        ctuMap.put("Echo", "SAT");
        ctuMap.put("Hibana", "SAT");
        ctuMap.put("Jackal", "GEO");
        ctuMap.put("Mira", "GEO");
        ctuMap.put("Ying", "SDU");
        ctuMap.put("Lesion", "SDU");
        ctuMap.put("Zofia", "GROM");
        ctuMap.put("Ela", "GROM");
        ctuMap.put("Dokkaebi", "SMB");
        ctuMap.put("Vigil", "SMB");
        ctuMap.put("Finka", "CBRN");
        ctuMap.put("Lion", "CBRN");
        ctuMap.put("Maestro", "GIS");
        ctuMap.put("Alibi", "GIS");
    }

    static private void loginAuth(final VolleyResponseListener listener)
    {
        String URL = "https://uplayconnect.ubi.com/ubiservices/v2/profiles/sessions";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.v("JSONResponse", response.toString());
                            expirationTimeStr = response.getString("expiration");
                            expirationTimeFormatted = fromISO8601UTC(expirationTimeStr);
                            userName = response.getString("nameOnPlatform");
                            headers.setTicket(response.getString("ticket"));
                            printLogs();
                            headers.printHeaders();
                            if (listener != null)
                            {
                                listener.onResponse();
                            }
                        }
                        catch (Exception e)
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
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                //Log.v("getHeaders", "Getting Headers");
                return headers.getHeaders();
            }
        };
        mQueue.add(request);
    }
    static public void getPlayer(String platform, String key, String vals, final VolleyResponseListener listener)
    {
        // valid platforms = psn, vbl, uplay
        // valid keys= nameOnPlatform, idOnPlatform, userId
        // valid vals = name or id (depends on the key)
        if (isExpired())
        {
            loginAuth(null);
            getPlayer(platform, key, vals, null);
        }
        String URL = "https://public-ubiservices.ubi.com/v2/profiles?platformType="+ platform + "&" + key + "=" + vals;
        //String URL = "https://api-ubiservices.ubi.com/v2/profiles?" + key + "=" + vals + "&platformType=" + platform;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.v("JSONResponse", response.toString());
                            JSONArray profilesArr = response.getJSONArray("profiles");
                            for (int i = 0; i < profilesArr.length(); i++)
                            {
                                JSONObject player = profilesArr.getJSONObject(i);
                                String userName = player.getString("nameOnPlatform");
                                String platform = player.getString("platformType");
                                String userID = player.getString("userId");
                                Player temp = new Player(userName, platform, userID);
                                Log.v("PlayerToString", temp.toString());
                                playersResult.add(temp);
                            }

                            //printLogs();
                            //headers.printHeaders();
                            if (listener != null)
                                listener.onResponse();
                        }
                        catch (Exception e)
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
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Log.v("getHeaders", "Getting Headers");
                return headers.getHeaders();
            }
        };
        mQueue.add(request);
    }

    static public ArrayList<Player> getPlayersResult()
    {
        return playersResult;
    }

    static public void getLevel(String id, String platform, final VolleyResponseListener listener)
    {
        // valid platforms = PS4, XBOXONE, PC
        if (isExpired())
        {
            loginAuth(null);
            getLevel(id, platform, null);
        }
        String URL = "https://public-ubiservices.ubi.com/v1/spaces/5172a557-50b5-4665-b7db-e3f2e8c5041d/sandboxes/OSBOR_" + platform + "_LNCH_A/r6playerprofile/playerprofile/progressions?profile_ids="+ id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.v("JSONResponse", response.toString());
                            JSONArray profilesArr = response.getJSONArray("player_profiles");
                            for (int i = 0; i < profilesArr.length(); i++)
                            {
                                JSONObject player = profilesArr.getJSONObject(i);
                                int xp = player.getInt("xp");
                                String userID = player.getString("profile_id");
                                int lootboxProbability = player.getInt("lootbox_probability");
                                int level = player.getInt("level");
                                Level temp = new Level(xp, userID, lootboxProbability, level);
                                Log.v("LevelToString", temp.toString());
                                levelsResult.add(temp);
                            }

                            //printLogs();
                            //headers.printHeaders();

                            if (listener != null)
                                listener.onResponse();
                        }
                        catch (Exception e)
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
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Log.v("getHeaders", "Getting Headers");
                return headers.getHeaders();
            }
        };
        mQueue.add(request);
    }

    static public ArrayList<Level> getLevelsResult()
    {
        return levelsResult;
    }

    static public void getStats(String id, String platform, String stats, final VolleyResponseListener listener)
    {
        // valid platforms = PS4, XBOXONE, PC
        if (isExpired())
        {
            loginAuth(null);
            getStats(id, platform, stats, null);
        }
        String URL = "https://public-ubiservices.ubi.com/v1/spaces/5172a557-50b5-4665-b7db-e3f2e8c5041d/sandboxes/OSBOR_" + platform + "_LNCH_A/playerstats2/statistics?populations=" + id + "&statistics=" + stats;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.v("JSONResponse", response.toString());
                            JSONObject resultsObj = response.getJSONObject("results");
                            Iterator<?> keys = resultsObj.keys();

                            while( keys.hasNext() )
                            {
                                String key = (String)keys.next();
                                if ( resultsObj.get(key) instanceof JSONObject )
                                {
                                    JSONObject currObj = resultsObj.getJSONObject(key);
                                    int casualWon = currObj.getInt("casualpvp_matchwon:infinite");
                                    int casualLost = currObj.getInt("casualpvp_matchlost:infinite");
                                    int casualPlayed = currObj.getInt("casualpvp_matchplayed:infinite");
                                    int casualKills = currObj.getInt("casualpvp_kills:infinite");
                                    int casualDeaths = currObj.getInt("casualpvp_death:infinite");
                                    int casualTimePlayed = currObj.getInt("casualpvp_timeplayed:infinite");
                                    int rankedWon = currObj.getInt("rankedpvp_matchwon:infinite");
                                    int rankedLost = currObj.getInt("rankedpvp_matchlost:infinite");
                                    int rankedPlayed = currObj.getInt("rankedpvp_matchplayed:infinite");
                                    int rankedKills = currObj.getInt("rankedpvp_kills:infinite");
                                    int rankedDeaths = currObj.getInt("rankedpvp_death:infinite");
                                    int rankedTimePlayed = currObj.getInt("rankedpvp_timeplayed:infinite");
                                    Stat temp = new Stat(casualWon,casualLost, casualPlayed, casualKills, casualDeaths, casualTimePlayed, rankedWon, rankedLost, rankedPlayed, rankedKills, rankedDeaths, rankedTimePlayed);
                                    statsResult.add(temp);

                                    if (listener != null)
                                        listener.onResponse();
                                }
                            }
                            //printLogs();
                            //headers.printHeaders();
                        }
                        catch (Exception e)
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
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Log.v("getHeaders", "Getting Headers");
                return headers.getHeaders();
            }
        };
        mQueue.add(request);
    }

    static public ArrayList<Stat> getStatsResult()
    {
        return statsResult;
    }

    static public void getRanked(String id, String platform, String region, int season, final VolleyResponseListener listener)
    {
        // valid platforms = PS4, XBOXONE, PC
        if (isExpired())
        {
            loginAuth(null);
            getRanked(id, platform, region, season, null);
        }
        String URL = "https://public-ubiservices.ubi.com/v1/spaces/5172a557-50b5-4665-b7db-e3f2e8c5041d/sandboxes/OSBOR_" + platform + "_LNCH_A/r6karma/players?board_id=pvp_ranked&season_id=" + season + "&region_id=" + region + "&profile_ids=" + id;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.v("JSONResponse", response.toString());
                            JSONObject resultsObj = response.getJSONObject("players");
                            Iterator<?> keys = resultsObj.keys();

                            while( keys.hasNext() )
                            {
                                String key = (String)keys.next();
                                if ( resultsObj.get(key) instanceof JSONObject )
                                {
                                    JSONObject currObj = resultsObj.getJSONObject(key);
                                    String updateTime = currObj.getString("update_time");
                                    double skillMean = currObj.getDouble("skill_mean");
                                    int season = currObj.getInt("season");
                                    String region = currObj.getString("region");
                                    String userID = currObj.getString("profile_id");
                                    int pastSeasonWins = currObj.getInt("past_seasons_wins");
                                    int pastSeasonLosses = currObj.getInt("past_seasons_losses");
                                    double maxmmr = currObj.getDouble("max_mmr");
                                    double mmr = currObj.getDouble("mmr");
                                    int wins = currObj.getInt("wins");
                                    int losses = currObj.getInt("losses");
                                    int abandons = currObj.getInt("abandons");
                                    double standardDeviation = currObj.getDouble("skill_stdev");
                                    int rank = currObj.getInt("rank");
                                    double nextRankMMR = currObj.getDouble("next_rank_mmr");
                                    double prevRankMMR = currObj.getDouble("previous_rank_mmr");
                                    int maxRank = currObj.getInt("max_rank");
                                    Ranked temp = new Ranked(updateTime, skillMean, season, region, userID, pastSeasonWins, pastSeasonLosses, maxmmr, mmr, wins, losses, abandons, standardDeviation, rank, nextRankMMR, prevRankMMR, maxRank);
                                    rankedResult.add(temp);
                                }
                            }
                            //printLogs();
                            //headers.printHeaders();
                            if (listener != null)
                                listener.onResponse();
                        }
                        catch (Exception e)
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
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Log.v("getHeaders", "Getting Headers");
                return headers.getHeaders();
            }
        };
        mQueue.add(request);
    }

    static public ArrayList<Ranked> getRankedResult()
    {
        return rankedResult;
    }

    static public void getOperators(String id, String platform, String[] stats, final VolleyResponseListener listener)
    {
        // valid platforms = PS4, XBOXONE, PC
        if (isExpired())
        {
            loginAuth(null);
            getOperators(id, platform, stats, null);
        }
        statArray = stats;
        String tempOps = Arrays.toString(stats);
        tempOps = tempOps.substring(1, tempOps.length()-1);
        final String operators = tempOps;
        Log.v("ImportedOperatorsArray", operators);

        String URL = "https://public-ubiservices.ubi.com/v1/spaces/5172a557-50b5-4665-b7db-e3f2e8c5041d/sandboxes/OSBOR_" + platform + "_LNCH_A/playerstats2/statistics?populations=" + id + "&statistics=" + operators;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.v("JSONResponse", response.toString());
                            JSONObject resultsObj = response.getJSONObject("results");
                            Iterator<?> keys = resultsObj.keys();
                            //String prefix = "operatorpvp_";
                            String suffix = ":infinite";

                            while( keys.hasNext() )
                            {
                                String key = (String) keys.next();
                                if (resultsObj.get(key) instanceof JSONObject)
                                {
                                    JSONObject currObj = resultsObj.getJSONObject(key);
                                    //int special1;
                                    //String special1Desc;
                                    //int special2;
                                    //String special2Desc;
                                    //int special3;
                                    //String special3Desc;
                                    String userID = key;
                                    String name = "Sledge";
                                    ArrayList<Integer> operatorStats = new ArrayList<>();
                                    for (int i = 0; i < operatorFinalMap.size(); i++)
                                    {
                                        for (int j = 0; j < statArray.length; j++)
                                        {
                                            int currentStat;
                                            name = operatorFinalMap.get(i).second;
                                            if (currObj.has(statArray[j] + ":" + operatorFinalMap.get(i).first + suffix))
                                                currentStat = currObj.getInt(statArray[j] + ":" + operatorFinalMap.get(i).first + suffix);//Log.v(name + " " + statArray[j], String.valueOf(currentStat = currObj.getInt(statArray[j] + ":" + operatorFinalMap.get(i).first + suffix)));
                                            else
                                                currentStat = 0;//Log.v(name + " " + statArray[j], String.valueOf(currentStat = 0));

                                            operatorStats.add(currentStat);
                                        }
                                        Operator temp = new Operator(userID, name, ctuMap.get(name), operatorStats);
                                        operatorFinalResult.put(name, temp);
                                        operatorStats.clear();
                                    }

                                    for (Operator op: operatorFinalResult.values()) {
                                        Log.v("OpTest", op.toString());
                                    }

                                    if (listener != null)
                                        listener.onResponse();
                                }
                            }
                            //printLogs();
                            //headers.printHeaders();
                        }
                        catch (Exception e)
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
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Log.v("getHeaders", "Getting Headers");
                return headers.getHeaders();
            }
        };
        mQueue.add(request);
    }

    static public HashMap<String, Operator> getOperatorFinalResult()
    {
        return operatorFinalResult;
    }

    static public boolean isExpired()
    {
        //if expired time > currenttime then call loginAuth
        return fromISO8601UTC(toISO8601UTC(new Date())).after(expirationTimeFormatted);
    }

    static public RequestQueue getmQueue()
    {
        return mQueue;
    }

    static public void setmQueue(RequestQueue queue)
    {
        mQueue = queue;
    }

    static private void printLogs()
    {
        Log.v("onResponse", "Printing Logs/Expiration Times");
        Log.v("UserName", userName);
        Log.v("ExpirationTimeStr", expirationTimeStr);
        Log.v("ExpirationTimeFormatted", expirationTimeFormatted.toString());
        Log.v("CurrentTimeFormatted", new Date().toString());
    }

    private static String toISO8601UTC(Date date)
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        df.setTimeZone(tz);
        return df.format(date);
    }

    private static Date fromISO8601UTC(String dateStr)
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        df.setTimeZone(tz);

        try
        {
            return df.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static class MyHeader
    {
        private String ubiAppID = "39baebad-39e5-4552-8c25-2c9b919064e2";
        private String contentType = "application/json; charset=UTF-8";
        private String ticket = "DEFAULT";
        private HashMap<String, String> headers = new HashMap<>();

        MyHeader(String encoded)
        {
            encodeCredentials(encoded);
            this.headers.put("Content-Type", contentType);
            this.headers.put("ubi-appid", ubiAppID);
        }

        public HashMap<String, String> getHeaders()
        {
            return this.headers;
        }

        private void encodeCredentials(String creds)
        {
            try
            {
                byte[] data = creds.getBytes("UTF-8");
                String temp = "Basic " + Base64.encodeToString(data, Base64.DEFAULT);
                this.headers.put("authorization", temp);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void setTicket(String ticket)
        {
            this.ticket = ticket;
            this.headers.put("authorization", "Ubi_v1 t=" + ticket);
        }

        public void printHeaders()
        {
            Log.v("onResponse", "Printing Headers");
            Log.v("HeaderContent", this.headers.get("Content-Type"));
            Log.v("HeaderAppID", this.headers.get("ubi-appid"));
            Log.v("HeaderAuth", this.headers.get("authorization"));
        }
    }
}

