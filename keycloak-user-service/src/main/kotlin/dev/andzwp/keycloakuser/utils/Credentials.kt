package dev.andzwp.keycloakuser.utils

import org.keycloak.representations.idm.CredentialRepresentation
import org.springframework.stereotype.Component

@Component
class Credentials {
    fun createCredentialsPassword(password: String): CredentialRepresentation {
        val passwordCredentials = CredentialRepresentation()
        passwordCredentials.isTemporary = false
        passwordCredentials.type = CredentialRepresentation.PASSWORD
        passwordCredentials.value = password
        return passwordCredentials
    }
}