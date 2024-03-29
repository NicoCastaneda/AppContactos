import com.example.gestiondecontactos.Contacto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DetalleViewModel {
    private val db = FirebaseFirestore.getInstance()
    private val contactosRef = db.collection("contactos")

    suspend fun obtenerContacto(contactoId: String): Contacto? {
        return try {
            val snapshot = contactosRef.document(contactoId).get().await()
            snapshot.toObject(Contacto::class.java)
        } catch (e: Exception) {
            println("Error al obtener el contacto: ${e.message}")
            null
        }
    }

    fun editarContacto(
        contactoId: String,
        nombre: String,
        telefono: String,
        correo: String,
        callback: (Contacto?) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val oldContacto = obtenerContacto(contactoId)
            callback(oldContacto)

            if (oldContacto != null) {
                try {
                    val nuevoContacto = hashMapOf(
                        "nombre" to nombre,
                        "telefono" to telefono,
                        "correo" to correo
                    )
                    contactosRef.document(contactoId).delete()
                    contactosRef.document(nombre).set(nuevoContacto)
                } catch (e: Exception) {
                    println("${e.message}")
                }
            }
        }
    }

    fun eliminarContacto(contactoId: String) {
        contactosRef.document(contactoId).delete()
    }
}
