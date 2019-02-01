package productions.pudl.siege.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import productions.pudl.siege.R;

public class More extends Fragment
{
    SharedPreferences prefs;
    EditText userNameEditText;
    Spinner platformSpinner;
    Spinner themeSpinner;
    Spinner languageSpinner;
    String defaultUserNameKey = "productions.pudl.siege.defaultUserName";
    String defaultPlatformKey = "productions.pudl.siege.defaultPlatform";
    ArrayAdapter<CharSequence> spinnerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View temp = inflater.inflate(R.layout.more_fragment, container, false);
        userNameEditText = temp.findViewById(R.id.moreDefaultNameInput);
        platformSpinner = temp.findViewById(R.id.morePlatformSpinner);
        themeSpinner = temp.findViewById(R.id.moreColourSchemeSpinner);
        languageSpinner = temp.findViewById(R.id.moreLanguageSpinner);

        prefs = this.getActivity().getSharedPreferences("productions.pudl.siege", Context.MODE_PRIVATE);

        spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.platform_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platformSpinner.setAdapter(spinnerAdapter);

        String userNameVal = prefs.getString(defaultUserNameKey, "null");
        if (!userNameVal.equals("null"))
            userNameEditText.setText(userNameVal);

        String platformVal = prefs.getString(defaultPlatformKey, "null");
        if (!platformVal.equals("null"))
        {
            platformSpinner.setSelection(spinnerAdapter.getPosition(platformVal));
        }

        ArrayAdapter<CharSequence> tempThemeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.theme_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> tempLanguageAdapter = ArrayAdapter.createFromResource(getContext(), R.array.languages_array, android.R.layout.simple_spinner_item);
        tempThemeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tempLanguageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        languageSpinner.setAdapter(tempLanguageAdapter);
        themeSpinner.setAdapter(tempThemeAdapter);
        return temp;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(defaultUserNameKey, userNameEditText.getText().toString());
        editor.putString(defaultPlatformKey, spinnerAdapter.getItem(platformSpinner.getSelectedItemPosition()).toString());

        editor.apply();
    }
}
