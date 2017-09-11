package in22labs.englishlearning.HomeTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import in22labs.englishlearning.Framework.Testmodule.Testmodule;
import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.SessionManager;

/**
 * Created by Ratan on 7/29/2015.
 */
public class ProfileHome extends Fragment {
    LinearLayout testStart;
    SessionManager session;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile,null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        testStart=(LinearLayout)getActivity().findViewById(R.id.testAccess);
        session=new SessionManager(getActivity());
        testStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Testmodule.class);
                startActivity(i);
                session.setFragpo(0);
            }
        });
    }
}
