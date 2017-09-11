package in22labs.englishlearning.Framework.FlipGame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import in22labs.englishlearning.R;

public class Flipgame extends AppCompatActivity {
    TextView t1, t2, t3, t4;
    LinearLayout lt1, lt2;
    ArrayList<String > QuesLsit, Ch1List, Ch2List;
    ArrayList<Integer> AnsList;
    String tempQues="", tempCh1, tempCh2;
    RelativeLayout rt;
    LinearLayout lt3;
    Button btn_submit;
    int i=0, j=0, k=0;
    CountDownTimer timer=null;
    public String Ques, Ques12, Ques2; String Ch1; String Ch2;
    Animation animFadeIn, animSlideinLeft, animSlideinRight, animFadeOut,animBlink,animZoomIn,animZoomOut,animRotate
            ,animMove,animSlideUp,animSlideDown,animBounce,animSequential,animTogether,animCrossFadeIn,animCrossFadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1= (TextView)findViewById(R.id.text1);
        t2= (TextView)findViewById(R.id.text2);
        t3= (TextView)findViewById(R.id.txt_timer);
        t4= (TextView)findViewById(R.id.txt_qnum) ;
        lt1 = (LinearLayout)findViewById(R.id.change);
        lt2 = (LinearLayout)findViewById(R.id.ok);
        lt3=(LinearLayout)findViewById(R.id.lt_choice);
        rt=(RelativeLayout)findViewById(R.id.rt_submit);
        btn_submit=(Button)findViewById(R.id.bt_nxt);
        int startTime = 10000;
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animSlideinLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideinleft);
        animSlideinRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideinright);
        animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);
        QuesLsit = new ArrayList<>();
        QuesLsit.add("Buy her a present for her birthday. She lives by a park.");
        QuesLsit.add("There's a hole in my trousers. I read the whole book in one day.");
        QuesLsit.add("There is a bird in the garden. The children have done their homework.");
        QuesLsit.add("Write your name on top of the test paper. I couldn't remember the right answer.");
        QuesLsit.add("I went to the sea to see my friend.");
        Ch1List = new ArrayList<>();
        Ch1List.add("Buy");
        Ch1List.add("hole");
        Ch1List.add("their");
        Ch1List.add("Write");
        Ch1List.add("see");
        Ch2List = new ArrayList<>();
        Ch2List.add("by");
        Ch2List.add("whole");
        Ch2List.add("There");
        Ch2List.add("right");
        Ch2List.add("sea");
        AnsList=new ArrayList<>();
        AnsList.add(0);
        AnsList.add(1);
        AnsList.add(0);
        AnsList.add(1);
        AnsList.add(0);
        if(i<QuesLsit.size()) {
            Ques = QuesLsit.get(i);
            Ch1 = Ch1List.get(i);
            Ch2 = Ch2List.get(i);
            if(AnsList.get(i)==0) {
                Ques12 = StartQuestion(Ques, Ch1, Ch2, 0, i).toString();
            }else if(AnsList.get(i)==1){
                Ques12 = StartQuestion(Ques, Ch1, Ch2, 1, i).toString();
            }
        }

        Toast.makeText(Flipgame.this, Ques12, Toast.LENGTH_SHORT).show();
       // t1.setText(QuesLsit.get(0));

        timer= new CountDownTimer(startTime, 1000) {

            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                long mils = millisUntilFinished;

                String hms=String.format("%02d:%02d:%02d",TimeUnit.MILLISECONDS.toHours(mils),
                        TimeUnit.MILLISECONDS.toMinutes(mils)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mils)),
                        TimeUnit.MILLISECONDS.toSeconds(mils)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mils)) );
                System.out.println(hms);
                t3.setText(hms);
            }

            public void onFinish() {
                t3.setText("done!");
                Toast.makeText(Flipgame.this,"Time Ended", Toast.LENGTH_SHORT).show();
                lt3.setVisibility(View.INVISIBLE);
                rt.setVisibility(View.VISIBLE);
                lt3.setAnimation(animSlideUp);
                rt.setAnimation(animSlideDown);
            }

        }.start();



        lt2.setAnimation(animSlideinLeft);
        lt1.setAnimation(animSlideinRight);

        lt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Ques2=t1.getText().toString();
                t1.setText("");
                tempQues="";
                String[] sentence = Ques12.split(" ");
                for(String word: sentence)
                {
                    if(word.equals(Ch1)){
                        tempCh1=Ch2;
                        tempQues = tempQues+" <font color='red'>"+tempCh1+"</b></font>";
                        Ques2 = Ques2+" "+tempCh1;
                    }else if(word.equals(Ch2)){
                        tempCh2=Ch1;
                        tempQues = tempQues+"  <font color='red'><b>"+tempCh2+"</b></font>";
                        Ques2 = Ques2+" "+tempCh2;
                    }else {
                        tempQues = tempQues + " " + word;
                        Ques2 = Ques2+" "+word;
                    }
                }
                //  t1.setText(Html.escapeHtml(""));

                t2.setText(Html.fromHtml(tempQues));
                t2.setVisibility(View.VISIBLE);
                t2.setAnimation(animFadeIn);
                Toast.makeText(Flipgame.this, t2.getText().toString(), Toast.LENGTH_SHORT).show();
                t1.setVisibility(View.INVISIBLE);
                if(AnsList.get(i)==1){
                    Toast.makeText(Flipgame.this, " Correct Answer You Have One Coin ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Flipgame.this, " Wrong Answer ", Toast.LENGTH_SHORT).show();
                }
                lt3.setVisibility(View.INVISIBLE);
                rt.setVisibility(View.VISIBLE);
                lt3.setAnimation(animSlideUp);
                rt.setAnimation(animSlideDown);
                timer.cancel();
                t3.setText("done!");
            }
        });

        lt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnsList.get(i)==0){
                    Toast.makeText(Flipgame.this, " Correct Answer You Have One Coin ", Toast.LENGTH_SHORT).show();
                }
