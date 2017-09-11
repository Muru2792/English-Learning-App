package in22labs.englishlearning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;



import com.pushbots.push.Pushbots;
//import com.pushbots.push.Pushbots;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import in22labs.englishlearning.Utils.ConnectionDetector;
import in22labs.englishlearning.Utils.DatabaseHelper;
import in22labs.englishlearning.Utils.SessionManager;
import in22labs.englishlearning.Utils.customHandler;
//import in22labs.englishlearning.broadcast_receivers.NotificationEventReceiver;



//import com.twitter.sdk.android.Twitter;
//import com.twitter.sdk.android.core.TwitterAuthConfig;
//import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {
   // UserFunctions userFunctions;
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
//    private static final String TWITTER_KEY = "4yhPFlfNGf1edrffbYZf6znOe";
//    private static final String TWITTER_SECRET = "a7QOp8h3GpDk3BzoAYa4KSHdNKm80QH05QSJKeWqfDLs5aOT5u";

    private static final String STORAGE_IN_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String STORAGE_OUT_PERMISSION = "android.permission.READ_EXTERNAL_STORAGE";
    private static final String NETWORK_PERMISSION = "android.permission.ACCESS_NETWORK_STATE";
//    private static final String WIFI_PERMISSION = "android.permission.ACCESS_WIFI_STATE";
//    private static final String RDCONTACT_PERMISSION = "android.permission.READ_CONTACTS";
//    private static final String GETCONTACT_PERMISSION = "android.permission.GET_ACCOUNTS";
//    private static final String RDPHONESTATE_PERMISSION = "android.permission.READ_PHONE_STATE";
//
    private static final String INTERNET = "android.permission.INTERNET";
    //private DatabaseReference root1 ;
    protected int _splashTime = 2000;
    private static String TAG = "PermissionDemo";
    private static final int REQUEST_WRITE_STORAGE = 112;
    ConnectionDetector cd;
    //    DatabaseHelper db;
//    String date2, time, id="1", title, content;
//    String date1, time1, id1, title1, content1;

    ArrayList<String> todayWord, todayContent, todayQuestion, txtCh1, txtCh2, txtCh3, txtCh4, txtAnswer;
    SessionManager session;
    int[] dr ={
            R.drawable.help3,
            R.drawable.help1,
            R.drawable.help2,
    };
    int it;
    List<Integer> it1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_splash);
        cd=new ConnectionDetector(SplashActivity.this);
//        todayWord=new ArrayList<>();
//        todayContent=new ArrayList<>();
//
//        todayWord.add("Today new Word - Outperform");
//        todayWord.add("Today new Word - Prevail");
//        todayWord.add("Today new Word - Emulate");
//
//        todayContent.add("The TN Class 10 rsults were announced yesterday and following the trend, girls have outperformed boys agains by scoring 96.2 percent while boys scored 92.5 per cent.");
//        todayContent.add("Mumbai Indians once again showed off their death bowling muscle as they eked out a 1 run victoryto prevail over Rising Pune Supergiant in final of the IPL.");
//        todayContent.add("TN is rolling out new syllabi for all classes, starting with higher secondary and saidthey will emulate the syllabus of CBSE.");
//        root1 = FirebaseDatabase.getInstance().getReference().child("UpdateCheck");

//        title="APSCC-FC - Unemployed Youth Login";
//        content="An Initiative of APSC corporation,  Youthway, is a holistic portal, which  empower the youth towards next generation learning, and position them in the competitive global skill map.";

        session=new SessionManager(getApplicationContext());

        Pushbots.sharedInstance().registerForRemoteNotifications();
        Pushbots.sharedInstance().setCustomHandler(customHandler.class);
        for (int i = 0; i < 3; i++) {
            it1.add(i);
        }
//        db = new DatabaseHelper(SplashActivity.this);
//        int pos = session.isAction();
//
//        Toast.makeText(SplashActivity.this, " "+pos, Toast.LENGTH_SHORT).show();
//
//        if(pos == 2){
//           Intent i = new Intent(SplashActivity.this, Helpline.class);
//           startActivity(i);
//        }else {
//            start();
//        }

//        Bundle extras = getIntent().getExtras();

//        long date = System.currentTimeMillis();
//        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmssa");
//        id1 = "PROFILE_ID" + sdf.format(date);
//        date1=getDate();
//        time1=getTime();
//        if(extras!= null){
//            title1=extras.getString("Title");
//            content1=extras.getString("Message");
//            if(title1 != null) {
//                db.setNotification(id1, title1, content1, date1, time1);
//                Toast.makeText(SplashActivity.this, "Db inserted", Toast.LENGTH_SHORT).show();
//            }
//        }

        int permission1 = checkCallingOrSelfPermission(STORAGE_OUT_PERMISSION);
        List<String> permissions = new ArrayList<>();
//
        if (permission1 == PackageManager.PERMISSION_DENIED) {
            permissions.add(STORAGE_OUT_PERMISSION);
            permissions.add(STORAGE_IN_PERMISSION);
            permissions.add(NETWORK_PERMISSION);
//            permissions.add(WIFI_PERMISSION);
//            permissions.add(RDCONTACT_PERMISSION);
//            permissions.add(GETCONTACT_PERMISSION);
//            permissions.add(RDPHONESTATE_PERMISSION);
            permissions.add(INTERNET);
        }

        if (permissions.isEmpty()) {
           // db=new DatabaseHelper(SplashActivity.this);
//            date2=getDate();
//            time=getTime();
//            db.setNotification(id, title, content, date2, time);
//            start();
            if(cd.isConnectingToInternet()){

                //getDeviceVersion();
                start();

            }else{
                start();
               Toast.makeText(SplashActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        } else {
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), 11);
      }


