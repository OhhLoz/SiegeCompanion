package productions.pudl.siege;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Search extends Fragment implements AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener
{
    SearchView search;
    ListView list;
    ListViewAdapter adapter2;
    String[] platformList;
    String[] userNameList;
    ArrayList<PreviousSearches> arraylist = new ArrayList<PreviousSearches>();

    String currPlatformSelected = "";
    String currUserNameSelected = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.platform_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setPrompt("Platform");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        userNameList = new String[]{"sallad_", "fork__", "KonoAma", "Darksubi_", "XboxTest", "PlaystationTest"};

        platformList = new String[] {"uPlay", "uPlay", "uPlay", "uPlay", "Xbox", "Playstation"};

        list = (ListView) view.findViewById(R.id.listview);

        // just for populating the arrays before implementing previous history
        if (userNameList.length == 0 || platformList.length == 0)
        {
            PreviousSearches previousSearches = new PreviousSearches("No Results", "Please Search Usernames to populate");
            arraylist.add(previousSearches);
        }
        else if (userNameList.length == platformList.length)
        {
            for (int i = 0; i < userNameList.length; i++)
            {
                PreviousSearches previousSearches = new PreviousSearches(platformList[i], userNameList[i]);
                arraylist.add(previousSearches);
            }
        }

        adapter2 = new ListViewAdapter(getContext(), arraylist);

        list.setAdapter(adapter2);

        search = (SearchView) view.findViewById(R.id.searchView);
        search.setIconified(false);
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        currPlatformSelected = (String) adapterView.getItemAtPosition(i);
        adapter2.filter(currUserNameSelected, currPlatformSelected);
        Log.v("item", (String) currPlatformSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        search.setIconified(true);
        search.setIconifiedByDefault(true);
        //display stats instead of listview
        //add searched name and platform ONLY if exists in the API database
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        search.setIconified(false);
        search.setIconifiedByDefault(false);
        currUserNameSelected = newText;
        adapter2.filter(currUserNameSelected, currPlatformSelected);
        return false;
    }
}
