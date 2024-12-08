package com.example.fitlifecenter

object ClaseData{
    var listaClases: MutableList<Clase> = mutableListOf()

    fun obtenerClasePorId(idClase: Int): Clase? {
        return listaClases.find { it.idClase == idClase }
    }
}