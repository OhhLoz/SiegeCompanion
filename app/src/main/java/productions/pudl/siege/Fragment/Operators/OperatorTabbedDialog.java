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

import productions.pudl.siege.Adapter.TabViewAdapter;
import productions.pudl.siege.R;

@SuppressLint("ValidFragment")
public class OperatorTabbedDialog extends android.support.v4.app.DialogFragment
{
    TabLayout tabLayout;
    ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootview = inflater.inflate(R.layout.operator_tabbed_dialog,container,false);
        tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootview.findViewById(R.id.masterViewPager);
        TabViewAdapter adapter = new TabViewAdapter(getChildFragmentManager());
        adapter.addFragment("Sledge",OperatorCustomFragment.createInstance("Sledge"));
        adapter.addFragment("Thatcher",OperatorCustomFragment.createInstance("Thatcher"));
        adapter.addFragment("Mute",OperatorCustomFragment.createInstance("Mute"));
        adapter.addFragment("Smoke",OperatorCustomFragment.createInstance("Smoke"));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Log.v("CustomFragment", "Misc Created!");
        return rootview;
    }
}
