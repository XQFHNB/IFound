package com.example.anif;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * @author XQF
 * @created 2017/4/13
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,"hPdOXRhaLqR2s4CeRuw88KuY-gzGzoHsz","wY0TIpTaasURni1cCstMAAIV");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
