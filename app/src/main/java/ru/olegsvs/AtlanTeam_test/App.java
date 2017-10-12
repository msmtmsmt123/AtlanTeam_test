package ru.olegsvs.AtlanTeam_test;

import android.app.Application;

import ru.olegsvs.AtlanTeam_test.api.TypicodeApi;
import ru.olegsvs.AtlanTeam_test.utils.Logging;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oleg.svs on 11.10.2017.
 */

public class App extends Application {

    public static boolean DEBUG = true;
    private static TypicodeApi typicodeApi;

    @Override
    public void onCreate() {
        super.onCreate();

        Logging.log("app created!");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        typicodeApi = retrofit.create(TypicodeApi.class);
    }

    public static TypicodeApi getApi() {
        return typicodeApi;
    }
}
