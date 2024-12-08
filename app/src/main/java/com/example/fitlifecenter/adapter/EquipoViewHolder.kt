package com.example.fitlifecenter.adapter

import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifecenter.ClaseData
import com.example.fitlifecenter.R
import com.example.fitlifecenter.Equipo
import com.example.fitlifecenter.EquipoData

class EquipoViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private var txt_varNombreEquipo: TextView = view.findViewById(R.id.txt_varNombreEquipo)
    private var txt_varUltimaReparacion: TextView = view.findViewById(R.id.txt_varUltimaReparacion)
    private var swt_Disponible: Switch = view.findViewById(R.id.swt_Disponible)

    fun render(equipo: Equipo){
        txt_varNombreEquipo.text = equipo.nombreEquipo
        txt_varUltimaReparacion.text = equipo.fechaEquipo
        swt_Disponible.isChecked = equipo.disponibleEquipo
    }
    fun initListener(rol: String,equipo: Equipo){
        swt_Disponible.setOnCheckedChangeListener { buttonView, isChecked ->
            if (rol.equals("owner")){
                equipo.disponibleEquipo=isChecked
                EquipoData.actualizarDisponible(equipo.idEquipo,isChecked)

            }else{
                swt_Disponible.isChecked = equipo.disponibleEquipo
            }
        }
    }
}