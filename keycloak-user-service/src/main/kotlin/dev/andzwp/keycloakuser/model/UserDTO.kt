package dev.andzwp.keycloakuser.model

data class UserDTO(
    val username: String,
    val password: String,
    val firstName: String,
    val secondName: String,
    val email: String
)
