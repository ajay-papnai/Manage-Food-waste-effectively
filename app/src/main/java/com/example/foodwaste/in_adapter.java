package com.example.foodwaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class in_adapter extends RecyclerView.Adapter<in_adapter.MyViewHolder> {

    private List<Inventory_item> data ;
    private Context context;

    public in_adapter( Context context , List<Inventory_item> data) {
        this.context = context ;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycler_list , parent , false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Inventory_item item = data.get(position);
        holder.food_name.setText(item.getFood_name());
        holder.qty.setText(item.getQty());
        holder.pr.setText(item.getPurchase());
        holder.exp.setText(item.getExpiry());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "sport : "+data.get(position).getFood_name(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView food_name, qty, pr , exp ;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            food_name = itemView.findViewById(R.id.food_name);
            qty = itemView.findViewById(R.id.qty);
            pr = itemView.findViewById(R.id.purchase);
            exp = itemView.findViewById(R.id.expiry);
        }
    }
}
