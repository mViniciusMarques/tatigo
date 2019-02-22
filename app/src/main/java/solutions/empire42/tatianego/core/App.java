package solutions.empire42.tatianego.core;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.onesignal.OneSignal;
import com.parse.*;

public class App extends Application {

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nTUYmeg9ieo03gG9xnXBCYSUVDGSZFZUNYKRvk6w")
                // if desired
                .clientKey("pd25QW52uE8wUTDnADXlCpC22xDQGxzXNlOdE2KD")
                .server("https://parseapi.back4app.com/")
                .build()
        );

       // ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "190380593606");
        ParsePush.subscribeInBackground("Bolo de Cenoura");
        installation.saveInBackground();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        ParsePush.subscribeInBackground("Bolo de Cenoura", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

    }

    public Context getContext() {
        return context;
    }
}
