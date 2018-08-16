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
            int seconds = operator.getTimeplayed();
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            seconds = (seconds % 3600) % 60;
            StringBuilder timePlayed = new StringBuilder();
            if (hours != 0)
                timePlayed.append(String.valueOf(hours) + "H ");
            if (minutes != 0)
                timePlayed.append(String.valueOf(minutes) + "M");
            if (hours == 0)
                timePlayed.append(" " + String.valueOf(seconds) + "S");
            String convertedTimePlayed = timePlayed.toString();

            ((TextView) v.findViewById(R.id.operatorKillsText)).setText(String.valueOf(operator.getKills()));
            ((TextView) v.findViewById(R.id.operatorDeathsText)).setText(String.valueOf(operator.getDeaths()));
            ((TextView) v.findViewById(R.id.operatorKDText)).setText(String.valueOf(operator.getKd()));
            ((TextView) v.findViewById(R.id.operatorWinsText)).setText(String.valueOf(operator.getWins()));
            ((TextView) v.findViewById(R.id.operatorLossesText)).setText(String.valueOf(operator.getLosses()));
            ((TextView) v.findViewById(R.id.operatorWLText)).setText(String.valueOf(operator.getWl()));
            ((TextView) v.findViewById(R.id.operatorPlayedText)).setText(String.valueOf(operator.getPlayed()));
            ((TextView) v.findViewById(R.id.operatorTimePlayedText)).setText(convertedTimePlayed);
            ((TextView) v.findViewById(R.id.operatorDBNOText)).setText(String.valueOf(operator.getDbno()));
            ((TextView) v.findViewById(R.id.operatorHeadshotText)).setText(String.valueOf(operator.getHeadshot()));
            ((TextView) v.findViewById(R.id.operatorMeleeKillsText)).setText(String.valueOf(operator.getMeleekills()));

            if (operator.isSpecial1()) {
                ((TextView) v.findViewById(R.id.operatorSpecial1Text)).setText(String.valueOf(operator.getSpecial1()));
                ((TextView) v.findViewById(R.id.operatorSpecial1Title)).setText(operator.getSpecial1Desc());
            }
            if (operator.isSpecial2()) {
                ((TextView) v.findViewById(R.id.operatorSpecial2Text)).setText(String.valueOf(operator.getSpecial2()));
                ((TextView) v.findViewById(R.id.operatorSpecial2Title)).setText(operator.getSpecial2Desc());
            }
            if(operator.isSpecial3())
            {
                ((TextView) v.findViewById(R.id.operatorSpecial3Text)).setText(String.valueOf(operator.getSpecial3()));
                ((TextView) v.findViewById(R.id.operatorSpecial3Title)).setText(operator.getSpecial3Desc());
            }
        }
        return v;
    }
}
