package com.s3ich4n.theater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TheaterApplication

fun main(args: Array<String>) {
    runApplication<TheaterApplication>(*args)
}
