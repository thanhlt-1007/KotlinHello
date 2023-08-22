package learn.kotlinsb.KotlinHello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.web.bind.annotation.RestController
//
// ROUTES
//
import org.springframework.web.bind.annotation.GetMapping
//
// PARAMS
//
import org.springframework.web.bind.annotation.RequestParam

@SpringBootApplication
class KotlinHelloApplication

fun main(args: Array<String>) {
  runApplication<KotlinHelloApplication>(*args)
}

@RestController
class MessageController {
  @GetMapping("/")
  fun index(@RequestParam("name") name: String) : String {
    return "Hello, nice to meet you $name!"
  }
}
