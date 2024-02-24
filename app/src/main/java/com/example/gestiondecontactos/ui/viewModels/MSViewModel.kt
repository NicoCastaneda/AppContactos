package com.example.gestiondecontactos.ui.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class MSViewModel {
    private val db= FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val user = auth.currentUser
    private val contactosRef = db.collection("contactos")


    fun agregarContacto(nombre: String, telefono: String, correo: String) {
        try {
            // Insertar el nuevo contacto en Firestore
            val nuevoContacto = hashMapOf(
                "nombre" to nombre,
                "telefono" to telefono,
                "correo" to correo
            )
            CoroutineScope(Dispatchers.IO).launch {
                contactosRef.add(nuevoContacto).await()
            }
        } catch (e: Exception) {
            println("Error al agregar el contacto: ${e.message}")
        } //C:\Users\Nicolas\AndroidStudioProjects\GestiondeContactos
    }

    fun eliminarContacto(nombre: String) {

    }

    fun obtenerContactos() {

    }
}