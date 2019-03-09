package solutions.empire42.tatianego.helper;

import android.util.Log;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import org.json.JSONObject;

public class CustomNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        JSONObject data = result.notification.payload.additionalData;

        if( data != null ){
            Log.i("NOTO", "notificationOpened: "+data);
        }
    }
}
