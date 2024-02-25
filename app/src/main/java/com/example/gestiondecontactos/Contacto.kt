package com.example.gestiondecontactos

data class Contacto(
    val nombre: String = "",
    val telefono: String = "",
    val correo: String = ""
) {
    constructor() : this("", "", "")
}
