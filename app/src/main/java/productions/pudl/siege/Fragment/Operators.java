package productions.pudl.siege.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import productions.pudl.siege.Adapter.MyUbiAPIAdapter;
import productions.pudl.siege.R;

public class Operators extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.operators_fragment, container, false);
        String encoded = "***REMOVED***";
        RequestQueue mQueue = Volley.newRequestQueue(view.getContext());
        MyUbiAPIAdapter.create(mQueue, encoded);
        //MyUbiAPIAdapter.printLogs();
        Log.v("UbiAdapter", "Token Expired");
        return view;
    }
}