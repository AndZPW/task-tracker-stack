package dev.andzwp.keycloakuser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KeycloakUserServiceApplication

fun main(args: Array<String>) {
	runApplication<KeycloakUserServiceApplication>(*args)
}
