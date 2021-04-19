package com.files.football.interfaz

import com.files.football.model.Team
import retrofit2.Call
import retrofit2.http.*

interface IApiService {

    @GET("search_all_teams.php")
    fun getPostById(@Query("l") id: String): Call<Team>

}