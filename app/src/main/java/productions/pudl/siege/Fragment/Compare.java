package productions.pudl.siege.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import productions.pudl.siege.R;

public class Compare extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.compare_fragment, container, false);
        ImageView leftImage = (ImageView) v.findViewById(R.id.leftImage);
        Picasso.get().load(R.drawable.r6slogo).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(leftImage);

        ImageView rightImage = (ImageView) v.findViewById(R.id.rightImage);
        Picasso.get().load(R.drawable.r6slogo).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(rightImage);
        return v;
    }
}
