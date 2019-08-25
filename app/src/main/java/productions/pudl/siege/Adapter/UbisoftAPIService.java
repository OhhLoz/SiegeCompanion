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
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

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
