package com.example.taskoneandtwo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetwork {


    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            NetworkInfo[] info = cm.getAllNetworkInfo();

            if (info != null) {

                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;

                    }

                }

            }

        }
        return false;

//        if (wifi != null && wifi.isConnectedOrConnecting() || mobile != null && mobile.isConnectedOrConnecting()) {
//            return true;
//        } else {
//            return false;
//        }

    }

}
