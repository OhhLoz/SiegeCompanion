package productions.pudl.siege.Adapter;

import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import productions.pudl.siege.Data.Level;
import productions.pudl.siege.Data.Player;


public class MyUbiAPIAdapter
{
    static private String expirationTimeStr = "DEFAULT";
    static private String userName = "DEFAULT";
    static private Date expirationTimeFormatted = null;
    static private RequestQueue mQueue;
    static private MyHeader headers;
    static private ArrayList<Player> playersResult;
    static private ArrayList<Level> levelsResult;

    static public void create(RequestQueue currQueue, String credentials)
    {
        headers = new MyHeader(credentials);
        mQueue = currQueue;
        if ((expirationTimeStr.equals("DEFAULT") && expirationTimeFormatted == null) || isExpired())
            loginAuth();
    }

    static private void loginAuth()
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

    static public void getPlayer(String platform, String key, String vals)
    {
        // valid platforms = psn, vbl, uplay
        // valid keys= nameOnPlatform, idOnPlatform, userId
        // valid vals = name or id (depends on the key)
        if (isExpired())
            loginAuth();
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
                            ArrayList<Player> playersArr = new ArrayList<>();
                            for (int i = 0; i < profilesArr.length(); i++)
                            {
                                JSONObject player = profilesArr.getJSONObject(i);
                                String userName = player.getString("nameOnPlatform");
                                String platform = player.getString("platformType");
                                String userID = player.getString("userId");
                                Player temp = new Player(userName, platform, userID);
                                Log.v("PlayerToString", temp.toString());
                                playersArr.add(temp);
                            }

                            //printLogs();
                            headers.printHeaders();
                            setPlayersResult(playersArr);
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

    static private void setPlayersResult(ArrayList<Player> temp)
    {
        playersResult = temp;
        Log.v("GetPlayer", "Set Results");
    }

    static public ArrayList<Player> getPlayersResult()
    {
        return playersResult;
    }

    static public void getLevel(String id, String platform)
    {
        // valid platforms = PS4, XBOXONE, PC
        if (isExpired())
            loginAuth();
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
                            ArrayList<Level> levelsArr = new ArrayList<>();
                            for (int i = 0; i < profilesArr.length(); i++)
                            {
                                JSONObject player = profilesArr.getJSONObject(i);
                                int xp = player.getInt("xp");
                                String userID = player.getString("profile_id");
                                int lootboxProbability = player.getInt("lootbox_probability");
                                int level = player.getInt("level");
                                Level temp = new Level(xp, userID, lootboxProbability, level);
                                Log.v("LevelToString", temp.toString());
                                levelsArr.add(temp);
                            }

                            //printLogs();
                            headers.printHeaders();
                            setLevelsResult(levelsArr);
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

    static private void setLevelsResult(ArrayList<Level> temp)
    {
        levelsResult = temp;
        Log.v("GetLevel", "Set Results");
    }

    static public ArrayList<Level> getLevelsResult()
    {
        return levelsResult;
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

