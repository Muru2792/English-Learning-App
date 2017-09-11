package in22labs.englishlearning.PracticeHome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import in22labs.englishlearning.HomeActivity;
import in22labs.englishlearning.R;

public class BooksActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Toolbar tb_videohome=(Toolbar)findViewById(R.id.toolbar_books);
        tb_videohome.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tb_videohome.setTitle("Audios");
        tb_videohome.setTitleTextColor(Color.parseColor("#FFFFFF"));

        tb_videohome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BooksActivity.this, HomeActivity.class);
                startActivity(i);
                BooksActivity.this.finish();
            }
        });
        tb_videohome.inflateMenu(R.menu.abs1);

    }


}
