package productions.pudl.siege.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import productions.pudl.siege.Data.User;
import productions.pudl.siege.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<User> mData ;
    private ArrayList<User> arraylist;

    public RecyclerViewAdapter(Context mContext, List<User> list)
    {
        this.mContext = mContext;
        this.mData = list;
        this.arraylist = new ArrayList<User>();
        this.arraylist.addAll(list);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.list_view_items,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.userName.setText(mData.get(position).getPlayerName());
        holder.platformName.setText(mData.get(position).getPlatform());
        // load image from the internet using Glide
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView platformName;
        //ImageView AnimeThumbnail;

        public MyViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.userName);
            platformName = (TextView) view.findViewById(R.id.platformName);
        }
    }

    public void filter(String userName, String platformName)
    {
        userName = userName.toLowerCase(Locale.getDefault());
        platformName = platformName.toLowerCase(Locale.getDefault());
        mData.clear();
        for (User currPrevSearch : arraylist)
        {
            if (userName.length() == 0 && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                mData.add(currPrevSearch);
            }
            else if (currPrevSearch.getPlayerName().toLowerCase(Locale.getDefault()).contains(userName) && currPrevSearch.getPlatform().toLowerCase(Locale.getDefault()).contains(platformName))
            {
                mData.add(currPrevSearch);
            }
        }
        notifyDataSetChanged();
    }
}
