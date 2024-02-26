package com.example.gestiondecontactos.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondecontactos.R
import com.example.gestiondecontactos.ui.viewModels.DetalleViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetalleContacto(detalleViewModel: DetalleViewModel, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Volver") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            detalle()
        }

    }
}

@Composable
fun detalle() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.perfilcontactos), "Foto_Perfil_Detalle",
            Modifier
                .padding(0.dp, 70.dp, 0.dp, 15.dp)
                .height(100.dp)
                .width(100.dp)
        )
        Text(
            text = "Nombre",
            Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Text(
            text = "Número",
            Modifier.padding(0.dp, 15.dp, 0.dp, 15.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )
        Text(
            text = "Correo",
            Modifier.padding(0.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )

        Box(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 15.dp)
                .fillMaxSize()
                .weight(0.8f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row {
                editarButton(onClick = { })
                eliminarButton(onClick = { })
            }

        }


    }
}

@Composable
fun editarButton(onClick: () -> Unit, icon: ImageVector = Icons.Rounded.Edit) {
    Button(onClick = onClick, Modifier.padding(0.dp, 0.dp, 30.dp, 0.dp)) {
        Icon(icon, contentDescription = null, modifier = Modifier.padding(0.dp, 0.dp, 15.dp, 0.dp))
        Text("Editar", fontSize = 18.sp)
    }
}

@Composable
fun eliminarButton(onClick: () -> Unit, icon: ImageVector = Icons.Rounded.Delete) {
    Button(onClick = onClick) {
        Icon(icon, contentDescription = null, modifier = Modifier.padding(0.dp, 0.dp, 15.dp, 0.dp))
        Text("Eliminar", fontSize = 18.sp)
    }
}