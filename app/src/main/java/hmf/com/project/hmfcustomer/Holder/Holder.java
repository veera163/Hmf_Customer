package hmf.com.project.hmfcustomer.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import hmf.com.project.hmfcustomer.R;

/**
 * Created by home on 6/18/2018.
 */

public class Holder extends RecyclerView.ViewHolder {

    public TextView order;

    public Holder(View itemView) {
        super(itemView);

        order=(TextView)itemView.findViewById(R.id.order);

    }
}
