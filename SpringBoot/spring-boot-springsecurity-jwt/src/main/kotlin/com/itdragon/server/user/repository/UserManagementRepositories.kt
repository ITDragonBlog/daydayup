package com.itdragon.server.user.repository

import com.itdragon.server.user.domain.ITDragonUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository: JpaRepository<ITDragonUser, String>, JpaSpecificationExecutor<ITDragonUser> {
    fun findByUsername(username: String): ITDragonUser?
}

