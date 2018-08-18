package productions.pudl.siege.Fragment.Operators;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import productions.pudl.siege.Adapter.TabViewAdapter;
import productions.pudl.siege.Data.Operator;
import productions.pudl.siege.R;

@SuppressLint("ValidFragment")
public class OperatorTabbedDialog extends android.support.v4.app.DialogFragment
{
    TabLayout tabLayout;
    ViewPager viewPager;
    String CTU;
    HashMap<String, Operator> operatorHashMap;

    @SuppressLint("ValidFragment")
    public OperatorTabbedDialog(String ctu, HashMap<String, Operator> operatorHashMap)
    {
        this.CTU = ctu;
        this.operatorHashMap = operatorHashMap;
    }

    @SuppressLint("ValidFragment")
    public OperatorTabbedDialog(String ctu)
    {
        this.CTU = ctu;
    }

    @SuppressLint("ValidFragment")
    public OperatorTabbedDialog(String ctu, String temp)
    {
        this.CTU = ctu;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootview = inflater.inflate(R.layout.operator_tabbed_dialog,container,false);
        tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootview.findViewById(R.id.masterViewPager);

        ((TextView) rootview.findViewById(R.id.ctuName)).setText(CTU.toUpperCase());
        Picasso.get().load(getResources().getIdentifier(CTU, "drawable", getContext().getPackageName())).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into((ImageView) rootview.findViewById(R.id.ctuPicture));

        TabViewAdapter adapter = new TabViewAdapter(getChildFragmentManager());
        switch(CTU)
        {
            case "sas":
                adapter.addFragment("Sledge",OperatorCustomFragment.createInstance(operatorHashMap.get("Sledge")));
                adapter.addFragment("Thatcher",OperatorCustomFragment.createInstance(operatorHashMap.get("Thatcher")));
                adapter.addFragment("Mute",OperatorCustomFragment.createInstance(operatorHashMap.get("Mute")));
                adapter.addFragment("Smoke",OperatorCustomFragment.createInstance(operatorHashMap.get("Smoke")));
                adapter.addFragment("Recruit(SAS)",OperatorCustomFragment.createInstance(operatorHashMap.get("Recruit(SAS)")));
                break;
            case "fbiswat":
                adapter.addFragment("Ash",OperatorCustomFragment.createInstance(operatorHashMap.get("Ash")));
                adapter.addFragment("Thermite",OperatorCustomFragment.createInstance(operatorHashMap.get("Thermite")));
                adapter.addFragment("Castle",OperatorCustomFragment.createInstance(operatorHashMap.get("Castle")));
                adapter.addFragment("Pulse",OperatorCustomFragment.createInstance(operatorHashMap.get("Pulse")));
                adapter.addFragment("Recruit(FBI)",OperatorCustomFragment.createInstance(operatorHashMap.get("Recruit(FBI)")));
                break;
            case "gign":
                adapter.addFragment("Twitch",OperatorCustomFragment.createInstance(operatorHashMap.get("Twitch")));
                adapter.addFragment("Montagne",OperatorCustomFragment.createInstance(operatorHashMap.get("Montagne")));
                adapter.addFragment("Rook",OperatorCustomFragment.createInstance(operatorHashMap.get("Rook")));
                adapter.addFragment("Doc",OperatorCustomFragment.createInstance(operatorHashMap.get("Doc")));
                adapter.addFragment("Recruit(GIGN)",OperatorCustomFragment.createInstance(operatorHashMap.get("Recruit(GIGN)")));

                break;
            case "spetsnaz":
                adapter.addFragment("Glaz",OperatorCustomFragment.createInstance(operatorHashMap.get("Glaz")));
                adapter.addFragment("Fuze",OperatorCustomFragment.createInstance(operatorHashMap.get("Fuze")));
                adapter.addFragment("Kapkan",OperatorCustomFragment.createInstance(operatorHashMap.get("Kapkan")));
                adapter.addFragment("Tachanka",OperatorCustomFragment.createInstance(operatorHashMap.get("Tachanka")));
                adapter.addFragment("Recruit(Spetsnaz)",OperatorCustomFragment.createInstance(operatorHashMap.get("Recruit(Spetsnaz)")));
                break;
            case "gsg9":
                adapter.addFragment("Blitz",OperatorCustomFragment.createInstance(operatorHashMap.get("Blitz")));
                adapter.addFragment("IQ",OperatorCustomFragment.createInstance(operatorHashMap.get("IQ")));
                adapter.addFragment("Jäger",OperatorCustomFragment.createInstance(operatorHashMap.get("Jäger")));
                adapter.addFragment("Bandit",OperatorCustomFragment.createInstance(operatorHashMap.get("Bandit")));
                adapter.addFragment("Recruit(GSG9)",OperatorCustomFragment.createInstance(operatorHashMap.get("Recruit(GSG9)")));
                break;
            case "jtf2":
                adapter.addFragment("Buck",OperatorCustomFragment.createInstance(operatorHashMap.get("Buck")));
                adapter.addFragment("Frost",OperatorCustomFragment.createInstance(operatorHashMap.get("Frost")));
                break;
            case "navyseals":
                adapter.addFragment("Blackbeard",OperatorCustomFragment.createInstance(operatorHashMap.get("Blackbeard")));
                adapter.addFragment("Valkyrie",OperatorCustomFragment.createInstance(operatorHashMap.get("Valkyrie")));
                break;
            case "bope":
                adapter.addFragment("Capitão",OperatorCustomFragment.createInstance(operatorHashMap.get("Capitão")));
                adapter.addFragment("Caveira",OperatorCustomFragment.createInstance(operatorHashMap.get("Caveira")));
                break;
            case "sat":
                adapter.addFragment("Hibana",OperatorCustomFragment.createInstance(operatorHashMap.get("Hibana")));
                adapter.addFragment("Echo",OperatorCustomFragment.createInstance(operatorHashMap.get("Echo")));
                break;
            case "geo":
                adapter.addFragment("Jackal",OperatorCustomFragment.createInstance(operatorHashMap.get("Jackal")));
                adapter.addFragment("Mira",OperatorCustomFragment.createInstance(operatorHashMap.get("Mira")));
                break;
            case "sdu":
                adapter.addFragment("Ying",OperatorCustomFragment.createInstance(operatorHashMap.get("Ying")));
                adapter.addFragment("Lesion",OperatorCustomFragment.createInstance(operatorHashMap.get("Lesion")));
                break;
            case "grom":
                adapter.addFragment("Zofia",OperatorCustomFragment.createInstance(operatorHashMap.get("Zofia")));
                adapter.addFragment("Ela",OperatorCustomFragment.createInstance(operatorHashMap.get("Ela")));
                break;
            case "smb":
                adapter.addFragment("Dokkaebi",OperatorCustomFragment.createInstance(operatorHashMap.get("Dokkaebi")));
                adapter.addFragment("Vigil",OperatorCustomFragment.createInstance(operatorHashMap.get("Vigil")));
                break;
            case "cbrn":
                adapter.addFragment("Finka",OperatorCustomFragment.createInstance(operatorHashMap.get("Finka")));
                adapter.addFragment("Lion",OperatorCustomFragment.createInstance(operatorHashMap.get("Lion")));
                break;
            case "gis":
                adapter.addFragment("Maestro",OperatorCustomFragment.createInstance(operatorHashMap.get("Maestro")));
                adapter.addFragment("Alibi",OperatorCustomFragment.createInstance(operatorHashMap.get("Alibi")));
                break;
            default:
                adapter.addFragment("Error",OperatorCustomFragment.createInstance(operatorHashMap.get("Sledge")));
                break;
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootview;
    }
}
