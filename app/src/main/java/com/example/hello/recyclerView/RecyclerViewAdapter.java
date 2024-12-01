package com.example.hello.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hello.R;
import com.example.hello.tech.TechModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.holder> {

    ArrayList<Model> data;
    Context context;

    public RecyclerViewAdapter(ArrayList<Model> data, Context context) {
        this.data = data;
        this.context = context;
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
        final Model model = data.get(position);

        holder.title.setText(model.getTitle());
        holder.subtitle.setText(model.getSubtitle());
        holder.imageView.setImageResource(model.getImageID());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyclerDescriptionActivity.class);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("subtitle", model.getSubtitle());
                intent.putExtra("imgId", model.getImageID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class holder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title, subtitle;
        CardView cardView;
        public holder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.main);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}
