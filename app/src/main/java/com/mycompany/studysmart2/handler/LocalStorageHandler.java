package com.mycompany.studysmart2.handler;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.mycompany.studysmart2.data.Logic;
import com.mycompany.studysmart2.data.StudentChoice;
import com.mycompany.studysmart2.data.University;

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

    private static final String PREF_NAME = "app storage";
    private static final String UNIVERSITY_ID = "uni_id";
    private static final int EMPTY_INT = -10;
    final private String USER = "student";
    final private String EMPTY = "NULL";
    final private String UNIVERSITY = "uni";

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

    public void storeData(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME,0);
        SharedPreferences.Editor editor = settings.edit();

        editor.clear();
        editor.putString(UNIVERSITY, Logic.instance.student.university.name);
        editor.putInt(UNIVERSITY_ID, Logic.instance.student.university.id);
        editor.commit();
    }

    public void loadData(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
        String university = settings.getString(UNIVERSITY, EMPTY);
        int universityId = settings.getInt(UNIVERSITY_ID, EMPTY_INT);

        if (university != EMPTY && universityId != EMPTY_INT) {
            Logic.instance.student.university = new University(universityId, university);
        }
    }
}
