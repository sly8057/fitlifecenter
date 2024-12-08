package com.example.fitlifecenter

object RoleHelper {
    fun getMenuForRole(role: String): Int {
        return when (role) {
            "Owner" -> R.menu.menu_owner
            "Trainer" -> R.menu.menu_trainer
            else -> throw IllegalArgumentException("Rol no válido")
        }
    }
}