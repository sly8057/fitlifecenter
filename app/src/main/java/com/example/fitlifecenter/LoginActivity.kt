package com.example.fitlifecenter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private var entrenadores = mutableListOf<Entrenador>()
    private var dueno = Dueno()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()

        btnLogin.setOnClickListener {
            if(!validateInfo()){
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            } else {
                login()
            }
        }
    }

    private fun validateInfo(): Boolean {
        if(etUsername.text.toString() == dueno.usuario &&
            etPassword.text.toString() == dueno.contrasena){
            saveSharedPreferences(dueno)
            return true
        } else {
            for (entrenador in entrenadores) {
                if(etUsername.text.toString() == entrenador.usuario &&
                    etPassword.text.toString() == entrenador.contrasena){
                    saveSharedPreferences(entrenador)
                    return true
                }
            }
        }
        return false
    }

    private fun saveSharedPreferences(dueno: Dueno) {
        val preferences : SharedPreferences = getSharedPreferences("preferenciasUsuario",
            MODE_PRIVATE)

        val editor : SharedPreferences.Editor = preferences.edit()
        editor.putString("usuario",dueno.usuario)
        editor.putString("password",dueno.contrasena)
        editor.putString("nombre", dueno.nombre)
        editor.putString("USER_ROLE",dueno.rol)

        editor.apply()
    }

    private fun saveSharedPreferences(entrenador: Entrenador) {
        val preferences : SharedPreferences = getSharedPreferences("preferenciasUsuario",
            MODE_PRIVATE)

        val editor : SharedPreferences.Editor = preferences.edit()
        editor.putString("usuario",entrenador.usuario)
        editor.putString("password",entrenador.contrasena)
        editor.putString("nombre", entrenador.nombre)
        editor.putString("USER_ROLE",entrenador.rol)

        editor.apply()
    }

    private fun login() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initComponents() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        val entrenador1 = Entrenador(1, "Nadia Peña", "Pnadia", "1234", "trainer")
        entrenadores.add(entrenador1)

        val entrenador2 = Entrenador(2, "Maria Lopez", "Lmaria", "1234", "trainer")
        entrenadores.add(entrenador2)

        val entrenador3 = Entrenador(3, "Juan Perez", "Pjuan", "1234", "trainer")
        entrenadores.add(entrenador3)

        val entrenador4 = Entrenador(4, "Pedro Rodriguez", "Rpedro", "1234", "trainer")
        entrenadores.add(entrenador4)

        dueno = Dueno(0, "Emilia Morales", "owner", "4321", "owner")
    }

    /*
    override fun onStart() {
        super.onStart()

        val sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("USER_ROLE", null)

        if (userRole != null) {
            login()
        }
    }
     */

}
