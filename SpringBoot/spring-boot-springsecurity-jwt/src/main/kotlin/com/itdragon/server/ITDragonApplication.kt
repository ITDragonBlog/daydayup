package com.itdragon.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ITDragonApplication

fun main(args: Array<String>) {
    SpringApplication(ITDragonApplication::class.java).run(*args)
}

