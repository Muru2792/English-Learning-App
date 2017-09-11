package in22labs.englishlearning.PracticeHome;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import in22labs.englishlearning.R;

public class NewsActivity extends AppCompatActivity {
    LinearLayout news1, news2, news3, news4, news5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        news1=(LinearLayout)findViewById(R.id.news_all1);
        news2=(LinearLayout)findViewById(R.id.news_all2);
        news3=(LinearLayout)findViewById(R.id.news_all3);
        news4=(LinearLayout)findViewById(R.id.news_all4);
        news5=(LinearLayout)findViewById(R.id.news_all5);

        Toolbar tb=(Toolbar)findViewById(R.id.toolbar1);
        tb.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                NewsActivity.this.finish();
            }
        });
        tb.setTitle("News");
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));
        tb.inflateMenu(R.menu.abs1);
        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewsActivity.this, Newsdetails.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
