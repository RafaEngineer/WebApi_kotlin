package br.com.webapi.webapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class WebapiApplication

fun main(args: Array<String>) {
	runApplication<WebapiApplication>(*args)
}