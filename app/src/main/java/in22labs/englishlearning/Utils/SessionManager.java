package in22labs.englishlearning.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.util.Log;

public class SessionManager {
	// LogCat tag
	private static String TAG = SessionManager.class.getSimpleName();

	// Shared Preferences
	SharedPreferences pref;

	Editor editor;
	Context _context;
	String nm, ca_name, ca_mail, ca_mob, testPrevScore;
	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Shared preferences file name
	private static final String PREF_NAME = "COMPANYPATTERNS";
	
	private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
	private static final String KEY_IS_NOTI_SET = "isNotiSet";
	private static final String KEY_IS_ACTION = "isAction";
	private static final String KEY_LOGIN_TYPE ="isType";//gmail or facebook
	private static final String KEY_IS_Q1 = "isQ1";
	private static final String KEY_IS_Q2 = "isQ2";
	private static final String KEY_IS_Q3 = "isQ3";
	private static final String KEY_IS_VERSION = "isVersion";
	private static final String KEY_PROFILE_URL = "isProfurl";
	private static final String KEY_FRAGPOS = "isFragpos";
	private static final String KEY_USERNAME ="isUsername";




	private static final String KEY_PREV_SCORE="prevscore";

	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void setLogin(boolean isLoggedIn) {

		editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);

		// commit changes
		editor.commit();

		Log.d(TAG, "User login session modified!");
	}

	public void SetAction(int i){
		editor.putInt(KEY_IS_ACTION, i);
		editor.commit();
	}

	public void setType(int i){
		editor.putInt(KEY_LOGIN_TYPE, i);
		editor.commit();
	}
	public void setProfurl(Uri url){
		editor.putString(KEY_PROFILE_URL, String.valueOf(url));
		editor.commit();
	}
	public void setProfname(String name){
		editor.putString(KEY_USERNAME, name);
		editor.commit();
	}

	public String isUsername(){return pref.getString(KEY_USERNAME, "no name");}
	public String isUrl(){return pref.getString(KEY_PROFILE_URL,"");}

	public int isType(){return pref.getInt(KEY_LOGIN_TYPE, 1);}

	public int isAction(){
		return pref.getInt(KEY_IS_ACTION, 1);
	}


	public void SetQ1(int i){
		editor.putInt(KEY_IS_Q1, i);
		editor.commit();
	}

	public void setVersion(int i){
		editor.putInt(KEY_IS_VERSION, i);
		editor.commit();
	}

	public void SetQ2(int i){
		editor.putInt(KEY_IS_Q2, i);
		editor.commit();
	}

	public void SetQ3(int i){
		editor.putInt(KEY_IS_Q3, i);
		editor.commit();
	}

	public int isVersion(){
		return  pref.getInt(KEY_IS_VERSION, 0);
	}
	public int isQ1(){
		return pref.getInt(KEY_IS_Q1, 0);
	}

	public int isQ2(){
		return pref.getInt(KEY_IS_Q2, 0);
	}

	public int isQ3(){
		return pref.getInt(KEY_IS_Q3, 0);
	}


	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_IS_LOGGED_IN, false);
	}
	public void setPrevScore(int PrevScore) {

		editor.putInt(KEY_PREV_SCORE, PrevScore);

		// commit changes
		editor.commit();

		Log.d(TAG, "User login session modified!");
	}

	public void setNoti(Boolean isSet){
		editor.putBoolean(KEY_IS_NOTI_SET, isSet);
		// commit changes
		editor.commit();
	}

	public Boolean isSetNoti(){
		return pref.getBoolean(KEY_IS_NOTI_SET, false);
	}
	public Integer isPrevScore(){
		return pref.getInt(KEY_PREV_SCORE, 0);
	}

	public void setFragpo(int i){
		editor.putInt(KEY_FRAGPOS, i);
		editor.commit();
	}

	public int isFragpos(){
		return pref.getInt(KEY_FRAGPOS, 0);
	}
}
