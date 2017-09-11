package in22labs.englishlearning.VideoHome;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.api.client.auth.oauth2.Credential;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Caption;
import com.google.api.services.youtube.model.CaptionListResponse;
import com.google.api.services.youtube.model.CaptionSnippet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import in22labs.englishlearning.PracticeHome.Newsdetails;
import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.Config;
import in22labs.englishlearning.Utils.DeveloperKey;
import in22labs.englishlearning.Utils.YouTubeFailureRecoveryActivity;

public class VideosActivity extends YouTubeFailureRecoveryActivity {

    private static YouTube youtube;
    List<Caption> lt;
    private static final String SRT = "srt";
    YouTube.Captions.Download captionDownload;
    String playerID, title;
    LinearLayout lt_videoQuestion;
    TextView txt_question, txt_ch1, txt_ch2, txt_ch3, txt_bfcomp;
    RelativeLayout rt_q1, rt_q2, rt_q3;
    ScrollView scrollView;
    public int limit;
    LinearLayout lt_questionset;
    String vqno, vquestion, vch1, vch2, vch3, vans;
    String[] Arrvqno, Arrvquestion, Arrvch1, Arrvch2, Arrvch3, Arrvans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerID = extras.getString("youtube_url");
            title=extras.getString("title");
            // and get whatever type user account id is
        }

        rt_q1=(RelativeLayout)findViewById(R.id.lt_vq1);
        rt_q2=(RelativeLayout)findViewById(R.id.lt_vq2);
        rt_q3=(RelativeLayout)findViewById(R.id.lt_vq3);

        lt_questionset=(LinearLayout)findViewById(R.id.videos_questdisplay);
//        lt_quesnumber=(LinearLayout)findViewById(R.id.videos_quesset);

        txt_question=(TextView)findViewById(R.id.videos_ques1);
        txt_ch1=(TextView)findViewById(R.id.videos_ch1);
        txt_ch2=(TextView)findViewById(R.id.videos_ch2);
        txt_ch3=(TextView)findViewById(R.id.videos_ch3);
        scrollView=(ScrollView)findViewById(R.id.scrollEnd);
        txt_bfcomp=(TextView)findViewById(R.id.video_bfcomp);
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar_videos);
        //tb.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tb.setTitle(title);
//        tb.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(VideosActivity.this, VideoList.class);
//                startActivity(i);
//                VideosActivity.this.finish();
//            }
//        });
//        tb.inflateMenu(R.menu.abs1);

        txt_bfcomp.setText("Watch the video and then, answer the question to win coins");
        lt_videoQuestion=(LinearLayout)findViewById(R.id.video_question);
        try {
            JSONObject Jobject = new JSONObject(loadJSONFromAsset());
            JSONArray Jarray = Jobject.getJSONArray("results");
            limit = Jarray.length();
            Arrvqno=new String[limit];
            Arrvquestion=new String[limit];
            Arrvch1=new String[limit];
            Arrvch2=new String[limit];
            Arrvch3=new String[limit];
            Arrvans=new String[limit];

            for(int i =0; i<limit;i++){
                JSONObject object = Jarray.getJSONObject(i);
                vqno=object.getString("qno");
                vquestion=object.getString("question");
                vch1=object.getString("ch1");
                vch2=object.getString("ch2");
                vch3=object.getString("ch3");
                vans=object.getString("answer");

                Arrvqno[i]=vqno;
                Arrvquestion[i]=vquestion;
                Arrvch1[i]=vch1;
                Arrvch2[i]=vch2;
                Arrvch3[i]=vch3;
                Arrvans[i]=vans;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        rt_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayQuestion(0);
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        rt_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayQuestion(1);
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        rt_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayQuestion(2);
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    }
                });
            }
        });


    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(playerID);
        }

        int i = player.getDurationMillis();
        player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onLoaded(String s) {

            }

            @Override
            public void onAdStarted() {

            }

            @Override
            public void onVideoStarted() {

            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(VideosActivity.this, "Video Ended", Toast.LENGTH_SHORT).show();
                txt_bfcomp.setText("Now answer these questions and win coins");
                lt_videoQuestion.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {

            }
        });

    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("videolist1.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void DisplayQuestion(int questionpost) {
        lt_videoQuestion.setVisibility(View.GONE);
        lt_questionset.setVisibility(View.VISIBLE);
        final int tq=questionpost;
        txt_question.setText(Arrvquestion[tq]);
        lt_questionset.setBackgroundColor(Color.parseColor("#2e7d32"));
        txt_ch1.setText(Arrvch1[tq]);
        txt_ch2.setText(Arrvch2[tq]);
        txt_ch3.setText(Arrvch3[tq]);

        txt_ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choosed=txt_ch1.getText().toString();
                if(Arrvans[tq].equals(choosed)){
                    showAlertDialog(VideosActivity.this," Answer is Correct","", R.drawable.thumbs);
                    lt_videoQuestion.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }else {
                    showAlertDialog(VideosActivity.this," Answer is Incorrect","", R.drawable.down);
                    lt_videoQuestion.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }
                //Toast.makeText(VideosActivity.this, Arrvans[tq]+" "+choosed, Toast.LENGTH_SHORT).show();
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        txt_ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choosed=txt_ch2.getText().toString();
                if(Arrvans[tq].equals(choosed)){
                    showAlertDialog(VideosActivity.this," Answer is Correct","", R.drawable.thumbs);
                    lt_videoQuestion.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }else {
                    showAlertDialog(VideosActivity.this," Answer is Incorrect","", R.drawable.down);
                    lt_videoQuestion.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    }
                });
            }
        });

        txt_ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choosed=txt_ch3.getText().toString();
                if(Arrvans[tq].equals(choosed)){
                    showAlertDialog(VideosActivity.this," Answer is Correct","", R.drawable.thumbs);
                    lt_videoQuestion.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }else {
                    showAlertDialog(VideosActivity.this," Answer is Incorrect","", R.drawable.down);
                    lt_videoQuestion.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    }
                });
            }
        });

    }
    private void showAlertDialog(Context mContext, String mTitle, String mBody, int mImage){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomDialog);
        builder.setCancelable(true);
        builder.setIcon(mImage);
        if(mTitle.length()>0)
            builder.setTitle(mTitle);
        if(mBody.length()>0)
            builder.setTitle(mBody);

        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
