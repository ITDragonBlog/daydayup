package com.kotlin.singleton

/**
 * 同步锁线程安全懒汉模式
 */
class LazyThreadSafeSynchronizedSingleton private constructor() {

    companion object {
        private var instance: LazyThreadSafeSynchronizedSingleton? = null

        @Synchronized
        fun get(): LazyThreadSafeSynchronizedSingleton{
            if(instance == null) instance = LazyThreadSafeSynchronizedSingleton()
            return instance!!
        }
    }

}