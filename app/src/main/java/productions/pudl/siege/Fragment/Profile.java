package productions.pudl.siege.Fragment;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;

import productions.pudl.siege.Adapter.MyUbiAPIAdapter;
import productions.pudl.siege.Data.Level;
import productions.pudl.siege.Data.Player;
import productions.pudl.siege.Data.Ranked;
import productions.pudl.siege.Data.Stat;
import productions.pudl.siege.R;

public class Profile extends Fragment
{
    RequestQueue mQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.operators_fragment, container, false);
        mQueue = Volley.newRequestQueue(view.getContext());
        Resources res = getResources();
        String[] tempArr;
        tempArr = res.getStringArray(R.array.overallStats);
        String tempStats = Arrays.toString(tempArr);
        tempStats = tempStats.substring(1, tempStats.length()-1);
        final String stats = tempStats;
        Log.v("TestToSeeIfArrayWorks", stats);
        //RequestQueue mQueue = Volley.newRequestQueue(view.getContext());
        //MyUbiAPIAdapter.create(mQueue, encoded);
        //final String stats = "casualpvp_matchwon:infinite, rankedpvp_timeplayed:infinite, rankedpvp_matchlost:infinite, casualpvp_matchlost:infinite, rankedpvp_death:infinite, casualpvp_timeplayed:infinite, rankedpvp_matchwon:infinite, rankedpvp_kills:infinite, casualpvp_matchplayed:infinite, casualpvp_death:infinite, rankedpvp_matchplayed:infinite, casualpvp_kills:infinite";

        Button testButton = (Button) view.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                MyUbiAPIAdapter.create(mQueue, "raspberrypicreations@gmail.com:1NnpENN6za61");
            }

        });

        Button testButton2 = (Button) view.findViewById(R.id.testButton2);
        testButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                MyUbiAPIAdapter.getPlayer("uplay", "userId", "28ca710b-270d-491b-8073-42654f82745d");
            }

        });

        Button testButton3 = (Button) view.findViewById(R.id.testButton3);
        testButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                for(Player temp : MyUbiAPIAdapter.getPlayersResult())
                    Log.v("FinalPlayer", temp.toString());
                //Log.v("FinalPlayer", MyUbiAPIAdapter.getPlayersResult().get(0).toString());
            }

        });

        Button testButton4 = (Button) view.findViewById(R.id.testButton4);
        testButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                MyUbiAPIAdapter.getLevel("28ca710b-270d-491b-8073-42654f82745d", "PC");
            }

        });

        Button testButton5 = (Button) view.findViewById(R.id.testButton5);
        testButton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                for(Level temp : MyUbiAPIAdapter.getLevelsResult())
                    Log.v("FinalPlayerLevel", temp.toString());
                //Log.v("FinalLevel", MyUbiAPIAdapter.getLevelsResult().get(0).toString());
            }

        });

        Button testButton6 = (Button) view.findViewById(R.id.testButton6);
        testButton6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                MyUbiAPIAdapter.getStats(MyUbiAPIAdapter.getPlayersResult().get(0).getUserID(), "PC", stats);
            }

        });

        Button testButton7 = (Button) view.findViewById(R.id.testButton7);
        testButton7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                for(Stat temp : MyUbiAPIAdapter.getStatsResult())
                    Log.v("FinalPlayerStats", temp.toString());
            }

        });

        Button testButton8 = (Button) view.findViewById(R.id.testButton8);
        testButton8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                MyUbiAPIAdapter.getRanked(MyUbiAPIAdapter.getPlayersResult().get(0).getUserID(), "PC", "emea", -1);
            }

        });

        Button testButton9 = (Button) view.findViewById(R.id.testButton9);
        testButton9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                for(Ranked temp : MyUbiAPIAdapter.getRankedResult())
                    Log.v("FinalPlayerRanked", temp.toString());
            }

        });
        return view;
    }
}
