package productions.pudl.siege.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import productions.pudl.siege.Data.GeneralObjects.GeneralAliasObject;
import productions.pudl.siege.Data.GeneralObjects.GeneralAliasesObject;
import productions.pudl.siege.R;

public class AliasListAdapter extends ArrayAdapter
{
    private final ArrayList<GeneralAliasObject> aliasesObject;
    private Activity context;

    public AliasListAdapter(Activity context, ArrayList<GeneralAliasObject> aliases)
    {
        super(context, R.layout.aliases_list_items, aliases);
        this.aliasesObject = aliases;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.aliases_list_items, null, true);

        TextView aliasName = (TextView) rowView.findViewById(R.id.aliasName);
        TextView createdAt = (TextView) rowView.findViewById(R.id.createdAt);

        GeneralAliasObject currItem = aliasesObject.get(position);

        String createdAtText = currItem.getCreatedAt();

        String[] strArray = createdAtText.split("-");
        String year = strArray[0];
        String month = strArray[1];
        String restOfString = strArray[2];
        String secondStrArray[] = restOfString.split("T");
        String date = secondStrArray[0];
        String time = secondStrArray[1].split("\\.")[0];

        String createdAtTextFinal = "Changed: " + date + "/" + month + "/" + year + " " + time;
        aliasName.setText(currItem.getName());
        createdAt.setText(createdAtTextFinal);
        return rowView;
    }
}
