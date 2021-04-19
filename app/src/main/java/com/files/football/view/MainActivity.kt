package com.files.football.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.files.football.R
import com.files.football.adapter.TeamAdapter
import com.files.football.connection.ConnectionSQLiteHelper
import com.files.football.dao.LigaDao
import com.files.football.interfaz.IApiService
import com.files.football.dao.entitie.Liga
import com.files.football.model.Team
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var service: IApiService
    val TAG_LOGS = "MainActivity"
    var ligas =  ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ligas = LigaDao().consultarLigas(this)

        spnLiga.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ligas)

        spnLiga.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                getData(ligas.get(p2))
                txtLiga.text = ligas.get(p2)

            }
        }

        rvTeam.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }


    fun getData(liga:String){
        progressBar.setVisibility(View.VISIBLE)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<IApiService>(IApiService::class.java)

        service.getPostById(liga).enqueue(object: Callback<Team>{
            override fun onResponse(call: Call<Team>?, response: Response<Team>?) {
                val post = response?.body()
                if (post !=null && post.teams!=null){
                    val adapter = TeamAdapter(post.teams as ArrayList<Team>)
                    rvTeam.adapter = adapter
                }


                progressBar.setVisibility(View.INVISIBLE)
            }
            override fun onFailure(call: Call<Team>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

}