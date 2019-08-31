package com.kotlin.singleton

/**
 * 双重检查懒汉模式
 */
class LazyThreadSafeDoubleCheckSingleton {
    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LazyThreadSafeDoubleCheckSingleton()
        }
    }
}