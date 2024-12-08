package com.example.fitlifecenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data  class Equipo (var nombreEquipo :String, var fechaEquipo:String, var disponibleEquipo:Boolean, var idEquipo :Int) :
    Parcelable