package in22labs.englishlearning.DictionaryHome;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import in22labs.englishlearning.R;

public class Dictionary extends AppCompatActivity {
    String[] ArrdictTitle, ArrdictMeaning;
    String dictTitle, dictMeaning;
    ListView lt_dictlist;
    public int limit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        lt_dictlist=(ListView)findViewById(R.id.listView_dictionarylist);
        try {
            JSONObject Jobject = new JSONObject(loadJSONFromAsset());
            JSONArray Jarray = Jobject.getJSONArray("results");
            limit = Jarray.length();
            ArrdictTitle=new String[limit];
            ArrdictMeaning=new String[limit];

            for(int i =0; i<limit;i++){
                JSONObject object = Jarray.getJSONObject(i);
                dictTitle=object.getString("title");
                dictMeaning=object.getString("mean");

                ArrdictTitle[i]=dictTitle;
                ArrdictMeaning[i]=dictMeaning;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lt_dictlist.setAdapter(new cardDictionary(Dictionary.this, ArrdictTitle, ArrdictMeaning));

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("dictionary.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private class cardDictionary extends BaseAdapter {
        private LayoutInflater inflater = null;
        Context mContext;
        String[] vtitle, vmean;

        private cardDictionary(Context context, String[] title, String[] mean){
            mContext=context;

            vtitle=title;
            vmean=mean;

            inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return vtitle.length;
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
            TextView txt_titile;
            LinearLayout lt_popup;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Holder holder = new Holder();
            final View rowView;

            rowView = inflater.inflate(R.layout.card_videolist, null);
            holder.txt_titile=(TextView)rowView.findViewById(R.id.dict_content);
            holder.lt_popup=(LinearLayout) rowView.findViewById(R.id.lt_dict1);


            holder.txt_titile.setText(vtitle[position]);


            holder.lt_popup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return rowView;
        }
    }
}
