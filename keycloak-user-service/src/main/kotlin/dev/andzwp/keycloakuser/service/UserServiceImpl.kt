package dev.andzwp.keycloakuser.service

import dev.andzwp.keycloakuser.model.UserDTO
import dev.andzwp.keycloakuser.utils.Credentials
import org.keycloak.admin.client.resource.UsersResource

import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation
import org.springframework.stereotype.Service
import javax.ws.rs.core.Response

@Service
class UserServiceImpl(private val userResource: UsersResource, private val credential: Credentials) : UserService {
    override fun addUser(userDTO: UserDTO): Response {
        val c: CredentialRepresentation = credential.createCredentialsPassword(userDTO.password)
        val user = UserRepresentation()
        user.username = userDTO.username
        user.firstName = userDTO.firstName
        user.lastName = userDTO.secondName
        user.email = userDTO.email
        user.credentials = listOf(c)
        user.isEnabled = true

        return userResource.create(user)

    }

    override fun findUser(username: String?): List<UserRepresentation> =
        userResource.search(username, true)

    override fun updateUser(userId: String?, userDTO: UserDTO) {
        val c = credential.createCredentialsPassword(userDTO.password)
        val user = UserRepresentation()
        user.username = userDTO.username
        user.firstName = userDTO.firstName
        user.lastName = userDTO.secondName
        user.email = userDTO.email
        user.credentials = listOf(c)

        userResource.get(userId)
            .update(user)

    }

    override fun deleteUser(userId: String?) = userResource.get(userId).remove()

}