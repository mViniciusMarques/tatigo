package solutions.empire42.tatianego.core;

import android.app.Application;
import android.content.Context;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.onesignal.OneSignal;
import com.parse.Parse;

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


        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

    }

    public Context getContext() {
        return context;
    }
}
