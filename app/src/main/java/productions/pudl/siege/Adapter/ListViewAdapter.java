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

import productions.pudl.siege.R;
import productions.pudl.siege.Data.User;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<User> userList = null;
    private ArrayList<User> arraylist;

    public ListViewAdapter(Context context, List<User> userList) {
        mContext = context;
        this.userList = userList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<User>();
        this.arraylist.addAll(userList);
    }

    public class ViewHolder {
        TextView userName;
        TextView platformName;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public User getItem(int position) {
        return userList.get(position);
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
        holder.userName.setText(userList.get(position).getPlayerName());
        holder.platformName.setText(userList.get(position).getPlatform());
        return view;
    }

    // Filter Class
    public void filter(String userName, String platformName)
    {
        userName = userName.toLowerCase(Locale.getDefault());
        platformName = platformName.toLowerCase(Locale.getDefault());
        userList.clear();
        for (User currPrevSearch : arraylist)
        {
            if (userName.length() == 0 && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                userList.add(currPrevSearch);
            }
            else if (currPrevSearch.getPlayerName().toLowerCase(Locale.getDefault()).contains(userName) && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                userList.add(currPrevSearch);
            }
        }
        notifyDataSetChanged();
    }

}