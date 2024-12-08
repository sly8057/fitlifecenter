package com.example.fitlifecenter

class Dueno (
    idUsuario: Int = 0,
    nombre: String = "",
    usuario: String = "",
    contrasena: String = "",
    rol: String = "",
    var contactoEmergencia: String = "",
    var esDueno: Boolean = true,
    var contactoTelefono: String = "",
    var ingresosMensuales: Double = 0.0,
    var numeroEmpleados: Int = 0
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
        contactoEmergencia = "",
        esDueno = true,
        contactoTelefono = "",
        ingresosMensuales = 0.0,
        numeroEmpleados = 0
    )
}