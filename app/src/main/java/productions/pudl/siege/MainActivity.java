package productions.pudl.siege;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import productions.pudl.siege.Adapter.MyUbiAPIAdapter;
import productions.pudl.siege.Adapter.UbisoftAPIService;
import productions.pudl.siege.Fragment.Compare;
import productions.pudl.siege.Fragment.More;
import productions.pudl.siege.Fragment.Operators.Operators;
import productions.pudl.siege.Fragment.Profile;
import productions.pudl.siege.Fragment.Search.Search;

public class MainActivity extends AppCompatActivity
{
    BottomBar bottomBar;
    Messenger messenger;
    Intent APIServiceIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIServiceIntent = new Intent(MainActivity.this, UbisoftAPIService.class);
        startService(APIServiceIntent);
        bindService(APIServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener()
        {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.navigation_compare) {
                    Compare compare = new Compare();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, compare).commit();
                    setTitle(R.string.title_compare);
                }
                else if (tabId == R.id.navigation_operators){
                    Operators operators = new Operators();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, operators).commit();
                    setTitle(R.string.title_operators);
                }
                else if (tabId == R.id.navigation_profile) {
                    Profile profile = new Profile();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, profile).commit();
                    setTitle(R.string.title_profile);
                }
                else if (tabId == R.id.navigation_search) {
                    Search search = new Search();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, search).commit();
                    setTitle(R.string.title_search);
                }
                else if (tabId == R.id.navigation_more) {
                    More more = new More();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, more).commit();
                    setTitle(R.string.title_more);
                }
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            // TODO Auto-generated method stub
            Log.d("MainActivity", "onServiceConnected");
            messenger = new Messenger(service);
            sendMessage(UbisoftAPIService.APIConstants.OPEN_CONN, null);
        }
        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            // TODO Auto-generated method stub
            Log.d("MainActivity", "onServiceDisconnected");
            messenger = null;
        }
    };

    public void sendMessage(int param, Bundle bundle)
    {
        Log.v("UbisoftAPIService", "Sent message to Service with param: " + String.valueOf(param));
        Message message = Message.obtain(null, param);
        if (bundle != null)
            message.setData(bundle);
        try
        {
            messenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}