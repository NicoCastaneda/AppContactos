package com.example.gestiondecontactos.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.gestiondecontactos.Contacto
import com.example.gestiondecontactos.ui.viewModels.MSViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun MainScreen(msViewModel: MSViewModel, navController: NavController) {
    val showAddContactDialog = remember { mutableStateOf(false) }
    val contactosState by msViewModel.obtenerContactos().collectAsState(initial = emptyList())

    Column {
        Text(text = "CONTACTOS")
        Box(modifier = Modifier.fillMaxSize().weight(0.8f)) {
            listaContactos(navController, contactosState)
        }


        agregarContactoButton(onClick = { showAddContactDialog.value = true })




        if (showAddContactDialog.value) {
            AgregarContactoDialog(
                onDismiss = { showAddContactDialog.value = false },
                onSaveContact = { nombre, numero, correo ->
                    msViewModel.agregarContacto(nombre, numero, correo)
                    showAddContactDialog.value = false
                }
            )
        }
    }


}


@Composable
fun listaContactos(navController: NavController, contactos: List<Contacto>) {
    LazyColumn {
        items(contactos) { contacto ->
            ContactoItem(navController = navController, contacto = contacto)
        }
    }
}

@Composable
fun ContactoItem(navController: NavController, contacto: Contacto) {
    Column(modifier = Modifier.padding(16.dp)
        .clickable { navController.navigate("detalleContacto/${contacto}") }
    ) {
        Text("Nombre: ${contacto.nombre}")
        Text("Teléfono: ${contacto.telefono}")
    }
}

@Composable
fun agregarContactoButton(onClick: () -> Unit, icon: ImageVector = Icons.Rounded.Add) {
    Button(onClick = onClick) {
        Icon(icon, contentDescription = null, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp))
        Text(text = "Agregar Contacto")
    }
}

@Composable
fun AgregarContactoDialog(
    onDismiss: () -> Unit,
    onSaveContact: (String, String, String) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(IntrinsicSize.Max)
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
            Button(onClick = { onSaveContact(nombre, numero, correo) }) {
                Text("Guardar")
            }
        }
    }
}
