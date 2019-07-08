package productions.pudl.siege.Fragment.Search;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
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

import productions.pudl.siege.Adapter.R6TabAPIAdapter;
import productions.pudl.siege.Adapter.SearchListViewAdapter;
import productions.pudl.siege.Adapter.VolleyResponseListener;
import productions.pudl.siege.Data.DetailedObjects.DetailedMainObject;
import productions.pudl.siege.Data.R6TabObjects.SearchObject;
import productions.pudl.siege.R;

public class Search extends Fragment implements AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener, ListView.OnItemClickListener, SearchView.OnCloseListener {
    SearchView searchView;
    ListView listView;
    SearchListViewAdapter searchListViewAdapter;
    //R6DBJSONAdapter r6DBJsonAdapter;
    public static ArrayList<SearchObject> searchResults;
    public static DetailedMainObject finalResult;
    SearchObject ItemClicked;
    View ItemView;
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

        listView = (ListView) view.findViewById(R.id.listview);
        mQueue = Volley.newRequestQueue(view.getContext());

        searchResults = new ArrayList<SearchObject>();
        searchListViewAdapter = new SearchListViewAdapter(view.getContext(), searchResults);
        listView.setOnItemClickListener(this);
        listView.setAdapter(searchListViewAdapter);

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
        searchListViewAdapter.filter(currUserNameSelected, currPlatformSelected);
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
        R6TabAPIAdapter.searchPlayer(getContext(), currPlatformSelected, currUserNameSelected, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse()
            {
                updateListView(R6TabAPIAdapter.getSearchResults());
            }
        });

        Log.v("Test", "Updated Search Results");
        searchView.clearFocus(); // removes the soft keyboard

        Log.v("JSON", "finished json import");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) // on SearchView text change
    {
        currUserNameSelected = newText;
        searchListViewAdapter.filter(currUserNameSelected, currPlatformSelected);
        return false;
    }

    @Override
    public boolean onClose() // on Searchview Close
    {
        setupSearchView();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Log.v("Item", "Item Clicked");
        ItemClicked = searchListViewAdapter.getItem(i);
        ItemView = view;
        Log.v("CurrItem", ItemClicked.getName() + ", " + ItemClicked.getPlatform());
        //r6DBJsonAdapter.ParseDetailed(ItemClicked.getUserID());
        //DetailedObject detail = r6DBJsonAdapter.getDetailedObject();
        //ShowPopup(view, detail);
    }

    public void updateListView(ArrayList<SearchObject> newArrayList)
    {
        searchListViewAdapter.updateSearchList(newArrayList);
    }

/*    public void updateFinalResult(DetailedMainObject finalObject)
    {
        finalResult = finalObject;
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        TabbedDialog dialogFragment = new TabbedDialog(finalResult);
        //dialogFragment.currItem = finalResult;
        dialogFragment.show(ft,"dialog");
        //listView.requestFocus();
    }*/
}
