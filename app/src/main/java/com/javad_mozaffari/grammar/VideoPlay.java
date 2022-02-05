package com.javad_mozaffari.grammar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.javad_mozaffari.grammar.R;

public class VideoPlay extends AppCompatActivity {


   // public static String Id = "id";
  //  int id;
    Bundle bundle;
   private TextView name, textCurrentTime, textTotalDuration;
    public ImageButton btnVideo, videoForward, videoBack;
    private SeekBar seekBar;
    private Handler handler = new Handler();
    private int seekForwardTime = 5 * 1000, seekBackwardTime = 5 * 1000;
    VideoView video;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);


       // id = Integer.parseInt(getIntent().getStringExtra(Id));

        bundle = getIntent().getExtras();
        name = findViewById(R.id.txt_detail);
        name.setText(bundle.getString("name"));


        seekBar = findViewById(R.id.seek);

        textCurrentTime = findViewById(R.id.textCurrentTime);
        textTotalDuration = findViewById(R.id.textTotalDuration);


        videoBack = findViewById(R.id.videoBack);
        videoForward = findViewById(R.id.videoForward);





        //video player

        btnVideo = findViewById(R.id.btnVideo);
        video = findViewById(R.id.video);
        video.setVideoPath(bundle.getString("video"));
        seekBar.setMax(100);



        btnVideo.setOnClickListener(view -> {


            if (video.isPlaying()) {
                handler.removeCallbacks(updater);
                video.pause();
                btnVideo.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            } else {

                video.start();
                btnVideo.setImageResource(R.drawable.ic_baseline_pause_24);
                updateSeekBar();
                textTotalDuration.setText(milliSecondsToTimer(video.getDuration()));
            }

        });





        video.setOnCompletionListener(video -> {
            textCurrentTime.setText(R.string.zero);
            seekBar.setProgress(0);
            btnVideo.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        });






        videoForward.setOnClickListener(view -> {
            int currentPosition = video.getCurrentPosition();
            if (currentPosition + seekForwardTime <= video.getDuration()) {
                video.seekTo(currentPosition + seekForwardTime);
            } else {
                video.seekTo(video.getDuration());
            }
        });

        videoBack.setOnClickListener(view -> {
            int currentPosition = video.getCurrentPosition();
            if (currentPosition - seekBackwardTime >= 0) {
                video.seekTo(currentPosition - seekBackwardTime);
            } else {
                video.seekTo(0);
            }
        });





        seekBar.setOnTouchListener((view, motionEvent) -> {
            SeekBar seekBar = (SeekBar) view;
            int playPosition = (video.getDuration() / 100) * seekBar.getProgress();
            video.seekTo(playPosition);
            if (video.isPlaying()){
                textCurrentTime.setText(milliSecondsToTimer(video.getCurrentPosition()));
            }

            return false;

        });


    }









    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = video.getCurrentPosition();
            if (video.isPlaying()){
                textCurrentTime.setText(milliSecondsToTimer(currentDuration));
            }

        }
    };


    private void updateSeekBar() {
        if (video.isPlaying()) {
            seekBar.setProgress((int) (((float) video.getCurrentPosition() / video.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }


    private String milliSecondsToTimer(long milliSeconds) {
        String timerString = "";
        String secondsString;

        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timerString = hours + ":";
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        timerString = timerString + minutes + ":" + secondsString;
        return timerString;
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        video.stopPlayback();
    }


}