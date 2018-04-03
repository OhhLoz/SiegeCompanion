package productions.pudl.siege;

import android.content.SearchRecentSuggestionsProvider;

public class SearchFragmentProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "productions.pudl.siege.SearchFragmentProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SearchFragmentProvider()
    {
        setupSuggestions(AUTHORITY, MODE);
    }
}