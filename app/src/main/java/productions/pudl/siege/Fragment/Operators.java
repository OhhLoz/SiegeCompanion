package productions.pudl.siege.Fragment;

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


import productions.pudl.siege.Adapter.MyUbiAPIAdapter;
import productions.pudl.siege.Data.Level;
import productions.pudl.siege.Data.Player;
import productions.pudl.siege.R;

public class Operators extends Fragment
{
    RequestQueue mQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.operators_fragment, container, false);
        mQueue = Volley.newRequestQueue(view.getContext());
        //RequestQueue mQueue = Volley.newRequestQueue(view.getContext());
        //MyUbiAPIAdapter.create(mQueue, encoded);

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
//                for(Player temp : MyUbiAPIAdapter.getPlayersResult())
//                    Log.v("FinalPlayer", temp.toString());
                Log.v("FinalPlayer", MyUbiAPIAdapter.getPlayersResult().get(0).toString());
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
//                for(Level temp : MyUbiAPIAdapter.getLevelsResult())
//                    Log.v("FinalPlayerLevel", temp.toString());
                Log.v("FinalLevel", MyUbiAPIAdapter.getLevelsResult().get(0).toString());
            }

        });
        return view;
    }
}