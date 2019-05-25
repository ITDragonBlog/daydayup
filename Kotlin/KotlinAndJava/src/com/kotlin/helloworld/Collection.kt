package com.kotlin.helloworld

fun main(args: Array<String>) {
    initList()
    initSet()
    initMap()
    getCollectionElement()
    sortCollectionElement()
    filterCollectionElement()
    mapCollectionElement()
    summaryCollectionElement()
    isumCollectionElement()
}

/* 伪代码
fun demo() {
    @Transactional
    fun modifyEquipmentEnergyValue(equipmentEnergyValues: List<EquipmentEnergyValue>): OperateStatus {
        // 通过上下文获取当前登录的用户，从而获取其权限
        val currentUser = ContextUtils.getCurrentUser()
        // 一个用户关联多个角色，一个角色绑定多个权限，所有一定会有重复的权限存在
        // 通过flatMap 方法获取所有权限，在通过.toSet()方法去重
        val authorities = currentUser.roles?.flatMap { it.authorities.orEmpty() }.toSet()
        // 先判断是否针对所有设备都有权限，避免没必要的事务回滚
        // 通过find 方法找出没有权限设备
        equipmentEnergyValues.find { !authorities.contains(it.equipment.id) }?.let {
            throw AuthenticationException("您没有权限$it设备能耗，请联系工程人员")
        }
        // 先判断是否存在重复数据或者不合理数据，避免没必要的事务回滚
        // 设备名称不能重复，用map映射出一个新集合，原集合不受影响
        val equipmenNameSize = equipmentEnergyValues.map { it.equipment.name }.toSet().size
        if (equipmenNameSize != equipmentEnergyValues.size) {
            throw IllegalArgumentException("设备不能重复修改")
        }
        // 通过 maxBy 方法找出值最大的一项
        if (equipmentEnergyValues.maxBy { it.value }.value >= 1000) {
            throw IllegalArgumentException("设备能耗值不符合规范")
        }
        // 旧数据和新数据求差集，找出需要清空的数据（或者设为零）
        val oldEquipmentEnergyValues = equipmentRepository.findByLocationAndDate(xxx,xxx)
        oldEquipmentEnergyValues.subtract(equipmentEnergyValues).forEach {
            // 删除
        }
        // 更新数据时考虑null值覆盖的问题
        equipmentEnergyValues.forEach {
            // 通过id判断是更新还是创建，用BeanUtils.copyProperties做复制时需要注意null的问题
        }
        return OperateStatus()
    }
}*/

fun getCollectionElement() {
    val list: MutableList<Int> = mutableListOf(1,2,3,4,5)
    println("getOrElse : ${list.getOrElse(10,{ 20 })}")
    println("getOrNull : ${list.getOrNull(10)}")
    println("firstOrNull : ${list.firstOrNull()}")
    println("firstOrNull : ${list.firstOrNull { it > 3 }}")
    println("indexOfFirst : ${list.indexOfFirst { it > 3 }}")
    println("indexOfLast : ${list.indexOfLast { it > 3 }}")
}

fun sortCollectionElement() {
    val persons = mutableListOf(Person("n1", 20, 2000.0),
        Person("n2", 24, 4000.0),
        Person("n3", 28, 6000.0),
        Person("n4", 26, 8000.0),
        Person("n5", 34, 7000.0),
        Person("n6", 44, 5000.0))
    persons.sortedBy { it.age }.map { println(it) }
    persons.map { it.age }.sorted()
    persons.sortBy { it.age }
    persons.reversed()
}

fun filterCollectionElement() {
    val list = listOf(-3,-2,1,3,5,3,7,2,10,9)
    println("filter : ${list.filter { it > 1 }}")
    println("filterIndexed : ${list.filterIndexed { index, result ->
        index % 2 == 0 && result > 5
    }}")
    println("take : ${list.take(5)}")
    println("takeWhile : ${list.takeWhile { it < 5 }}")
    println("drop : ${list.drop(5)}")
    println("distinct : ${list.distinct()}")
    println("distinctBy : ${list.distinctBy { it % 2 }}")
    println("slice : ${list.slice(IntRange(1,5))}")
}

fun summaryCollectionElement() {
    val persons = mutableListOf(Person("n1", 20, 2000.0),
        Person("n2", 24, 4000.0),
        Person("n3", 28, 6000.0),
        Person("n4", 26, 8000.0),
        Person("n5", 34, 7000.0),
        Person("n6", 44, 5000.0))
    println("maxBy : ${persons.maxBy { it.age }}")
    println("sumByDouble : ${persons.sumByDouble { it.salary }}")
    println("average : ${persons.map { it.salary }.average()}")
    println("any : ${persons.any { it.salary < 1000 }}")
}

fun mapCollectionElement() {
    val list = listOf(-3,-2,1,3,5,3,7,2,10,9)
    list.map { it + 1 }.forEach { print("$it \t") }
    println()
    list.forEach { print("$it \t") }
    list.mapIndexedNotNull { index, value ->
        if (index % 2 == 0) value else null
    }.forEach { print("$it \t") }
    println("flatMap : ${list.flatMap { listOf(it, it + 1,"n$it") }}")
    println("groupBy : ${list.groupBy { if (it % 2 == 0) "偶数" else "奇数" }}")
}

fun isumCollectionElement() {
    val list1 = mutableListOf(1,2,3,4,5)
    val list2 = mutableListOf(4,5,6,7)
    println("intersect : ${list1.intersect(list2)}")
    println("subtract : ${list1.subtract(list2)}")
    println("union : ${list1.union(list2)}")
    println("minus : ${list1.minus(list2)}")
}

fun initList() {
    // 声明并初始化不可变List集合
    val list: List<Any> = listOf<Any>(1, "2", 3)
    // 声明并初始化可变MutableList集合
    val mutableList: MutableList<Any> = mutableListOf<Any>(4, "5", 6)
    mutableList.add("7")
    list.map { print("$it \t") }
    mutableList.map { print("$it \t") }
}

fun initSet() {
    // 声明并初始化不可变Set集合
    val set: Set<Any> = setOf<Any>(1, "2", 3, "3")
    // 声明并初始化可变MutableSet集合
    val mutableSet: MutableSet<Any> = mutableSetOf<Any>(4, "5", 6)
    mutableSet.add(6)
    set.map { print("$it \t") }
    mutableSet.map { print("$it \t") }
}

fun initMap() {
    // 声明并初始化不可变Map集合
    val map: Map<String, Any> = mapOf("k1" to "v1" , "k2" to 3)
    // 声明并初始化可变MutableMap集合
    val mutableMap: MutableMap<String, Any> = mutableMapOf("k1" to "v1" , "k1" to 3)
    map.map { println("key : ${it.key} \t value : ${it.value}") }
    mutableMap.map { println("key : ${it.key} \t value : ${it.value}") }
}

data class Person(
    var name: String = "",
    var age: Int = 0,
    var salary: Double = 0.0
)

