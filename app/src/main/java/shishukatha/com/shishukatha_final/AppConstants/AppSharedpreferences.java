package shishukatha.com.shishukatha_final.AppConstants;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedpreferences {

    /** Current state of the application/object **/
    private Context mContext = null;

    /**
     * Saving persistent application data object which stores private primitive
     * data in key-value pairs
     **/
    private SharedPreferences mSharedPreference = null;

    /** Interface object used for modifying values in a SharedPreferences object **/
    private SharedPreferences.Editor mEditor = null;

    /**
     * Initializes Activity Context, SharedPreference instance and Editor
     * instance
     *
     * @param context application context object
     */
    public AppSharedpreferences(final Context context) {
        mContext = context;

        // Initializing the Shared preference object
        mSharedPreference = mContext.getSharedPreferences(
                AppConstants.APP_SHARED_PREFERENCE, Context.MODE_PRIVATE);

        // Initializing the editor object
        mEditor = mSharedPreference.edit();
    }

    /**
     * Clears all data from preferences
     */
    public void clearAll() {
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * sets the data in the Shared preference based on the key value
     *
     * @param key used to set the respective value
     * @param value : string
     */
    public void setData(final String key, final String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * gets the Value stored in the Shared preference.
     *
     * @param key used to get the respective value
     * @return String value
     */
    public String getData(final String key) {
        return mSharedPreference.getString(key, null);
    }

    public void setBooleanData(final String key, final boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public Boolean getBooleanData(final String key) {
        return mSharedPreference.getBoolean(key, false);
    }

    public void setIntData(final String key, final int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public int getIntData(final String key) {
        return mSharedPreference.getInt(key, 0);
    }
}
