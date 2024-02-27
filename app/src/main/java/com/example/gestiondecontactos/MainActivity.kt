package com.example.gestiondecontactos

import DetalleViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gestiondecontactos.ui.navigation.NavManager
import com.example.gestiondecontactos.ui.theme.GestionDeContactosTheme
import com.example.gestiondecontactos.ui.viewModels.MSViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionDeContactosTheme {
                NavManager(msViewModel = MSViewModel(), detalleViewModel = DetalleViewModel())
            }
        }
    }
}
