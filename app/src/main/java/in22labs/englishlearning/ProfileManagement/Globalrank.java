package in22labs.englishlearning.ProfileManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in22labs.englishlearning.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Globalrank extends Fragment {


    public Globalrank() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_globalrank, container, false);
    }

}
