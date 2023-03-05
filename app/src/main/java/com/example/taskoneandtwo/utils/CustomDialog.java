package com.example.taskoneandtwo.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.taskoneandtwo.R;

public class CustomDialog {

    private static CustomDialog alertDialog;

    public static CustomDialog getInstance() {
        if (alertDialog == null) {
            alertDialog = new CustomDialog();
        }
        return alertDialog;
    }

    public void showDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_aleartdialog,null);
        builder.setView(view);

        TextView yes = view.findViewById(R.id.yes);
        TextView cancel = view.findViewById(R.id.cancel);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.CENTER);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                ((Activity) context).finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                CustomDialog.getInstance().showDialog(context);

            }
        });



    }

}
