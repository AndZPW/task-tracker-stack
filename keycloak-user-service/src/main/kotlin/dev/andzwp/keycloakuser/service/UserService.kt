package dev.andzwp.keycloakuser.service

import dev.andzwp.keycloakuser.model.UserDTO
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation


interface UserService {
    fun addUser(userDTO: UserDTO)

    fun findUser(username: String?): List<UserRepresentation>

    fun updateUser(userId: String?, userDTO: UserDTO)

    fun deleteUser(userId: String?)

}