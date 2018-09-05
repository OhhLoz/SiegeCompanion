package productions.pudl.siege.Fragment.Operators;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import productions.pudl.siege.Data.Operator;
import productions.pudl.siege.R;

public class OperatorCustomFragment extends Fragment
{
    private Operator operator;
    public static OperatorCustomFragment createInstance(Operator operator)
    {
        OperatorCustomFragment fragment = new OperatorCustomFragment();
        fragment.operator = operator;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.operator_tab,container,false);
        if (operator != null)
        {
            ((TextView) v.findViewById(R.id.operatorKillsText)).setText(String.valueOf(operator.getKills()));
            ((TextView) v.findViewById(R.id.operatorDeathsText)).setText(String.valueOf(operator.getDeaths()));
            ((TextView) v.findViewById(R.id.operatorKDText)).setText(String.valueOf(operator.getKd()));
            ((TextView) v.findViewById(R.id.operatorWinsText)).setText(String.valueOf(operator.getWins()));
            ((TextView) v.findViewById(R.id.operatorLossesText)).setText(String.valueOf(operator.getLosses()));
            ((TextView) v.findViewById(R.id.operatorWLText)).setText(String.valueOf(operator.getWl()));
            ((TextView) v.findViewById(R.id.operatorPlayedText)).setText(String.valueOf(operator.getPlayed()));
            ((TextView) v.findViewById(R.id.operatorTimePlayedText)).setText(operator.getTimeplayedStr());
            ((TextView) v.findViewById(R.id.operatorKillsRoundText)).setText(String.valueOf(operator.getKR()));
            ((TextView) v.findViewById(R.id.operatorDBNOText)).setText(String.valueOf(operator.getDbno()));
            ((TextView) v.findViewById(R.id.operatorHeadshotText)).setText(String.valueOf(operator.getHeadshot()));
            ((TextView) v.findViewById(R.id.operatorMeleeKillsText)).setText(String.valueOf(operator.getMeleekills()));
        }
        return v;
    }
}
