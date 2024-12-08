package com.example.fitlifecenter.ui.clases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitlifecenter.Clase
import com.example.fitlifecenter.ClaseData
import com.example.fitlifecenter.R
import com.example.fitlifecenter.databinding.FragmentClasesBinding

class ClasesFragment : Fragment() {
    private var _binding: FragmentClasesBinding? = null
    private lateinit var linearContainer: LinearLayout
    private lateinit var txtName : TextView
    private lateinit var txtMes: TextView
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar : Button

    private var claseAEditar: Clase? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClasesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val view = inflater.inflate(R.layout.fragment_clases, container, false)

        // Configurar el botón para abrir ClaseFragment
        val btnAgregar = root.findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val action = ClasesFragmentDirections.actionVerClaseEntrenadorFragmentToClaseFragment("")
            findNavController().navigate(action)
        }


        linearContainer = root.findViewById(R.id.linearLayoutContainer)

        for (clase in ClaseData.listaClases) {
            val cardView = layoutInflater.inflate(R.layout.diseniotarjeta, linearContainer, false)

            txtName = cardView.findViewById(R.id.txtName)
            txtMes = cardView.findViewById(R.id.txtMes)
            btnEditar = cardView.findViewById(R.id.btnEditar)
            btnEliminar = cardView.findViewById(R.id.btnEliminar)
            txtName.text = clase.tipo
            txtMes.text = clase.fecha + " a las "+clase.hora


            btnEditar.setOnClickListener {
                val idClase = clase.idClase.toString()
                val action = ClasesFragmentDirections.actionVerClaseEntrenadorFragmentToClaseFragment(idClase)
                findNavController().navigate(action)
            }

            btnEliminar.setOnClickListener {
                ClaseData.listaClases.remove(clase)
                linearContainer.removeView(cardView)
            }
            linearContainer.addView(cardView)
        }


        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}