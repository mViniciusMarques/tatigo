package solutions.empire42.tatianego.helper;

import android.util.Log;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;
import org.json.JSONObject;

public class CustomNotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {

    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;

        if( data != null ){
            Log.i("NOTR", "notificationReceveid: "+data);
        }
    }
}
