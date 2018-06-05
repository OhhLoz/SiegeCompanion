package productions.pudl.siege.Fragment.Search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import productions.pudl.siege.Adapter.AliasListAdapter;
import productions.pudl.siege.Data.DetailedObjects.DetailedGeneralObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedMainObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedPlacementsObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedRanksObject;
import productions.pudl.siege.Data.DetailedObjects.DetailedStatObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasesObject;
import productions.pudl.siege.R;

public class CustomTabFragment extends Fragment
{
    DetailedGeneralObject general;
    DetailedStatObject casual;
    DetailedStatObject ranked;
    int level = 0;
    int kills = 0;
    int deaths = 0;
    int assists = 0;
    double KD = 0;
    int wins = 0;
    int losses = 0;
    int played = 0;
    double WL = 0;
    String timePlayed = "";
    int seconds = 0;
    int hours = 0;
    int minutes = 0;

    int rank = 0;
    String rankStr = "";

    int bulletsHit = 0;
    int bulletsFired = 0;
    double accuracy = 0;
    int headshots = 0;
    double headshotHits = 0;
    double headshotKills = 0;
    int meleekills = 0;
    int penetrationkills = 0;
    // assists

    int gadgetsDestroyed = 0;
    int blindfires = 0;
    int dbno = 0;
    int dbnoAssists = 0;
    int revives = 0;
    int revivesDenied = 0;
    int suicides = 0;
    int rappelBreaches = 0;

    DetailedPlacementsObject placement;
    DetailedRanksObject placementRanks;
    int placementGlobal = 0;
    int placementAPAC = 0;
    int APACRank = 0;
    int placementEMEA = 0;
    int EMEARank = 0;
    int placementNCSA = 0;
    int NCSARank = 0;

    ArrayList<GeneralAliasObject> aliasesObject;
    ListView aliasesListView;

    String flag;

