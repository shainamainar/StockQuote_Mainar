package csci490.cofc.edu.stockquote_mainar;

import android.content.SearchRecentSuggestionsProvider;


/**
 * Created by smainar on 3/15/2018.
 */

public class MySuggestionsProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.MySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public MySuggestionsProvider(){
        setupSuggestions(AUTHORITY, MODE);
    }
}
