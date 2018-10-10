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

        ImageView userImage = view.findViewById(R.id.userImage);
        Picasso.get().load("https://ubisoft-avatars.akamaized.net/" + userID + "/default_256_256.png").placeholder(R.drawable.ic_operators).into(userImage);

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
                        //populateView(view, statResult);
                        MyUbiAPIAdapter.getLevel(userID, platform, new VolleyResponseListener() {
                            @Override
                            public void onError(String message) {

                            }

                            @Override
                            public void onResponse() {
                                levelObj = MyUbiAPIAdapter.getLevelsResult().get(0);
                                MyUbiAPIAdapter.getRanked(userID, platform, "emea", -1, new VolleyResponseListener() {
                                    @Override
                                    public void onError(String message) {

                                    }

                                    @Override
                                    public void onResponse() {
                                        rankedObj = MyUbiAPIAdapter.getRankedResult().get(0);
                                        MyUbiAPIAdapter.getPlayer(platform, "userId", userID, new VolleyResponseListener() {
                                            @Override
                                            public void onError(String message) {

                                            }

                                            @Override
                                            public void onResponse() {
                                                playerObj = MyUbiAPIAdapter.getPlayersResult().get(0);
                                                playerObj.setLevelObj(levelObj);
                                                playerObj.setRankedObj(rankedObj);
                                                playerObj.setStatsObj(statResult.get(0));
                                                populateView(view, playerObj);
                                            }
                                        });
                                    }
                                });
                            }
                        });
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

    public void populateView(View view, ArrayList<Stat> statResult) {
        if (!statResult.isEmpty()) {

            //GENERAL
            ((TextView) view.findViewById(R.id.generalKillsText)).setText(String.valueOf(statResult.get(0).getGeneralKills()));
            ((TextView) view.findViewById(R.id.generalDeathsText)).setText(String.valueOf(statResult.get(0).getGeneralDeaths()));
            ((TextView) view.findViewById(R.id.generalKDText)).setText(String.valueOf(statResult.get(0).getGeneralKD()));
            ((TextView) view.findViewById(R.id.generalWinsText)).setText(String.valueOf(statResult.get(0).getGeneralWins()));
            ((TextView) view.findViewById(R.id.generalLossesText)).setText(String.valueOf(statResult.get(0).getGeneralLost()));
            ((TextView) view.findViewById(R.id.generalWLText)).setText(String.valueOf(statResult.get(0).getGeneralWL()));
            ((TextView) view.findViewById(R.id.generalAssistsText)).setText(String.valueOf(statResult.get(0).getGeneralAssists()));
            ((TextView) view.findViewById(R.id.generalPlayedText)).setText(String.valueOf(statResult.get(0).getGeneralPlayed()));
            ((TextView) view.findViewById(R.id.generalTimePlayedText)).setText(String.valueOf(statResult.get(0).getGeneralTimePlayedStr()));

            //CASUAL
            ((TextView) view.findViewById(R.id.casualKillsText)).setText(String.valueOf(statResult.get(0).getCasualKills()));
            ((TextView) view.findViewById(R.id.casualDeathsText)).setText(String.valueOf(statResult.get(0).getCasualDeaths()));
            ((TextView) view.findViewById(R.id.casualKDText)).setText(String.valueOf(statResult.get(0).getCasualKD()));
            ((TextView) view.findViewById(R.id.casualWinsText)).setText(String.valueOf(statResult.get(0).getCasualWon()));
            ((TextView) view.findViewById(R.id.casualLossesText)).setText(String.valueOf(statResult.get(0).getCasualLost()));
            ((TextView) view.findViewById(R.id.casualWLText)).setText(String.valueOf(statResult.get(0).getCasualWL()) + "%");
            ((TextView) view.findViewById(R.id.casualPlayedText)).setText(String.valueOf(statResult.get(0).getCasualPlayed()));
            ((TextView) view.findViewById(R.id.casualTimePlayedText)).setText(String.valueOf(statResult.get(0).getCasualTimePlayedStr()));

            //RANKED
            ((TextView) view.findViewById(R.id.rankedKillsText)).setText(String.valueOf(statResult.get(0).getRankedKills()));
            ((TextView) view.findViewById(R.id.rankedDeathsText)).setText(String.valueOf(statResult.get(0).getRankedDeaths()));
            ((TextView) view.findViewById(R.id.rankedKDText)).setText(String.valueOf(statResult.get(0).getRankedKD()));
            ((TextView) view.findViewById(R.id.rankedWinsText)).setText(String.valueOf(statResult.get(0).getRankedWon()));
            ((TextView) view.findViewById(R.id.rankedLossesText)).setText(String.valueOf(statResult.get(0).getRankedLost()));
            ((TextView) view.findViewById(R.id.rankedWLText)).setText(String.valueOf(statResult.get(0).getRankedWL()) + "%");
            ((TextView) view.findViewById(R.id.rankedPlayedText)).setText(String.valueOf(statResult.get(0).getRankedPlayed()));
            ((TextView) view.findViewById(R.id.rankedTimePlayedText)).setText(String.valueOf(statResult.get(0).getRankedTimePlayedStr()));
            ((TextView) view.findViewById(R.id.rankedRankText)).setText("ERROR");

            //WEAPON
            ((TextView) view.findViewById(R.id.weaponHSText)).setText(String.valueOf(statResult.get(0).getWeaponHeadshots()));
            ((TextView) view.findViewById(R.id.weaponHSHitText)).setText(String.valueOf(statResult.get(0).getWeaponHSHit()) + "%");
            ((TextView) view.findViewById(R.id.weaponHSKillText)).setText(String.valueOf(statResult.get(0).getWeaponHSKill()) + "%");
            ((TextView) view.findViewById(R.id.weaponBulletsHitText)).setText(String.valueOf(statResult.get(0).getWeaponBulletHit()));
            ((TextView) view.findViewById(R.id.weaponBulletsFiredText)).setText(String.valueOf(statResult.get(0).getWeaponBulletFired()));
            ((TextView) view.findViewById(R.id.weaponAccuracyText)).setText(String.valueOf(statResult.get(0).getWeaponAccuracy()) + "%");
            ((TextView) view.findViewById(R.id.weaponPenetrationKillsText)).setText(String.valueOf(statResult.get(0).getWeaponPenetrations()));
            ((TextView) view.findViewById(R.id.weaponBlindKillsText)).setText(String.valueOf(statResult.get(0).getWeaponBlindKills()));
            ((TextView) view.findViewById(R.id.weaponMeleeKillsText)).setText(String.valueOf(statResult.get(0).getWeaponMeleeKills()));

            //MISC
            ((TextView) view.findViewById(R.id.miscBarricadeText)).setText(String.valueOf(statResult.get(0).getMiscBarricade()));
            ((TextView) view.findViewById(R.id.miscReinforcementsText)).setText(String.valueOf(statResult.get(0).getMiscReinforcementDeploy()));
            ((TextView) view.findViewById(R.id.miscRappelBreachesText)).setText(String.valueOf(statResult.get(0).getMiscRappelBreach()));
            ((TextView) view.findViewById(R.id.miscRevivesText)).setText(String.valueOf(statResult.get(0).getMiscRevives()));
            ((TextView) view.findViewById(R.id.miscSuicidesText)).setText(String.valueOf(statResult.get(0).getMiscSuicides()));
            ((TextView) view.findViewById(R.id.miscGadgetsDestroyedText)).setText(String.valueOf(statResult.get(0).getMiscGadgetDestroyed()));
            ((TextView) view.findViewById(R.id.miscDBNOText)).setText(String.valueOf(statResult.get(0).getWeaponDBNO()));
            ((TextView) view.findViewById(R.id.miscDBNOAssistsText)).setText(String.valueOf(statResult.get(0).getWeaponDBNOAssists()));

            //Secure
            ((TextView) view.findViewById(R.id.secureWinsText)).setText(String.valueOf(statResult.get(0).getSecureWins()));
            ((TextView) view.findViewById(R.id.secureLossesText)).setText(String.valueOf(statResult.get(0).getSecureLosses()));
            ((TextView) view.findViewById(R.id.secureWLText)).setText(String.valueOf(statResult.get(0).getSecureWL()) + "%");
            ((TextView) view.findViewById(R.id.securePlayedText)).setText(String.valueOf(statResult.get(0).getSecurePlayed()));
            ((TextView) view.findViewById(R.id.secureBestScoreText)).setText(String.valueOf(statResult.get(0).getSecureBestScore()));
            ((TextView) view.findViewById(R.id.secureTimePlayedText)).setText(String.valueOf(statResult.get(0).getSecureTimePlayedStr()));

            //Hostage
            ((TextView) view.findViewById(R.id.hostageWinsText)).setText(String.valueOf(statResult.get(0).getHostageWins()));
            ((TextView) view.findViewById(R.id.hostageLossesText)).setText(String.valueOf(statResult.get(0).getHostageLosses()));
            ((TextView) view.findViewById(R.id.hostageWLText)).setText(String.valueOf(statResult.get(0).getHostageWL()) + " %");
            ((TextView) view.findViewById(R.id.hostagePlayedText)).setText(String.valueOf(statResult.get(0).getHostagePlayed()));
            ((TextView) view.findViewById(R.id.hostageBestScoreText)).setText(String.valueOf(statResult.get(0).getHostageBestScore()));
            ((TextView) view.findViewById(R.id.hostageTimePlayedText)).setText(String.valueOf(statResult.get(0).getHostageTimePlayedStr()));

            //Bomb
            ((TextView) view.findViewById(R.id.bombWinsText)).setText(String.valueOf(statResult.get(0).getBombWins()));
            ((TextView) view.findViewById(R.id.bombLossesText)).setText(String.valueOf(statResult.get(0).getBombLosses()));
            ((TextView) view.findViewById(R.id.bombWLText)).setText(String.valueOf(statResult.get(0).getBombWL()) + "%");
            ((TextView) view.findViewById(R.id.bombPlayedText)).setText(String.valueOf(statResult.get(0).getBombPlayed()));
            ((TextView) view.findViewById(R.id.bombBestScoreText)).setText(String.valueOf(statResult.get(0).getBombBestScore()));
            ((TextView) view.findViewById(R.id.bombTimePlayedText)).setText(String.valueOf(statResult.get(0).getBombTimePlayedStr()));
        }
    }

    public void populateView(View view, Player player) {
        if (player != null) {

            ((TextView) view.findViewById(R.id.level)).setText("Level: " + String.valueOf(player.getLevelObj().getLevel()));
            ((TextView) view.findViewById(R.id.userName)).setText(player.getPlayerName());

            //GENERAL
            ((TextView) view.findViewById(R.id.generalKillsText)).setText(String.valueOf(player.getStatObj().getGeneralKills()));
            ((TextView) view.findViewById(R.id.generalDeathsText)).setText(String.valueOf(player.getStatObj().getGeneralDeaths()));
            ((TextView) view.findViewById(R.id.generalKDText)).setText(String.valueOf(player.getStatObj().getGeneralKD()));
            ((TextView) view.findViewById(R.id.generalWinsText)).setText(String.valueOf(player.getStatObj().getGeneralWins()));
            ((TextView) view.findViewById(R.id.generalLossesText)).setText(String.valueOf(player.getStatObj().getGeneralLost()));
            ((TextView) view.findViewById(R.id.generalWLText)).setText(String.valueOf(player.getStatObj().getGeneralWL()));
            ((TextView) view.findViewById(R.id.generalAssistsText)).setText(String.valueOf(player.getStatObj().getGeneralAssists()));
            ((TextView) view.findViewById(R.id.generalPlayedText)).setText(String.valueOf(player.getStatObj().getGeneralPlayed()));
            ((TextView) view.findViewById(R.id.generalTimePlayedText)).setText(String.valueOf(player.getStatObj().getGeneralTimePlayedStr()));

            //CASUAL
            ((TextView) view.findViewById(R.id.casualKillsText)).setText(String.valueOf(player.getStatObj().getCasualKills()));
            ((TextView) view.findViewById(R.id.casualDeathsText)).setText(String.valueOf(player.getStatObj().getCasualDeaths()));
            ((TextView) view.findViewById(R.id.casualKDText)).setText(String.valueOf(player.getStatObj().getCasualKD()));
            ((TextView) view.findViewById(R.id.casualWinsText)).setText(String.valueOf(player.getStatObj().getCasualWon()));
            ((TextView) view.findViewById(R.id.casualLossesText)).setText(String.valueOf(player.getStatObj().getCasualLost()));
            ((TextView) view.findViewById(R.id.casualWLText)).setText(String.valueOf(player.getStatObj().getCasualWL()) + "%");
            ((TextView) view.findViewById(R.id.casualPlayedText)).setText(String.valueOf(player.getStatObj().getCasualPlayed()));
            ((TextView) view.findViewById(R.id.casualTimePlayedText)).setText(String.valueOf(player.getStatObj().getCasualTimePlayedStr()));

            //RANKED
            ((TextView) view.findViewById(R.id.rankedKillsText)).setText(String.valueOf(player.getStatObj().getRankedKills()));
            ((TextView) view.findViewById(R.id.rankedDeathsText)).setText(String.valueOf(player.getStatObj().getRankedDeaths()));
            ((TextView) view.findViewById(R.id.rankedKDText)).setText(String.valueOf(player.getStatObj().getRankedKD()));
            ((TextView) view.findViewById(R.id.rankedWinsText)).setText(String.valueOf(player.getStatObj().getRankedWon()));
            ((TextView) view.findViewById(R.id.rankedLossesText)).setText(String.valueOf(player.getStatObj().getRankedLost()));
            ((TextView) view.findViewById(R.id.rankedWLText)).setText(String.valueOf(player.getStatObj().getRankedWL()) + "%");
            ((TextView) view.findViewById(R.id.rankedPlayedText)).setText(String.valueOf(player.getStatObj().getRankedPlayed()));
            ((TextView) view.findViewById(R.id.rankedTimePlayedText)).setText(String.valueOf(player.getStatObj().getRankedTimePlayedStr()));
            ((TextView) view.findViewById(R.id.rankedRankText)).setText(String.valueOf(getResources().getStringArray(R.array.ranks)[player.getRankedObj().getRank()]));

            //WEAPON
            ((TextView) view.findViewById(R.id.weaponHSText)).setText(String.valueOf(player.getStatObj().getWeaponHeadshots()));
            ((TextView) view.findViewById(R.id.weaponHSHitText)).setText(String.valueOf(player.getStatObj().getWeaponHSHit()) + "%");
            ((TextView) view.findViewById(R.id.weaponHSKillText)).setText(String.valueOf(player.getStatObj().getWeaponHSKill()) + "%");
            ((TextView) view.findViewById(R.id.weaponBulletsHitText)).setText(String.valueOf(player.getStatObj().getWeaponBulletHit()));
            ((TextView) view.findViewById(R.id.weaponBulletsFiredText)).setText(String.valueOf(player.getStatObj().getWeaponBulletFired()));
            ((TextView) view.findViewById(R.id.weaponAccuracyText)).setText(String.valueOf(player.getStatObj().getWeaponAccuracy()) + "%");
            ((TextView) view.findViewById(R.id.weaponPenetrationKillsText)).setText(String.valueOf(player.getStatObj().getWeaponPenetrations()));
            ((TextView) view.findViewById(R.id.weaponBlindKillsText)).setText(String.valueOf(player.getStatObj().getWeaponBlindKills()));
            ((TextView) view.findViewById(R.id.weaponMeleeKillsText)).setText(String.valueOf(player.getStatObj().getWeaponMeleeKills()));

            //MISC
            ((TextView) view.findViewById(R.id.miscBarricadeText)).setText(String.valueOf(player.getStatObj().getMiscBarricade()));
            ((TextView) view.findViewById(R.id.miscReinforcementsText)).setText(String.valueOf(player.getStatObj().getMiscReinforcementDeploy()));
            ((TextView) view.findViewById(R.id.miscRappelBreachesText)).setText(String.valueOf(player.getStatObj().getMiscRappelBreach()));
            ((TextView) view.findViewById(R.id.miscRevivesText)).setText(String.valueOf(player.getStatObj().getMiscRevives()));
            ((TextView) view.findViewById(R.id.miscSuicidesText)).setText(String.valueOf(player.getStatObj().getMiscSuicides()));
            ((TextView) view.findViewById(R.id.miscGadgetsDestroyedText)).setText(String.valueOf(player.getStatObj().getMiscGadgetDestroyed()));
            ((TextView) view.findViewById(R.id.miscDBNOText)).setText(String.valueOf(player.getStatObj().getWeaponDBNO()));
            ((TextView) view.findViewById(R.id.miscDBNOAssistsText)).setText(String.valueOf(player.getStatObj().getWeaponDBNOAssists()));

            //Secure
            ((TextView) view.findViewById(R.id.secureWinsText)).setText(String.valueOf(player.getStatObj().getSecureWins()));
            ((TextView) view.findViewById(R.id.secureLossesText)).setText(String.valueOf(player.getStatObj().getSecureLosses()));
            ((TextView) view.findViewById(R.id.secureWLText)).setText(String.valueOf(player.getStatObj().getSecureWL()) + "%");
            ((TextView) view.findViewById(R.id.securePlayedText)).setText(String.valueOf(player.getStatObj().getSecurePlayed()));
            ((TextView) view.findViewById(R.id.secureBestScoreText)).setText(String.valueOf(player.getStatObj().getSecureBestScore()));
            ((TextView) view.findViewById(R.id.secureTimePlayedText)).setText(String.valueOf(player.getStatObj().getSecureTimePlayedStr()));

            //Hostage
            ((TextView) view.findViewById(R.id.hostageWinsText)).setText(String.valueOf(player.getStatObj().getHostageWins()));
            ((TextView) view.findViewById(R.id.hostageLossesText)).setText(String.valueOf(player.getStatObj().getHostageLosses()));
            ((TextView) view.findViewById(R.id.hostageWLText)).setText(String.valueOf(player.getStatObj().getHostageWL()) + " %");
            ((TextView) view.findViewById(R.id.hostagePlayedText)).setText(String.valueOf(player.getStatObj().getHostagePlayed()));
            ((TextView) view.findViewById(R.id.hostageBestScoreText)).setText(String.valueOf(player.getStatObj().getHostageBestScore()));
            ((TextView) view.findViewById(R.id.hostageTimePlayedText)).setText(String.valueOf(player.getStatObj().getHostageTimePlayedStr()));

            //Bomb
            ((TextView) view.findViewById(R.id.bombWinsText)).setText(String.valueOf(player.getStatObj().getBombWins()));
            ((TextView) view.findViewById(R.id.bombLossesText)).setText(String.valueOf(player.getStatObj().getBombLosses()));
            ((TextView) view.findViewById(R.id.bombWLText)).setText(String.valueOf(player.getStatObj().getBombWL()) + "%");
            ((TextView) view.findViewById(R.id.bombPlayedText)).setText(String.valueOf(player.getStatObj().getBombPlayed()));
            ((TextView) view.findViewById(R.id.bombBestScoreText)).setText(String.valueOf(player.getStatObj().getBombBestScore()));
            ((TextView) view.findViewById(R.id.bombTimePlayedText)).setText(String.valueOf(player.getStatObj().getBombTimePlayedStr()));
        }
    }
}
