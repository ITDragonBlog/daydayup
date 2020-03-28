package com.itdragon.server.api.rest.models

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("创建用户模型")
class ITDragonCreateInfo {
    @ApiModelProperty("用户账号,登录账号")
    var username: String? = null
    var nikename: String? = null
}