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

import productions.pudl.siege.Adapter.TabViewAdapter;
import productions.pudl.siege.Data.Operator;
import productions.pudl.siege.R;

@SuppressLint("ValidFragment")
public class OperatorTabbedDialog extends android.support.v4.app.DialogFragment
{
    TabLayout tabLayout;
    ViewPager viewPager;
    String CTU;

    @SuppressLint("ValidFragment")
    public OperatorTabbedDialog(String ctu)
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
        Picasso.get().load(getResources().getIdentifier(CTU, "drawable", getContext().getPackageName())).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into((ImageView) rootview.findViewById(R.id.ctuPicture));

        TabViewAdapter adapter = new TabViewAdapter(getChildFragmentManager());
        switch(CTU)
        {
            case "sas":
                adapter.addFragment("Sledge",OperatorCustomFragment.createInstance("Sledge"));
                adapter.addFragment("Thatcher",OperatorCustomFragment.createInstance("Thatcher"));
                adapter.addFragment("Mute",OperatorCustomFragment.createInstance("Mute"));
                adapter.addFragment("Smoke",OperatorCustomFragment.createInstance("Smoke"));
                break;
            case "fbiswat":
                adapter.addFragment("Ash",OperatorCustomFragment.createInstance("Ash"));
                adapter.addFragment("Thermite",OperatorCustomFragment.createInstance("Thermite"));
                adapter.addFragment("Castle",OperatorCustomFragment.createInstance("Castle"));
                adapter.addFragment("Pulse",OperatorCustomFragment.createInstance("Pulse"));
                break;
            case "gign":
                adapter.addFragment("Twitch",OperatorCustomFragment.createInstance("Twitch"));
                adapter.addFragment("Montagne",OperatorCustomFragment.createInstance("Montagne"));
                adapter.addFragment("Rook",OperatorCustomFragment.createInstance("Rook"));
                adapter.addFragment("Doc",OperatorCustomFragment.createInstance("Doc"));
                break;
            case "spetsnaz":
                adapter.addFragment("Glaz",OperatorCustomFragment.createInstance("Glaz"));
                adapter.addFragment("Fuze",OperatorCustomFragment.createInstance("Fuze"));
                adapter.addFragment("Kapkan",OperatorCustomFragment.createInstance("Kapkan"));
                adapter.addFragment("Tachanka",OperatorCustomFragment.createInstance("Tachanka"));
                break;
            case "gsg9":
                adapter.addFragment("Blitz",OperatorCustomFragment.createInstance("Blitz"));
                adapter.addFragment("IQ",OperatorCustomFragment.createInstance("IQ"));
                adapter.addFragment("Jäger",OperatorCustomFragment.createInstance("Jäger"));
                adapter.addFragment("Bandit",OperatorCustomFragment.createInstance("Bandit"));
                break;
            case "jtf2":
                adapter.addFragment("Buck",OperatorCustomFragment.createInstance("Buck"));
                adapter.addFragment("Frost",OperatorCustomFragment.createInstance("Frost"));
                break;
            case "navyseals":
                adapter.addFragment("Blackbeard",OperatorCustomFragment.createInstance("Blackbeard"));
                adapter.addFragment("Valkyrie",OperatorCustomFragment.createInstance("Valkyrie"));
                break;
            case "bope":
                adapter.addFragment("Capitão",OperatorCustomFragment.createInstance("Capitão"));
                adapter.addFragment("Caveira",OperatorCustomFragment.createInstance("Caveira"));
                break;
            case "sat":
                adapter.addFragment("Hibana",OperatorCustomFragment.createInstance("Hibana"));
                adapter.addFragment("Echo",OperatorCustomFragment.createInstance("Echo"));
                break;
            case "geo":
                adapter.addFragment("Jackal",OperatorCustomFragment.createInstance("Jackal"));
                adapter.addFragment("Mira",OperatorCustomFragment.createInstance("Mira"));
                break;
            case "sdu":
                adapter.addFragment("Ying",OperatorCustomFragment.createInstance("Ying"));
                adapter.addFragment("Lesion",OperatorCustomFragment.createInstance("Lesion"));
                break;
            case "grom":
                adapter.addFragment("Zofia",OperatorCustomFragment.createInstance("Zofia"));
                adapter.addFragment("Ela",OperatorCustomFragment.createInstance("Ela"));
                break;
            case "smb":
                adapter.addFragment("Dokkaebi",OperatorCustomFragment.createInstance("Dokkaebi"));
                adapter.addFragment("Vigil",OperatorCustomFragment.createInstance("Vigil"));
                break;
            case "cbrn":
                adapter.addFragment("Finka",OperatorCustomFragment.createInstance("Finka"));
                adapter.addFragment("Lion",OperatorCustomFragment.createInstance("Lion"));
                break;
            case "gis":
                adapter.addFragment("Maestro",OperatorCustomFragment.createInstance("Maestro"));
                adapter.addFragment("Alibi",OperatorCustomFragment.createInstance("Alibi"));
                break;
            default:
                adapter.addFragment("Error",OperatorCustomFragment.createInstance("Error"));
                break;
        }

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootview;
    }
}
