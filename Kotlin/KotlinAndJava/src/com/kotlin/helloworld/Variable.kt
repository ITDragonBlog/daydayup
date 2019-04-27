package com.kotlin.helloworld

fun main(args: Array<String>) {
//    var 不可变变量 Kotlin默认情况下推荐使用val，相当于Java的final
    var b: Byte = 0
    var s: Short = 0
    var i: Int = 0
    var l: Long = 0L
    var f: Float = 0.0f
    var d: Double = 0.0
    var bl: Boolean = true
    var c: Char = 'c'
//  变量的取值范围
    rangeValue()
//  显示转换和智能推断
    variableConvert(c)
    valAndVar()
}

fun rangeValue() {
    println("Byte (${Byte.MIN_VALUE} ~ ${Byte.MAX_VALUE})")
    println("Short (${Short.MIN_VALUE} ~ ${Short.MAX_VALUE})")
    println("Int (${Int.MIN_VALUE} ~ ${Int.MAX_VALUE})")
    println("Long (${Long.MIN_VALUE} ~ ${Long.MAX_VALUE})")
    println("Float (${Float.MIN_VALUE} ~ ${Float.MAX_VALUE})")
    println("Double (${Double.MIN_VALUE} ~ ${Double.MAX_VALUE})")
}

fun variableConvert(char: Char) {
    var number: Int = 1
    println("显示转换成Int类型: ${number + char.toInt()}")
    var num = 2
    println("智能推断变量类型: ${num.javaClass}")
}

fun valAndVar() {
    val map: MutableMap<String, String> = mutableMapOf("Key" to "ITDragon")
    map["Key"] = "ITDragonBlog"
    println(map)
    var i = 2
    i ++
    println(i)
}
