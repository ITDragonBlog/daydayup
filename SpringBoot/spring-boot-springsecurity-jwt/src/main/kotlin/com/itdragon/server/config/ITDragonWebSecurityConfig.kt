package com.itdragon.server.config

import com.itdragon.server.security.service.ITDragonJwtAuthenticationEntryPoint
import com.itdragon.server.security.service.ITDragonJwtAuthenticationTokenFilter
import com.itdragon.server.security.service.ITDragonLogoutSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * web 应用的安全适配器
 */
@Configuration
@EnableWebSecurity
class ITDragonWebSecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var jwtAuthenticationTokenFilter: ITDragonJwtAuthenticationTokenFilter
    @Autowired
    lateinit var authenticationEntryPoint: ITDragonJwtAuthenticationEntryPoint
    @Autowired
    lateinit var logoutSuccessHandler: ITDragonLogoutSuccessHandler

    /**
     * 配置密码编码器
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun itdragonAuthenticationManager(): AuthenticationManager {
        return authenticationManager()
    }

    /**
     * 第一步：将JWT拦截器添加到默认的账号密码拦截器之前，表示token验证成功后无需登录
     * 第二步：配置异常处理器和登出处理器
     * 第三步：开启权限拦截，对所有请求进行拦截
     * 第四步：开放不需要拦截的请求，比如用户注册、OPTIONS请求和静态资源等
     * 第五步：允许OPTIONS请求，为跨域配置做准备
     * 第六步：允许访问静态资源，访问swagger时需要
     */
    override fun configure(http: HttpSecurity) {
        // 添加jwt拦截器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
                // 配置异常处理器
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                // 配置登出逻辑
                .and().logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                // 开启权限拦截
                .and().authorizeRequests()
                // 开放不需要拦截的请求
                .antMatchers(HttpMethod.POST, "/itdragon/api/v1/user").permitAll()
                // 允许所有OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允许静态资源访问
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // 对除了以上路径的所有请求进行权限拦截
                .antMatchers("/itdragon/api/v1/**").authenticated()
                // 先暂时关闭跨站请求伪造，它限制除了get以外的大多数方法。
                .and().csrf().disable()
                // 允许跨域请求
                .cors().disable()

    }

}