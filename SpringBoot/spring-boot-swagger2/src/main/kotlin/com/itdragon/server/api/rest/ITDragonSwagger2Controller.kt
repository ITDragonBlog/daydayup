package com.itdragon.server.api.rest

import io.swagger.annotations.*
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import java.util.*
import javax.servlet.http.HttpServletResponse

@Api(tags = ["SwaggerDemo"])
@RestController
@RequestMapping("/itdragon")
class ITDragonSwagger2Controller {


    @ApiOperation("方法说明", notes = "通过ApiOperation注解修饰方法，对方法做详细介绍。若不使用，Swagger会以函数名作为描述。", httpMethod = "GET")
    @GetMapping("/ApiOperation/info")
    fun getITDragonApiOperationInfo(@RequestParam("ids") ids: String): ResponseEntity<Unit> {
        return ResponseEntity.ok(Unit)
    }

    @ApiOperation("参数说明", notes = "通过ApiImplicitParams注解修饰参数，对参数做详细介绍。若不适用，")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "deviceIds", value = "设备ID集合，用逗号区分", required = true, dataType = "String", paramType = "query"),
        ApiImplicitParam(name = "search", value = "查询字段")])
    @GetMapping("/ApiImplicitParams/info")
    fun getITDragonApiImplicitParamsInfo(@RequestParam("deviceIds") deviceIds: String,
                                         @RequestParam("search") search: String,
                                         @ApiIgnore currentUser: String): ResponseEntity<Unit> {
        return ResponseEntity.ok(Unit)
    }

    @ApiOperation("对象参数说明", notes = "")
    @PostMapping("/ApiModel/info")
    fun postITDragonApiModelInfo(@RequestBody itDragonCreateInfo: ITDragonCreateInfo) {
    }

    @GetMapping("/Native/info")
    fun getITDragonNativeInfo(@RequestParam("active", required = false) active: Boolean?,
                              @RequestParam("search", required = false) search: String?,
                              @RequestParam("startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") startTime: Date?,
                              @RequestParam("endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") endTime: Date?,
                              @RequestParam("page", required = false, defaultValue = "1") page: Int = 1,
                              @RequestParam("size", required = false, defaultValue = "10") size: Int = 10): ResponseEntity<Unit> {
        return ResponseEntity.ok(Unit)
    }

}

@ApiModel("创建用户模型")
class ITDragonCreateInfo {
    @ApiModelProperty("用户账号,登录账号")
    var username: String? = null
    var nikename: String? = null
}
