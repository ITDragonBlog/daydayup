package com.kotlin.singleton

/**
 * 静态内部类-懒汉模式
 */
class LazyThreadSafeStaticInnerClassSingleton private constructor(){

    companion object{
        fun getInstance() = Holder.instance
    }

    private object Holder{
        val instance = LazyThreadSafeStaticInnerClassSingleton()
    }
}
