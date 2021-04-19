package com.files.football.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.files.football.connection.ConnectionSQLiteHelper
import com.files.football.dao.entitie.Liga
import com.files.football.dao.interfaces.ILiga
import com.files.football.utilities.UtilitiesBD
import java.util.*

class LigaDao : ILiga {


    /**
     * metodo que permite consultar las ligas que se encuentran en la base de datos SQLite
     * @param c Contexto principal
     */
    override fun consultarLigas(c: Context): ArrayList<String> {
        val conn = ConnectionSQLiteHelper(c, "bd_liga", null, 1)
        var ligasObject =  ArrayList<Liga>()
        var ligas =  ArrayList<String>()
        val db: SQLiteDatabase = conn.getReadableDatabase()
        try {
            val cursor =
                db.rawQuery("SELECT * FROM " + UtilitiesBD.TABLA_LIGA, null)
            cursor.moveToFirst()
            Log.e("Prueba",cursor.getString(1))

            var liga: Liga? = null
            do {
                liga = Liga(
                    cursor.getInt(0),
                    cursor.getString(1)
                )
                ligasObject.add(liga)

            }while (cursor.moveToNext())

            for (i in 0 until ligasObject.size) {
                ligas.add(ligasObject.get(i).liga)
            }
        }catch (e: Exception) {
            e.printStackTrace()
            Log.e("Prueba",e.message+"")
        }

        return ligas
    }

    /**
     * Metodo que inserta ligas en la base de datos SQLite
     */
    override fun insertLigas(c: Context){
        val conn = ConnectionSQLiteHelper(c, "bd_liga", null, 1)
        val db = conn.writableDatabase

        val values = ContentValues()
        val values2 = ContentValues()
        val values3 = ContentValues()
        values.put(UtilitiesBD.CAMPO_ID, 1)
        values.put(UtilitiesBD.CAMPO_LIGA, "Spanish La Liga")
        values2.put(UtilitiesBD.CAMPO_ID, 2)
        values2.put(UtilitiesBD.CAMPO_LIGA, "Copa Libertadores")
        values3.put(UtilitiesBD.CAMPO_ID, 3)
        values3.put(UtilitiesBD.CAMPO_LIGA, "Coppa Italia")
        db.insert(UtilitiesBD.TABLA_LIGA, UtilitiesBD.CAMPO_ID.toString() + "", values)
        db.insert(UtilitiesBD.TABLA_LIGA, UtilitiesBD.CAMPO_ID.toString() + "", values2)
        db.insert(UtilitiesBD.TABLA_LIGA, UtilitiesBD.CAMPO_ID.toString() + "", values3)
        db.close()
    }

}