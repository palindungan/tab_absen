package com.example.tababsenapp.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public Context context;
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
    }
}
