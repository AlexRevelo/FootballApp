package com.files.football.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.files.football.R
import com.files.football.dao.LigaDao

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        initApp()


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    private fun initApp(){
        val pref = getSharedPreferences("preference", 0)
        val editor = pref.edit()
        val first = pref.getBoolean("first",false)

        if (!first){
            LigaDao().insertLigas(this)
            editor.putBoolean("first", true)
            editor.apply()
        }
    }


}