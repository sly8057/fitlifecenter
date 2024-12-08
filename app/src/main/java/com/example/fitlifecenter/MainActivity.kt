package com.example.fitlifecenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitlifecenter.databinding.ActivityMainBinding
import com.example.fitlifecenter.ui.clases.ClasesFragment
import com.example.fitlifecenter.ui.home.HomeFragment
import com.example.fitlifecenter.ui.inventario.InventarioFragment
import com.example.fitlifecenter.ui.reportes.ReportesFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val REQUEST_CODE_REGISTRAR = 1
    private var rol: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        val headerView = navView.getHeaderView(0)
        val headerName = headerView.findViewById<TextView>(R.id.txtNombre)
        val headerUser = headerView.findViewById<TextView>(R.id.txtUsuario)

        val sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("USER_ROLE", "trainer")
        val userUser = sharedPreferences.getString("usuario", "Usuario")
        val userName = sharedPreferences.getString("nombre", "Nombre")

        headerName.text = userName
        headerUser.text = userUser

        // val userRole = intent.getStringExtra("USER_ROLE") ?: "Trainer"

        // Set menu based on the role
        when (userRole) {
            "owner" -> {
                rol = 0
                navView.menu.clear().apply {
                    navView.inflateMenu(R.menu.menu_owner)
                }
                appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.nav_home, R.id.nav_clases_admin, R.id.nav_reportes, R.id.nav_logout, R.id.nav_inventario_dueno
                    ), drawerLayout
                )
                // navView.menu.clear()
                // navView.inflateMenu(R.menu.menu_owner)
            }

            "trainer" -> {
                rol = 1
                navView.menu.clear().apply {
                    navView.inflateMenu(R.menu.menu_trainer)
                }
                appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.nav_home, R.id.nav_clases, R.id.nav_inventario_entrenador, R.id.nav_logout, R.id.nav_reporte_equipo
                    ), drawerLayout
                )
                // navView.menu.clear()
                // navView.inflateMenu(R.menu.menu_trainer)
            }

            else -> {
                // Menú por defecto en caso de error
                navView.menu.clear().apply {
                    navView.inflateMenu(R.menu.activity_main_drawer)
                }
                appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_logout), drawerLayout)
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home, R.id.nav_reportes, R.id.nav_clases, R.id.nav_inventario_entrenador,R.id.nav_clases_admin, R.id.nav_inventario_dueno, R.id.nav_reporte_equipo -> {
                    // Deja que el NavController maneje estos fragmentos
                    val handled = NavigationUI.onNavDestinationSelected(menuItem, navController)
                    if (handled) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START) // Cierra el drawer si se navega
                    }
                    handled
                }
                R.id.nav_logout -> {
                    // Acción personalizada para cerrar sesión
                    // val sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE)
                    // sharedPreferences.edit().clear().apply()

                    // Redirige al inicio de sesión
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // Cierra la actividad actual
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("USER_ROLE", "trainer")

        when (userRole) {
            "owner" -> menuInflater.inflate(R.menu.menu_owner, menu)
            "trainer" -> menuInflater.inflate(R.menu.menu_trainer, menu)
            else -> menuInflater.inflate(R.menu.activity_main_drawer, menu) // Menú por defecto
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_REGISTRAR && resultCode == Activity.RESULT_OK) {
            data?.getParcelableArrayListExtra<Paciente>("listaPacientes")?.let {
                listaPacientes.clear()
                listaPacientes.addAll(it)
            }
        }
    }
     */

}