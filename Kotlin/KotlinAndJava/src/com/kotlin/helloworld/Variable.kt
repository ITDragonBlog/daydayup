package com.kotlin.helloworld

fun main(args: Array<String>) {
//    val 不可变变量 Kotlin默认情况下推荐使用val，相当于Java的final
    val b: Byte = 0
    val s: Short = 0
    val i: Int = 0
    val l: Long = 0L
    val f: Float = 0.0f
    val d: Double = 0.0
    val bl: Boolean = true
    val c: Char = 'c'
//    i = 2 Val cannot be reassigned
//    var 可变变量
    var ii = 1  // 智能推测ii的变量类型为Int
    ii = 2      // 赋值没有报错
//    是否可写的定义是什么？
    val map: MutableMap<String, Any> = mutableMapOf()
    map["key"] = "value"
}