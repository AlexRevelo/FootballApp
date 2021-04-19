package com.files.football.utilities

object UtilitiesBD {
    //constantes campos tabla
    const val TABLA_LIGA = "liga"
    const val CAMPO_ID = "id_liga"
    const val CAMPO_LIGA = "liga"

    //sql para crear tabla
    const val CREAR_TABLA_PERIODO =
        "CREATE TABLE $TABLA_LIGA($CAMPO_ID INTEGER, $CAMPO_LIGA TEXT)"
}