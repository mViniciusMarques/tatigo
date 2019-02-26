package solutions.empire42.tatianego.core;

import android.content.Context;
import android.content.SharedPreferences;
import com.parse.ParseUser;
import solutions.empire42.tatianego.util.Constants;

public class UserSharedPreferenceManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;

    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "TatianeApp";

    public UserSharedPreferenceManager(Context context) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            prefsEditor = sharedPreferences.edit();
    }

    public void setUserOnSharedPreference(ParseUser user) {
        prefsEditor.putString(Constants.USERNAME, user.getUsername());
        prefsEditor.putString(Constants.USER_EMAIL, user.getEmail());
        prefsEditor.putString(Constants.USER_TOKEN, user.getSessionToken());
        prefsEditor.putString(Constants.USER_ID, user.getObjectId());
        prefsEditor.putString(Constants.USER_CREATEDAT, user.getCreatedAt().toString());
        prefsEditor.putString(Constants.USER_UPDATEAT, user.getUpdatedAt().toString());
        prefsEditor.commit();
    }

    public void cleanUpSharedBucked() {
        prefsEditor.clear();
    }

    public ParseUser getLoggedUser() {
        if (!sharedPreferences.getString(Constants.USER_ID, "").isEmpty()) {
            ParseUser user = new ParseUser();
            user.setEmail(sharedPreferences.getString(Constants.USER_EMAIL, null));
            user.setUsername(sharedPreferences.getString(Constants.USERNAME, null));
            user.setObjectId(sharedPreferences.getString(Constants.USER_ID, null));
            return user;
        }
        return null;
    }

}
