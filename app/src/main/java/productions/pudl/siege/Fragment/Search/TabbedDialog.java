package productions.pudl.siege.Fragment.Search;

import android.annotation.SuppressLint;
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
import productions.pudl.siege.R;

@SuppressLint("ValidFragment")
public class TabbedDialog extends android.support.v4.app.DialogFragment
{
    public static DetailedMainObject currItem;
    TabLayout tabLayout;
    ViewPager viewPager;

    ImageView profilePicture;
    TextView userName;
    TextView platformName;
    TextView level;

    @SuppressLint("ValidFragment")
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
        String levelText = getString(R.string.level);
        if (userName.length() <= 5)
            levelText = getString(R.string.levelShort);
        level.setText(levelText + ": " + String.valueOf(currItem.getLevel()));
        
        tabLayout = (TabLayout) rootview.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) rootview.findViewById(R.id.masterViewPager);
        TabViewAdapter adapter = new TabViewAdapter(getChildFragmentManager());
        adapter.addFragment("General", CustomTabFragment.createInstance(currItem, "general"));
        adapter.addFragment("Casual", CustomTabFragment.createInstance(currItem, "casual"));
        adapter.addFragment("Ranked", CustomTabFragment.createInstance(currItem, "ranked"));

        if(currItem.getPlacements().getGlobal() != 0)
            adapter.addFragment("Placements", CustomTabFragment.createInstance(currItem, "placements"));

        adapter.addFragment("Weapons", CustomTabFragment.createInstance(currItem, "weapon"));
        adapter.addFragment("Misc", CustomTabFragment.createInstance(currItem, "misc"));
        adapter.addFragment("Aliases", CustomTabFragment.createInstance(currItem, "aliases"));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootview;
    }
}