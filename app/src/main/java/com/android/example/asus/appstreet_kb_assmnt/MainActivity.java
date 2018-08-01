package com.android.example.asus.appstreet_kb_assmnt;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int KEEP_TIME = 2000;
   // private Handler handler;
    ProgressBar bar;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "----------onCreate----------");

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progress);
    }

        Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                bar.incrementProgressBy(5);
            }
        };


    public void onStart()
    {
        super.onStart();
        bar.setProgress(0);

        Thread background = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    for (int i = 0; i < 20 && isRunning; i++)
                    {
                        Thread.sleep(1000);
                        handler.sendMessage(handler.obtainMessage());
                    }
                }
                catch (Throwable t)
                {
                    // just end the background thread
                }
            }

        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,  GalleryActivity.class);

                Log.d(TAG, "----------startActivityIntent----------");
                startActivity(intent);
            }
        }, KEEP_TIME);

//        isRunning = true;
//        background.start();
    }

    public void onStop() {
        super.onStop();
        isRunning = false;

    }


}


