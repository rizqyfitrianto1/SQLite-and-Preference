package com.example.trainer.androidlatihanlima.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsHelper {

    SharedPreferences prefs;
    Context ctx;

    private static PrefsHelper instance;
    public static PrefsHelper sharedInstance(Context context){
        if (instance == null){
            instance = new PrefsHelper(context);

        }
        return instance;
    }
    private PrefsHelper(Context ctx){
        this.ctx = ctx;
        this.prefs = ctx.getSharedPreferences("SAMPLE", Context.MODE_PRIVATE);
    }

    public void setNamaDefault(String nama){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("NAMADEFAULT", nama);
        editor.apply();
    }

    public String getNamaDefault(){
        return  prefs.getString("NAMADEFAULT", "Reezky Illma");
    }

    public void  cekLogin(boolean x){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("CEKLOGIN", x);
        editor.apply();
    }

    public boolean  isUserLogin(){
        return prefs.getBoolean("CEKLOGIN", false);
    }

}
