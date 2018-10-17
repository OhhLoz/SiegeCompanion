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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import productions.pudl.siege.Adapter.MyUbiAPIAdapter;
import productions.pudl.siege.Adapter.VolleyResponseListener;
import productions.pudl.siege.Data.Level;
import productions.pudl.siege.Data.Player;
import productions.pudl.siege.Data.Ranked;
import productions.pudl.siege.Data.Stat;
import productions.pudl.siege.R;

public class Profile extends Fragment {
    //RequestQueue mQueue;
    ArrayList<Stat> statResult = new ArrayList<>();
    Level levelObj;
    Player playerObj;
    Ranked rankedObj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final String userID = "28ca710b-270d-491b-8073-42654f82745d";
        final String platform = "PC";
        final View view = inflater.inflate(R.layout.profile_fragment, container, false);

        MyUbiAPIAdapter.create(view.getContext(), "raspberrypicreations@gmail.com:1NnpENN6za61", new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse() {
                MyUbiAPIAdapter.getStats(userID, platform, getResources().getStringArray(R.array.overallStats), new VolleyResponseListener() {
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse() {
                            statResult = MyUbiAPIAdapter.getStatsResult();
                            populateStats(view, statResult.get(0));
                        }
                    });

                MyUbiAPIAdapter.getLevel(userID, platform, new VolleyResponseListener() {
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse() {
                            levelObj = MyUbiAPIAdapter.getLevelsResult().get(0);
                            populateLevel(view, levelObj);
                        }
                    });
                MyUbiAPIAdapter.getRanked(userID, platform, "emea", -1, new VolleyResponseListener() { // need to figure out how to decide which region they are from
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse() {
                            rankedObj = MyUbiAPIAdapter.getRankedResult().get(0);
                            populateRanked(view, rankedObj);
                        }
                    });
                MyUbiAPIAdapter.getPlayer(platform, "userId", userID, new VolleyResponseListener() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse() {
                        playerObj = MyUbiAPIAdapter.getPlayersResult().get(0);
                        //playerObj.setLevelObj(levelObj);
                        //playerObj.setRankedObj(rankedObj);
                        //playerObj.setStatsObj(statResult.get(0));
                        populatePlayer(view, playerObj);
                    }
                });
            }
        });
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
                MyUbiAPIAdapter.getPlayer("uplay", "statResult.get(0)Id", "28ca710b-270d-491b-8073-42654f82745d", null);
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
                MyUbiAPIAdapter.getStats(MyUbiAPIAdapter.getPlayersResult().get(0).getstatResult.get(0)ID(), "PC", stats, null);
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
                MyUbiAPIAdapter.getRanked(MyUbiAPIAdapter.getPlayersResult().get(0).getstatResult.get(0)ID(), "PC", "emea", -1, null);
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
                //MyUbiAPIAdapter.getOperators(MyUbiAPIAdapter.getPlayersResult().get(0).getstatResult.get(0)ID(), "PC", tempOperatorArr);
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

    public void populateStats(View view, Stat stats)
    {
        if (stats != null)
        {
            //GENERAL
            ((TextView) view.findViewById(R.id.generalKillsText)).setText(String.valueOf(stats.getGeneralKills()));
            ((TextView) view.findViewById(R.id.generalDeathsText)).setText(String.valueOf(stats.getGeneralDeaths()));
            ((TextView) view.findViewById(R.id.generalKDText)).setText(String.valueOf(stats.getGeneralKD()));
            ((TextView) view.findViewById(R.id.generalWinsText)).setText(String.valueOf(stats.getGeneralWins()));
            ((TextView) view.findViewById(R.id.generalLossesText)).setText(String.valueOf(stats.getGeneralLost()));
            ((TextView) view.findViewById(R.id.generalWLText)).setText(String.valueOf(stats.getGeneralWL()));
            ((TextView) view.findViewById(R.id.generalAssistsText)).setText(String.valueOf(stats.getGeneralAssists()));
            ((TextView) view.findViewById(R.id.generalPlayedText)).setText(String.valueOf(stats.getGeneralPlayed()));
            ((TextView) view.findViewById(R.id.generalTimePlayedText)).setText(String.valueOf(stats.getGeneralTimePlayedStr()));

            //CASUAL
            ((TextView) view.findViewById(R.id.casualKillsText)).setText(String.valueOf(stats.getCasualKills()));
            ((TextView) view.findViewById(R.id.casualDeathsText)).setText(String.valueOf(stats.getCasualDeaths()));
            ((TextView) view.findViewById(R.id.casualKDText)).setText(String.valueOf(stats.getCasualKD()));
            ((TextView) view.findViewById(R.id.casualWinsText)).setText(String.valueOf(stats.getCasualWon()));
            ((TextView) view.findViewById(R.id.casualLossesText)).setText(String.valueOf(stats.getCasualLost()));
            ((TextView) view.findViewById(R.id.casualWLText)).setText(String.valueOf(stats.getCasualWL()) + "%");
            ((TextView) view.findViewById(R.id.casualPlayedText)).setText(String.valueOf(stats.getCasualPlayed()));
            ((TextView) view.findViewById(R.id.casualTimePlayedText)).setText(String.valueOf(stats.getCasualTimePlayedStr()));

            //RANKED
            ((TextView) view.findViewById(R.id.rankedKillsText)).setText(String.valueOf(stats.getRankedKills()));
            ((TextView) view.findViewById(R.id.rankedDeathsText)).setText(String.valueOf(stats.getRankedDeaths()));
            ((TextView) view.findViewById(R.id.rankedKDText)).setText(String.valueOf(stats.getRankedKD()));
            ((TextView) view.findViewById(R.id.rankedWinsText)).setText(String.valueOf(stats.getRankedWon()));
            ((TextView) view.findViewById(R.id.rankedLossesText)).setText(String.valueOf(stats.getRankedLost()));
            ((TextView) view.findViewById(R.id.rankedWLText)).setText(String.valueOf(stats.getRankedWL()) + "%");
            ((TextView) view.findViewById(R.id.rankedPlayedText)).setText(String.valueOf(stats.getRankedPlayed()));
            ((TextView) view.findViewById(R.id.rankedTimePlayedText)).setText(String.valueOf(stats.getRankedTimePlayedStr()));

            //WEAPON
            ((TextView) view.findViewById(R.id.weaponHSText)).setText(String.valueOf(stats.getWeaponHeadshots()));
            ((TextView) view.findViewById(R.id.weaponHSHitText)).setText(String.valueOf(stats.getWeaponHSHit()) + "%");
            ((TextView) view.findViewById(R.id.weaponHSKillText)).setText(String.valueOf(stats.getWeaponHSKill()) + "%");
            ((TextView) view.findViewById(R.id.weaponBulletsHitText)).setText(String.valueOf(stats.getWeaponBulletHit()));
            ((TextView) view.findViewById(R.id.weaponBulletsFiredText)).setText(String.valueOf(stats.getWeaponBulletFired()));
            ((TextView) view.findViewById(R.id.weaponAccuracyText)).setText(String.valueOf(stats.getWeaponAccuracy()) + "%");
            ((TextView) view.findViewById(R.id.weaponPenetrationKillsText)).setText(String.valueOf(stats.getWeaponPenetrations()));
            ((TextView) view.findViewById(R.id.weaponBlindKillsText)).setText(String.valueOf(stats.getWeaponBlindKills()));
            ((TextView) view.findViewById(R.id.weaponMeleeKillsText)).setText(String.valueOf(stats.getWeaponMeleeKills()));

            //MISC
            ((TextView) view.findViewById(R.id.miscBarricadeText)).setText(String.valueOf(stats.getMiscBarricade()));
            ((TextView) view.findViewById(R.id.miscReinforcementsText)).setText(String.valueOf(stats.getMiscReinforcementDeploy()));
            ((TextView) view.findViewById(R.id.miscRappelBreachesText)).setText(String.valueOf(stats.getMiscRappelBreach()));
            ((TextView) view.findViewById(R.id.miscRevivesText)).setText(String.valueOf(stats.getMiscRevives()));
            ((TextView) view.findViewById(R.id.miscSuicidesText)).setText(String.valueOf(stats.getMiscSuicides()));
            ((TextView) view.findViewById(R.id.miscGadgetsDestroyedText)).setText(String.valueOf(stats.getMiscGadgetDestroyed()));
            ((TextView) view.findViewById(R.id.miscDBNOText)).setText(String.valueOf(stats.getWeaponDBNO()));
            ((TextView) view.findViewById(R.id.miscDBNOAssistsText)).setText(String.valueOf(stats.getWeaponDBNOAssists()));

            //Secure
            ((TextView) view.findViewById(R.id.secureWinsText)).setText(String.valueOf(stats.getSecureWins()));
            ((TextView) view.findViewById(R.id.secureLossesText)).setText(String.valueOf(stats.getSecureLosses()));
            ((TextView) view.findViewById(R.id.secureWLText)).setText(String.valueOf(stats.getSecureWL()) + "%");
            ((TextView) view.findViewById(R.id.securePlayedText)).setText(String.valueOf(stats.getSecurePlayed()));
            ((TextView) view.findViewById(R.id.secureBestScoreText)).setText(String.valueOf(stats.getSecureBestScore()));
            ((TextView) view.findViewById(R.id.secureTimePlayedText)).setText(String.valueOf(stats.getSecureTimePlayedStr()));

            //Hostage
            ((TextView) view.findViewById(R.id.hostageWinsText)).setText(String.valueOf(stats.getHostageWins()));
            ((TextView) view.findViewById(R.id.hostageLossesText)).setText(String.valueOf(stats.getHostageLosses()));
            ((TextView) view.findViewById(R.id.hostageWLText)).setText(String.valueOf(stats.getHostageWL()) + " %");
            ((TextView) view.findViewById(R.id.hostagePlayedText)).setText(String.valueOf(stats.getHostagePlayed()));
            ((TextView) view.findViewById(R.id.hostageBestScoreText)).setText(String.valueOf(stats.getHostageBestScore()));
            ((TextView) view.findViewById(R.id.hostageTimePlayedText)).setText(String.valueOf(stats.getHostageTimePlayedStr()));

            //Bomb
            ((TextView) view.findViewById(R.id.bombWinsText)).setText(String.valueOf(stats.getBombWins()));
            ((TextView) view.findViewById(R.id.bombLossesText)).setText(String.valueOf(stats.getBombLosses()));
            ((TextView) view.findViewById(R.id.bombWLText)).setText(String.valueOf(stats.getBombWL()) + "%");
            ((TextView) view.findViewById(R.id.bombPlayedText)).setText(String.valueOf(stats.getBombPlayed()));
            ((TextView) view.findViewById(R.id.bombBestScoreText)).setText(String.valueOf(stats.getBombBestScore()));
            ((TextView) view.findViewById(R.id.bombTimePlayedText)).setText(String.valueOf(stats.getBombTimePlayedStr()));
        }
    }

    public void populateRanked(View view, Ranked rankedObj)
    {
        if (rankedObj != null)
        {
            //RANKED
            int rank = rankedObj.getRank();
            ((TextView) view.findViewById(R.id.rankedRankText)).setText(String.valueOf(getResources().getStringArray(R.array.ranks)[rank]));
            //((ImageView) v.findViewById(R.id.rankPicture)).setImageResource(getResources().getIdentifier("r" + String.valueOf(rank), "drawable", getActivity().getPackageName()));
            Picasso.get().load(getResources().getIdentifier("r" + String.valueOf(rank), "drawable", getActivity().getPackageName())).placeholder(R.drawable.r6slogo).into((ImageView) view.findViewById(R.id.rankPicture));
        }
    }

    public void populateLevel(View view, Level levelObj)
    {
        if (levelObj != null)
        {
            ((TextView) view.findViewById(R.id.level)).setText("Level: " + String.valueOf(levelObj.getLevel()));
        }
    }

    public void populatePlayer(View view, Player player)
    {
        if (player != null)
        {
            ImageView userImage = view.findViewById(R.id.userImage);
            Picasso.get().load("https://ubisoft-avatars.akamaized.net/" + player.getUserID() + "/default_256_256.png").placeholder(R.drawable.ic_operators).fit().into(userImage);
            ((TextView) view.findViewById(R.id.userName)).setText(player.getPlayerName());
        }
    }
}
