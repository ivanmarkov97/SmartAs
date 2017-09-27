package com.example.ivan.smartas.RecyclerViewListOrder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.smartas.OrderShowActivity;
import com.example.ivan.smartas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 13.09.2017.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orders = new ArrayList<>();
    private Context context;

    public OrderAdapter(){
    }

    public OrderAdapter(Context context){
        this.context = context;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.subjectName.setText(order.getSubject());
        holder.orderType.setText(order.getType());
        holder.orderDate.setText(order.getCreate_date());
        holder.orderDeadline.setText(order.getEnd_date());
        holder.orderCost.setText(String.valueOf(order.getCost()));
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(ArrayList<Order> orders){
        this.orders = orders;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder implements onItemClick{

        TextView subjectName;
        TextView orderType;
        TextView orderDate;
        TextView orderDeadline;
        TextView orderCost;

        public OrderViewHolder(View itemView) {
            super(itemView);

            subjectName = (TextView) itemView.findViewById(R.id.subject_name);
            orderType = (TextView) itemView.findViewById(R.id.order_type);
            orderDate = (TextView) itemView.findViewById(R.id.order_date);
            orderDeadline = (TextView) itemView.findViewById(R.id.order_limit);
            orderCost = (TextView) itemView.findViewById(R.id.order_cost);
        }

        @Override
        public void itemClick(final int pos) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "cliked " + pos, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), OrderShowActivity.class);
                    intent.putExtra("task_id", orders.get(pos).getId());
                    intent.putExtra("task_name", orders.get(pos).getSubject());
                    intent.putExtra("task_type", orders.get(pos).getType());
                    intent.putExtra("task_description", orders.get(pos).getDescription());
                    intent.putExtra("task_cost", orders.get(pos).getCost());
                    intent.putExtra("task_date", orders.get(pos).getCreate_date());
                    intent.putExtra("task_limit", orders.get(pos).getEnd_date());
                    v.getContext().startActivity(intent);
                }
            });
        }

        private void bind(int pos){
            itemClick(pos);
        }
    }
}
