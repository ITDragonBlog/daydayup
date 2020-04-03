package com.itdragon.server.api.rest

import com.itdragon.server.user.domain.ITDragonUser
import com.itdragon.server.user.model.UserCreateInfo
import com.itdragon.server.user.model.UserUpdateInfo
import com.itdragon.server.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/itdragon/api/v1")
class UserManagementController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    fun listUsers(
            @RequestParam("page", required = false, defaultValue = "1") page: Int = 1,
            @RequestParam("size", required = false, defaultValue = "10") size: Int = 10,
            @RequestParam("search", required = false) search: String?): ResponseEntity<Page<ITDragonUser>> {
        val userList = userService.listUsers(search, PageRequest.of(page - 1, size))
        return ResponseEntity.ok(userList)
    }

    @PostMapping("/user")
    fun createUser(@RequestBody userCreateInfo: UserCreateInfo): ResponseEntity<ITDragonUser> {
        return ResponseEntity.ok(userService.createUser(userCreateInfo))
    }

    @PutMapping("/user")
    fun updateUser(@RequestBody userUpdateInfo: UserUpdateInfo): ResponseEntity<ITDragonUser> {
        return ResponseEntity.ok(userService.updateUser(userUpdateInfo))
    }

    @DeleteMapping("/user/{username}")
    fun deleteUser(@PathVariable("username") username: String): ResponseEntity<Unit> {
        userService.deleteUser(username)
        return ResponseEntity.ok(Unit)
    }

    @GetMapping("/user/{username}")
    fun getUserDetailInfo(@PathVariable("username") username: String): ResponseEntity<ITDragonUser> {
        return ResponseEntity.ok(userService.findByUsername(username))
    }

}