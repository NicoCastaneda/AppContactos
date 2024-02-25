package com.example.gestiondecontactos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.gestiondecontactos.ui.viewModels.MSViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gestiondecontactos.ui.viewModels.DetalleViewModel
import com.example.gestiondecontactos.ui.views.DetalleContacto
import com.example.gestiondecontactos.ui.views.MainScreen


@Composable
fun NavManager(msViewModel: MSViewModel, detalleViewModel: DetalleViewModel){
    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ms") {
        composable("ms") {
            MainScreen(msViewModel, navController)
        }
        composable("detalleContacto") { backStackEntry ->
            // Aquí debes obtener el contactoId de backStackEntry.arguments y mostrar la pantalla de detalle correspondiente
            DetalleContacto(detalleViewModel, navController)
            }
    }
}