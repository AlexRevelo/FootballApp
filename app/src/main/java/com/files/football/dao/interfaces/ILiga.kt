package com.files.football.dao.interfaces

import android.content.Context
import com.files.football.dao.entitie.Liga
import java.util.*

interface ILiga {
    fun consultarLigas(c:Context): ArrayList<String>
    fun insertLigas(c:Context)
}