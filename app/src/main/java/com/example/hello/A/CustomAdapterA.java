package com.example.hello.A;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hello.R;
import com.example.hello.recyclerView.RecyclerDescriptionActivity;

import java.util.ArrayList;

public class CustomAdapterA extends RecyclerView.Adapter<CustomAdapterA.holder>{

    ArrayList<ModelA> data;
    Context context;

    public CustomAdapterA(ArrayList<ModelA> data, Context context) {
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
        ModelA temp = data.get(position);
//        holder.imageView.setImageResource(temp.getImageID());
        holder.title.setText(temp.getTitle());
        holder.subtile.setText(temp.getSubtitle());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecyclerDescriptionActivity.class);
                intent.putExtra("title", temp.getTitle());
                intent.putExtra("subtitle", temp.getSubtitle());
//                intent.putExtra("imageID", temp.getImageID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, subtile;
        CardView cardView;
        public holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            subtile = itemView.findViewById(R.id.subtitle);
            cardView = itemView.findViewById(R.id.main);
        }
    }
}
