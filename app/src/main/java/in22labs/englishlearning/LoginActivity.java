package in22labs.englishlearning;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import in22labs.englishlearning.Utils.DatabaseHelper;
import in22labs.englishlearning.Utils.SessionManager;


public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener{
    EditText edt_username, edt_password;
//
    public String username, password, email, birthday;
//    ArrayList<String> localuserlist;
    Button login;
//    RelativeLayout home;
    private SessionManager session;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog mProgressDialog;
    SignInButton signInButton;
    LoginButton loginButton;
    CallbackManager callbackManager;
    DatabaseHelper db;
    URL profile_pic;
    //    TextView Skip, forgot_pwd;
//    ConnectionDetector cd;
//    ProgressDialog progress;
//
//    public int limit;
//    Request request;
//    Boolean login_status;
//    String Message, candidate_name, candidate_mail, candidate_mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_username=(EditText)findViewById(R.id.edt_username);
        edt_password=(EditText)findViewById(R.id.edt_password);
        login=(Button)findViewById(R.id.btn_login);
//        home=(RelativeLayout)findViewById(R.id.homme);
//        Skip =(TextView)findViewById(R.id.login_skip);
//        cd = new ConnectionDetector(LoginActivity.this);
//        db=new DatabaseHelper(LoginActivity.this);
        session = new SessionManager(getApplicationContext());

        db=new DatabaseHelper(LoginActivity.this);
//        localuserlist=db.checkUsername();
//        progress = new ProgressDialog(LoginActivity.this);
//        forgot_pwd=(TextView)findViewById(R.id.forgot_pwd);
        edt_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=edt_username.getText().toString().trim();
//                Toast.makeText(LoginActivity.this,username,Toast.LENGTH_SHORT).show();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this /* FragmentActivity */, LoginActivity.this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        //facebook SDK
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                request.setParameters(parameters);
                request.executeAsync();

                session.setLogin(true);
                session.setType(3);
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


//        db.deleteTable();
//        db.deleteTableTRAIN();
//        db.createTable();
//        db.createTableTRAIN();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edt_username.getText().toString().trim();
                password = edt_password.getText().toString().trim();

                Toast.makeText(LoginActivity.this, username+" "+password, Toast.LENGTH_SHORT).show();
                if(username.equals("admin") && password.equals("admin")) {
                    session.setLogin(true);
                    session.setType(1);//normal login
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(LoginActivity.this, "Username/Password are wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
//
//        forgot_pwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
//        Skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, Tabmainactivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }


    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            //Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideProgressDialog();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
//        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            if(acct!=null) {
                session.setProfurl(acct.getPhotoUrl());
                Toast.makeText(LoginActivity.this, session.isUrl(), Toast.LENGTH_SHORT).show();
                //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            }
            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
//            signInButton.setVisibility(View.GONE);
            session.setLogin(true);
            session.setType(2);
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);

            //findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
//            mStatusTextView.setText(R.string.signed_out);
//
//            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }
    private void GotoNext() {
//
//
//
//       new Thread(new Runnable() {
//            public void run() {
//                try {
//                    String token = FirebaseInstanceId.getInstance().getToken();
//                    String userloginurl = Config.SENDUSERLOGIN1 + username + Config.SENDExtension + token;
//                    OkHttpClient client = new OkHttpClient();
//
////                                    RequestBody formBody = new FormBody.Builder()
////                                            .add("username", username)
////                                            .add("password", password)
////                                            .build();
//                    Request request = new Request.Builder()
//                            .url(userloginurl)
////                                                .post(formBody)
//                            .build();
//                    Response responses = client.newCall(request).execute();
//
//                    String jsonData = null;
//                    try {
//                        jsonData = responses.body().string();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    JSONObject Jobject = new JSONObject(jsonData);
//                    login_status = Jobject.getJSONObject("results").getBoolean("status");
//                    Message = Jobject.getJSONObject("results").getString("Message");
//
//
////                                    JSONStringer
////                                    JSONArray Jarray = Jobject.getJSONArray("results");
////                                    limit = Jarray.length();
////                                    for (int i = 0; i < limit; i++) {
////                                    JSONObject object = Jarray.getJSONObject(i);
////                                    login_status=object.getBoolean("status");
////                                    Message=object.getString("Message");
////                                    }
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Toast.makeText(LoginActivity.this, login_status.toString(), Toast.LENGTH_SHORT).show();
//                            //Toast.makeText(LoginActivity.this, Message, Toast.LENGTH_SHORT).show();
//                            if (login_status) {
//
//                            } else {
//                                // Toast.makeText(LoginActivity.this, Message, Toast.LENGTH_SHORT).show();
//                                progress.dismiss();
//                            }
//                        }
//
//                    });
//                    progress.dismiss();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    progress.dismiss();
//                } catch (IOException e) {
//                    e.printStackTrace();
////                                    progress.dismiss();
//                }
//            }
//        }).start();
    }


    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
                Toast.makeText(LoginActivity.this, profile_pic.toString(),Toast.LENGTH_SHORT).show();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
                Toast.makeText(LoginActivity.this, object.getString("email"), Toast.LENGTH_SHORT).show();
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));
                db.InsertUser("POF1010", object.getString("first_name")+" "+object.getString("last_name"),object.getString("email"),"admin",profile_pic.toString() );
            return bundle;
        }
        catch(JSONException e) {
           // Log.d(TAG,"Error parsing JSON");
        }
        return null;
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


}
