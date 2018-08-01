package com.android.example.asus.appstreet_kb_assmnt;


import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;



public class PhotoFragment extends Fragment {
    // tag for logcat
    public static final String TAG = PhotoFragment.class.getSimpleName();

    private ProgressBar mProgressBar;
    private TextView mDescText;
    private ImageView mPhoto;

    private GalleryItem mItem;
    private RequestQueue mRq;


    private boolean mLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        mItem = (GalleryItem) getActivity().getIntent().getSerializableExtra("item");


        mRq = Volley.newRequestQueue(getActivity());

        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

      //  mDescText = (TextView) view.findViewById(R.id.desc_text);

        mPhoto = (ImageView) view.findViewById(R.id.photo);
        Glide.with(this).load(mItem.getUrl()).thumbnail(0.5f).into(mPhoto);

        mProgressBar.setVisibility(View.GONE);
        // download original single photo
//        LinearLayout downloadView = (LinearLayout) view.findViewById(R.id.download);
//        downloadView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                downloadPhoto();
//                Toast.makeText(getActivity(), "Start downloading", Toast.LENGTH_LONG).show();
//            }
//        });

        // open url link for Flickr official app
//        LinearLayout openView = (LinearLayout) view.findViewById(R.id.open);
//        openView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openApp();
//            }
//        });
//
//        // load original photo
//        startLoading();
        return view;
 }


    // cancel downloading request when fragment is stopped
    private void stopLoading() {
        if (mRq != null) {
            mRq.cancelAll(TAG);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        stopLoading();
    }
}