//        int i=0;
//        session.setNoti(false);
//        if (!session.isSetNoti()) {
//            ShowNotification(todayWord.get(i), todayContent.get(i),i);
//            session.setNoti(true);
//
//        }
    }
//    private void getDeviceVersion() {
//
//
//        root1 = FirebaseDatabase.getInstance().getReference().child("UpdateCheck");
//        root1.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//                Iterator i = dataSnapshot.getChildren().iterator();
//                String Calue = (String) ((DataSnapshot) i.next()).getValue();
////                    Toast.makeText(SplashActivity.this,Calue,Toast.LENGTH_SHORT).show();
//
//                Gotoapp(Calue);
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                Iterator i = dataSnapshot.getChildren().iterator();
//                String Calue = (String) ((DataSnapshot) i.next()).getValue();
////                    Toast.makeText(SplashActivity.this,Calue,Toast.LENGTH_SHORT).show();
//
//                Gotoapp(Calue);
//                //showNotification("APSCCFC", "Update Available!!");
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//
//
//    }

//    private void Gotoapp(String calue) {
//
//        int versionCode = session.isVersion();
//      //  String versionName = BuildConfig.VERSION_NAME;
////        Toast.makeText(SplashActivity.this, String.valueOf(versionCode),Toast.LENGTH_SHORT).show();
//
//        if (String.valueOf(versionCode).equals(calue)) {
//           start();
//        } else {
//            Collections.shuffle(it1);
//            it=it1.get(0);
//            //ShowNotification(todayWord.get(it), todayContent.get(it),it);
//            start();
//            session.setVersion(Integer.parseInt(calue));
//        }
//
//    }

   private void SdcardOverwrite() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Permission to access the SD-CARD is required for this app")
                    .setTitle("Permission required");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    Log.i(TAG, "Clicked");
                    makeRequest();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            makeRequest();
        }


    }
//
    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show();

                    Log.i(TAG, "Permission has been denied by user");

                } else {
//					Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Permission has been granted by user");
                   // db=new DatabaseHelper(SplashActivity.this);
//                    db.InsertUserDetails(1,"16SCC08UG083", "16SCC08UG083");
//                    db.InsertUserDetails(2,"test", "test");
                    start();

                }

            }
        }
    }
    public void start(){
        new Handler().postDelayed(new Runnable() {
//
            @Override
            public void run() {
                if(session.isLoggedIn()){
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    SplashActivity.this.startActivity(i);

                    SplashActivity.this.finish();
                }else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(i);

                    SplashActivity.this.finish();
                }
                // overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            }
        }, _splashTime);
    }



    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you really want to exit?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        SdcardOverwrite();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    // To prevent crash on resuming activity  : interaction with fragments allowed only after Fragments Resumed or in OnCreate
    // http://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        // handleIntent();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        //userFunctions = new UserFunctions();
//       // if(userFunctions.isUserLoggedIn(getApplicationContext())){
//            Intent intent = getIntent();
//            String pos = intent.getStringExtra("Helpline");
//            if(pos !=null){
//                Intent i = new Intent(SplashActivity.this, Helpline.class);
//                startActivity(i);
//            }else {
//                start();
//            }
//    }

//
//
//    private void pushNotification(String token) {
//        JSONObject jPayload = new JSONObject();
//        JSONObject jNotification = new JSONObject();
//        JSONObject jData = new JSONObject();
//        try {
//            jNotification.put("title", "Google I/O 2016");
//            jNotification.put("text", "Firebase Cloud Messaging (App)");
//            jNotification.put("sound", "default");
//            jNotification.put("badge", "1");
//            jNotification.put("click_action", "OPEN_ACTIVITY_1");
//
//            jData.put("picture_url", "http://opsbug.com/static/google-io.jpg");
//
//            jPayload.put("to", token);
//            //jPayload.put("to", "/topics/news");
//            //jPayload.put("condition", "'logined' in topics || 'news' in topics");
//            //jPayload.put("registration_ids", jData);
//            jPayload.put("priority", "high");
//            jPayload.put("notification", jNotification);
//            jPayload.put("data", jData);
//
//            URL url = new URL("https://fcm.googleapis.com/fcm/send");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
////            conn.setRequestProperty("Authorization", AUTH_KEY);
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setDoOutput(true);
//
//            // Send FCM message content.
//            OutputStream outputStream = conn.getOutputStream();
//            outputStream.write(jPayload.toString().getBytes());
//
//            // Read FCM response.
//            InputStream inputStream = conn.getInputStream();
//            final String resp = convertStreamToString(inputStream);
//
//            Handler h = new Handler(Looper.getMainLooper());
//            h.post(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e("Response", resp);
//                    //txtStatus.setText(resp);
//                }
//            });
//        } catch (JSONException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String convertStreamToString(InputStream is) {
//        Scanner s = new Scanner(is).useDelimiter("\\A");
//        return s.hasNext() ? s.next().replace(",", ",\n") : "";
//    }
}
