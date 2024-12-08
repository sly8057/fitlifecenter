package com.example.fitlifecenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifecenter.R
import com.example.fitlifecenter.Equipo

class EquipoAdapter(private val equipoList : List<Equipo>,private val rol:String): RecyclerView.Adapter<EquipoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resourse_equipo,parent,false)
        return EquipoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return equipoList.size
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        holder.render(equipoList[position])
        holder.initListener(rol,equipoList[position])
    }
}