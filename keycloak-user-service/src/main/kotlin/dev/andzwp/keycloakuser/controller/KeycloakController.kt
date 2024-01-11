package dev.andzwp.keycloakuser.controller

import dev.andzwp.keycloakuser.model.UserDTO
import dev.andzwp.keycloakuser.service.UserService

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class KeycloakController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody user: UserDTO) =
        userService.addUser(user)

    @GetMapping("/{username}")
    fun fetchUser(@PathVariable("username") username: String) =
        userService.findUser(username)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUser(@PathVariable("id") id: String, @RequestBody user: UserDTO) =
        userService.updateUser(id, user)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable("id") id: String) =
        userService.deleteUser(id)

}