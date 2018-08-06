package com.anyasoft.es.Hmf_Customer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anyasoft.es.Hmf_Customer.R;

/**
 * Created by home on 6/14/2018.
 */

public class Redemption  extends Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_redemption, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=(TextView)view.findViewById(R.id.textView);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Redemption");

        textView.setText(getActivity().getTitle());

    }
}
