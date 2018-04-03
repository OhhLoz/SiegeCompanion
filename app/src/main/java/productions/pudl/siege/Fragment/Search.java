package productions.pudl.siege.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.provider.SearchRecentSuggestions;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import productions.pudl.siege.Adapter.JSONAdapter;
import productions.pudl.siege.Adapter.ListViewAdapter;
import productions.pudl.siege.R;
import productions.pudl.siege.SearchFragmentProvider;
import productions.pudl.siege.Data.User;

public class Search extends Fragment implements AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener, View.OnClickListener, SearchView.OnCloseListener {
    SearchView searchView;
    ListView list;
    ListViewAdapter adapter2;
    String[] platformList;
    String[] userNameList;
    ArrayList<User> arraylist = new ArrayList<User>();

    private RequestQueue mQueue;

    String currPlatformSelected = "";
    String currUserNameSelected = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.platform_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setPrompt("Platform");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchClickListener(this);
        searchView.setOnCloseListener(this);

        userNameList = new String[]{"sallad_", "fork__", "KonoAma", "Darksubi_", "XboxTest", "PlaystationTest"};
        platformList = new String[]{"uPlay", "uPlay", "uPlay", "uPlay", "Xbox", "Playstation"};

        list = (ListView) view.findViewById(R.id.listview);
        mQueue = Volley.newRequestQueue(getContext());

        // just for populating the arrays before implementing previous history
        if (userNameList.length == 0 || platformList.length == 0) {
            //User previousSearches = new User("No Results", "Please Search Usernames to populate");
            //arraylist.add(previousSearches);
            //currUserNameSelected = "Please Search Usernames to populate";
            //currPlatformSelected = "No Results";
        } else if (userNameList.length == platformList.length) {
            for (int i = 0; i < userNameList.length; i++) {
                User user = new User(platformList[i], userNameList[i]);
                arraylist.add(user);
            }
        }

        adapter2 = new ListViewAdapter(getContext(), arraylist);
        list.setAdapter(adapter2);
        //adapter2.filter(currUserNameSelected, currPlatformSelected);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) // Spinner Select
    {
        currPlatformSelected = (String) adapterView.getItemAtPosition(i);
        adapter2.filter(currUserNameSelected, currPlatformSelected);
        Log.v("item", (String) currPlatformSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) // on no spinner select
    {
    }

    @Override
    public boolean onQueryTextSubmit(String query) // on SearchView text submit
    {
        searchView.setIconified(true);
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(getContext(), SearchFragmentProvider.AUTHORITY, SearchFragmentProvider.MODE);
        suggestions.saveRecentQuery(query, null);

        Log.v("JSON", "starting json import");
        JSONAdapter jsonAdapter = new JSONAdapter("sallad_", "uplay", mQueue);
        Log.v("JSON", "finished json import");
        //searchView.setIconifiedByDefault(true);
        //display stats instead of listview
        //add searched name and platform ONLY if exists in the API database
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) // on SearchView text change
    {
        //searchView.setIconified(false);
        //searchView.setIconifiedByDefault(false);
        currUserNameSelected = newText;
        adapter2.filter(currUserNameSelected, currPlatformSelected);
        return false;
    }

    @Override
    public void onClick(View view) // on Searchview Click
    {
        searchView.setIconified(true);
    }

    @Override
    public boolean onClose() // on Searchview Close
    {
        searchView.setIconified(true);
        return false;
    }
}
