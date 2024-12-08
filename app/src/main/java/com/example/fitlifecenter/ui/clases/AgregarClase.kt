package com.example.fitlifecenter.ui.clases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fitlifecenter.Clase
import com.example.fitlifecenter.ClaseData
import com.example.fitlifecenter.R
import com.example.fitlifecenter.databinding.FragmentAgregarclaseBinding
import java.util.Calendar

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarClase.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarClase : Fragment() {

    private lateinit var entrenador: Spinner
    private lateinit var tipoClase: Spinner
    private lateinit var horaClase: Spinner
    private lateinit var fechaClase: CalendarView
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar : Button
    private lateinit var fecha: String
    private var trainer : String = " "
    private var tipo: String = " "
    private var hora: String = " "

    private var idClase: Int = -1

    private var _binding: FragmentAgregarclaseBinding? = null
    private val binding get() = _binding!!

    /*companion object {
        private const val ARG_ID_CLASE = "idClase"

        fun newInstance(idClase: Int?): AgregarClase {
            val fragment = AgregarClase()
            val args = Bundle()
            if (idClase != null) {
                args.putInt(ARG_ID_CLASE, idClase)
            }
            fragment.arguments = args
            return fragment
        }
    }*/
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           // param1 = it.getString(ARG_PARAM1)
           // param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgregarclaseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        arguments?.let {
            //idClase = it.getInt("idClase", -1)
            val idClaseString = it.getString("idClase", "-1")  // Recibes como String
            idClase = idClaseString.toIntOrNull() ?: -1  // Convierte a Int (o usa -1 si no es válido)
        }
        entrenador = root.findViewById(R.id.spnEntrenador)
        tipoClase = root.findViewById(R.id.spnTipoC)
        horaClase = root.findViewById(R.id.spnHora)
        fechaClase = root.findViewById(R.id.cV)
        btnAgregar = root.findViewById(R.id.btnAgregar)
        btnRegresar = root.findViewById(R.id.btnRegresar)




        /*binding.cV.setOnDateChangeListener { _, year, month, dayOfMonth ->
            fecha = "$dayOfMonth/${month + 1}/$year"
        }*/
        val today = Calendar.getInstance()
        fecha = "${today.get(Calendar.DAY_OF_MONTH)}/${today.get(Calendar.MONTH) + 1}/${today.get(
            Calendar.YEAR)}"
        binding.cV.setOnDateChangeListener { _, year, month, dayOfMonth ->
            fecha = "$dayOfMonth/${month + 1}/$year"
            actualizarHorasDisponibles(fecha)
        }
        val entrenadores = resources.getStringArray(R.array.Entrenadores)
        val ets = ArrayAdapter(requireContext(),R.layout.spinner_color_dropdown,entrenadores)
        entrenador.adapter = ets
        entrenador.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                trainer = entrenadores[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        val tipos = resources.getStringArray(R.array.TiposClase)
        val tps = ArrayAdapter(requireContext(),R.layout.spinner_color_dropdown,tipos)
        tipoClase.adapter = tps
        tipoClase.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tipo = tipos[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        val horas = resources.getStringArray(R.array.HorasClase)
        val hrs = ArrayAdapter(requireContext(),R.layout.spinner_color_dropdown,horas)
        horaClase.adapter = hrs
        horaClase.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //hora = horas[p2]
                hora = horaClase.getItemAtPosition(p2).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btnAgregar.setOnClickListener { guardarClase() /*insertData()*/ }
        btnRegresar.setOnClickListener { findNavController().popBackStack() }


        return root
    }
    private fun guardarClase() {
        // Validar que todos los campos están completos
        if (trainer.isBlank() || tipo.isBlank() || hora.isBlank() || fecha.isBlank()) {
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (idClase != -1) {
            // Editar clase existente
            val clase = ClaseData.obtenerClasePorId(idClase)
            clase?.let {
                it.entrenador = trainer
                it.tipo = tipo
                it.hora = hora
                it.fecha = fecha
                Toast.makeText(requireContext(), "Clase actualizada exitosamente", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Crear nueva clase
            val nuevaClase = Clase(
                idClase = ClaseData.listaClases.size + 1,
                entrenador = trainer,
                tipo = tipo,
                hora = hora,
                fecha = fecha
            )
            ClaseData.listaClases.add(nuevaClase)
            Toast.makeText(requireContext(), "Clase agregada exitosamente", Toast.LENGTH_SHORT).show()
        }

        // Regresar a la lista de clases
        findNavController().navigateUp()
    }
    private fun actualizarHorasDisponibles(fechaSeleccionada: String) {
        try {
            val horasTotales = resources.getStringArray(R.array.HorasClase).toMutableList()
            val horasOcupadas = ClaseData.listaClases.filter { it.fecha == fechaSeleccionada }.map { it.hora }
            val horasDisponibles = horasTotales.filterNot { horasOcupadas.contains(it) }

            val adaptador = ArrayAdapter(requireContext(), R.layout.spinner_color_dropdown, horasDisponibles)
            horaClase.adapter = adaptador
            if (horasDisponibles.isEmpty()) {
                Toast.makeText(requireContext(), "No hay horarios disponibles para esta fecha", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error al actualizar las horas disponibles: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


    var cont: Int = 0
    private fun insertData() {
        if (trainer.isBlank() || tipo.isBlank() || hora.isBlank() || fecha.isBlank()) {
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val nuevaClase = Clase(
                idClase = cont,
                entrenador = trainer,
                tipo = tipo,
                hora = hora,
                fecha = fecha
            )
            ClaseData.listaClases.add(nuevaClase)
            cont += 1

            Toast.makeText(requireContext(), "Clase agregada exitosamente", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error al agregar la clase: ${e.message}", Toast.LENGTH_LONG).show()
        }

    }


}