    public static CustomTabFragment createInstance(DetailedMainObject currItem, String flag)
    {
        CustomTabFragment fragment = new CustomTabFragment();

        fragment.level = currItem.getLevel();
        fragment.flag = flag;

        if (flag.equals("general"))
        {
            fragment.general = currItem.getStats().getGeneral();
            Log.v("CustomFragment", "General Created!");
            fragment.kills = fragment.general.getKills();
            fragment.deaths = fragment.general.getDeaths();
            fragment.KD = (double) fragment.kills / (double) fragment.deaths;
            fragment.KD = (double) Math.round(fragment.KD * 1000d) / 1000d;
            fragment.assists = fragment.general.getAssists();

            fragment.wins = fragment.general.getWon();
            fragment.losses = fragment.general.getLost();
            fragment.played = fragment.general.getPlayed();
            fragment.WL = ((double) fragment.wins / (double) fragment.played) * 100;
            fragment.WL = (double) Math.round(fragment.WL * 100d) / 100d;

            fragment.seconds = fragment.general.getTimePlayed();
            fragment.hours = fragment.seconds / 3600;
            fragment.minutes = (fragment.seconds % 3600) / 60;
            fragment.seconds = (fragment.seconds % 3600) % 60;

            StringBuilder timePlayed = new StringBuilder();
            if (fragment.hours != 0)
                timePlayed.append(String.valueOf(fragment.hours) + "H ");
            if (fragment.minutes != 0)
                timePlayed.append(String.valueOf(fragment.minutes) + "M");
            if(fragment.hours == 0)
                timePlayed.append(" " + String.valueOf(fragment.seconds) + "S");
            fragment.timePlayed = timePlayed.toString();
        }
        else if(flag.equals("casual"))
        {
            fragment.casual = currItem.getStats().getCasual();
            Log.v("CustomFragment", "Casual Created!");
            fragment.kills = fragment.casual.getKills();
            fragment.deaths = fragment.casual.getDeaths();
            fragment.KD = (double) fragment.kills / (double) fragment.deaths;
            fragment.KD = (double) Math.round(fragment.KD * 1000d) / 1000d;

            fragment.wins = fragment.casual.getWon();
            fragment.losses = fragment.casual.getLost();
            fragment.played = fragment.casual.getPlayed();
            fragment.WL = ((double) fragment.wins / (double) fragment.played) * 100;
            fragment.WL = (double) Math.round(fragment.WL * 100d) / 100d;

            fragment.seconds = fragment.casual.getTimePlayed();
            fragment.hours = fragment.seconds / 3600;
            fragment.minutes = (fragment.seconds % 3600) / 60;
            fragment.seconds = (fragment.seconds % 3600) % 60;

            StringBuilder timePlayed = new StringBuilder();
            if (fragment.hours != 0)
                timePlayed.append(String.valueOf(fragment.hours) + "H ");
            if (fragment.minutes != 0)
                timePlayed.append(String.valueOf(fragment.minutes) + "M");
            if(fragment.hours == 0)
                timePlayed.append(" " + String.valueOf(fragment.seconds) + "S");
            fragment.timePlayed = timePlayed.toString();
        }
        else if(flag.equals("ranked"))
        {
            fragment.ranked = currItem.getStats().getRanked();
            Log.v("CustomFragment", "Ranked Created!");
            fragment.kills = fragment.ranked.getKills();
            fragment.deaths = fragment.ranked.getDeaths();
            fragment.KD = (double) fragment.kills / (double) fragment.deaths;
            fragment.KD = (double) Math.round(fragment.KD * 1000d) / 1000d;

            fragment.wins = fragment.ranked.getWon();
            fragment.losses = fragment.ranked.getLost();
            fragment.played = fragment.ranked.getPlayed();
            fragment.WL = ((double) fragment.wins / (double) fragment.played) * 100;
            fragment.WL = (double) Math.round(fragment.WL * 100d) / 100d;

            fragment.seconds = fragment.ranked.getTimePlayed();
            fragment.hours = fragment.seconds / 3600;
            fragment.minutes = (fragment.seconds % 3600) / 60;
            fragment.seconds = (fragment.seconds % 3600) % 60;

            fragment.rank = currItem.getRanksObject().getMaxRank();

            fragment.rankStr = currItem.getRanksObject().getRankString(fragment.rank);

            StringBuilder timePlayed = new StringBuilder();
            if (fragment.hours != 0)
                timePlayed.append(String.valueOf(fragment.hours) + "H ");
            if (fragment.minutes != 0)
                timePlayed.append(String.valueOf(fragment.minutes) + "M");
            if(fragment.hours == 0)
                timePlayed.append(" " + String.valueOf(fragment.seconds) + "S");
            fragment.timePlayed = timePlayed.toString();
        }
        else if (flag.equals("placements"))
        {
            fragment.placementRanks = currItem.getRanksObject();

            fragment.placement = currItem.getPlacements();
            fragment.placementGlobal = fragment.placement.getGlobal();
            fragment.placementAPAC = fragment.placement.getAPAC();
            fragment.APACRank = fragment.placementRanks.getAPAC().getRank();
            fragment.placementEMEA = fragment.placement.getEMEA();
            fragment.EMEARank = fragment.placementRanks.getEMEA().getRank();
            fragment.placementNCSA = fragment.placement.getNCSA();
            fragment.NCSARank = fragment.placementRanks.getNCSA().getRank();
        }
        else if(flag.equals("weapon"))
        {
            fragment.general = currItem.getStats().getGeneral();
            Log.v("CustomFragment", "Weapon Created!");
            fragment.bulletsFired = fragment.general.getBulletsFired();
            fragment.bulletsHit = fragment.general.getBulletsHit();
            if(fragment.bulletsFired != 0)
            {
                fragment.accuracy = ((double) fragment.bulletsHit / (double) fragment.bulletsFired) * 100;
                fragment.accuracy = (double) Math.round(fragment.accuracy * 100d) / 100d;
            }
            else
                fragment.accuracy = fragment.bulletsHit;

            fragment.headshots = fragment.general.getHeadshot();
            fragment.kills = fragment.general.getKills();
            fragment.headshotHits = ((double) fragment.headshots / (double) fragment.bulletsHit) * 100;
            fragment.headshotHits = (double) Math.round(fragment.headshotHits * 100d) / 100d;
            fragment.headshotKills = ((double) fragment.headshots / (double) fragment.kills) * 100;
            fragment.headshotKills = (double) Math.round(fragment.headshotKills * 100d) / 100d;

            fragment.meleekills = fragment.general.getMeleekills();
            fragment.penetrationkills = fragment.general.getPenetrationKills();
            fragment.assists = fragment.general.getAssists();
        }
        else if(flag.equals("misc"))
        {
            fragment.general = currItem.getStats().getGeneral();
            Log.v("CustomFragment", "Misc Created!");
            fragment.dbno = fragment.general.getDbno();
            fragment.dbnoAssists = fragment.general.getDbnoAssists();
            fragment.gadgetsDestroyed = fragment.general.getGadgetsDestroyed();
            fragment.revives = fragment.general.getRevives();
            fragment.revivesDenied = fragment.general.getRevivesDenied();
            fragment.suicides = fragment.general.getSuicides();
            fragment.rappelBreaches = fragment.general.getRappelBreaches();
            fragment.blindfires = fragment.general.getBlindKills();
        }
        else if (flag.equals("aliases"))
        {
            fragment.aliasesObject = currItem.getAliasesObject().getAliasObjects();
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(flag.equals("general"))
        {
            Log.v("CustomFragment", "General Inflated!");
            View v = inflater.inflate(R.layout.general_stats,container,false);
            ((TextView) v.findViewById(R.id.generalKillsText)).setText(String.valueOf(kills));
            ((TextView) v.findViewById(R.id.generalDeathsText)).setText(String.valueOf(deaths));
            ((TextView) v.findViewById(R.id.generalAssistsText)).setText(String.valueOf(assists));
            ((TextView) v.findViewById(R.id.generalKDText)).setText(String.valueOf(KD));
            ((TextView) v.findViewById(R.id.generalWinsText)).setText(String.valueOf(wins));
            ((TextView) v.findViewById(R.id.generalLossesText)).setText(String.valueOf(losses));
            ((TextView) v.findViewById(R.id.generalPlayedText)).setText(String.valueOf(played));
            ((TextView) v.findViewById(R.id.generalWLText)).setText(String.valueOf(WL)+"%");
            ((TextView) v.findViewById(R.id.generalTimePlayedText)).setText(timePlayed);
            return v;
        }
        else if (flag.equals("casual"))
        {
            Log.v("CustomFragment", "Casual Created!");
            View v = inflater.inflate(R.layout.casual_stats,container,false);
            ((TextView) v.findViewById(R.id.casualKillsText)).setText(String.valueOf(kills));
            ((TextView) v.findViewById(R.id.casualDeathsText)).setText(String.valueOf(deaths));
            ((TextView) v.findViewById(R.id.casualKDText)).setText(String.valueOf(KD));
            ((TextView) v.findViewById(R.id.casualWinsText)).setText(String.valueOf(wins));
            ((TextView) v.findViewById(R.id.casualLossesText)).setText(String.valueOf(losses));
            ((TextView) v.findViewById(R.id.casualWLText)).setText(String.valueOf(WL)+"%");
            ((TextView) v.findViewById(R.id.casualLevelText)).setText(String.valueOf(level));
            ((TextView) v.findViewById(R.id.casualPlayedText)).setText(String.valueOf(played));
            ((TextView) v.findViewById(R.id.casualTimePlayedText)).setText(timePlayed);
            return v;
        }
        else if (flag.equals("ranked"))
        {
            Log.v("CustomFragment", "Ranked Created!");
            View v = inflater.inflate(R.layout.ranked_stats,container,false);
            ((TextView) v.findViewById(R.id.rankedKillsText)).setText(String.valueOf(kills));
            ((TextView) v.findViewById(R.id.rankedDeathsText)).setText(String.valueOf(deaths));
            ((TextView) v.findViewById(R.id.rankedKDText)).setText(String.valueOf(KD));
            ((TextView) v.findViewById(R.id.rankedWinsText)).setText(String.valueOf(wins));
            ((TextView) v.findViewById(R.id.rankedLossesText)).setText(String.valueOf(losses));
            ((TextView) v.findViewById(R.id.rankedWLText)).setText(String.valueOf(WL)+"%");
            ((ImageView) v.findViewById(R.id.rankPicture)).setImageResource(getResources().getIdentifier("r" + String.valueOf(rank), "drawable", getActivity().getPackageName()));
            ((TextView) v.findViewById(R.id.rankedRankText)).setText(String.valueOf(rankStr));
            ((TextView) v.findViewById(R.id.rankedPlayedText)).setText(String.valueOf(played));
            ((TextView) v.findViewById(R.id.rankedTimePlayedText)).setText(timePlayed);
            return v;
        }
        else if (flag.equals("placements"))
        {
            Log.v("CustomFragment", "Placements Created!");
            View v = inflater.inflate(R.layout.placements_stats,container,false);
            //((ImageView) v.findViewById(R.id.rankPicture)).setImageResource(getResources().getIdentifier("r" + String.valueOf(rank), "drawable", getActivity().getPackageName()));
            ((TextView) v.findViewById(R.id.placementsGlobalText)).setText(String.valueOf(placementGlobal) + getSuffix(placementGlobal));
            ((ImageView) v.findViewById(R.id.placementsAPACPicture)).setImageResource(getResources().getIdentifier("r" + String.valueOf(APACRank), "drawable", getActivity().getPackageName()));
            ((TextView) v.findViewById(R.id.placementsAPACText)).setText(String.valueOf(placementAPAC) + getSuffix(placementAPAC));
            ((ImageView) v.findViewById(R.id.placementsEMEAPicture)).setImageResource(getResources().getIdentifier("r" + String.valueOf(EMEARank), "drawable", getActivity().getPackageName()));
            ((TextView) v.findViewById(R.id.placementsEMEAText)).setText(String.valueOf(placementEMEA) + getSuffix(placementEMEA));
            ((ImageView) v.findViewById(R.id.placementsNCSAPicture)).setImageResource(getResources().getIdentifier("r" + String.valueOf(NCSARank), "drawable", getActivity().getPackageName()));
            ((TextView) v.findViewById(R.id.placementsNCSAText)).setText(String.valueOf(placementNCSA) + getSuffix(placementNCSA));
            return v;
        }
        else if (flag.equals("weapon"))
        {
            Log.v("CustomFragment", "Weapon Created!");
            View v = inflater.inflate(R.layout.weapon_stats,container,false);
            ((TextView) v.findViewById(R.id.weaponHSText)).setText(String.valueOf(headshots));
            ((TextView) v.findViewById(R.id.weaponHSHitText)).setText(String.valueOf(headshotHits)+"%");
            ((TextView) v.findViewById(R.id.weaponHSKillsText)).setText(String.valueOf(headshotKills)+"%");
            ((TextView) v.findViewById(R.id.weaponBulletsHitText)).setText(String.valueOf(bulletsHit));
            ((TextView) v.findViewById(R.id.weaponBulletsFiredText)).setText(String.valueOf(bulletsFired));
            ((TextView) v.findViewById(R.id.weaponAccuracyText)).setText(String.valueOf(accuracy)+"%");
            ((TextView) v.findViewById(R.id.weaponMeleeKillsText)).setText(String.valueOf(meleekills));
            ((TextView) v.findViewById(R.id.weaponPenetrationKillsText)).setText(String.valueOf(penetrationkills));
            ((TextView) v.findViewById(R.id.weaponAssistsText)).setText(String.valueOf(assists));
            return v;
        }
        else if (flag.equals("misc"))
        {
            Log.v("CustomFragment", "Misc Created!");
            View v = inflater.inflate(R.layout.misc_stats,container,false);
            ((TextView) v.findViewById(R.id.miscRevivesText)).setText(String.valueOf(revives));
            ((TextView) v.findViewById(R.id.miscRevivesDeniedText)).setText(String.valueOf(revivesDenied));
            ((TextView) v.findViewById(R.id.miscSuicidesText)).setText(String.valueOf(suicides));
            ((TextView) v.findViewById(R.id.miscDBNOText)).setText(String.valueOf(dbno));
            ((TextView) v.findViewById(R.id.miscDBNOAssistText)).setText(String.valueOf(dbnoAssists));
            ((TextView) v.findViewById(R.id.miscGadgetsDestroyedText)).setText(String.valueOf(gadgetsDestroyed));
            ((TextView) v.findViewById(R.id.miscRappelBreachesText)).setText(String.valueOf(rappelBreaches));
            ((TextView) v.findViewById(R.id.miscBlindFiresText)).setText(String.valueOf(blindfires));
            return v;
        }
        else if (flag.equals("aliases"))
        {
            Log.v("CustomFragment", "Aliases Created!");
            View v = inflater.inflate(R.layout.aliases_stats,container,false);
            aliasesListView = v.findViewById(R.id.aliasesListView);
            AliasListAdapter aliasListAdapter = new AliasListAdapter(getActivity(), aliasesObject);
            aliasesListView.setAdapter(aliasListAdapter);
            return v;
        }
        return getView();
    }

    public String getSuffix(int input)
    {
        // if (input last2 chars >= 11 && <=13 return "th"

        switch(input % 10)
        {
            case 1: return "st";
            case 2: return "nd";
            case 3: return "rd";
            default : return "th";
        }
    }
}
