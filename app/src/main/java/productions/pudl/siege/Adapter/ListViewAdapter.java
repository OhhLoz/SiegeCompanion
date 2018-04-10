package productions.pudl.siege.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import productions.pudl.siege.Data.GeneralObjects.GeneralObject;
import productions.pudl.siege.Data.User;
import productions.pudl.siege.R;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<GeneralObject> generalObjectsArrayList = null;
    private ArrayList<GeneralObject> arraylist;

    public ListViewAdapter(Context context, ArrayList<GeneralObject> userList) {
        mContext = context;
        this.generalObjectsArrayList = userList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<GeneralObject>();
        this.arraylist.addAll(userList);
    }

    public class ViewHolder {
        TextView userName;
        TextView platformName;
    }

    @Override
    public int getCount() {
        return generalObjectsArrayList.size();
    }

    @Override
    public GeneralObject getItem(int position) {
        return generalObjectsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            holder.userName = (TextView) view.findViewById(R.id.userName);
            holder.platformName = (TextView) view.findViewById(R.id.platformName);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.userName.setText(generalObjectsArrayList.get(position).getName());
        holder.platformName.setText(generalObjectsArrayList.get(position).getPlatform());
        return view;
    }

    public void updateSearchList(ArrayList<GeneralObject> newArrayList)
    {
        this.generalObjectsArrayList = newArrayList;
        this.notifyDataSetChanged();
    }

    // Filter Class
    public void filter(String userName, String platformName)
    {
        userName = userName.toLowerCase(Locale.getDefault());
        platformName = platformName.toLowerCase(Locale.getDefault());
        generalObjectsArrayList.clear();
        for (GeneralObject currPrevSearch : arraylist)
        {
            if (userName.length() == 0 && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                generalObjectsArrayList.add(currPrevSearch);
            }
            else if (currPrevSearch.getName().toLowerCase(Locale.getDefault()).contains(userName) && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                generalObjectsArrayList.add(currPrevSearch);
            }
        }
        notifyDataSetChanged();
    }

}