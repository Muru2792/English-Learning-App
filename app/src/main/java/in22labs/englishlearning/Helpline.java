package in22labs.englishlearning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import in22labs.englishlearning.HomeTab.LessonHome;
import in22labs.englishlearning.Utils.DatabaseHelper;
import in22labs.englishlearning.Utils.SessionManager;

public class Helpline extends AppCompatActivity {
    SessionManager sessionManager;
    int[] dr ={
            R.drawable.help3,
            R.drawable.help2,
            R.drawable.help1,
    };
    DatabaseHelper db;
    ListView listHelp, listAnsed;
    ArrayList<String> todayWord, todayContent, todayQuestion, txtCh1, txtCh2, txtCh3, txtCh4, txtAnswer, txtExplantion;
    ArrayList<String> TtodayWord, TtodayContent, TtodayQuestion, TtxtCh1, TtxtCh2, TtxtCh3, TtxtCh4, TtxtAnswer, TtxtExplantion;
    ArrayList<String>  TtodayAnseContent, TtxtAnseExplantion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);
        sessionManager=new SessionManager(Helpline.this);
        sessionManager.setNoti(false);
        todayWord=new ArrayList<>();
        todayContent=new ArrayList<>();
        todayQuestion=new ArrayList<>();
        txtCh1=new ArrayList<>();
        txtCh2=new ArrayList<>();
        txtCh3=new ArrayList<>();
        txtCh4=new ArrayList<>();
        txtAnswer=new ArrayList<>();
        txtExplantion=new ArrayList<>();

        TtodayWord=new ArrayList<>();
        TtodayContent=new ArrayList<>();
        TtodayQuestion=new ArrayList<>();
        TtxtCh1=new ArrayList<>();
        TtxtCh2=new ArrayList<>();
        TtxtCh3=new ArrayList<>();
        TtxtCh4=new ArrayList<>();
        TtxtAnswer=new ArrayList<>();
        TtxtExplantion=new ArrayList<>();
        todayWord.add("Today new Word - Outperform");
        todayWord.add("Today new Word - Prevail");
        todayWord.add("Today new Word - Emulate");

        todayContent.add("The TN Class 10 rsults were announced yesterday and following the trend, girls have outperformed boys agains by scoring 96.2 percent while boys scored 92.5 per cent.");
        todayContent.add("Mumbai Indians once again showed off their death bowling muscle as they eked out a 1 run victoryto prevail over Rising Pune Supergiant in final of the IPL.");
        todayContent.add("TN is rolling out new syllabi for all classes, starting with higher secondary and saidthey will emulate the syllabus of CBSE.");

        todayQuestion.add("What does the word 'Outperform' means?");
        txtCh1.add("Move away from something");
        txtCh2.add("Perform on a stage");
        txtCh3.add("Promise to do something");
        txtCh4.add("Perform better than");
        txtAnswer.add("Perform better than");
        txtExplantion.add("What does the word 'Outperform' means? = Perform better than");

        todayQuestion.add("What does the word 'Prevail' means?");
        txtCh1.add("To defeat someome in a game, competition, argument etc.");
        txtCh2.add("To suddenly stop doing something");
        txtCh3.add("To increase, simplify, or make something better");
        txtCh4.add("To enter a place in order to make something");
        txtAnswer.add("To defeat someome in a game, competition, argument etc.");
        txtExplantion.add("What does the word 'Prevail' means? = To defeat someome in a game, competition, argument etc.");

        todayQuestion.add("What does the word 'Emulate' means?");
        txtCh1.add("To try hard to be like or do better than");
        txtCh2.add("To suddenly stop doing something");
        txtCh3.add("To enter a place in order to make something");
        txtCh4.add("Promise to do something");
        txtAnswer.add("To try hard to be like or do better than");
        txtExplantion.add("What does the word 'Emulate' means? = To try hard to be like or do better than");


        db=new DatabaseHelper(Helpline.this);
        String message = getIntent().getStringExtra("Index");
//        Bundle extras = getIntent().getExtras();
//        if(extras !=null) {
            int value = Integer.parseInt(message);
            int isAlready;
