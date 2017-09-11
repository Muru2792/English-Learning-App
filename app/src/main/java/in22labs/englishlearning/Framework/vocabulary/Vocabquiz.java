package in22labs.englishlearning.Framework.vocabulary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import in22labs.englishlearning.R;

public class Vocabquiz extends AppCompatActivity {
    RelativeLayout rt;
    TextView txt_Ques, txt_Ch1, txt_Ch2;
    int tq =1;
    TextView timer1;
    CountDownTimer timer=null;
    int startTime = 10000;
    ArrayList<String> Vques,Vch1, Vch2, Vanswer;
    int[] d = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabquiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rt=(RelativeLayout)findViewById(R.id.imagebackground);
        txt_Ques=(TextView)findViewById(R.id.vocab_ques);
        txt_Ch1=(TextView)findViewById(R.id.vocab_ch1);
        txt_Ch2=(TextView)findViewById(R.id.vocab_ch2);
        timer1=(TextView)findViewById(R.id.txt_timer_vocab);

        Vques = new ArrayList<>();
        Vques.add("GAIETY");
        Vques.add("RIVETING");
        Vques.add("LAUNCH");
        Vques.add("STRIFE");
        Vques.add("ABANDON");

        Vch1 = new ArrayList<>();
        Vch1.add("The state or quality of being light-hearted or cheerful.");
        Vch1.add("Healing");
        Vch1.add("Start or set in motion");
        Vch1.add("to create opening");
        Vch1.add("Cease to support or look after");

        Vch2 = new ArrayList<>();
        Vch2.add("Sadnesss");
        Vch2.add("Completely engrossing; compelling.");
        Vch2.add("Bombardent");
        Vch2.add("Angry or bitter disagreement over fundamental issues; conflict.");
        Vch2.add("Helping them collect data");

        Vanswer = new ArrayList<>();
        Vanswer.add("The state or quality of being light-hearted or cheerful.");
        Vanswer.add("Completely engrossing; compelling.");
        Vanswer.add("Start or set in motion");
        Vanswer.add("Angry or bitter disagreement over fundamental issues; conflict.");
        Vanswer.add("Cease to support or look after");

        setQuestion(tq);


        timer= new CountDownTimer(startTime, 1000) {

            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                long mils = millisUntilFinished;

                String hms=String.format("%02d:%02d:%02d",TimeUnit.MILLISECONDS.toHours(mils),
                        TimeUnit.MILLISECONDS.toMinutes(mils)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mils)),
                        TimeUnit.MILLISECONDS.toSeconds(mils)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mils)) );
                System.out.println(hms);
                timer1.setText(hms);
            }

            public void onFinish() {
                timer1.setText("done!");
                tq++;
                if(tq<=Vques.size())
                setQuestion(tq);
            }

        }.start();



    }

    @SuppressLint("NewApi")
    private void setQuestion(int Question) {
        txt_Ques.setText(Vques.get(Question));
        txt_Ch1.setText(Vch1.get(Question));
        txt_Ch2.setText(Vch2.get(Question));
        rt.setBackground(getDrawable(d[Question-1]));
        timer.start();
    }

}
