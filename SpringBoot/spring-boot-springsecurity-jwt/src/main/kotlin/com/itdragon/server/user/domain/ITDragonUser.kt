package com.itdragon.server.user.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.util.*
import javax.persistence.*

@Entity
class ITDragonUser : BaseModel() {
    var username: String = ""
    var password: String = ""
    var email: String? = ""
    var nickName: String? = ""
    var active: Boolean = false
    var tokenInvalidDate: Date = Date()
}


@MappedSuperclass
open class BaseModel {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    var id: String = ""

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var createTime: Date? = null

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    var lastModifiedTime: Date? = null

}