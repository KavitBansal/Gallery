package com.android.example.asus.gallery;

import android.net.Uri;

/**
 * Created by asus on 18/7/18.
 */

public class UrlManager {
    private static final String TAG = UrlManager.class.getSimpleName();


    public static final String API_KEY = "9cde2708c44a740260546d1b939dbd57";
                                    //   "6f102c62f41998d151e5a1b48713cf13"

    // must public
    public static final String PREF_SEARCH_QUERY = "searchQuery";

    /*----------- private -------------------------*/
//    private static final String TAG = FlickrFetchr.class.getSimpleName();

    private static final String ENDPOINT = "https://api.flickr.com/services/rest/" ;
    private static final String METHOD_GETRECENT = "flickr.photos.getRecent" ;
    private static final String METHOD_SEARCH = "flickr.photos.search" ;
    private static final String PARAM_EXTRAS = "extras" ;
    private static final String PARAM_TEXT = "query" ;
    private static final String PAGE = "page";
    private static final String EXTRA_SMALL_URL = "url_s" ;

    private static final String XML_PHOTO = "photo" ;

    // Singleton, make sure only max. 1 instance exist, even for multi-thread

    private static volatile UrlManager instance = null;
    private UrlManager() {

    }

    public static UrlManager getInstance() {
        if (instance == null) {
            synchronized (UrlManager.class) {
                if (instance == null) {
                    instance = new UrlManager();
                }
            }
        }
        return instance;
    }

    public static String getItemUrl(String query, int page) {
        String url;
        if (query != null) {
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_SEARCH)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("text", query)
                    .appendQueryParameter("page", String.valueOf(page))
                    .build().toString();
        } else {
            url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_GETRECENT)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("page", String.valueOf(page))
                    .build().toString();
        }
        return url;
    }

    private static final String FLICKR_URL = "http://flickr.com/photo.gne?id=%s";
    private static final String METHOD_GETINFO = "flickr.photos.getInfo";

    public static String getFlickrUrl(String id) {
        return String.format(FLICKR_URL, id);
    }

    public static String getPhotoInfoUrl(String id) {
        return Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_GETINFO)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .appendQueryParameter("photo_id", id)
                .build().toString();
    }
}
