package com.example.fitlifecenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Clase(
    var idClase: Int,
    var entrenador: String,
    var tipo: String,
    var hora: String,
    var fecha: String

) : Parcelable