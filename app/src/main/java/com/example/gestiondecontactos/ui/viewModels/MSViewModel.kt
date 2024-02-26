package com.example.gestiondecontactos.ui.viewModels

import com.example.gestiondecontactos.Contacto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class MSViewModel {
    private val db = FirebaseFirestore.getInstance()
    private val contactosRef = db.collection("contactos")


    fun agregarContacto(nombre: String, telefono: String, correo: String) {
        try {
            val nuevoContacto = hashMapOf(
                "nombre" to nombre,
                "telefono" to telefono,
                "correo" to correo
            )
            // Agrega el nuevo contacto en Firestore con ID igual al nombre
            contactosRef.document(nombre).set(nuevoContacto)
        } catch (e: Exception) {
            println("${e.message}")
        }
    }


    fun obtenerContactos(): Flow<List<Contacto>> = flow {
        try {
            val snapshot = contactosRef.get().await()
            val contactos = snapshot.documents.mapNotNull { document ->
                document.toObject(Contacto::class.java)
            }
            emit(contactos)
        } catch (e: Exception) {
            println("${e.message}")
        }

    }

}