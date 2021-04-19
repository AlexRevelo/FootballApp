package com.files.football.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.files.football.R
import com.files.football.model.Team
import com.google.gson.Gson
import kotlinx.android.synthetic.main.detail_team.*
import kotlinx.android.synthetic.main.detail_team.imgBadge

class DetailTeam : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_team)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        val strTeam = intent.extras!!.getString("team")
        val gson = Gson()
        val team = gson.fromJson(strTeam, Team::class.java)

        txtTeam.text = team.strTeam
        Glide.with(this).load(team.strTeamBadge).into(imgBadge)
        Glide.with(this).load(team.strTeamJersey).into(imgJersey)
        Glide.with(this).load(team.strStadiumThumb).into(imgStadium)
        txtStadiumD.text = team.strStadium
        txtDescription.text = team.strDescriptionEN
        txtFoundation.text = "Foundation year: "+team.intFormedYear
        var web = if(team.strWebsite.equals("")|| team.strWebsite==null)  View.INVISIBLE else View.VISIBLE
        imgWeb.setVisibility(web)
        imgWeb.setOnClickListener{
            val url = Uri.parse("https://"+team.strWebsite)
            val intent = Intent(Intent().action,url)
            startActivity(intent)
        }
        var face = if(team.strFacebook.equals("")|| team.strFacebook==null)  View.INVISIBLE else View.VISIBLE
        imgFacebook.setVisibility(face)
        imgFacebook.setOnClickListener{
            val url = Uri.parse("https://"+team.strFacebook)
            val intent = Intent(Intent().action,url)
            startActivity(intent)
        }
        var insta = if(team.strInstagram.equals("")|| team.strInstagram==null)  View.INVISIBLE else View.VISIBLE
        imgInstagram.setVisibility(insta)
        imgInstagram.setOnClickListener{
            val url = Uri.parse("https://"+team.strInstagram)
            val intent = Intent(Intent().action,url)
            startActivity(intent)
        }

        var twit = if(team.strTwitter.equals("")|| team.strTwitter==null)  View.INVISIBLE else View.VISIBLE
        imgTwitter.setVisibility(twit)
        imgTwitter.setOnClickListener{
            val url = Uri.parse("https://"+team.strTwitter)
            val intent = Intent(Intent().action,url)
            startActivity(intent)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}