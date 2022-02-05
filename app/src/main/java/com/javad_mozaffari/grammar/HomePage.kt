package com.javad_mozaffari.grammar

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.javad_mozaffari.grammar.R
import com.javad_mozaffari.grammar.Room_Activities.RoomActivity
import com.javad_mozaffari.grammar.SplashScreen.Companion.mediaplayerSplash
import kotlin.system.exitProcess

class HomePage : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

      val  bottomNavigation:BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.audio -> audio()
                R.id.video -> video()
                R.id.info -> info()
                R.id.vocabulary -> room()
            }
            true
        }
    }

    private fun audio() {
        var audioPage = Intent(this, AudioList::class.java)
        startActivity(audioPage)
    }

    private fun video() {
        var videoPage = Intent(this, VideoList::class.java)
        startActivity(videoPage)
    }

    private fun info() {
        var infoPage = Intent(this, InfoPage::class.java)
        startActivity(infoPage)
    }

    private fun room() {
        var infoPage = Intent(this, RoomActivity::class.java)
        startActivity(infoPage)
    }

    override fun onBackPressed() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setTitle("خروج از برنامه")
            .setMessage("آیا قصد خروج از برنامه را دارید؟")
            .setIcon(R.drawable.i)


            .setPositiveButton("بله"
            ) { _, _ ->
                moveTaskToBack(true)
                exitProcess(1)
                if (mediaplayerSplash.isPlaying) {
                    mediaplayerSplash.stop()
                }
            }
            .setNegativeButton("خیر"
            ) { dialog, _ -> dialog.dismiss() }


        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

}