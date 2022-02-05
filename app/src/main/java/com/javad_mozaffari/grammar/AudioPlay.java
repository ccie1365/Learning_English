package com.javad_mozaffari.grammar;


import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.javad_mozaffari.grammar.R;


public class AudioPlay extends AppCompatActivity {

    MediaPlayer player;
    public static String ID = "id";
    int id;
    Bundle bundle;
    private TextView name, textCurrentTime, textTotalDuration;
    public ImageButton play, btnBack, btnForward;
   private SeekBar seekBar;
    private Handler handler = new Handler();
    private int seekForwardTime = 5 * 1000, seekBackwardTime = 5 * 1000;





    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);


        id = Integer.parseInt(getIntent().getStringExtra(ID));

        bundle = getIntent().getExtras();
        name = findViewById(R.id.txt_detail);
        name.setText(bundle.getString("name"));

        play = findViewById(R.id.btnPlay);
        seekBar = findViewById(R.id.seek);
        player = new MediaPlayer();
        textCurrentTime = findViewById(R.id.textCurrentTime);
        textTotalDuration = findViewById(R.id.textTotalDuration);
        btnBack = findViewById(R.id.btnBack);
        btnForward = findViewById(R.id.btnForward);

        seekBar.setMax(100);


        //play and pause button click listener
        play.setOnClickListener(view -> {
            if (player.isPlaying()) {
                handler.removeCallbacks(updater);
                player.pause();
                play.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            } else {
                player.start();
                play.setImageResource(R.drawable.ic_baseline_pause_24);
                updateSeekBar();
            }
        });


        prepareMediaPlayer();

        seekBar.setOnTouchListener((view, motionEvent) -> {
            SeekBar seekBar = (SeekBar) view;
            int playPosition = (player.getDuration() / 100) * seekBar.getProgress();
            player.seekTo(playPosition);
            textCurrentTime.setText(milliSecondsToTimer(player.getCurrentPosition()));
            return false;

        });
        player.setOnBufferingUpdateListener((player, i) -> seekBar.setSecondaryProgress(i));

        player.setOnCompletionListener(player -> {
            seekBar.setProgress(0);
            play.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            textCurrentTime.setText(R.string.zero);
            textTotalDuration.setText(R.string.zero);
            player.reset();
            prepareMediaPlayer();
        });

        btnForward.setOnClickListener(view -> {
            int currentPosition = player.getCurrentPosition();
            if (currentPosition + seekForwardTime <= player.getDuration()) {
                player.seekTo(currentPosition + seekForwardTime);
            } else {
                player.seekTo(player.getDuration());
            }
        });

        btnBack.setOnClickListener(view -> {
            int currentPosition = player.getCurrentPosition();
            if (currentPosition - seekBackwardTime >= 0) {
                player.seekTo(currentPosition - seekBackwardTime);
            } else {
                player.seekTo(0);
            }
        });


    }

    private void prepareMediaPlayer() {
        try {
            player.setDataSource(bundle.getString("mp3"));
            player.prepare();
            textTotalDuration.setText(milliSecondsToTimer(player.getDuration()));
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }




    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = player.getCurrentPosition();
            textCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };


    private void updateSeekBar() {
        if (player.isPlaying()) {
            seekBar.setProgress((int) (((float) player.getCurrentPosition() / player.getDuration()) * 100));
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
        player.stop();
    }


}