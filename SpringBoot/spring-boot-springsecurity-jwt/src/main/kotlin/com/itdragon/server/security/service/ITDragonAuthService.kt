package com.itdragon.server.security.service

import com.itdragon.server.security.utils.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class ITDragonAuthService {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var userDetailsService: UserDetailsService
    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    fun login(username: String, password: String): String {
        // 初始化UsernamePasswordAuthenticationToken对象
        val upAuthenticationToken = UsernamePasswordAuthenticationToken(username, password)
        // 身份验证
        val authentication = authenticationManager.authenticate(upAuthenticationToken)
        // 验证成功后回将用户信息存放到 securityContextHolder的Context中
        SecurityContextHolder.getContext().authentication = authentication
        // 生成token并返回
        val userDetails = userDetailsService.loadUserByUsername(username)
        return jwtTokenUtil.generateToken(userDetails.username)
    }

}