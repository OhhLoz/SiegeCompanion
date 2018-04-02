package productions.pudl.siege;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<PreviousSearches> previousSearchesList = null;
    private ArrayList<PreviousSearches> arraylist;

    public ListViewAdapter(Context context, List<PreviousSearches> previousSearchesList) {
        mContext = context;
        this.previousSearchesList = previousSearchesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<PreviousSearches>();
        this.arraylist.addAll(previousSearchesList);
    }

    public class ViewHolder {
        TextView userName;
        TextView platformName;
    }

    @Override
    public int getCount() {
        return previousSearchesList.size();
    }

    @Override
    public PreviousSearches getItem(int position) {
        return previousSearchesList.get(position);
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
        holder.userName.setText(previousSearchesList.get(position).getPlayerName());
        holder.platformName.setText(previousSearchesList.get(position).getPlatform());
        return view;
    }

    // Filter Class
    public void filter(String userName, String platformName)
    {
        userName = userName.toLowerCase(Locale.getDefault());
        platformName = platformName.toLowerCase(Locale.getDefault());
        previousSearchesList.clear();
        for (PreviousSearches currPrevSearch : arraylist)
        {
            if (userName.length() == 0 && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                previousSearchesList.add(currPrevSearch);
            }
            else if (currPrevSearch.getPlayerName().toLowerCase(Locale.getDefault()).contains(userName) && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                previousSearchesList.add(currPrevSearch);
            }
        }
        notifyDataSetChanged();
    }

}