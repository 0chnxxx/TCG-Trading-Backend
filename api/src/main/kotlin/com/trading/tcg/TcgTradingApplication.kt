package com.trading.tcg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TcgTradingApplication

fun main(args: Array<String>) {
    runApplication<TcgTradingApplication>(*args)
}
