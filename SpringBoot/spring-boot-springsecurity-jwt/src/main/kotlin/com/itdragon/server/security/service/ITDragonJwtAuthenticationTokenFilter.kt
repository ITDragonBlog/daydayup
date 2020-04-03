package com.itdragon.server.security.service

import com.itdragon.server.security.utils.JwtTokenUtil
import org.slf4j.LoggerFactory
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
    lateinit var userDetailsService: ITDragonUserDetailsService

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val authHeader = request.getHeader(this.tokenHeader)
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            val authToken = authHeader.substring(tokenHead.length)
            val username = jwtTokenUtil.getUsernameFromToken(authToken)
            if (username.isNullOrBlank()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Auth token is illegal")
                return
            }
            logger.info("checking authentication $username")

            if (SecurityContextHolder.getContext().authentication == null) {

                val userDetails = this.userDetailsService.loadUserByUsername(username!!)

                if (jwtTokenUtil.validateToken(authToken, username)) {
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    logger.info("authenticated model $username, setting security context")
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }
        filterChain.doFilter(request, response)
    }

}