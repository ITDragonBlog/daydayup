package com.itdragon.server.security.service

import com.itdragon.server.security.utils.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 自定义JWT拦截器
 */
@Component
class ITDragonJwtAuthenticationTokenFilter: OncePerRequestFilter() {

    @Value("\${itdragon.jwt.header}")
    lateinit var tokenHeader: String
    @Value("\${itdragon.jwt.tokenHead}")
    lateinit var tokenHead: String
    @Autowired
    lateinit var userDetailsService: UserDetailsService
    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    /**
     * 拦截器验证步骤
     * 第一步：从请求头中获取token
     * 第二步：从token中获取用户信息，判断token数据是否合法
     * 第三步：校验token是否有效，包括token是否过期、token是否已经刷新
     * 第四步：检验成功后将用户信息存放到SecurityContextHolder Context中
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        // 从请求头中获取token
        val authHeader = request.getHeader(this.tokenHeader)
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            val authToken = authHeader.substring(tokenHead.length)
            // 从token中获取用户信息
            val username = jwtTokenUtil.getUsernameFromToken(authToken)
            if (username.isBlank()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Auth token is illegal")
                return
            }

            // 验证token是否有效
            val userDetails = this.userDetailsService.loadUserByUsername(username)
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                // 将用户信息添加到SecurityContextHolder 的Context
                val authentication = UsernamePasswordAuthenticationToken(userDetails, userDetails.password, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }

        }

        filterChain.doFilter(request, response)
    }

}