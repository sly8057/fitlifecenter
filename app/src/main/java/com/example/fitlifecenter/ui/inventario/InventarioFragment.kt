package com.example.fitlifecenter.ui.inventario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitlifecenter.R

class InventarioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inventario, container, false)

        val btnAddEquipment = view.findViewById<Button>(R.id.btn_add_equipment)
        btnAddEquipment.setOnClickListener {
            Toast.makeText(context, "Agregar equipo no implementado", Toast.LENGTH_SHORT).show()
            // Lógica para agregar equipo
        }

        return view
    }
}