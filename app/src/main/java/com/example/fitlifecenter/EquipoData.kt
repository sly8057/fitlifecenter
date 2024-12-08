package com.example.fitlifecenter

object EquipoData {
    var listaEquipo: MutableList<Equipo> = mutableListOf()

    fun obtenerEquipoPorId(idEquipo: Int): Equipo? {
        return listaEquipo.find { it.idEquipo == idEquipo }
    }
    fun actualizarDisponible(idEquipo: Int,disponibilidad:Boolean){
        val equipo = obtenerEquipoPorId(idEquipo)
        equipo?.let {
            it.disponibleEquipo = disponibilidad
        }
    }
}