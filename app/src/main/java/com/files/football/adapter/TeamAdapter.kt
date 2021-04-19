package com.files.football.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.files.football.R
import com.files.football.model.Team
import com.files.football.view.DetailTeam
import com.google.gson.Gson
import java.util.ArrayList

class TeamAdapter(var list: ArrayList<Team>): RecyclerView.Adapter<TeamAdapter.TeamHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        var v =LayoutInflater.from(parent.context).inflate(R.layout.cardview_team, parent,false)
        return TeamHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {

        holder.bindItems(list[position])
    }

    class TeamHolder(view:View)  : RecyclerView.ViewHolder(view){

        fun bindItems(data: Team){
            var name: TextView = itemView.findViewById(R.id.txtName)
            var stadium: TextView = itemView.findViewById(R.id.txtStadiumD)
            var imgBadge: ImageView = itemView.findViewById(R.id.imgBadge)

            name.text = data.strTeam
            stadium.text = "Stadium: "+data.strStadium

            Glide.with(itemView.context).load(data.strTeamBadge).into(imgBadge)

            itemView.setOnClickListener{
                val gson = Gson()
                val strData = gson.toJson(data)
                val intent = Intent(itemView.context, DetailTeam::class.java)
                intent.putExtra("team",strData)
                itemView.context.startActivity(intent)
            }
        }
    }
}