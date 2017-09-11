package in22labs.englishlearning.Utils;

import android.app.Application;


import com.pushbots.push.Pushbots;

import in22labs.englishlearning.VideoHome.VideoList;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Pushbots Library
        Pushbots.sharedInstance().init(this);

    }
}