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

