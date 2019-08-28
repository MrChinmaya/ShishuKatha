package shishukatha.com.shishukatha_final.AppConstants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	
	
	// Editor for Shared preferences
	Editor editor;
	
	
	// Context
	Context _context;
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// Sharedpref file name
	private static final String PREF_NAME = "MoneyPlant";
	
	
	// User name (make variable public to access from outside)
	public static final String KEY_LOGIN_TYPE = "logintype";
	public static final String KEY_CUST_ID = "custid";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String USER_RR_NO = "user_rr_no";

	
	
	
	
	// Email address (make variable public to access from outside)
	public static final String KEY_MESSAGE = "message";
	
	public static int FLAG = 0;
	
	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		
		editor = pref.edit();
		
	}
	
	/**
	 * Create login session
	 * */
	@SuppressLint("NewApi")
	
	public void saveUser(String mRRNo){

		// Storing sno in pref
		editor.putString(USER_RR_NO, mRRNo);
		// commit changes
		editor.commit();
	}	
	

	
	public HashMap<String, String> getUser(){
		HashMap<String, String> user = new HashMap<String, String>();
	
		user.put(KEY_LOGIN_TYPE, pref.getString(KEY_LOGIN_TYPE, null));
		user.put(KEY_CUST_ID, pref.getString(KEY_CUST_ID, null));
		user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
		user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
		return user;
	}



	public void deleteUser()
	{
		// Clearing all data from Shared Preferences
		editor.remove(KEY_LOGIN_TYPE);
		editor.remove(KEY_CUST_ID);
		editor.remove(KEY_USERNAME);
		editor.remove(KEY_PASSWORD);
		editor.commit();
	}



	
	
	
}
