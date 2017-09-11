package in22labs.englishlearning.Framework.Testmodule;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
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

public class Dragactivity extends AppCompatActivity {
    LinearLayout lt_textqueue;
    TextView txtQue1, txtQue2,txtQue3,txtQue4,txtQue5,txtQue6, txtTitle;
    String strText="";
    int i=0, j=0, k=0;
    Button Dragchecker, Submit;
    Animation shake;
    CountDownTimer timer;
    RelativeLayout layoutText;
    int Score;
    ArrayList<String> textArray = new ArrayList<>();
    String Answer="My Name", question="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragactivity);
        lt_textqueue=(LinearLayout)findViewById(R.id.id_dragtextlt);
        txtQue1=(TextView)findViewById(R.id.text1);
        txtQue2=(TextView)findViewById(R.id.text2);
        txtQue3=(TextView)findViewById(R.id.text3);
        txtQue4=(TextView)findViewById(R.id.text4);
        txtQue5=(TextView)findViewById(R.id.text5);
        txtQue6=(TextView)findViewById(R.id.text6);
        txtTitle =(TextView)findViewById(R.id.txttitle);
        Dragchecker=(Button)findViewById(R.id.btn_check);
        Submit=(Button)findViewById(R.id.btn_submit);
        layoutText=(RelativeLayout)findViewById(R.id.btn_text);

        Score = getIntent().getIntExtra("Testscore", 0);
        txtQue1.setText("Hi");
        txtQue2.setText("My");
        txtQue3.setText("is");
        txtQue4.setText("was");
        txtQue5.setText("Name");
        txtQue6.setText("Are");

//        txtQue1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String temp = txtQue1.getText().toString();
//                textArray.add(j, temp);
//                Toast.makeText(Dragactivity.this,temp, Toast.LENGTH_SHORT).show();
//                if(textArray.get(j)!="null") {
//                    TextView textView = new TextView(Dragactivity.this);
//                    textView.setText(textArray.get(j));
//                    textView.setPadding(10,5,5,5);
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    params.setMargins(10,10,10,10);
//                    textView.setLayoutParams(params);
//
//                    // textView.setId(R.id.text_aftedrag);
//
//                    lt_textqueue.addView(textView);
//                    lt_textqueue.setBackground(getDrawable(R.drawable.textbackground));
//
//                    txtQue1.setVisibility(View.GONE);
//                }
//                j++;
//            }
//        });
        txtQue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txtQue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txtQue4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txtQue5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txtQue6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        shake = AnimationUtils.loadAnimation(this, R.anim.shake_view);
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
//                txtQue1.startAnimation(shake);
//                txtQue2.startAnimation(shake);
//                txtQue3.startAnimation(shake);
//                txtQue4.startAnimation(shake);
//                txtQue5.startAnimation(shake);
//                txtQue6.startAnimation(shake);
                timer.start();
            }

        }.start();


        txtTitle.setText("வார்த்தைகளை சரியாக வரிசைப்படுத்தவும்\n“என் பெயர்”");
        txtQue1.setOnTouchListener(new MyTouchListener());
        txtQue2.setOnTouchListener(new MyTouchListener());
        txtQue3.setOnTouchListener(new MyTouchListener());
        txtQue4.setOnTouchListener(new MyTouchListener());
        txtQue5.setOnTouchListener(new MyTouchListener());
        txtQue6.setOnTouchListener(new MyTouchListener());
//        lt_textqueue.setOnDragListener(new MyDragListener());
//        txtTitle.setOnDragListener(new MyDragListener());
        Dragchecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            for(int i =0; i<textArray.size();i++){

                    if (i == 0) {
                        question = question + textArray.get(i);
                    }else
                    {
                        question = question + " " + textArray.get(i);
                    }

            }
            if(question.equals(Answer)){
                Toast.makeText(Dragactivity.this, "Answer is correct", Toast.LENGTH_SHORT).show();
                layoutText.setVisibility(View.GONE);
                Dragchecker.setVisibility(View.GONE);
                Submit.setVisibility(View.VISIBLE);
                Score=Score+3;

            }else {
                Toast.makeText(Dragactivity.this, question+" Answer is incorrect", Toast.LENGTH_SHORT).show();
                refresh(v);
            }
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Dragactivity.this, ResultActivity.class);
                i.putExtra("testscore", Score);
                startActivity(i);
            }
        });
    }

    private final class MyTouchListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.GONE);
                TextView tvDay = (TextView) findViewById(view.getId());
                strText=tvDay.getText().toString();
                textArray.add(i,strText);
                i++;

                if(textArray.get(k)!="null") {
                    TextView textView = new TextView(Dragactivity.this);
                    textView.setText(textArray.get(k));
                    textView.setPadding(10,5,5,5);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10,10,10,10);
                    textView.setLayoutParams(params);

                    // textView.setId(R.id.text_aftedrag);

                    lt_textqueue.addView(textView);
                    lt_textqueue.setBackground(getDrawable(R.drawable.textbackground));

                    //txtQue1.setVisibility(View.GONE);
                }
                k++;


                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.textbackground);
        Drawable normalShape = getResources().getDrawable(R.drawable.buttonbackground);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
//                    // Dropped, reassign View to ViewGroup
//                    View view = (View) event.getLocalState();
//                    ViewGroup owner = (ViewGroup) view.getParent();
//                    owner.removeView(view);
//                    LinearLayout container = (LinearLayout) v;
//                    container.addView(view);
//                    view.setVisibility(View.VISIBLE);
                        j=k-1;
                      if(textArray.get(j)!="null") {
                          TextView textView = new TextView(Dragactivity.this);
                          textView.setText(textArray.get(j));
                          textView.setPadding(10,5,5,5);
                          LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                          params.setMargins(10,10,10,10);
                          textView.setLayoutParams(params);

                            // textView.setId(R.id.text_aftedrag);

                          lt_textqueue.addView(textView);

                          j++;
                        }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
//                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    public void refresh(View view){          //refresh is onClick name given to the button
        onRestart();
    }

    @Override
    protected void onRestart() {

        // TODO Auto-generated method stub
        super.onRestart();
        Intent i = new Intent(Dragactivity.this, Dragactivity.class);  //your class
        startActivity(i);
        finish();

    }
}
