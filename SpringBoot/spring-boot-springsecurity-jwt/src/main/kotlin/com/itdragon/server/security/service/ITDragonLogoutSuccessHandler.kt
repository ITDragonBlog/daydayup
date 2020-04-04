package com.itdragon.server.security.service

import com.alibaba.fastjson.JSONObject
import com.itdragon.server.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * 自定义用户登出处理器
 * 1. 返回json格式数据
 * 2. 登出后旧token失效
 */
@Component
class ITDragonLogoutSuccessHandler: LogoutSuccessHandler {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun onLogoutSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        try {
            // 更新用户登出时间，使其旧token失效
            if (authentication != null) {
                val jwtUser = authentication.principal as JwtUser
                val user = jwtUser.model
                user.tokenInvalidDate = Date()
                userRepository.save(user)
            }

            // 返回json格式数据
            response!!.contentType = "application/json;charset=utf-8"
            val out = response.writer
            out.write(JSONObject.toJSONString(mapOf("code" to 200, "message" to "登出成功")))
            out.flush()
            out.close()
        } catch (e: Exception) {

        }
    }

}