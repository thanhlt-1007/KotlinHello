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

// data class
data class Message(val id: Int?, val text: String)

@SpringBootApplication
class KotlinHelloApplication

fun main(args: Array<String>) {
  runApplication<KotlinHelloApplication>(*args)
}

@RestController
class HomeController {
  @GetMapping("/")
  fun index(@RequestParam("name") name: String) : String {
    return "Hello, nice to meet you $name!"
  }
}

@RestController
class MessagesController {
  @GetMapping("/api/v1/messages")
  fun index() : MutableList<Message> {
    val mMessagesList : MutableList<Message> = mutableListOf()

    val enMessage = Message(1, "Hello")
    mMessagesList.add(enMessage)

    val viMessage = Message(2, "Xin chào")
    mMessagesList.add(viMessage)

    val jaMessage = Message(2, "こんにちは")
    mMessagesList.add(jaMessage)

    return mMessagesList
  }
}
