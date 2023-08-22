package learn.kotlinsb.KotlinHello

//
// SPRING BOOT
//
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//
// REST CONTROLLER
//
import org.springframework.web.bind.annotation.RestController

//
// ROUTE
//
import org.springframework.web.bind.annotation.GetMapping

//
// PARAM
//
import org.springframework.web.bind.annotation.RequestParam

//
// MODEL
//

// data class
data class Message(val id: Int?, val text: String)

//
// REST CONTROLLER
//
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

//
// SPRING BOOT
//
@SpringBootApplication
class KotlinHelloApplication

fun main(args: Array<String>) {
  runApplication<KotlinHelloApplication>(*args)
}
