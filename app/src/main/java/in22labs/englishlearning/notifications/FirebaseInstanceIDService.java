package in22labs.englishlearning.notifications;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


import java.io.IOException;

import in22labs.englishlearning.Utils.SessionManager;


/**
 * Created by in22labs on 5/23/2016.
 */
public class FirebaseInstanceIDService extends FirebaseInstanceIdService {


    String username;

    SessionManager sm;
    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();
        sm= new SessionManager(getApplicationContext());
//
//        if(sm.isLoggedIn()) {
//            username=sm.Username();
//            registerToken(token, username);
////
//        }
    }

    private void registerToken(String token, String username) {


//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = new FormBody.Builder()
//                .add("candidateid", username)
//                .add("Token",token)
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://www.youthwayap.org/Mobile/saveregid")
//                .post(body)
//                .build();
//
//        try {
//            client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
