package in22labs.englishlearning.PracticeHome;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import in22labs.englishlearning.Framework.Testmodule.Multiplechoice;
import in22labs.englishlearning.R;

public class Newsdetails extends AppCompatActivity {
    ImageView im_news;
    TextView txt_title_news;
    TextView txt_message_news;
    TextView txt_question, txt_ch1, txt_ch2, txt_ch3;
    RelativeLayout rt_q1, rt_q2, rt_q3;
    ScrollView scrollView;
    LinearLayout lt_questionset, lt_quesnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetails);
        im_news=(ImageView)findViewById(R.id.image_news);
        txt_title_news=(TextView)findViewById(R.id.text_title_news);
        txt_message_news=(TextView)findViewById(R.id.text_subject_news);

        rt_q1=(RelativeLayout)findViewById(R.id.lt_q1);
        rt_q2=(RelativeLayout)findViewById(R.id.lt_q2);
        rt_q3=(RelativeLayout)findViewById(R.id.lt_q3);

        lt_questionset=(LinearLayout)findViewById(R.id.news_questdisplay);
        lt_quesnumber=(LinearLayout)findViewById(R.id.news_quesset);

        txt_question=(TextView)findViewById(R.id.news_ques1);
        txt_ch1=(TextView)findViewById(R.id.news_ch1);
        txt_ch2=(TextView)findViewById(R.id.news_ch2);
        txt_ch3=(TextView)findViewById(R.id.news_ch3);
        scrollView=(ScrollView)findViewById(R.id.scrollEnd);


        Toolbar tb=(Toolbar)findViewById(R.id.toolbar2);
