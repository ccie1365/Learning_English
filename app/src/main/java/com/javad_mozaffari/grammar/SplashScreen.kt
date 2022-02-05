package com.javad_mozaffari.grammar

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.javad_mozaffari.grammar.R

class SplashScreen : AppCompatActivity() {

    private val splashTime:Long=6000

    companion object {
        lateinit var mediaplayerSplash: MediaPlayer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mediaplayerSplash =MediaPlayer.create(this, R.raw.intro)
        mediaplayerSplash.start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomePage::class.java))
            finish()
        },splashTime)
    }
}