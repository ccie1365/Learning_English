package com.javad_mozaffari.grammar.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.javad_mozaffari.grammar.Model.Grammar;
import com.javad_mozaffari.grammar.VideoPlay;
import com.javad_mozaffari.grammar.R;
import com.javad_mozaffari.grammar.SplashScreen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    Context context;
    List<Grammar> list_video;


    public VideoAdapter(Context context, List<Grammar> list_video) {
        this.context = context;
        this.list_video = list_video;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);


        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.lesson_video.setText(list_video.get(position).getName());




        Picasso.get().load(list_video.get(position).getLink_img()).into(holder.image_lesson_video);

    }


    @Override
    public int getItemCount() {

        return list_video.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lesson_video;
        ImageView image_lesson_video;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson_video = itemView.findViewById(R.id.name_lesson_video);




            image_lesson_video = itemView.findViewById(R.id.img_lesson_video);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, VideoPlay.class);
       //     intent.putExtra(VideoPlay.Id, list_video.get(getAdapterPosition()).getId());
            intent.putExtra("name", list_video.get(getAdapterPosition()).getName());

            intent.putExtra("video", list_video.get(getAdapterPosition()).getLink_video());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            SplashScreen.mediaplayerSplash.stop();
        }
    }
}
