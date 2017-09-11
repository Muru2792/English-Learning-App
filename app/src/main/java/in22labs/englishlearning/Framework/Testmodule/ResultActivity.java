package in22labs.englishlearning.Framework.Testmodule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import in22labs.englishlearning.HomeActivity;
import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.SessionManager;
import pl.droidsonroids.gif.GifImageView;

public class ResultActivity extends AppCompatActivity {
    TextView txtScore, txtPrev;
    ImageView im_close;
    Button btn_home;
    int Score;
    SessionManager sm;
    GifImageView gifImageView;
    RelativeLayout rt_home;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtScore=(TextView)findViewById(R.id.testendscore);
        txtPrev=(TextView)findViewById(R.id.testPrev);
        im_close=(ImageView)findViewById(R.id.testendclose);
        btn_home=(Button)findViewById(R.id.btn_end_home);
        rt_home=(RelativeLayout)findViewById(R.id.rt_result);
        gifImageView=(GifImageView)findViewById(R.id.gifreader);
        //rt_home.setVisibility(View.INVISIBLE);
        sm = new SessionManager(ResultActivity.this);
        timer= new CountDownTimer(6000, 1000) {

            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                long mils = millisUntilFinished;

                String hms=String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(mils),
                        TimeUnit.MILLISECONDS.toMinutes(mils)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mils)),
                        TimeUnit.MILLISECONDS.toSeconds(mils)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mils)) );
                System.out.println(hms);

            }

            public void onFinish() {
                rt_home.setVisibility(View.VISIBLE);
                gifImageView.setVisibility(View.INVISIBLE);
            }

        }.start();

        Score=getIntent().getIntExtra("testscore", 0);
        txtScore.setText(String.valueOf(Score)+" Coins");
        txtPrev.setText(String.valueOf(sm.isPrevScore()+" Coins"));

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, HomeActivity.class);
                sm.setPrevScore(Score);
                startActivity(i);
                finish();

            }
        });

        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, HomeActivity.class);
                sm.setPrevScore(Score);
                startActivity(i);
                finish();
            }
        });

    }
}
