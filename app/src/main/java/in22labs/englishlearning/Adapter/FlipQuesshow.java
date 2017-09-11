package in22labs.englishlearning.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import in22labs.englishlearning.R;


public class FlipQuesshow extends BaseAdapter {

    Context mContext;
    String[] Ques;
    String[] Ch1;
    String[] Ch2;




    private static LayoutInflater inflater = null;

    public FlipQuesshow(Context context, String[] ques, String[] ch1, String[] ch2) {
        mContext = context;
        Ques=ques;
        Ch1 = ch1;
        Ch2 = ch2;



        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return Ques.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Holder {
        TextView Ques;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        final View rowView;
        rowView = inflater.inflate(R.layout.cardview, null);
        holder.Ques = (TextView) rowView.findViewById(R.id.Ques);
//        holder.Ques.setText(Ques[position]);
        String Ques1 = setText1(Ques[position]);
        holder.Ques.setText(Html.fromHtml(Ques1));
        return rowView;
    }

    private String setText1(String que) {
        String tempQues="";
        String[] sentence = que.split(" ");
        for (String word : sentence) {
            if (word.equals(Ch1)) {

                tempQues = tempQues + " <font color='red'><u>" + Ch1 + "</u></font>";

            } else if (word.equals(Ch2)) {

                tempQues = tempQues + "  <font color='red'><u>" + Ch2 + "</u></font>";

            } else {
                tempQues = tempQues + " " + word;

            }
        }
        return tempQues;
//        ques.setText(Html.fromHtml(tempQues));
    }


}
