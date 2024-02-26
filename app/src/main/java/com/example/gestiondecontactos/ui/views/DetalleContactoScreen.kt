package com.example.gestiondecontactos.ui.views

import DetalleViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondecontactos.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.gestiondecontactos.Contacto

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetalleContacto(detalleViewModel: DetalleViewModel, navController: NavController, contactoId: String) {
    var contacto by remember { mutableStateOf<Contacto?>(null) }

    LaunchedEffect(Unit) {
        // Obtener los datos del contacto cuando se carga la pantalla
        val detalle = detalleViewModel.obtenerContacto(contactoId)
        if (detalle != null) {
            contacto = detalle
        }
    }

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
            //contacto.value?.let { it -> detalle(detalleViewModel, it, navController, contactoId) }
            contacto?.let { detalle(detalleViewModel, it, navController, contactoId) }
        }

    }
}

@Composable
fun detalle(detalleViewModel: DetalleViewModel, contacto: Contacto,navController: NavController,contactoId: String) {

    val showDialog = remember { mutableStateOf(false) }
    val showDeleteDialog = remember { mutableStateOf(false) }

    val onEditButtonClick: () -> Unit = {
        showDialog.value = true
    }
    val onDeleteButtonClick: () -> Unit = {
        showDeleteDialog.value = true
    }

    // Diálogo para editar el contacto
    if (showDialog.value) {
        EditarContactoDialog(
            onDismiss = { showDialog.value = false },
            onSaveContact = { nombre, numero, correo ->
                detalleViewModel.editarContacto(contactoId, nombre, numero, correo){ }
                showDialog.value = false
            },
            contacto = contacto,
            navController = navController
        )
    }

    //Diálogo para eliminar el contacto
    if (showDeleteDialog.value) {
        EliminarContactoDialog(
            onDismiss = { showDeleteDialog.value = false },
            onDeleteContact = {
                detalleViewModel.eliminarContacto(contactoId)
                showDeleteDialog.value = false
                navController.popBackStack()
            }
        )
    }


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
            text = contacto.nombre,
            Modifier.padding(0.dp, 0.dp, 0.dp, 15.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Text(
            text = contacto.telefono,
            Modifier.padding(0.dp, 15.dp, 0.dp, 15.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )
        Text(
            text = contacto.correo,
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
                editarButton(onClick = onEditButtonClick)
                eliminarButton(onClick = onDeleteButtonClick)
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

@Composable
fun EditarContactoDialog(
    onDismiss: () -> Unit,
    onSaveContact: (String, String, String) -> Unit,
    contacto: Contacto,
    navController: NavController
) {
    var nombre by remember { mutableStateOf(contacto.nombre) }
    var numero by remember { mutableStateOf(contacto.telefono) }
    var correo by remember { mutableStateOf(contacto.correo) }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = numero,
                onValueChange = { numero = it },
                label = { Text("Número") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                onSaveContact(nombre, numero, correo)
                navController.popBackStack()
            }
            ) {
                Text("Guardar")
            }
        }
    }
}

@Composable
fun EliminarContactoDialog(
    onDismiss: () -> Unit,
    onDeleteContact: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("¿Estás seguro de que quieres eliminar este contacto?")
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onDeleteContact) {
                    Text("Confirmar")
                }
                Button(onClick = onDismiss) {
                    Text("Cancelar")
                }
            }
        }
    }
}