//            if(TtodayWord.isEmpty()) {
            if(value==4) {

            }else {
                db.InsertQuestion(todayContent.get(value), todayQuestion.get(value), txtCh1.get(value), txtCh2.get(value), txtCh3.get(value), txtCh4.get(value), txtAnswer.get(value), txtExplantion.get(value), 0);
            }
// }else {

//                for(String temp:TtodayWord){
//                    if(temp!=todayContent.get(value)){
//                        db.InsertQuestion(todayContent.get(value), todayQuestion.get(value), txtCh1.get(value), txtCh2.get(value), txtCh3.get(value), txtCh4.get(value), txtAnswer.get(value), txtExplantion.get(value), 0);
//                        Toast.makeText(Helpline.this, "Already Inserted", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }

//         }

        TtodayContent=db.getSumary();
        TtodayQuestion=db.getQuestion();
        TtxtCh1=db.getCh1();
        TtxtCh2=db.getCh2();
        TtxtCh3=db.getCh3();
        TtxtCh4=db.getCh4();
        TtxtAnswer=db.getAnswer();
        TtxtExplantion=db.getExplanation();
        Toast.makeText(Helpline.this, " "+TtodayContent.size(),Toast.LENGTH_SHORT).show();
            //TtodayWord=db.get;




//            Toast.makeText(Helpline.this," "+value, Toast.LENGTH_SHORT).show();
//
//            if(sessionManager.isQ1()==1){
//                TtodayWord.add(todayWord.get(0));
//                TtodayContent.add(todayContent.get(0));
//                TtodayQuestion.add(todayQuestion.get(0));
//                TtxtCh1.add(txtCh1.get(0));
//                TtxtCh2.add(txtCh2.get(0));
//                TtxtCh3.add(txtCh3.get(0));
//                TtxtCh4.add(txtCh4.get(0));
//                TtxtAnswer.add(txtAnswer.get(0));
//                TtxtExplantion.add(txtExplantion.get(0));
//            }
//            if (sessionManager.isQ2()==1){
//                TtodayWord.add(todayWord.get(1));
//                TtodayContent.add(todayContent.get(1));
//                TtodayQuestion.add(todayQuestion.get(1));
//                TtxtCh1.add(txtCh1.get(1));
//                TtxtCh2.add(txtCh2.get(1));
//                TtxtCh3.add(txtCh3.get(1));
//                TtxtCh4.add(txtCh4.get(1));
//                TtxtAnswer.add(txtAnswer.get(1));
//                TtxtExplantion.add(txtExplantion.get(1));
//            }
//
//            if (sessionManager.isQ3()==1){
//                TtodayWord.add(todayWord.get(2));
//                TtodayContent.add(todayContent.get(2));
//                TtodayQuestion.add(todayQuestion.get(2));
//                TtxtCh1.add(txtCh1.get(2));
//                TtxtCh2.add(txtCh2.get(2));
//                TtxtCh3.add(txtCh3.get(2));
//                TtxtCh4.add(txtCh4.get(2));
//                TtxtAnswer.add(txtAnswer.get(2));
//                TtxtExplantion.add(txtExplantion.get(2));
//            }
//            if(value==0){
//                sessionManager.SetQ1(1);
//            }else if (value==1){
//                sessionManager.SetQ2(1);
//            }else if (value==2){
//                sessionManager.SetQ3(1);
//            }

