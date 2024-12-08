package com.example.fitlifecenter

class Entrenador (
    idUsuario: Int = 0,
    nombre: String = "",
    usuario: String = "",
    contrasena: String = "",
    rol: String = "",
    var especialidad: String = "",
    var esEntrenador: Boolean = true,
    var calificacionPromedio: Float = 0f,
    var horasTrabajadas: Int = 0,
    var clasesAsignadas: List<String> = emptyList(),
    var asistenciaMensual: Int = 0
) : Usuario(idUsuario, nombre,
    usuario, contrasena, rol) {

    constructor(
        idUsuario: Int,
        nombre: String,
        usuario: String,
        contrasena: String,
        rol: String
    ) : this(
        idUsuario,
        nombre,
        usuario,
        contrasena,
        rol,
        especialidad = "",
        esEntrenador = true,
        calificacionPromedio = 0f,
        horasTrabajadas = 0,
        clasesAsignadas = emptyList(),
        asistenciaMensual = 0
    )
}