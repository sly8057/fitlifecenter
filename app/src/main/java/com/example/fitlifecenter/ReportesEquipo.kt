package com.example.fitlifecenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class ReportesEquipo : Fragment() {

    private lateinit var spnEquipo: Spinner
    private lateinit var descripccion: EditText
    private lateinit var btnAgregarReporte: Button
    private lateinit var btnRegresarse: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reportes_equipo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniComponent(view)
        initListener(view)
    }

    private fun iniComponent(view: View){
        spnEquipo = view.findViewById(R.id.spn_equipos)
        descripccion = view.findViewById(R.id.editTextTextMultiLine)
        btnAgregarReporte = view.findViewById(R.id.btn_agregarReporte)
        btnRegresarse = view.findViewById(R.id.btn_regresar)
    }

    private fun initListener(view: View){
        btnAgregarReporte.setOnClickListener{
            if (verificarDatos()){
                val fechaActual = Calendar.getInstance()
                val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                fechaActual.add(Calendar.DATE,-3)
                val fecha = formatoFecha.format(fechaActual.time)
                val nuevoEquipo = Equipo(
                    idEquipo = EquipoData.listaEquipo.size + 1,
                    nombreEquipo = spnEquipo.selectedItem.toString(),
                    disponibleEquipo = false,
                    fechaEquipo = fecha.toString()
                )
                EquipoData.listaEquipo.add(nuevoEquipo)
                Toast.makeText(requireContext(), "Se registro su reporte", Toast.LENGTH_SHORT).show()
                descripccion.text= null
//Falta agragar a lo que hizo nacho
            }
        }
        btnRegresarse.setOnClickListener{
            regresar()
        }
    }
    private fun verificarDatos():Boolean{
        if (descripccion.text.isNotEmpty()){
            return true
        }
        return false
    }

    private fun regresar(){
        parentFragmentManager.popBackStack()
    }

}