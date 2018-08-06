package hmf.com.project.hmfcustomer.Adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hmf.com.project.hmfcustomer.Holder.Holder;
import hmf.com.project.hmfcustomer.R;

import java.util.List;

/**
 * Created by home on 6/18/2018.
 */

public class OrdersAdpter extends RecyclerView.Adapter<Holder> {

    private Context context;
    private List<String> orders;

    public OrdersAdpter(Context context, List<String> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.orders_list, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.order.setText(orders.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
