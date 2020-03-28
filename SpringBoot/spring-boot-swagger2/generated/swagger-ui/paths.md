## Paths
### 参数说明
```
GET /itdragon/ApiImplicitParams/info
```

#### Description

通过ApiImplicitParams注解修饰参数，对参数做详细介绍。若不适用，

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|QueryParameter|deviceIds||true|string||
|QueryParameter|deviceIds|设备ID集合，用逗号区分|true|||
|QueryParameter|search||false|string||
|BodyParameter|search|查询字段|false|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|boolean|


#### Tags

* SwaggerDemo

### 对象参数说明
```
POST /itdragon/ApiModel/info
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|BodyParameter|body||false|创建用户模型||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|创建用户模型|


#### Tags

* SwaggerDemo

### 方法说明
```
GET /itdragon/ApiOperation/info
```

#### Description

通过ApiOperation注解修饰方法，对方法做详细介绍。若不使用，Swagger会以函数名作为描述。

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|QueryParameter|ids||true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|boolean|


#### Tags

* SwaggerDemo

### GET /itdragon/Native/info
#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|QueryParameter|active||false|boolean||
|QueryParameter|endTime||false|string (date-time)||
|QueryParameter|page||false|integer (int32)||
|QueryParameter|search||false|string||
|QueryParameter|size||false|integer (int32)||
|QueryParameter|startTime||false|string (date-time)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|boolean|


#### Tags

* SwaggerDemo

