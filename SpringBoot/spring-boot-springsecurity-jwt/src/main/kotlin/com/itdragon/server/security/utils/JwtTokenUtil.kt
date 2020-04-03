package com.itdragon.server.security.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

private const val CLAIM_KEY_USERNAME = "itdragon"
private const val CLAIM_KEY_CREATED = "created"

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
    fun generateToken(username: String): String? {
        try {
            val claims = HashMap<String, Any>()
            claims[CLAIM_KEY_USERNAME] = username
            claims[CLAIM_KEY_CREATED] = Date()
            return generateJWT(claims)
        } catch (e: Exception) {
        }
        return null
    }

    /**
     * 刷新令牌Token
     */
    fun refreshToken(token: String): String? {
        try {

        } catch (e: Exception) {
        }
        return null
    }

    /**
     * 从token 中获取用户名
     */
    fun getUsernameFromToken(token: String): String? {
        return try {
            val claims = parseJWT(token)
            claims!!.subject
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 校验token
     */
    fun validateToken(token: String, username: String): Boolean {
        return getUsernameFromToken(token) == username
    }

    /**
     * 生成jwt
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
     * 解析jwt
     */
    private fun parseJWT(token: String): Claims? {
        return try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .body
        } catch (e: Exception) {
            null
        }
    }

}