//                if (TtodayWord.isEmpty()) {
//                    TtodayWord.add(todayWord.get(value));
//                    TtodayContent.add(todayContent.get(value));
//                    TtodayQuestion.add(todayQuestion.get(value));
//                    TtxtCh1.add(txtCh1.get(value));
//                    TtxtCh2.add(txtCh2.get(value));
//                    TtxtCh3.add(txtCh3.get(value));
//                    TtxtCh4.add(txtCh4.get(value));
//                    TtxtAnswer.add(txtAnswer.get(value));
//                    TtxtExplantion.add(txtExplantion.get(value));
//                } else {
////                    for (String temp : TtodayWord) {
////                        if (temp != todayWord.get(value)) {
//                            TtodayWord.add(todayWord.get(value));
//                            TtodayContent.add(todayContent.get(value));
//                            TtodayQuestion.add(todayQuestion.get(value));
//                            TtxtCh1.add(txtCh1.get(value));
//                            TtxtCh2.add(txtCh2.get(value));
//                            TtxtCh3.add(txtCh3.get(value));
//                            TtxtCh4.add(txtCh4.get(value));
//                            TtxtAnswer.add(txtAnswer.get(value));
//                            TtxtExplantion.add(txtExplantion.get(value));
////                        }
////                    }
//                }


        listHelp=(ListView)findViewById(R.id.listView_helpline);
        listAnsed=(ListView)findViewById(R.id.listView_helplineanswer);

        TtodayAnseContent=db.getAnsSumary();
        TtxtAnseExplantion=db.getAnsExplanation();
        if(!TtodayAnseContent.isEmpty()){
            listAnsed.setVisibility(View.VISIBLE);
            listAnsed.setAdapter(new listHelpUpdate(Helpline.this, TtodayAnseContent, TtxtAnseExplantion));
        }
        listHelp.setAdapter(new listHelpline(Helpline.this, TtodayContent, TtodayQuestion, TtxtCh1,TtxtCh2, TtxtCh3,TtxtCh4,TtxtAnswer,TtxtExplantion));
