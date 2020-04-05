package com.itdragon.server.security.utils

import com.itdragon.server.security.service.JwtUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

private const val CLAIM_KEY_USERNAME = "itdragon"

@Component
class JwtTokenUtil {

    @Value("\${itdragon.jwt.secret}")
    private val secret: String = "ITDragon"

    @Value("\${itdragon.jwt.expiration}")
    private val expiration: Long = 24 * 60 * 60

    /**
     * 生成令牌Token
     * 1. 建议使用唯一、可读性高的字段作为生成令牌的参数
     */
    fun generateToken(username: String): String {
        return try {
            val claims = HashMap<String, Any>()
            claims[CLAIM_KEY_USERNAME] = username
            generateJWT(claims)
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * 校验token
     * 1. 判断用户名和token包含的属性一致
     * 2. 判断token是否失效
     */
    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        userDetails as JwtUser
        return getUsernameFromToken(token) == userDetails.username && !isInvalid(token, userDetails.model.tokenInvalidDate)
    }

    /**
     * token 失效判断，依据如下：
     * 1. 关键字段被修改后token失效，包括密码修改、用户退出登录等
     * 2. token 过期失效
     */
    private fun isInvalid(token: String, tokenInvalidDate: Date?): Boolean {
        return try {
            val claims = parseJWT(token)
            claims!!.issuedAt.before(tokenInvalidDate) && isExpired(token)
        } catch (e: Exception) {
            false
        }
    }

    /**
     * token 过期判断，常见逻辑有几种：
     * 1. 基于本地内存，问题是重启服务失效
     * 2. 基于数据库，常用的有Redis数据库，但是频繁请求也是不小的开支
     * 3. 用jwt的过期时间和当前时间做比较（推荐）
     */
    private fun isExpired(token: String): Boolean {
        return try {
            val claims = parseJWT(token)
            claims!!.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 从token 中获取用户名
     */
    fun getUsernameFromToken(token: String): String {
        return try {
            val claims = parseJWT(token)
            claims!![CLAIM_KEY_USERNAME].toString()
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * 生成jwt方法
     */
    fun generateJWT(claims: Map<String, Any>): String {
        return Jwts.builder()
                .setClaims(claims)      // 定义属性
                .setIssuedAt(Date())    // 设置发行时间
                .setExpiration(Date(System.currentTimeMillis() + expiration * 1000)) // 设置令牌有效期
                .signWith(SignatureAlgorithm.HS512, secret) // 使用指定的算法和密钥对jwt进行签名
                .compact()              // 压缩字符串
    }

    /**
     * 解析jwt方法
     */
    private fun parseJWT(token: String): Claims? {
        return try {
            Jwts.parser()
                    .setSigningKey(secret)  // 设置密钥
                    .parseClaimsJws(token)  // 解析token
                    .body
        } catch (e: Exception) {
            null
        }
    }

}