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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

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
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ImageView userImage = (ImageView) view.findViewById(R.id.userImage);
        Picasso.get().load(R.drawable.r6slogo).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(userImage);
        TextView levelName = (TextView) view.findViewById(R.id.level);
        levelName.setText("Level: ");
        //View view = inflater.inflate(R.layout.test_profile_fragment, container, false);
        /*mQueue = Volley.newRequestQueue(view.getContext());
        Resources res = getResources();
        String[] tempStatsArr = res.getStringArray(R.array.overallStats);
        String tempStats = Arrays.toString(tempStatsArr);
        tempStats = tempStats.substring(1, tempStats.length()-1);
        final String stats = tempStats;
        Log.v("ImportedStatsArray", stats);

        final String[] tempOperatorArr = res.getStringArray(R.array.operators);

        Button testButton = (Button) view.findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                MyUbiAPIAdapter.create(getContext(), "raspberrypicreations@gmail.com:1NnpENN6za61", null);
            }

        });

        Button testButton2 = (Button) view.findViewById(R.id.testButton2);
        testButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                MyUbiAPIAdapter.getPlayer("uplay", "userId", "28ca710b-270d-491b-8073-42654f82745d", null);
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
                MyUbiAPIAdapter.getLevel("28ca710b-270d-491b-8073-42654f82745d", "PC", null);
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
                MyUbiAPIAdapter.getStats(MyUbiAPIAdapter.getPlayersResult().get(0).getUserID(), "PC", stats, null);
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
                MyUbiAPIAdapter.getRanked(MyUbiAPIAdapter.getPlayersResult().get(0).getUserID(), "PC", "emea", -1, null);
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

        Button testButton10 = (Button) view.findViewById(R.id.testButton10);
        testButton10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                //MyUbiAPIAdapter.getOperators(MyUbiAPIAdapter.getPlayersResult().get(0).getUserID(), "PC", tempOperatorArr);
                MyUbiAPIAdapter.getOperators("28ca710b-270d-491b-8073-42654f82745d", "PC", tempOperatorArr, null);
            }

        });

        Button testButton11 = (Button) view.findViewById(R.id.testButton11);
        testButton11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                for(Ranked temp : MyUbiAPIAdapter.getRankedResult())
                    Log.v("FinalPlayerRanked", temp.toString());
            }

        });*/
        return view;
    }
}
