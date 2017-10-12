package ru.olegsvs.AtlanTeam_test.utils;

import android.util.Log;

import ru.olegsvs.AtlanTeam_test.App;

/**
 * Created by oleg.svs on 12.10.2017.
 */

public class Logging {
    public static void log(String str) {
        if(App.DEBUG) {
            Log.i("AtlanTeamDEBUG", "log: " + str);
        }
    }
}
