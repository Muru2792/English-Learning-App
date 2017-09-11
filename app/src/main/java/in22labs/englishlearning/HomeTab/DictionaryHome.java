package in22labs.englishlearning.HomeTab;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import in22labs.englishlearning.DictionaryHome.Dictionary;
import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.SessionManager;

/**
 * Created by Ratan on 7/29/2015.
 */
public class DictionaryHome extends Fragment {

    SessionManager session;

    String[] ArrdictTitle, ArrdictMeaning;
    String dictTitle, dictMeaning;
    ListView lt_dictlist;
    public int limit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_dictionary,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        session=new SessionManager(getActivity());
        lt_dictlist=(ListView)getActivity().findViewById(R.id.listView_dictionarylist);
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
        lt_dictlist.setAdapter(new cardDictionary(getActivity(), ArrdictTitle, ArrdictMeaning));
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("dictionary.json");
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

            rowView = inflater.inflate(R.layout.card_dict1, null);
            holder.txt_titile=(TextView)rowView.findViewById(R.id.dict_content);
            holder.lt_popup=(LinearLayout) rowView.findViewById(R.id.lt_dict1);


            holder.txt_titile.setText(vtitle[position]);


            holder.lt_popup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDictionary(vtitle[position], vmean[position]);
                }
            });

            return rowView;
        }
    }

    @SuppressLint("NewApi")
    private void showDictionary(String title, String Content) {

        LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.dialog, null);

        final Dialog popup = new Dialog(getActivity(), R.style.FullHeightDialog);
        popup.setContentView(layout);
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView close=(TextView)layout.findViewById(R.id.txtClose);
        TextView txtTitle=(TextView)layout.findViewById(R.id.textDialog);
        TextView txtContent=(TextView)layout.findViewById(R.id.txtmeaning);
        popup.show();
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();

            }
        });

        txtTitle.setText(title);
        txtContent.setText(Content);

    }


}