//        tb.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//        tb.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//                Newsdetails.this.finish();
//            }
//        });
        tb.setTitle("Trump, in Israel, Says He Has New Reasons to Hope For Middle East Peace");
        tb.setTitleTextColor(Color.parseColor("#000000"));


        im_news.setImageDrawable(getDrawable(R.drawable.p1));
        txt_title_news.setText("Trump, in Israel, Says He Has New Reasons to Hope For Middle East Peace");
        txt_message_news.setText("Jerusalem: U.S. President Donald Trump said in Israel on Monday he came away from a weekend visit to Saudi Arabia with new reasons for hope that peace and stability could be achieved in the Middle East.\n\n" +
                "On the second leg of his first overseas trip since entering office, Trump is to hold talks separately with Israeli Prime Minister Benjamin Netanyahu and Palestinian President Mahmoud Abbas in a stopover lasting 28 hours.\n\n" +
                "Later on Monday, the U.S. leader will pray at Judaism's Western Wall and visit the Church of the Holy Sepulchre in Jerusalem, and on Tuesday he will travel to Bethlehem in the occupied West Bank.\n\n" +
                "Netanyahu and his wife Sara, as well as President Reuven Rivlin and members of the Israeli cabinet, were at Tel Aviv's Ben-Gurion airport to greet Trump and first lady Melania in a red carpet ceremony after what is believed to have been the first direct flight from Riyadh to Israel.\n\n" +
                "During my travels in recent days, I have found new reasons for hope, Trump said in a brief speech on arrival.\n\n" +
                "We have before us a rare opportunity to bring security and stability and peace to this region and its people, defeating terrorism and creating a future of harmony, prosperity and peace, but we can only get there working together. There is no other way,he said.\n\n" +
                "Trump's tour comes in the shadow of difficulties at home, where he is struggling to contain a scandal after firing James Comey as FBI director nearly two weeks ago. The trip ends on Saturday after visits to the Vatican, Brussels and Sicily.\n\n" +
                "During his two days in Riyadh, Trump received a warm welcome from Arab leaders, who focused on his desire to restrain Iran's influence in the region, a commitment they found wanting in the Republican president's Democratic predecessor, Barack Obama.\n\n" +
                "What's happened with Iran has brought many of the parts of the Middle East toward Israel, Trump said in public remarks at a meeting in Jerusalem with Rivlin.\n\n" +
                "And you could say that's one of the - if there's a benefit, that would be the benefit. Because I've seen such a different feeling toward Israel from countries that as you know were not feeling so well about Israel not so long ago. And it's brought a lot of folks together, Trump said.\n\n" +
                "ULTIMATE DEAL\n\n" +
                "Earlier at the airport, Netanyahu said Israel shared Trump's commitment to peace - but he also repeated his right-wing government's political and security demands of the Palestinians, including recognition of Israel as a Jewish state.\n\n" +
                "May your first trip to our region prove to be a historic milestone on the path towards reconciliation and peace, Netanyahu said.\n\n" +
                "Trump used his visit to Riyadh to bolster U.S. ties with Arab and Islamic nations, announce $110 billion in U.S. arms sales to Saudi Arabia, and send Iran a tough message.\n\n" +
                "Trump has vowed to do whatever is necessary to broker peace between Israel and the Palestinians -- something he has called the ultimate deal -- but has given little indication of how he could revive negotiations that collapsed in 2014.\n\n" +
                "U.S. Secretary of State Rex Tillerson told reporters en route to Tel Aviv that any three-way meeting between Trump, Netanyahu and Abbas was for a later date.\n\n" +
                "When Trump met Abbas this month in Washington, he stopped shortly of explicitly recommitting his administration to a two-state solution to the decades-old conflict, a long-standing foundation of U.S. policy. He has since spoken in support of Palestinian self-determination.\n\n" +
                "Trump has also opted against an immediate move of the U.S. Embassy in Tel Aviv to Jerusalem, a longtime demand of Israel.\n\n" +
                "A senior administration official told Reuters last week that Trump remained committed to his campaign pledge to ultimately relocate the embassy, but would not announce such a move during this trip.\n\n" +
                "On Sunday, Israel authorised some economic concessions to the Palestinians that it said would improve civilian life in areas controlled by the Palestinian Authority and were intended to respond to Trump's request for confidence-building steps.\n\n" +
                "The United States welcomed the move but the Palestinians said they had heard such promises before.\n\n" +
                "Trump will have visited significant centres of Islam, Judaism and Christianity by the end of his trip, a point that his aides say bolsters his argument that the fight against Islamist militancy is a battle between good and evil.");

        rt_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayQuestion(0);
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(scrollView.FOCUS_DOWN);
                    }
                });
                rt_q1.setClickable(false);
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
                rt_q2.setClickable(false);
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
                rt_q3.setClickable(false);
            }
        });


    }

    private void DisplayQuestion(int questionpost) {
        lt_quesnumber.setVisibility(View.GONE);
        lt_questionset.setVisibility(View.VISIBLE);
        final int tq=questionpost;

        String[] question1=new String[3];
        String[] ch1=new String[3];
        String[] ch2=new String[3];
        String[] ch3=new String[3];
        final String[] answer=new String[3];

        question1[0]="Earlier at the airport, Netanyahu said Israel shared Trump's _________ to peace";
        question1[1]="Struggling";
        question1[2]="Reuven Rivlin and members of the ________ cabinet";

        ch1[0]="Commitment";
        ch2[0]="Immediate";
        ch3[0]="Significant";

        ch1[1]="Success";
        ch2[1]="Punishment";
        ch3[1]="Engage in conflict.";

        ch1[2]="Lousiana";
        ch2[2]="Palestine";
        ch3[2]="Israeli";

        answer[0]="Commitment";
        answer[1]="Engage in conflict.";
        answer[2]="Israeli";

        txt_question.setText(question1[tq]);
        lt_questionset.setBackgroundColor(Color.parseColor("#2e7d32"));
        txt_ch1.setText(ch1[tq]);
        txt_ch2.setText(ch2[tq]);
        txt_ch3.setText(ch3[tq]);

        txt_ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choosed=txt_ch1.getText().toString();
                if(choosed.equals(answer[tq])){

                    //showSteps(Newsdetails.this,"Answer is Correct", R.drawable.ic_done_black_18dp);

                    showAlertDialog(Newsdetails.this," Answer is Correct","", R.drawable.thumbs);
                    lt_quesnumber.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }else {
                    showAlertDialog(Newsdetails.this," Answer is Incorrect","", R.drawable.down);
                    lt_quesnumber.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }
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
                if(choosed.equals(answer[tq])){
                    showAlertDialog(Newsdetails.this," Answer is Correct","", R.drawable.thumbs);
                    lt_quesnumber.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }else {
                    showAlertDialog(Newsdetails.this," Answer is Incorrect","", R.drawable.down);
                    lt_quesnumber.setVisibility(View.VISIBLE);
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
                if(choosed.equals(answer[tq])){
                    showAlertDialog(Newsdetails.this," Answer is Correct","", R.drawable.thumbs);
                    lt_quesnumber.setVisibility(View.VISIBLE);
                    lt_questionset.setVisibility(View.GONE);
                }else {
                    showAlertDialog(Newsdetails.this," Answer is Incorrect","", R.drawable.down);
                    lt_quesnumber.setVisibility(View.VISIBLE);
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


    @SuppressLint("NewApi")
    private void showSteps(Context mContext, String mTitle, int mImage1) {

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.steps_popup, null);

        final Dialog popup = new Dialog(mContext, R.style.FullHeightDialog);
        popup.setContentView(layout);
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView result=(TextView)layout.findViewById(R.id.txtresultstatus);
        ImageView im_status=(ImageView)layout.findViewById(R.id.overlapImage);

        result.setText(mTitle);
        im_status.getResources().getDrawable(mImage1);
        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.



    }


}
