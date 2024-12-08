package com.example.fitlifecenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifecenter.adapter.EquipoAdapter

class InventarioDueno : Fragment() {

    private lateinit var equipoAdapter: EquipoAdapter

    private lateinit var rcvInvPatron: RecyclerView
    private lateinit var listEquipo:MutableList<Equipo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventario_dueno, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
        initUi(view)
    }

    private fun initComponents(view: View){
        rcvInvPatron = view.findViewById(R.id.rcv_inv_patron)
        listEquipo = mutableListOf()
        listEquipo= EquipoData.listaEquipo
        //aqui la lista esta vacia falta llenarla con lo que hizo nacho
    }

    private fun initUi(view: View){
        equipoAdapter = EquipoAdapter(listEquipo,"owner")
        rcvInvPatron.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.VERTICAL,false)
        rcvInvPatron.adapter = equipoAdapter
    }

}