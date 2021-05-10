package com.example.mylocation;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private static SharedPreferences prf;

    private MySharedPreference(){
    }

    public static SharedPreferences getInstance(Context context){

        if(prf == null)
            prf =context.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        return prf;
    }//End method

    public static void clearData(Context context) {
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.clear();
        editor.commit();
    }//End method

    public static void clearValue(Context context, String key){
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.remove(key);
        editor.commit();
    }//End method

    public static void putString (Context context, String key, String value){
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.putString(key, value);
        editor.commit();
    }//End method

    public static void putInt(Context context, String key, int value){
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }//End method

    public static void putBoolean(Context context, String key, Boolean value){
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }//End method


    public static String getString(Context context, String key, String valueDefault){
            return getInstance(context).getString(key, valueDefault);
    }//End method

    public static int getInt(Context context, String key, int valueDefault){
        return getInstance(context).getInt(key, valueDefault);
    }//End method

    public static Boolean getBoolean (Context context, String key, Boolean valueDefault){
        return getInstance(context).getBoolean(key, valueDefault);
    }//End method

    public static void putLong(Context context, String key, long value){
        SharedPreferences.Editor editor = getInstance(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }//End method

    public static Long getLong (Context context, String key, Long valueDefault){
        return getInstance(context).getLong(key, valueDefault);
    }//End method

}//End Class
