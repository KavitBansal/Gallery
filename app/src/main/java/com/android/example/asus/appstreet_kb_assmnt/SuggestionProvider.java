package com.android.example.asus.appstreet_kb_assmnt;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by asus on 18/7/18.
 */
public class SuggestionProvider extends SearchRecentSuggestionsProvider {

    // part of content uri which is defined in AndroidManifest.xml
    public static final String AUTHORITY = "com.android.example.asus.appstreet_kb_assmnt" +
            ".SuggestionProvider";

    // suggestion mode which gives recent queries
    public static final int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
