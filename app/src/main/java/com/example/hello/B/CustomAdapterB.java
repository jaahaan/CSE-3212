package com.example.hello.B;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;

import java.util.ArrayList;

public class CustomAdapterB extends RecyclerView.Adapter<CustomAdapterB.holder> {

    ArrayList<ModelB> list;

    public CustomAdapterB(ArrayList<ModelB> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sample_view, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImageId());
        holder.title.setText(list.get(position).getTitle());
        holder.subtitle.setText(list.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title, subtitle;

        public holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}
