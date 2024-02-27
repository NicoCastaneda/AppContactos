package com.example.gestiondecontactos.ui.navigation

import DetalleViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.gestiondecontactos.ui.viewModels.MSViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gestiondecontactos.ui.views.DetalleContacto
import com.example.gestiondecontactos.ui.views.MainScreen

@Composable
fun NavManager(msViewModel: MSViewModel, detalleViewModel: DetalleViewModel) {
    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ms") {
        composable("ms") {
            MainScreen(msViewModel, navController)
        }
        composable("detalleContacto/{contactoId}") { backStackEntry ->

            val contactoId = backStackEntry.arguments?.getString("contactoId")
            DetalleContacto(detalleViewModel, navController, contactoId ?: "")
        }
    }
}