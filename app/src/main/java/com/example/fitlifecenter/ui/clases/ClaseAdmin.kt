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
import com.example.fitlifecenter.ClaseData
import com.example.fitlifecenter.R
import com.example.fitlifecenter.databinding.FragmentVercadBinding

class ClaseAdmin : Fragment() {

    private var _binding: FragmentVercadBinding? = null
    private lateinit var linearContainer: LinearLayout
    private lateinit var txtName : TextView
    private lateinit var txtMes: TextView
    private lateinit var txtClase: TextView
    private lateinit var txtEntrenador : TextView
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar : Button
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            // param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVercadBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnAgregar = root.findViewById<Button>(R.id.btnAgregar)
        btnAgregar.setOnClickListener {
            val action = ClasesFragmentDirections.actionVerClaseEntrenadorFragmentToClaseFragment("")
            findNavController().navigate(action)
        }
        linearContainer = root.findViewById(R.id.linearLayoutContainer)

        for (clase in ClaseData.listaClases) {
            val cardView = layoutInflater.inflate(R.layout.diseniotarjeta, linearContainer, false)
            txtClase = cardView.findViewById(R.id.txtClase)
            txtEntrenador = cardView.findViewById(R.id.txtEntrenador)

            txtName = cardView.findViewById(R.id.txtName)
            txtMes = cardView.findViewById(R.id.txtMes)
            btnEditar = cardView.findViewById(R.id.btnEditar)
            btnEliminar = cardView.findViewById(R.id.btnEliminar)

            txtEntrenador.text = "Entrenador: "
            txtName.text = clase.tipo
            txtMes.text = clase.entrenador


            btnEliminar.setOnClickListener {
                ClaseData.listaClases.remove(clase)
                linearContainer.removeView(cardView)
            }

            btnEditar.setOnClickListener {
                val idClase = clase.idClase.toString()
                val action = ClasesFragmentDirections.actionVerClaseEntrenadorFragmentToClaseFragment(idClase)
                findNavController().navigate(action)
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