package productions.pudl.siege.Fragment;

import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import productions.pudl.siege.Data.GeneralObjects.GeneralAliasObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasesObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralLastPlayedObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRankObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralRanksObject;
import productions.pudl.siege.R;

public class Operators extends Fragment
{

    String encoded;
    RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.operators_fragment, container, false);
        mQueue = Volley.newRequestQueue(view.getContext());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Button b = (Button) view.findViewById(R.id.loginButton);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    String userdata = "INSERT CREDENTIALS";
                    byte[] data = userdata.getBytes("UTF-8");
                    encoded = "Basic " + Base64.encodeToString(data, Base64.DEFAULT);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                String URL = "https://uplayconnect.ubi.com/ubiservices/v2/profiles/sessions";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                TextView textView = getView().findViewById(R.id.textView);
                                textView.setText("Sent out encoded user/pass to ubi endpoint" + encoded);
                                Log.v("JSONArray", response.toString());
                                TextView textView2 = getView().findViewById(R.id.textView2);
                                textView2.setText(response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError
                    {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=UTF-8");
                        headers.put("x-app-id", "175980a6-8ce2-449c-976b-e8cd9c05d86b");
                        headers.put("ubi-appid", "39baebad-39e5-4552-8c25-2c9b919064e2");
                        headers.put("authorization", encoded);
                        return headers;
                    }
                };
                mQueue.add(request);
            }

        });

        return view;
    }
}
//                //style="?android:attr/borderlessButtonStyle"
//                String urladdr = "https://uplayconnect.ubi.com/ubiservices/v2/profiles/sessions";
//
//                try {
//                    String userdata = "***REMOVED***";
//                    byte[] data = userdata.getBytes("UTF-8");
//                    String encoded = "Basic " + Base64.encodeToString(data, Base64.DEFAULT);
//                    URL url = new URL(urladdr);
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                    urlConnection.setRequestMethod("POST");
//                    urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                    urlConnection.setRequestProperty("ubi-appid", "39baebad-39e5-4552-8c25-2c9b919064e2");
//                    urlConnection.setRequestProperty("Ubi_v1", "t=@ticket");
//                    urlConnection.setRequestProperty("authorization", encoded);
//                    urlConnection.setDoOutput(true);
//                    urlConnection.setChunkedStreamingMode(0);
//
//                    OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//                    out.write(encoded.getBytes());
//                    out.flush();
//
//                    Log.v("UbiAPI", "Sent out encoded user/pass to ubi endpoint " + encoded);
//                    TextView textView = view.findViewById(R.id.textView);
//                    textView.setText("Sent out encoded user/pass to ubi endpoint" + encoded);
//
//                    StringBuffer sb = new StringBuffer();
//                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                    int chr;
//                    while ((chr = in.read()) != -1)
//                    {
//                        sb.append((char) chr);
//                    }
//                    String reply = sb.toString();
//                    Log.v("UbiAPI", "Received Reply from Ubi Endpoint!");
//                    Log.v("UbiAPI", reply);
//                    TextView textView2 = view.findViewById(R.id.textView2);
//                    textView.setText(reply);
//                    urlConnection.disconnect();
//                } catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });


