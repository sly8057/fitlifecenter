package com.example.fitlifecenter.ui.clases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitlifecenter.ClaseData
//import com.example.fitlifecenter.ARG_PARAM1
//import com.example.fitlifecenter.ARG_PARAM2
import com.example.fitlifecenter.R
import com.example.fitlifecenter.databinding.FragmentVercadBinding

/**
 * A simple [Fragment] subclass.
 * Use the [verClaseAdmin.newInstance] factory method to
 * create an instance of this fragment.
 */
class verClaseAdmin : Fragment() {

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


            btnEditar.setOnClickListener {

            }

            btnEliminar.setOnClickListener {
                ClaseData.listaClases.remove(clase)
                linearContainer.removeView(cardView)
            }
            linearContainer.addView(cardView)
        }
        return root
    }


}