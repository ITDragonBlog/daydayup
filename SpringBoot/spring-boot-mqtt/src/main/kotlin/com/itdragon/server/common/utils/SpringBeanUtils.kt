package com.itdragon.server.common.utils

import org.springframework.beans.BeansException
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class SpringBeanUtils : BeanFactoryPostProcessor {

    @Throws(BeansException::class)
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        Companion.beanFactory = beanFactory
    }

    companion object {
        var beanFactory: ConfigurableListableBeanFactory? = null
            private set

        @Throws(BeansException::class)
        fun <T> getBean(name: String): T {
            return beanFactory!!.getBean(name) as T
        }

        @Throws(BeansException::class)
        fun <T> getBean(name: Class<T>): T {
            return beanFactory!!.getBean(name)
        }

        fun containsBean(name: String): Boolean {
            return beanFactory!!.containsBean(name)
        }

        @Throws(NoSuchBeanDefinitionException::class)
        fun isSingleton(name: String): Boolean {
            return beanFactory!!.isSingleton(name)
        }

        @Throws(NoSuchBeanDefinitionException::class)
        fun getType(name: String): Class<*>? {
            return beanFactory!!.getType(name)
        }

        @Throws(NoSuchBeanDefinitionException::class)
        fun getAliases(name: String): Array<String> {
            return beanFactory!!.getAliases(name)
        }
    }

}

