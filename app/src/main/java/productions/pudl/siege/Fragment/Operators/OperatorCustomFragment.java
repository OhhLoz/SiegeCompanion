package productions.pudl.siege.Fragment.Operators;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import productions.pudl.siege.R;

public class OperatorCustomFragment extends Fragment
{
    private String opName = "";
    public static OperatorCustomFragment createInstance(String opName)
    {
        OperatorCustomFragment fragment = new OperatorCustomFragment();
        fragment.opName = opName;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.operator_tab,container,false);
        return v;
    }
}
