package com.navigine.naviginedemo;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.util.*;
import java.lang.*;
import java.io.*;
import java.util.*;

import com.navigine.naviginesdk.*;

public class SplashActivity extends Activity
{
  private static final String TAG = "Navigine.Demo";
  
  private TextView  mErrorLabel   = null;
  private TimerTask mTimerTask    = null;
  private Handler   mHandler      = new Handler();
  private Timer     mTimer        = new Timer();
  
  @Override public void onCreate(Bundle savedInstanceState)
  {
    Log.d(TAG, "SplashActivity created");
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_splash);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                         WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    mErrorLabel = (TextView)findViewById(R.id.splash__error_label);
    mErrorLabel.setVisibility(View.GONE);
    
    // Starting interface updates
    mTimerTask =
      new TimerTask()
      {
        @Override public void run()
        {
          mHandler.post(mRunnable);
        }
      };
    mTimer.schedule(mTimerTask, 500, 500);
  }

  @Override public void onBackPressed()
  {
    moveTaskToBack(true);
  }
  
  private int mLoader = 0;
  private void updateLoader()
  {
    if (DemoApp.Navigation == null)
      return;

    if (mLoader == 0)
    {
      mLoader = NavigineSDK.startLocationLoader(DemoApp.LOCATION_NAME, false);
    }
    else
    {
      int state = NavigineSDK.checkLoader(mLoader);
      if (state >= 0 && state <= 99)
      {
        Log.d(TAG, "Location loader: progress " + state + "%");
        return;
      }
      NavigineSDK.stopLoader(mLoader);
      mLoader = 0;

      if (state == 100)
        Log.d(TAG, "Location loader: FINISHED");
      else
        Log.d(TAG, "Location loader: FAILED with error=" + state);
    }
  }

  boolean mInitialized = false;
  boolean mFinished    = false;
  
  final Runnable mRunnable =
    new Runnable()
    {
      public void run()
      {
        if (mFinished)
          return;

        if (!mInitialized)
        {
          mInitialized = true;
          if (DemoApp.initialize(getApplicationContext()))
            updateLoader();
          else
            mErrorLabel.setVisibility(View.VISIBLE);
          return;
        }

        if (DemoApp.Navigation == null)
          return;

        if (mLoader > 0)
        {
          updateLoader();
          return;
        }
        else
        {
          final String archiveFile = NavigineSDK.getLocationFile(DemoApp.LOCATION_NAME);
          if ((new File(archiveFile)).exists())
          {
            // Starting main activity
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            mTimerTask.cancel();
            mTimerTask = null;
            mFinished = true;
          }
        }
      }
    };
}