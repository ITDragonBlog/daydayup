package com.itdragon.server.user.service

import com.itdragon.server.user.domain.ITDragonUser
import com.itdragon.server.user.model.UserCreateInfo
import com.itdragon.server.user.model.UserUpdateInfo
import com.itdragon.server.user.repository.UserRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.annotation.Resource
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun createUser(userCreateInfo: UserCreateInfo): ITDragonUser {
        userRepository.findByUsername(userCreateInfo.username)?.let {
            throw EntityExistsException("用户名[${userCreateInfo.username}] 已经存在")
        }
        userCreateInfo.password = passwordEncoder.encode(userCreateInfo.password)
        val user = ITDragonUser()
        BeanUtils.copyProperties(userCreateInfo, user)
        return userRepository.save(user)
    }

    fun updateUser(userUpdateInfo: UserUpdateInfo): ITDragonUser {
        val existed = userRepository.findById(userUpdateInfo.id).get()
        BeanUtils.copyProperties(userUpdateInfo, existed)
        return userRepository.save(existed)
    }

    fun deleteUser(username: String): Boolean {
        val existed = findByUsername(username)
        userRepository.delete(existed)
        return true
    }

    fun listUsers(search: String?, pageable: Pageable): Page<ITDragonUser> {
        var querySpecification = Specification<ITDragonUser> { _, _, cb -> cb.conjunction() }
        search?.let {
            querySpecification = querySpecification.and { root, _, criteriaBuilder ->
                criteriaBuilder.like(root.get<String>("username"), "%$search%")
            }
        }
        return userRepository.findAll(querySpecification, pageable)
    }

    fun findByUsername(username: String): ITDragonUser {
        return userRepository.findByUsername(username)
                ?: throw EntityNotFoundException("通过用户名[$username]没有找到用户")
    }

}