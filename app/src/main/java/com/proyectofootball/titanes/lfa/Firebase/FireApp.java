package com.proyectofootball.titanes.lfa.Firebase;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Tonatiuh on 10/09/2016.
 */
public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /* previus version */
        //Firebase.setAndroidContext(this);

        /* newest version */
        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
