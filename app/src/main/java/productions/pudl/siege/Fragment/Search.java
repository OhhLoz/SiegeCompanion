package productions.pudl.siege.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import productions.pudl.siege.Adapter.R6DBJSONAdapter;
import productions.pudl.siege.Adapter.ListViewAdapter;
import productions.pudl.siege.Adapter.RecyclerViewAdapter;
import productions.pudl.siege.Data.GeneralObjects.GeneralObject;
import productions.pudl.siege.Data.User;
import productions.pudl.siege.R;
public class Search extends Fragment implements AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    SearchView searchView;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    String[] platformList;
    String[] userNameList;
    ArrayList<User> userList = new ArrayList<User>();
    ArrayList<GeneralObject> searchResults = new ArrayList<>();
    ArrayAdapter<CharSequence> spinnerAdapter;

    private RequestQueue mQueue;

    String currPlatformSelected = "";
    String currUserNameSelected = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.platform_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setPrompt("Platform");
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        setupSearchView();

        userNameList = new String[]{"sallad_", "fork__", "KonoAma", "Darksubi_", "XboxTest", "PlaystationTest"};
        platformList = new String[]{"PC", "PC", "PC", "PC", "Xbox", "PS4"};

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mQueue = Volley.newRequestQueue(getContext());

        // just for populating the arrays before implementing previous history
        if (userNameList.length == 0 || platformList.length == 0)  // if no hardcoded values given
        {
            User previousSearches = new User("No Results", "Please Search Usernames to populate");
            userList.add(previousSearches);
            currUserNameSelected = "Please Search Usernames to populate";
            currPlatformSelected = "No Results";
        }
        else if (userNameList.length == platformList.length) // if there are equal matching pairs in the hardcoded string arrays
        {
            for (int i = 0; i < userNameList.length; i++) {
                User user = new User(platformList[i], userNameList[i]);
                userList.add(user);
            }
        }

        //if i replace the below userList with searchResults it doesn't work as it isnt populated yet
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), userList);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }

    private void setupSearchView()
    {
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.setQueryHint("Enter Username...");
        searchView.clearFocus();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) // Spinner Select
    {
        currPlatformSelected = (String) adapterView.getItemAtPosition(i);
        recyclerViewAdapter.filter(currUserNameSelected, currPlatformSelected);
        Log.v("item", (String) currPlatformSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) // on no spinner select
    {
    }

    @Override
    public boolean onQueryTextSubmit(String query) // on SearchView text submit
    {
        //searchView.setIconified(true);
        currUserNameSelected = query;

        Log.v("JSON", "starting json import with username = " + currUserNameSelected + " and platform = " + currPlatformSelected);
        //R6StatsJSONAdapter r6StatsJsonAdapter = new R6StatsJSONAdapter(currUserNameSelected, currPlatformSelected, mQueue);
        R6DBJSONAdapter r6DBJsonAdapter = new R6DBJSONAdapter(currUserNameSelected, currPlatformSelected, mQueue);
        r6DBJsonAdapter.ParseGeneral();
        searchResults = r6DBJsonAdapter.getSearchResults();
        Log.v("JSON", "finished json import");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) // on SearchView text change
    {
        currUserNameSelected = newText;
        recyclerViewAdapter.filter(currUserNameSelected, currPlatformSelected);
        return false;
    }

    @Override
    public boolean onClose() // on Searchview Close
    {
        setupSearchView();
        return true;
    }
}
