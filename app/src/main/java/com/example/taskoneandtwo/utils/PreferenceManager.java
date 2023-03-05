package com.example.taskoneandtwo.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceManager {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "EarningPre";
    private static PreferenceManager preferenceManager;
    private static Context mContext;
    private static final String USER_IS_LOGIN = "IsLoggedIn";

    public static PreferenceManager getPreferenceManager(Context context) {

        if (preferenceManager == null) {
            mContext = context;
            preferenceManager = new PreferenceManager();
        }
        return preferenceManager;
    }

/*    public void setUser(Data userData) {

        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editor.putString("token", userData.getToken());
        editor.putInt("id", userData.getId());
        editor.putString("name", userData.getName());
        editor.putString("email", userData.getEmail());
        editor.putString("balance", userData.getBalance());
        editor.putString("referral_id", userData.getReferralId());
        editor.putString("image", userData.getImage());
        editor.commit();

    }

    public Data getUser() {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return new Data(
                sharedPreferences.getString("token", null),
                sharedPreferences.getInt("id", 0),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("balance", null),
                sharedPreferences.getString("referral_id", null),
                sharedPreferences.getString("image", null));
    }

    public void setLoginData(LoginData login_Data) {

        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editor.putString("token", login_Data.getToken());
        editor.putInt("id", login_Data.getId());
        editor.putString("referral_id", login_Data.getReferralId());
        editor.putString("name", login_Data.getName());
        editor.putString("email", login_Data.getEmail());
        editor.putInt("balance", login_Data.getBalance());
        editor.putInt("balance_usd", login_Data.getBalanceUsd());
        editor.putString("image", login_Data.getImage());
        editor.putBoolean("logged", true);
        editor.commit();

    }

    public LoginData getLoginData() {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return new LoginData(
                sharedPreferences.getString("token", null),
                sharedPreferences.getInt("id", 0),
                sharedPreferences.getString("referral_id", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getInt("balance", 0),
                sharedPreferences.getInt("balance_usd", 0),
                sharedPreferences.getString("image", null));
    }

    public boolean isLoggedIn() {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);
    }

    public void logoutUser() {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ;
    }*/


}
