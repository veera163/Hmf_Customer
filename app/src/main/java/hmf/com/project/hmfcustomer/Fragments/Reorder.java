package hmf.com.project.hmfcustomer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hmf.com.project.hmfcustomer.R;

/**
 * Created by home on 6/21/2018.
 */

public class Reorder extends Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_reorder, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=(TextView)view.findViewById(R.id.textView);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("ReOrder");
        textView.setText(getActivity().getTitle());

    }
}
