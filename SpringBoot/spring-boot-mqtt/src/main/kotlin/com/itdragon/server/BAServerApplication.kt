package com.itdragon.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BAServerApplication

fun main(args: Array<String>) {
    SpringApplication(BAServerApplication::class.java).run(*args)
}

