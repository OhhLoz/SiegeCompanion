package productions.pudl.siege.Fragment;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;

import productions.pudl.siege.Adapter.R6DBJSONAdapter;
import productions.pudl.siege.Adapter.ListViewAdapter;
import productions.pudl.siege.Data.DetailedObjects.DetailedMainObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralObject;
import productions.pudl.siege.Data.User;
import productions.pudl.siege.R;

public class Search extends Fragment implements AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener, ListView.OnItemClickListener, SearchView.OnCloseListener {
    SearchView searchView;
    ListView listView;
    ListViewAdapter listViewAdapter;
    R6DBJSONAdapter r6DBJsonAdapter;
    String[] platformList;
    String[] userNameList;
    ArrayList<User> userList = new ArrayList<User>();
    public static ArrayList<GeneralObject> searchResults;
    public static DetailedMainObject finalResult;
    GeneralObject ItemClicked;
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

        userNameList = new String[]{"sallad_", "fork__", "KonoAma", "Darksubi_", "XboxTest", "PlaystationTest"};
        platformList = new String[]{"PC", "PC", "PC", "PC", "Xbox", "PS4"};

        listView = (ListView) view.findViewById(R.id.listview);
        mQueue = Volley.newRequestQueue(view.getContext());

//        // just for populating the arrays before implementing previous history
//        if (userNameList.length == 0 || platformList.length == 0)  // if no hardcoded values given
//        {
//            User previousSearches = new User("No Results", "Please Search Usernames to populate");
//            userList.add(previousSearches);
//            currUserNameSelected = "Please Search Usernames to populate";
//            currPlatformSelected = "No Results";
//        }
//        else if (userNameList.length == platformList.length) // if there are equal matching pairs in the hardcoded string arrays
//        {
//            for (int i = 0; i < userNameList.length; i++) {
//                User user = new User(platformList[i], userNameList[i]);
//                userList.add(user);
//            }
//        }

        //if i replace the below userList with searchResults it doesn't work as it isnt populated yet
        searchResults = new ArrayList<GeneralObject>();
        listViewAdapter = new ListViewAdapter(view.getContext(), searchResults);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);

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
        listViewAdapter.filter(currUserNameSelected, currPlatformSelected);
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
        r6DBJsonAdapter = new R6DBJSONAdapter(this, currUserNameSelected, currPlatformSelected, mQueue);
        Toast.makeText(getContext(), "Searching Data...", Toast.LENGTH_SHORT).show();
        r6DBJsonAdapter.ParseGeneral();
        searchResults = r6DBJsonAdapter.getSearchResults();
        Log.v("Test", "Updated Search Results");
        searchView.clearFocus(); // removes the soft keyboard


        Log.v("JSON", "finished json import");
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) // on SearchView text change
    {
        currUserNameSelected = newText;
        listViewAdapter.filter(currUserNameSelected, currPlatformSelected);
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
        ItemClicked = listViewAdapter.getItem(i);
        ItemView = view;
        Log.v("CurrItem", ItemClicked.getName() + ", " + ItemClicked.getPlatform());
        r6DBJsonAdapter.ParseDetailed(ItemClicked.getUserID());
        //DetailedObject detail = r6DBJsonAdapter.getDetailedObject();
        //ShowPopup(view, detail);
    }

    public void updateListView(ArrayList<GeneralObject> newArrayList)
    {
        listViewAdapter.updateSearchList(newArrayList);
        Toast.makeText(getContext(), "Search Complete!", Toast.LENGTH_SHORT).show();
    }

    public void updateFinalResult(DetailedMainObject finalObject)
    {
        finalResult = finalObject;
        Toast.makeText(getContext(), "Fetched all Data for User: " + finalResult.getName(), Toast.LENGTH_SHORT).show();
        ShowPopup(ItemView, finalResult);
        //listView.requestFocus();
    }

    private void ShowPopup(View view, DetailedMainObject currItem)
    {
        Dialog myDialog = new Dialog(view.getContext());
        ImageView profilePicture;
        TextView userName;
        TextView platformName;
        TextView generalKillsText;
        TextView generalDeathsText;
        TextView generalAssistsText;
        TextView generalKDText;
        TextView generalWinsText;
        TextView generalLossesText;
        TextView generalPlayedText;
        TextView generalWLText;
        TextView generalRevivesText ;
        TextView generalSuicidesText;
        TextView generalLevelText;
        TextView generalTimePlayedText;

        myDialog.setContentView(R.layout.layout_stats);
        profilePicture = (ImageView) myDialog.findViewById(R.id.profilePicture);
        userName = (TextView) myDialog.findViewById(R.id.userName);
        platformName = (TextView) myDialog.findViewById(R.id.platformName);
        generalKillsText = (TextView) myDialog.findViewById(R.id.generalKillsText);
        generalDeathsText = (TextView) myDialog.findViewById(R.id.generalDeathsText);
        generalAssistsText = (TextView) myDialog.findViewById(R.id.generalAssistsText);
        generalKDText = myDialog.findViewById(R.id.generalKDText);
        generalWinsText = (TextView) myDialog.findViewById(R.id.generalWinsText);
        generalLossesText = (TextView) myDialog.findViewById(R.id.generalLossesText);
        generalPlayedText = (TextView) myDialog.findViewById(R.id.generalPlayedText);
        generalWLText = (TextView) myDialog.findViewById(R.id.generalWLText);
        generalRevivesText = (TextView) myDialog.findViewById(R.id.generalRevivesText);
        generalSuicidesText = (TextView) myDialog.findViewById(R.id.generalSuicidesText);
        generalLevelText = (TextView) myDialog.findViewById(R.id.generalLevelText);
        generalTimePlayedText = (TextView) myDialog.findViewById(R.id.generalTimePlayedText);

        int kills = currItem.getStats().getGeneral().getKills();
        int deaths = currItem.getStats().getGeneral().getDeaths();
        double KD = (double) kills /  (double) deaths;
        KD = (double) Math.round(KD * 1000d) / 1000d;

        int wins = currItem.getStats().getGeneral().getWon();
        int losses = currItem.getStats().getGeneral().getLost();
        double WL = (double) wins /  (double) losses;
        WL = (double) Math.round(WL * 1000d) / 1000d;

        int seconds = currItem.getStats().getGeneral().getTimePlayed();
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = (seconds % 3600) % 60;

        StringBuilder timePlayed = new StringBuilder();
        if(hours != 0)
            timePlayed.append(String.valueOf(hours) + "H ");
        if(minutes != 0)
            timePlayed.append(String.valueOf(minutes) + "M");

        currItem.getUserPicture(profilePicture);
        userName.setText(currItem.getName());
        platformName.setText(currItem.getPlatform());
        generalKillsText.setText(String.valueOf(kills));
        generalDeathsText.setText(String.valueOf(deaths));
        generalAssistsText.setText(String.valueOf(currItem.getStats().getGeneral().getAssists()));
        generalKDText.setText(String.valueOf(KD));
        generalWinsText.setText(String.valueOf(wins));
        generalLossesText.setText(String.valueOf(losses));
        generalPlayedText.setText(String.valueOf(currItem.getStats().getGeneral().getPlayed()));
        generalWLText.setText(String.valueOf(WL));
        generalRevivesText.setText(String.valueOf(currItem.getStats().getGeneral().getRevives()));
        generalSuicidesText.setText(String.valueOf(currItem.getStats().getGeneral().getSuicides()));
        generalLevelText.setText(String.valueOf(currItem.getLevel()));
        generalTimePlayedText.setText(timePlayed.toString());

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
