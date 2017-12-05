package org.miejski.keepit

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KeepItApplication

fun main(args: Array<String>) {
    SpringApplication.run(KeepItApplication::class.java, *args)
}
