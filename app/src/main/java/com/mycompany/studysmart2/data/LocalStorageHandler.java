package com.mycompany.studysmart2.data;
import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;

/**
 * LocalStoreHandler
 * Created by anders on 22-10-2015.
 *
 * This class is a singleton which handles the PreferenceManager aka. all the data stored
 * locally on the device for offline use and the active student information.
 *
 * All other classes should use this class when accessing or storing data for local storage.
 */
public class LocalStorageHandler {

    final private String USER = "student";
    final private String EMPTY = "NULL";

    PreferenceManager prefs;
    boolean isEmpty;

    private static LocalStorageHandler ourInstance = new LocalStorageHandler();

    // TODO: gør til rigtig singleton m. hjælp fra AndroidElementer
    public static LocalStorageHandler getInstance() {
        return ourInstance;
    }

    private LocalStorageHandler() {
    }
    public Boolean isEmpty(Context c) {
        if(prefs.getDefaultSharedPreferences(c).getString(USER, EMPTY) == EMPTY){
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return isEmpty;
    }

    public boolean loggedUser(String user, Context c){
        prefs.getDefaultSharedPreferences(c).edit().putString(USER, user).commit();
        return true;
    }
}
