package com.example.gestiondecontactos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.gestiondecontactos.ui.viewModels.MSViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gestiondecontactos.ui.views.MainScreen

@Composable
fun NavManager(msViewModel: MSViewModel){
    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ms") {
        composable("ms") {
            MainScreen(msViewModel, navController)
        }
    }
}