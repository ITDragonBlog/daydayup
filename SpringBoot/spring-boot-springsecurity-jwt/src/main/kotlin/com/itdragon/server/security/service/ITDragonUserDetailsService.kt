package com.itdragon.server.security.service

import com.itdragon.server.user.domain.ITDragonUser
import com.itdragon.server.user.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * 实现UserDetailsService接口，自定义loadUserByUsername方法。无需额外配置，底层代码是通过
 * this.getUserDetailsService().loadUserByUsername(username: String) 获取UserDetails
 */
@Component
class ITDragonUserDetailsService : UserDetailsService {

    private val logger = LoggerFactory.getLogger(javaClass)
    @Autowired
    lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails {
        logger.info("用户登录: $username")
        // todo ... something
        return JwtUser(userService.findByUsername(username))
    }

}

class JwtUser(
        var model: ITDragonUser
) : UserDetails {

    override fun getAuthorities(): List<GrantedAuthority>? {
        return listOf()
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return model.username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return model.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return model.active
    }

}