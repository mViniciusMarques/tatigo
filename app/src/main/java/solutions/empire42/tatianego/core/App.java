package solutions.empire42.tatianego.core;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.List;

public class App extends Application {

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this.getApplicationContext();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("VvKHXz1b1pyAzq7eKi53h9DvTWAPL1gAj9dSNYy0")
                .clientKey("0o4aBsoHVoBKbvHbXdUUCKdLuWuL2egL1RAqqC6R")
                .server("https://parseapi.back4app.com/")
                .build()
        );

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        if (installation == null) {
            installation.put("GCMSenderId", "190380593606");
            installation.saveInBackground();


            OneSignal.startInit(this)
                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                    .unsubscribeWhenNotificationsAreDisabled(true)
                    .init();
        }
    }

    public Context getContext () {
        return context;
    }

    public static void setBadge(Context context, int count) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    public static String getLauncherClassName(Context context) {

        PackageManager pm = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            String pkgName = resolveInfo.activityInfo.applicationInfo.packageName;
            if (pkgName.equalsIgnoreCase(context.getPackageName())) {
                String className = resolveInfo.activityInfo.name;
                return className;
            }
        }
        return null;
    }
}
