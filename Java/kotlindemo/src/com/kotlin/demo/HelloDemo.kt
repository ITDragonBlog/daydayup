package com.kotlin.demo

enum class Lang{
    A,
    B,
    C,
    D;

    companion object {
        fun parse(name: String):Lang{
            return Lang.valueOf(name.toUpperCase());
        }
    }
}

infix fun Int.extensionFun(i: Int): Int {
    return this + i
}

fun main(args: Array<String>) {
    println(10 extensionFun 20)
    println(10.extensionFun(20))
    val c = { x: Int, y: Int -> x + y }
    println(c(1,2))
    println("Hello Kotlin")
    var name = "itdragon"
    println(name)
    var num: Int = 222
    var lnum: Long = 99999999999999
    println("num ： " + num)
    println("lnum ： " + lnum)
    args.map {
        println(it)
    }
    val lang = Lang.parse("B")
    print(lang)

    var a = 1
    val s1 = "a is $a"
    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
    println("max : ${maxOf(5, 6)}")
    println("max : ${maxOf2(5, 4)}")
    println(printProduct("4", "5"))
    println(printProduct("a", "8"))
    fun printLength(obj: Any) {
        println("'$obj' string length is ${getStringLength(obj)?:"... error , is not string"}")
    }
    printLength("abcd1234efg")
    printLength(1000)
    printLength(listOf(Any()))

    val items = listOf<String>("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index ++
    }
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    val x = 11
    val y = 9
    if (x in 1..y+1){
        println("fits in range")
    }
}

fun describe(obj: Any): String =
        when(obj) {
            1 -> "One"
            "Hello" -> "ITDragon"
            is Long -> "Long"
            !is String -> "Not is String"
            else -> "Unknow"
        }

fun getStringLength(obj: Any): Int? {
    if (obj is String) return obj.length
    return null
}

fun maxOf(a: Int, b: Int): Int {
    return if (a > b) {
        a
    } else {
        b
    }
}

fun maxOf2(a: Int, b: Int) = if (a > b) a else b

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)
    if (null != x && null != y) {
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' is not number")
    }
}