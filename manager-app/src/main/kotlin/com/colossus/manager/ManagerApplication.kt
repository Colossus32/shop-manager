package com.colossus.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ManagerApplication

fun main(args: Array<String>) {
    runApplication<ManagerApplication>(*args)
}