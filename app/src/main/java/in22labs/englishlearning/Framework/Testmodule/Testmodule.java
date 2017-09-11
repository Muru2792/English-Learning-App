package in22labs.englishlearning.Framework.Testmodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import in22labs.englishlearning.HomeActivity;
import in22labs.englishlearning.R;

public class Testmodule extends AppCompatActivity {
    Button b1;
    ImageView im_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testmodulesr1);
        b1= (Button)findViewById(R.id.btn_teststart);
        im_close=(ImageView)findViewById(R.id.teststartclose);

        Animation mAnimation = new AlphaAnimation(1, 0);
        mAnimation.setDuration(400);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.REVERSE);

        b1.startAnimation(mAnimation);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.clearAnimation();
                Intent i = new Intent(Testmodule.this, Startscreen.class);
                startActivity(i);
            }
        });

        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Testmodule.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

}
