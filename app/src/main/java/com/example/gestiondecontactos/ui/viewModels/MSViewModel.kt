package com.example.gestiondecontactos.ui.viewModels

import com.example.gestiondecontactos.Contacto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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