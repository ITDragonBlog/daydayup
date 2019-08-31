package com.kotlin.singleton

/**
 * 非线程安全的懒汉模式
 */
class LazyNoThreadSafeSingleton {
    companion object {
        val instance by lazy(LazyThreadSafetyMode.NONE) {
            LazyNoThreadSafeSingleton()
        }
    }
}