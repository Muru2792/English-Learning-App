package in22labs.englishlearning.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Murugan on 5/24/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase dbase;
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "English.db";

    Context c;
    //Table column
    public static final String TAB_HELPQUESTION = "helquestion";
    public static final String TAB_VIDEOLIST = "videolist";
    public static final String TAB_USERPROFILE = "userprof";

    //Key params
    public static final String SNO = "sno";
    public static final String SUMMARY = "summary";
    public static final String QUESTION = "question";
    public static final String CH1 = "ch1";
    public static final String CH2 ="ch2";
    public static final String CH3 ="ch3";
    public static final String CH4="ch4";
    public static final String ANSWER="answer";
    public static final String EXPLANATION="explantation";
    public static final String ANSWERED="answered";

    public static final String VID = "vid";
    public static final String VTITLE = "vtitle";
    public static final String VCATE = "vcate";
    public static final String VDIFF = "vdiff";
    public static final String VDATE = "vdate";
    public static final String VCOIN = "vcoin";
    public static final String VURL = "vurl";

    public static final String PID = "profid";
    public static final String PNAME = "profname";
    public static final String PIMAGEURL = "profimageurl";
    public static final String PUSERNAME = "profusername";
    public static final String PUSERPASS = "profuserpass";





    //Table Create


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_HELP = "CREATE TABLE IF NOT EXISTS " + TAB_HELPQUESTION  + "("
                + SNO + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + SUMMARY + " TEXT, "
                + QUESTION + " TEXT, "
                + CH1 + " TEXT, "
                + CH2 + " TEXT, "
                + CH3 + " TEXT, "
                + CH4 + " TEXT, "
                + ANSWER + " TEXT, "
                + EXPLANATION + " TEXT, "
                + ANSWERED + " INTEGER )";

        String CREATE_TABLE_VIDEO = "CREATE TABLE IF NOT EXISTS " + TAB_VIDEOLIST  + "("
                + SNO + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + VID + " TEXT, "
                + VTITLE + " TEXT, "
                + VCATE + " TEXT, "
                + VDIFF + " TEXT, "
                + VDATE + " TEXT, "
                + VCOIN + " TEXT, "
                + VURL + " TEXT )";

        String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "+ TAB_USERPROFILE + "("
                + SNO + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + PID + " TEXT, "
                + PNAME + " TEXT, "
                + PUSERNAME + " TEXT, "
                + PUSERPASS + " TEXT, "
                + PIMAGEURL + " TEXT ) ";

        db.execSQL(CREATE_TABLE_HELP);
        db.execSQL(CREATE_TABLE_VIDEO);
        db.execSQL(CREATE_TABLE_USER);
        Toast.makeText(c, "Database Created ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void InsertQuestion(String sum, String quest, String ch1, String ch2, String ch3, String ch4, String ans, String exp, int answerd){
        SQLiteDatabase dbase=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUMMARY, sum);
        values.put(QUESTION, quest);
        values.put(CH1, ch1);
        values.put(CH2, ch2);
        values.put(CH3, ch3);
        values.put(CH4, ch4);
        values.put(ANSWER, ans);
        values.put(EXPLANATION, exp);
        values.put(ANSWERED, answerd);
        dbase.insert(TAB_HELPQUESTION, null, values);
    }

    public void InsertVideo(String vid, String vtitle, String vcate, String vdiff, String vdate, String vcoin, String vurl){
        SQLiteDatabase dbase=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(VID, vid);
        values.put(VTITLE, vtitle);
        values.put(VCATE, vcate);
        values.put(VDIFF, vdiff);
        values.put(VDATE, vdate);
        values.put(VCOIN, vcoin);
        values.put(VURL, vurl);
        dbase.insert(TAB_VIDEOLIST, null, values);
    }

    public void InsertUser(String pPID, String pPNAME, String pPUSERNAME, String pPUSERPASS, String pPIMAGEURL){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PID, pPID);
        values.put(PNAME, pPNAME);
        values.put(PUSERNAME, pPUSERNAME);
        values.put(PUSERPASS, pPUSERPASS);
        values.put(PIMAGEURL, pPIMAGEURL);
        db.insert(TAB_USERPROFILE,null,values);
    }
    public void updateQuestion(String sum, int avail){
        SQLiteDatabase dbase=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ANSWERED, avail);
        dbase.update(TAB_HELPQUESTION, values,SUMMARY + " = ?", new String[] {sum});
    }

    public ArrayList<String> getSumary(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT summary FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getQuestion(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT question FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getCh1(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT ch1 FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getCh2(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT ch2 FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getCh3(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT ch3 FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getCh4(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT ch4 FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getAnswer(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT answer FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getExplanation(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT explantation FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getAnswered(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT answered FROM helquestion WHERE answered = 0";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getAnsSumary(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT summary FROM helquestion WHERE answered = 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

    public ArrayList<String> getAnsExplanation(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Username = new ArrayList<String>();
        String query = "SELECT DISTINCT explantation FROM helquestion WHERE answered = 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Username.add(cursor.getString(0));// limit1
//                Toast.makeText(c, "User "+cursor.getString(0), Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        return Username;
    }

}
