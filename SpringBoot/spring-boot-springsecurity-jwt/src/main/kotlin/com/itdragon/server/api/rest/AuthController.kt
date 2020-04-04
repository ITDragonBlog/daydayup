package com.itdragon.server.api.rest

import com.itdragon.server.security.service.ITDragonAuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @Autowired
    lateinit var authService: ITDragonAuthService

    @PostMapping("/auth/login")
    fun createAuthenticationToken(@RequestBody request: ITDragonJwtRequest): ResponseEntity<*> {
        val token = authService.login(request.username, request.password)
        return ResponseEntity.ok(TDragonJwtResponse(token))
    }

}

data class ITDragonJwtRequest(var username: String, var password: String)

data class TDragonJwtResponse(var token: String)