//                else if(t2.getText().toString().trim()==Ques.trim()){
//                    Toast.makeText(Flipgame.this, "Success", Toast.LENGTH_SHORT).show();
//                }
                else{
                    Toast.makeText(Flipgame.this, " Wrong Answer", Toast.LENGTH_SHORT).show();
                }
                lt3.setVisibility(View.INVISIBLE);
                rt.setVisibility(View.VISIBLE);
                lt3.setAnimation(animSlideUp);
                rt.setAnimation(animSlideDown);
                timer.cancel();
                t3.setText("done!");
        }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                Ques12="";
                lt3.setAnimation(animSlideDown);
                rt.setAnimation(animSlideUp);
                if(i<QuesLsit.size()) {
                    timer.start();
                    Ques = QuesLsit.get(i);
                    Ch1 = Ch1List.get(i);
                    Ch2 = Ch2List.get(i);
                    t1.setVisibility(View.GONE);
                    t2.setVisibility(View.GONE);
                    t2.setText("");
                    if(AnsList.get(i)==0) {
                        Ques12 = StartQuestion(Ques, Ch1, Ch2, 0, i).toString();
                    }else if(AnsList.get(i)==1){
                        Ques12 = StartQuestion(Ques, Ch1, Ch2, 1, i).toString();
                    }
                }else {
                    Intent i = new Intent(Flipgame.this, FlipResult.class);
                    startActivity(i);
                }
            }
        });

    }



    private String StartQuestion(String Ques, String Ch1, String Ch2, int i, int j) {
        String Ques1="";
        int q1= j+1;
        int q2=QuesLsit.size();
        t4.setText(q1+"/"+q2);
        if(q1==q2){
            btn_submit.setText("Submit");
        }
        tempQues="";
        if(i==0) {
            String[] sentence = Ques.split(" ");
            for (String word : sentence) {
                if (word.equals(Ch1)) {

                    tempQues = tempQues + " <font color='red'>" + Ch1 + "</b></font>";
                    Ques1 =Ques1+" "+Ch1;
                } else if (word.equals(Ch2)) {

                    tempQues = tempQues + "  <font color='red'><b>" + Ch2 + "</b></font>";
                    Ques1 =Ques1+" "+Ch2;
                } else {
                    tempQues = tempQues + " " + word;
                    Ques1 =Ques1+" "+word;
                }
            }

        }else {
            String[] sentence = Ques.split(" ");
            for(String word: sentence)
            {
                if(word.equals(Ch1)){
                    tempCh1=Ch2;
                    tempQues = tempQues+" <font color='red'>"+tempCh1+"</b></font>";
                    Ques1 =Ques1+" "+tempCh1;
                }else if(word.equals(Ch2)){
                    tempCh2=Ch1;
                    tempQues = tempQues+"  <font color='red'><b>"+tempCh2+"</b></font>";
                    Ques1 =Ques1+" "+tempCh2;
                }else {
                    tempQues = tempQues + " " + word;
                    Ques1 =Ques1+" "+word;
                }
            }
            //  t1.setText(Html.escapeHtml(""));


        }
        t1.setText(Html.fromHtml(tempQues));

        tempQues="";
        t1.setVisibility(View.VISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t1.setAnimation(animFadeIn);
        lt3.setVisibility(View.VISIBLE);
        rt.setVisibility(View.INVISIBLE);

        return Ques1;
    }

    private void ChangeQuestion(String Ques, String Ch1, String Ch2) {
        String[] sentence = Ques.split(" ");
        for(String word: sentence)
        {
            if(word.equals(Ch1)){
                tempCh1=Ch2;
                tempQues = tempQues+" <font color='red'>"+tempCh1+"</b></font>";
            }else if(word.equals(Ch2)){
                tempCh2=Ch1;
                tempQues = tempQues+"  <font color='red'><b>"+tempCh2+"</b></font>";
            }else {
                tempQues = tempQues + " " + word;
            }
        }
      //  t1.setText(Html.escapeHtml(""));
        t1.setText(tempQues);

    }
}
