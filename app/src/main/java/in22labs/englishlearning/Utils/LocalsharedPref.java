package in22labs.englishlearning.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.ArraySet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Murugan on 5/4/2017.
 */

public class LocalsharedPref {
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;
    private static final String  KEY_FIRSTCLICK = "firstclick";
    // first click means set TRUE & second click means set FALSE
    Set KEY_FRISTCLICKSET;
    int PRIVATE_MODE = 0;
    Set temp;

    // Shared preferences file name
    private static final String PREF_NAME = "ENGLISHAPP";

    public LocalsharedPref(Context mContext){
        this._context = mContext;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            KEY_FRISTCLICKSET = new ArraySet<>();
        }
    }

    public void SetFirstclick(int position, boolean isFirstClick){

        editor.putStringSet(KEY_FIRSTCLICK, KEY_FRISTCLICKSET);
        editor.commit();
    }


    public void SetFirstClick(int position, boolean isFirstClick){
        String id = "card"+position;
        editor.putBoolean(id, isFirstClick);
    }
    public boolean isFirstClick(int position){
        String id ="card"+position;
        return pref.getBoolean(id, false);
    }

}
