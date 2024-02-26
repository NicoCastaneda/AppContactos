import com.example.gestiondecontactos.Contacto
import com.google.firebase.firestore.FirebaseFirestore
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
}
