package productions.pudl.siege.Fragment.Operators;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import productions.pudl.siege.R;

public class OperatorCustomFragment extends Fragment
{
    private String mText = "";
    public static OperatorCustomFragment createInstance(String txt)
    {
        OperatorCustomFragment fragment = new OperatorCustomFragment();
        fragment.mText = txt;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.general_stats,container,false);
        return v;
    }
}
