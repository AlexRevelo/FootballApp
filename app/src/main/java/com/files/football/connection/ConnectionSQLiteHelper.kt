package com.files.football.connection

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import com.files.football.utilities.UtilitiesBD

class ConnectionSQLiteHelper
/**
 * Metodo para realizar la conexion a la base de datos local
 * @param context context de la clase donde se realiza la peticion a la conexion
 * @param name nombre de la base de datos.
 * @param factory factor de la base de datos
 * @param version version de la base de datos
 */
    (
    context: Context?,
    name: String?,
    factory: CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    /**
     * Metodo para la creacion de la tabla en la base de datos.
     * @param db
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(UtilitiesBD.CREAR_TABLA_PERIODO)
    }

    /**
     * Metodo para el manejo de versiones de la tabla.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS liga")
        onCreate(db)
    }
}