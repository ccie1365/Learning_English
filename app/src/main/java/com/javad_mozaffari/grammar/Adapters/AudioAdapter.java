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
import com.javad_mozaffari.grammar.AudioPlay;
import com.javad_mozaffari.grammar.R;
import com.javad_mozaffari.grammar.SplashScreen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.MyViewHolder> {

    Context context;
    List<Grammar> list_audio;


    public AudioAdapter(Context context, List<Grammar> list_audio) {
        this.context = context;
        this.list_audio = list_audio;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_audio, parent, false);


        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.lesson.setText(list_audio.get(position).getName());




        Picasso.get().load(list_audio.get(position).getLink_img()).into(holder.image_lesson_audio);

    }


    @Override
    public int getItemCount() {

        return list_audio.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView lesson;
        ImageView image_lesson_audio;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson = itemView.findViewById(R.id.name_lesson_audio);




            image_lesson_audio = itemView.findViewById(R.id.img_lesson_audio);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, AudioPlay.class);
            intent.putExtra(AudioPlay.ID, list_audio.get(getAdapterPosition()).getId());
            intent.putExtra("name", list_audio.get(getAdapterPosition()).getName());
            intent.putExtra("mp3", list_audio.get(getAdapterPosition()).getLink_mp3());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            SplashScreen.mediaplayerSplash.stop();
        }
    }
}
