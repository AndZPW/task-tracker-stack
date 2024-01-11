package dev.andzwp.keycloakuser.config



import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakConfiguration {

    @Value("")
    private lateinit var serverUrl: String

    @Value("")
    private lateinit var realm: String

    @Value("")
    private lateinit var username: String

    @Value("")
    private lateinit var password: String

    @Value("")
    private lateinit var clientId: String

    @Value("")
    private lateinit var clientSecret: String

    @Bean
    fun keycloakBean(): Keycloak =
        KeycloakBuilder.builder()
            .serverUrl(serverUrl)
            .realm(realm)
            .grantType(OAuth2Constants.PASSWORD)
            .username(username)
            .password(password)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .resteasyClient(ResteasyClientBuilderImpl().connectionPoolSize(20).build())
            .build()

}