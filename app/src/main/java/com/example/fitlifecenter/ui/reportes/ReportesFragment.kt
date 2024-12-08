package com.example.fitlifecenter.ui.reportes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fitlifecenter.R
import com.example.fitlifecenter.databinding.FragmentReportesBinding
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class ReportesFragment : Fragment() {
    private var _binding: FragmentReportesBinding? = null
    private val binding get() = _binding!!
    val retencion = 50f
    var randomizeClass = Random.nextInt(5, 30)
    var randomizeClass1 = Random.nextInt(5, 30)
    var randomizeClass2 = Random.nextInt(5, 30)
    var randomizeClass3 = Random.nextInt(5, 30)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_reportes, container, false)

        _binding = FragmentReportesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Toast.makeText(context, "Generando reporte...", Toast.LENGTH_SHORT).show()
        val months = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
            "Septiembre", "Octubre", "Noviembre", "Diciembre")


        binding.txtMes.text = months[Random.nextInt(0, 11)]
        binding.txtMes1.text = months[Random.nextInt(0, 11)]
        binding.txtMes2.text = months[Random.nextInt(0, 11)]
        binding.txtMes3.text = months[Random.nextInt(0, 11)]

        binding.txtNote.text = "Metrica de $randomizeClass clases*"
        binding.txtNote1.text = "Metrica de $randomizeClass1 clases*"
        binding.txtNote2.text = "Metrica de $randomizeClass2 clases*"
        binding.txtNote3.text = "Metrica de $randomizeClass3 clases*"

        binding.ratingBar.rating = retencion / randomizeClass
        binding.ratingBar1.rating = retencion / randomizeClass1
        binding.ratingBar2.rating = retencion / randomizeClass2
        binding.ratingBar3.rating = retencion / randomizeClass3

        return root
    }

    override fun onResume() {
        super.onResume()
        binding.ratingBar.rating = retencion / randomizeClass
        binding.ratingBar1.rating = retencion / randomizeClass1
        binding.ratingBar2.rating = retencion / randomizeClass2
        binding.ratingBar3.rating = retencion / randomizeClass3
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}