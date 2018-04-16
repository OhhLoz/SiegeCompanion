package productions.pudl.siege;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import productions.pudl.siege.Adapter.TabViewAdapter;
import productions.pudl.siege.Data.DetailedObjects.DetailedMainObject;

public class TabbedDialog extends android.support.v4.app.DialogFragment
{
    public static DetailedMainObject currItem;
    TabLayout tabLayout;
    ViewPager viewPager;

    ImageView profilePicture;
    TextView userName;
    TextView platformName;
    TextView level;

    public TabbedDialog(DetailedMainObject currItem)
    {
        this.currItem = currItem;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootview = inflater.inflate(R.layout.tabbed_dialog,container,false);

        profilePicture = (ImageView) rootview.findViewById(R.id.profilePicture);
        userName = (TextView) rootview.findViewById(R.id.userName);
        platformName = (TextView) rootview.findViewById(R.id.platformName);
        level = (TextView) rootview.findViewById(R.id.level);

        currItem.getUserPicture(profilePicture);
        userName.setText(currItem.getName());
        platformName.setText(currItem.getPlatform());
        level.setText(getString(R.string.level) + ": " + String.valueOf(currItem.getLevel()));
        
        tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootview.findViewById(R.id.masterViewPager);
        TabViewAdapter adapter = new TabViewAdapter(getChildFragmentManager());
        adapter.addFragment("General", CustomTabFragment.createInstance(currItem, "general"));
        adapter.addFragment("Casual", CustomTabFragment.createInstance(currItem, "casual"));
        adapter.addFragment("Ranked", CustomTabFragment.createInstance(currItem, "ranked"));
        adapter.addFragment("Weapons", CustomTabFragment.createInstance(currItem, "weapon"));
        adapter.addFragment("Misc", CustomTabFragment.createInstance(currItem, "misc"));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootview;
    }
}