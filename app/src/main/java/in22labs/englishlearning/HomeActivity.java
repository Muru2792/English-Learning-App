package in22labs.englishlearning;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.concurrent.TimeUnit;

import in22labs.englishlearning.HomeTab.EnglishHome;
import in22labs.englishlearning.ProfileManagement.CityrankFragment;
import in22labs.englishlearning.ProfileManagement.MyrankData;
import in22labs.englishlearning.Utils.SessionManager;

public class HomeActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    int count;
    CountDownTimer timer;
    GoogleApiClient mGoogleApiClient;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        session=new SessionManager(HomeActivity.this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new EnglishHome()).commit();
        //createActivity();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == R.id.nav_item_home) {
                    FragmentTransaction fT = mFragmentManager.beginTransaction();
                    fT.replace(R.id.containerView, new EnglishHome()).commit();
                }
//
//                if (menuItem.getItemId() == R.id.nav_item_helpline) {
//                    Intent i = new Intent(HomeActivity.this, Helpline.class);
//                    i.putExtra("Index", "4");
//                    startActivity(i);
//
//                }
//
                if (menuItem.getItemId() == R.id.nav_item_logout) {
                    int loginTYPE=session.isType();
                    if(loginTYPE==1){
                        session.setLogin(false);
                    }else if (loginTYPE==2){
                        session.setLogin(false);
                        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                new ResultCallback<Status>() {
                                    @Override
                                    public void onResult(Status status) {
                                        // [START_EXCLUDE]

                                        // [END_EXCLUDE]
                                    }
                                });
                    }else if (loginTYPE==3){
                        session.setLogin(false);
                        disconnectFromFacebook();
                    }
                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(i);
                    session.setFragpo(0);


                }
//
                if (menuItem.getItemId() == R.id.nav_item_myreportcard) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new MyrankData()).addToBackStack(null).commit();
                }
//
                if (menuItem.getItemId() == R.id.nav_item_publicprof) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new CityrankFragment()).addToBackStack(null).commit();
                }
//
//                if (menuItem.getItemId() == R.id.nav_item_other) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new OtherFragment()).addToBackStack(null).commit();
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_district) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new DistrictFragment()).addToBackStack(null).commit();
//                }
                if (menuItem.getItemId() == R.id.nav_item_helpline) {
                   Intent i = new Intent(HomeActivity.this, Helpline.class);
                    i.putExtra("Index","4");
                    startActivity(i);
                }
//
//                if (menuItem.getItemId() == R.id.nav_item_ranking) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new CateRatingCollege()).addToBackStack(null).commit();
//                }
//                if (menuItem.getItemId() == R.id.nav_item_rating) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new CateRank()).addToBackStack(null).commit();
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_live) {
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView, new LiveRegister()).addToBackStack(null).commit();
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_notification) {
//                    Intent i = new Intent(MainActivity.this, NotificationCenter.class);
//                    i.putExtra("a4ncontent", "not");
//                    i.putExtra("message", "not");
//                    startActivity(i);
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_share) {
//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("text/plain");
//                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Really Amazing Application");
//                    shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.in22labs.counsel&hl=en");
//                    startActivity(Intent.createChooser(shareIntent, "Share Via"));
//                }
//                if (menuItem.getItemId() == R.id.nav_item_terms) {
//                    String str = "http://tnea.a4n.in/Legal/terms_and_conditions";
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
//                }
//                if (menuItem.getItemId() == R.id.nav_item_rate) {
//                    String str = "https://play.google.com/store/apps/details?id=com.in22labs.counsel&hl=en";
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
//                }
//
//                if (menuItem.getItemId() == R.id.nav_item_policy) {
//                    String str = "http://tnea.a4n.in/Legal/privacy_policy";
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
//                }


                mDrawerLayout.closeDrawer(GravityCompat.START);


// Prepare intent which is triggered if the
                // notification is selected
                timer= new CountDownTimer(3000, 1000) {

                    @SuppressLint("DefaultLocale")
                    public void onTick(long millisUntilFinished) {
                        long mils = millisUntilFinished;

                        String hms=String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(mils),
                                TimeUnit.MILLISECONDS.toMinutes(mils)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mils)),
                                TimeUnit.MILLISECONDS.toSeconds(mils)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mils)) );
                        System.out.println(hms);

                    }

                    public void onFinish() {
                        //createActivity();
                        timer.start();
                    }

                }.start();
                return false;

            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        };

        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    private void createActivity() {
        Intent intent = new Intent(HomeActivity.this, Helpline.class);
        PendingIntent pIntent = PendingIntent.getActivity(HomeActivity.this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(HomeActivity.this)
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject").setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .addAction(R.mipmap.ic_launcher, "Call", pIntent)
                .addAction(R.mipmap.ic_launcher, "More", pIntent)
                .addAction(R.mipmap.ic_launcher, "And more", pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
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
                        session.setFragpo(0);
                        System.exit(0);
                    }
                });
//        builder1.setNeutralButton("Rate Us",new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                String str ="https://play.google.com/store/apps/details?id=com.in22labs.aptitudelp";
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
//
//            }
//        });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }
}
