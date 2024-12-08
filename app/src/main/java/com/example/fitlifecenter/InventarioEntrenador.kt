package com.example.fitlifecenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifecenter.adapter.EquipoAdapter

class InventarioEntrenador : Fragment() {

    private lateinit var equipoAdapter: EquipoAdapter

    private lateinit var rcvInvEntrenador: RecyclerView
    private lateinit var listEquipo:MutableList<Equipo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventario_entrenador, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
        initUi(view)
    }

    private fun initComponents(view: View){
        rcvInvEntrenador = view.findViewById(R.id.rcv_inv_entrenador)
        listEquipo = mutableListOf()
        listEquipo= EquipoData.listaEquipo
    }

    private fun initUi(view: View){

        equipoAdapter = EquipoAdapter(listEquipo,"trainer")
        rcvInvEntrenador.layoutManager = LinearLayoutManager(view.context,
            LinearLayoutManager.VERTICAL,false)
        rcvInvEntrenador.adapter = equipoAdapter
    }

}