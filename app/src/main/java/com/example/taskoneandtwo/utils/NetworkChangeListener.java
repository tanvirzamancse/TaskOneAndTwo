package com.example.taskoneandtwo.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.airbnb.lottie.LottieAnimationView;
import com.example.taskoneandtwo.R;


public class NetworkChangeListener extends BroadcastReceiver {

    private static NetworkChangeListener listener;

    public static NetworkChangeListener getListener() {
        if (listener == null) {
            listener = new NetworkChangeListener();
        }
        return listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (!CheckNetwork.isConnected(context)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.popup_internet, null);
            builder.setView(view);

            LottieAnimationView loading = view.findViewById(R.id.loading);
            AppCompatButton button = view.findViewById(R.id.retry);
            loading.setVisibility(View.VISIBLE);

            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
            dialog.getWindow().setGravity(Gravity.CENTER);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                    context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    ((Activity) context).finish();

                }
            });

        } else {
//            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
        }
    }
}