//        listHelp.setAdapter();





    }

    public void createNotification(View view) {


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Helpline.this, HomeActivity.class);
        sessionManager.SetAction(1);
        startActivity(i);
        Helpline.this.finish();

    }

    private class listHelpUpdate extends BaseAdapter{

        Context mContext;
        ArrayList<String>  TodayContent, TxtExplantion;
        private LayoutInflater inflater = null;

        private listHelpUpdate(Context context, ArrayList<String> todayContent, ArrayList<String> txtExp){
            mContext=context;
            TodayContent=todayContent;
            TxtExplantion=txtExp;
            inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        @Override
        public int getCount() {
            return TodayContent.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class Holder{

            TextView txtContent;
            TextView txtExplantion;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            final View rowView;
            rowView = inflater.inflate(R.layout.card_hlexp, null);
            holder.txtContent=(TextView)rowView.findViewById(R.id.helplineExpSummary);
            holder.txtExplantion=(TextView)rowView.findViewById(R.id.helplineExpQuestion);

            holder.txtContent.setText(TodayContent.get(position));
            holder.txtExplantion.setText(TxtExplantion.get(position));
            return rowView;
        }
    }



    private class listHelpline extends BaseAdapter{
        Context mContext;
        ArrayList<String>  TodayContent, TodayQuestion, TxtCh1, TxtCh2, TxtCh3, TxtCh4, TxtAnswer, TxtExplantion;
        private LayoutInflater inflater = null;

        private listHelpline(Context context, ArrayList<String> todayContent, ArrayList<String> todayQuestion,ArrayList<String> txtCh1, ArrayList<String> txtCh2, ArrayList<String> txtCh3, ArrayList<String> txtCh4, ArrayList<String> txtAnswer, ArrayList<String> txtExplantion){
            mContext=context;

            TodayContent=todayContent;
            TodayQuestion=todayQuestion;
            TxtCh1=txtCh1;
            TxtCh2=txtCh2;
            TxtCh3=txtCh3;
            TxtCh4=txtCh4;
            TxtAnswer=txtAnswer;
            TxtExplantion=txtExplantion;
            inflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {

            return TtodayContent.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class Holder{
            ImageView im_coins;
            TextView txtContent;
            LinearLayout lt_ch1, lt_ch2, lt_ch3, lt_ch4;
            ImageView btn_ch1, btn_ch2, btn_ch3, btn_ch4;
            TextView txt_Question, txt_ch1, txt_ch2, txt_ch3, txt_ch4;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            final View rowView;

            rowView = inflater.inflate(R.layout.card_hlquestion, null);
            holder.im_coins=(ImageView)rowView.findViewById(R.id.im_coins);
            holder.txtContent=(TextView)rowView.findViewById(R.id.helplineSummary);
            holder.txt_Question=(TextView)rowView.findViewById(R.id.helplineQuestion);

            holder.lt_ch1=(LinearLayout)rowView.findViewById(R.id.HL_ch1_lt);
            holder.btn_ch1=(ImageView)rowView.findViewById(R.id.HL_ch1_btn);
            holder.txt_ch1=(TextView)rowView.findViewById(R.id.HL_ch1_txt);

            holder.lt_ch2=(LinearLayout)rowView.findViewById(R.id.HL_ch2_lt);
            holder.btn_ch2=(ImageView)rowView.findViewById(R.id.HL_ch2_btn);
            holder.txt_ch2=(TextView)rowView.findViewById(R.id.HL_ch2_txt);

            holder.lt_ch3=(LinearLayout)rowView.findViewById(R.id.HL_ch3_lt);
            holder.btn_ch3=(ImageView)rowView.findViewById(R.id.HL_ch3_btn);
            holder.txt_ch3=(TextView)rowView.findViewById(R.id.HL_ch3_txt);

            holder.lt_ch4=(LinearLayout)rowView.findViewById(R.id.HL_ch4_lt);
            holder.btn_ch4=(ImageView)rowView.findViewById(R.id.HL_ch4_btn);
            holder.txt_ch4=(TextView)rowView.findViewById(R.id.HL_ch4_txt);


            holder.txtContent.setText(TodayContent.get(position));
            holder.txt_Question.setText(TodayQuestion.get(position));
            holder.txt_ch1.setText(TxtCh1.get(position));
            holder.txt_ch2.setText(TxtCh2.get(position));
            holder.txt_ch3.setText(TxtCh3.get(position));
            holder.txt_ch4.setText(TxtCh4.get(position));

            holder.lt_ch1.setVisibility(View.VISIBLE);
            holder.lt_ch2.setVisibility(View.VISIBLE);
            holder.lt_ch3.setVisibility(View.VISIBLE);
            holder.lt_ch4.setVisibility(View.VISIBLE);

            holder.txt_ch1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(Objects.equals(TxtCh1.get(position), TxtAnswer.get(position))){
                    showAlertDialog(mContext, "Answer is Correct", "", R.drawable.thumbs);
                    db.updateQuestion(TodayContent.get(position),1);
                    holder.lt_ch1.setVisibility(View.GONE);
                    holder.lt_ch2.setVisibility(View.GONE);
                    holder.lt_ch3.setVisibility(View.GONE);
                    holder.lt_ch4.setVisibility(View.GONE);
                }else{
                    showAlertDialog(mContext, "Answer is Incorrect", "", R.drawable.down);
                }
                }
            });

            holder.txt_ch2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Objects.equals(TxtCh2.get(position), TxtAnswer.get(position))){
                        showAlertDialog(mContext, "Answer is Correct", "", R.drawable.thumbs);
                        db.updateQuestion(TodayContent.get(position),1);
                        holder.lt_ch1.setVisibility(View.GONE);
                        holder.lt_ch2.setVisibility(View.GONE);
                        holder.lt_ch3.setVisibility(View.GONE);
                        holder.lt_ch4.setVisibility(View.GONE);
                    }else{
                        showAlertDialog(mContext, "Answer is Incorrect", "", R.drawable.down);
                    }
                }
            });

            holder.txt_ch3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Objects.equals(TxtCh3.get(position), TxtAnswer.get(position))){
                        showAlertDialog(mContext, "Answer is Correct", "", R.drawable.thumbs);
                        db.updateQuestion(TodayContent.get(position),1);
                        holder.lt_ch1.setVisibility(View.GONE);
                        holder.lt_ch2.setVisibility(View.GONE);
                        holder.lt_ch3.setVisibility(View.GONE);
                        holder.lt_ch4.setVisibility(View.GONE);
                    }else{
                        showAlertDialog(mContext, "Answer is Incorrect", "", R.drawable.down);
                    }
                }
            });

            holder.txt_ch4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Objects.equals(TxtCh4.get(position), TxtAnswer.get(position))){
                        showAlertDialog(mContext, "Answer is Correct", "", R.drawable.thumbs);
                        db.updateQuestion(TodayContent.get(position),1);
                        holder.lt_ch1.setVisibility(View.GONE);
                        holder.lt_ch2.setVisibility(View.GONE);
                        holder.lt_ch3.setVisibility(View.GONE);
                        holder.lt_ch4.setVisibility(View.GONE);
                    }else{
                        showAlertDialog(mContext, "Answer is Incorrect", "", R.drawable.down);
                    }
                }
            });

            return rowView;
        }
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
