package productions.pudl.siege.Adapter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
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

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import productions.pudl.siege.Data.Operator;
import productions.pudl.siege.R;

public class UbisoftAPIService extends Service
{
    private Messenger messenger = new Messenger(new APIMessageHandler());
    private Messenger replyMessenger;

    static private String globalCreds = "raspberrypicreations@gmail.com:1NnpENN6za61";
    static private String expirationTimeStr = "DEFAULT";
    static private String userName = "DEFAULT";
    static private Date expirationTimeFormatted = null;
    static private RequestQueue mQueue;
    static private UbisoftAPIService.MyHeader headers;
    static private HashMap<String, Operator> operatorFinalResult = new HashMap<>();
    static private HashMap<String, String> ctuMap = new HashMap<String, String>(){{
        put("Sledge", "SAS");
        put("Thatcher", "SAS");
        put("Smoke", "SAS");
        put("Mute", "SAS");
        put("Recruit(SAS)", "SAS");
        put("Castle", "FBISWAT");
        put("Ash", "FBISWAT");
        put("Recruit(FBISWAT)", "FBISWAT");
        put("Thermite", "FBISWAT");
        put("Pulse", "FBISWAT");
        put("Recruit(GIGN)", "GIGN");
        put("Rook", "GIGN");
        put("Doc", "GIGN");
        put("Twitch", "GIGN");
        put("Montagne", "GIGN");
        put("Recruit(Spetsnaz)", "Spetsnaz");
        put("Glaz", "Spetsnaz");
        put("Fuze", "Spetsnaz");
        put("Kapkan", "Spetsnaz");
        put("Tachanka", "Spetsnaz");
        put("Recruit(GSG9)", "GSG9");
        put("Blitz", "GSG9");
        put("Bandit", "GSG9");
        put("Jäger", "GSG9");
        put("IQ", "GSG9");
        put("Buck", "JTF2");
        put("Frost", "JTF2");
        put("Blackbeard", "NAVYSEALS");
        put("Valkyrie", "NAVYSEALS");
        put("Caveira", "BOPE");
        put("Capitão", "BOPE");
        put("Echo", "SAT");
        put("Hibana", "SAT");
        put("Jackal", "GEO");
        put("Mira", "GEO");
        put("Ying", "SDU");
        put("Lesion", "SDU");
        put("Zofia", "GROM");
        put("Ela", "GROM");
        put("Dokkaebi", "SMB");
        put("Vigil", "SMB");
        put("Finka", "CBRN");
        put("Lion", "CBRN");
        put("Maestro", "GIS");
        put("Alibi", "GIS");
        put("Maverick", "GSUTR");
        put("Clash", "GSUTR");
        put("Nomad", "GIGR");
        put("Kaid", "GIGR");
        put("Gridlock", "SASR");
        put("Mozzie", "SASR");
        put("Nøkk", "USSRJaeg");
        put("Warden", "USSRJaeg");
    }};
    static private SparseArray<Pair<String, String>> operatorFinalMap = new SparseArray<Pair<String, String>>(){{
        append(0, new Pair<>("1:1", "Recruit(SAS)"));
        append(1, new Pair<>("1:2", "Recruit(FBISWAT)"));
        append(2, new Pair<>("1:3", "Recruit(GIGN)"));
        append(3, new Pair<>("1:4", "Recruit(Spetsnaz)"));
        append(4, new Pair<>("1:5", "Recruit(GSG9)"));
        append(5, new Pair<>("2:1", "Smoke"));
        append(6, new Pair<>("3:1", "Mute"));
        append(7, new Pair<>("4:1", "Sledge"));
        append(8, new Pair<>("5:1", "Thatcher"));
        append(9, new Pair<>("2:2", "Castle"));
        append(10, new Pair<>("3:2", "Ash"));
        append(11, new Pair<>("4:2", "Pulse"));
        append(12, new Pair<>("5:2", "Thermite"));
        append(13, new Pair<>("2:3", "Doc"));
        append(14, new Pair<>("3:3", "Rook"));
        append(15, new Pair<>("4:3", "Twitch"));
        append(16, new Pair<>("5:3", "Montagne"));
        append(17, new Pair<>("2:4", "Glaz"));
        append(18, new Pair<>("3:4", "Fuze"));
        append(19, new Pair<>("4:4", "Kapkan"));
        append(20, new Pair<>("5:4", "Tachanka"));
        append(21, new Pair<>("2:5", "Blitz"));
        append(22, new Pair<>("3:5", "IQ"));
        append(23, new Pair<>("4:5", "Jäger"));
        append(24, new Pair<>("5:5", "Bandit"));
        append(25, new Pair<>("2:6", "Buck"));
        append(26, new Pair<>("3:6", "Frost"));
        append(27, new Pair<>("2:7", "Blackbeard"));
        append(28, new Pair<>("3:7", "Valkyrie"));
        append(29, new Pair<>("2:8", "Capitão"));
        append(30, new Pair<>("3:8", "Caveira"));
        append(31, new Pair<>("2:9", "Hibana"));
        append(32, new Pair<>("3:9", "Echo"));
        append(33, new Pair<>("2:A", "Jackal"));
        append(34, new Pair<>("3:A", "Mira"));
        append(35, new Pair<>("2:B", "Ying"));
        append(36, new Pair<>("3:B", "Lesion"));
        append(37, new Pair<>("2:C", "Ela"));
        append(38, new Pair<>("3:C", "Zofia"));
        append(39, new Pair<>("2:D", "Dokkaebi"));
        append(40, new Pair<>("3:D", "Vigil"));
        append(41, new Pair<>("3:E", "Lion"));
        append(42, new Pair<>("4:E", "Finka"));
        append(43, new Pair<>("2:F", "Maestro"));
        append(44, new Pair<>("3:F", "Alibi"));
        append(45, new Pair<>("2:10", "Maverick"));
        append(46, new Pair<>("3:10", "Clash"));
        append(47, new Pair<>("2:11", "Nomad"));
        append(48, new Pair<>("3:11", "Kaid"));
        append(49, new Pair<>("2:12", "Mozzie"));
        append(50, new Pair<>("3:12", "Gridlock"));
        append(51, new Pair<>("2:13", "Nøkk"));
        append(52, new Pair<>("2:14", "Warden"));
    }};
    static private HashMap<String, String> platformMap = new HashMap<String, String>(){{
        put("uplay", "PC");
        put("xbl", "XBOXONE");
        put("psn", "PS4");
    }};

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("UbisoftAPIService", "Service Created");
    }

    public void changeContext()
    {
        mQueue = Volley.newRequestQueue(this);
    }

    public void create(Context context, String credentials, final VolleyResponseListener listener)
    {
        headers = new UbisoftAPIService.MyHeader(credentials);
        mQueue = Volley.newRequestQueue(context);
        globalCreds = credentials;
        if (((expirationTimeStr.equals("DEFAULT") && expirationTimeFormatted == null)) || isExpired())
            openConnection(listener);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.v("UbisoftAPIService", "Service Bound");
        return messenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.v("UbisoftAPIService", "Service Started (StartCommand)");
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    public void sendMessage(int param, Bundle bundle)
    {
        Log.v("UbisoftAPIService", "Sent message to Activity with param: " + String.valueOf(param));
        Message message = Message.obtain(null, param);
        if (bundle != null)
            message.setData(bundle);
        //message.replyTo = messenger;
        try
        {
            replyMessenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    private class APIMessageHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            replyMessenger = msg.replyTo;
            //Log.d("RunningService", "Reply Received");
            Bundle receivedBundle = msg.getData();
            switch (msg.what)
            {
                case APIConstants.OPEN_CONN:
                    Log.v("APIMessageHandler", "Handled message OPEN_CONN");
                    create(getApplicationContext(), globalCreds, null);
                    break;
                case APIConstants.CLOSE_CONN:
                    Log.v("APIMessageHandler", "Handled message CLOSE_CONN");
                    closeConnection();
                    break;
                case APIConstants.CHANGE_CONN:
                    Log.v("APIMessageHandler", "Handled message CHANGE_CONN");
                    changeContext();
                    break;
                case APIConstants.GET_OPERATORS:
                    Log.v("APIMessageHandler", "Handled message GET_OPERATORS");
                    getOperators(receivedBundle.getString("id"), receivedBundle.getString("platform"), null);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private void closeConnection()
    {
        stopForeground(true); // Cancel Notification
    }

    private void openConnection(final VolleyResponseListener listener)
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

    public void getOperators(final String id, final String platform, final VolleyResponseListener listener)
    {
        // valid platforms = PS4, XBOXONE, PC
        if (isExpired())
        {
            openConnection(new VolleyResponseListener() {
                @Override
                public void onError(String message) {

                }

                @Override
                public void onResponse()
                {
                    getOperators(id, platform, listener);
                }
            });
        }
        else
        {
            final String[] statArray = getResources().getStringArray(R.array.operators);
            String tempOps = Arrays.toString(statArray);
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
                                                {
                                                    currentStat = currObj.getInt(statArray[j] + ":" + operatorFinalMap.get(i).first + suffix);//Log.v(name + " " + statArray[j], String.valueOf(currentStat = currObj.getInt(statArray[j] + ":" + operatorFinalMap.get(i).first + suffix)));
                                                    operatorStats.add(currentStat);
                                                }
                                                else
                                                {
                                                    currentStat = 0;//Log.v(name + " " + statArray[j], String.valueOf(currentStat = 0));
                                                    operatorStats.add(currentStat);
                                                }
                                            }
                                            Operator temp = new Operator(userID, name, ctuMap.get(name), operatorStats);
                                            // special stats here
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
    }

    private String toISO8601UTC(Date date)
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        df.setTimeZone(tz);
        return df.format(date);
    }

    private Date fromISO8601UTC(String dateStr)
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

    private void printLogs()
    {
        Log.v("onResponse", "Printing Logs/Expiration Times");
        Log.v("UserName", userName);
        Log.v("ExpirationTimeStr", expirationTimeStr);
        Log.v("ExpirationTimeFormatted", expirationTimeFormatted.toString());
        Log.v("CurrentTimeFormatted", new Date().toString());
    }

    public boolean isExpired()
    {
        //if expired time > currenttime then call loginAuth
        return fromISO8601UTC(toISO8601UTC(new Date())).after(expirationTimeFormatted);
    }

    public RequestQueue getmQueue()
    {
        return mQueue;
    }

    public void setmQueue(RequestQueue queue)
    {
        mQueue = queue;
    }

    public class MyHeader
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

    static public class APIConstants
    {
        public static final int OPEN_CONN = 0;
        public static final int CLOSE_CONN = 1;
        public static final int CHANGE_CONN = 2;

        public static final int GET_PLAYER = 3;
        public static final int GET_LEVEL = 4;
        public static final int GET_STATS = 5;
        public static final int GET_RANKED = 6;
        public static final int GET_OPERATORS = 7;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.v("UbisoftAPIService", "Service Unbound");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy()
    {
        Log.v("UbisoftAPIService", "Service Destroyed");
        closeConnection();
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent)
    {
        super.onRebind(intent);
        changeContext();
        Log.v("UbisoftAPIService", "Service Rebound");
    }
}
