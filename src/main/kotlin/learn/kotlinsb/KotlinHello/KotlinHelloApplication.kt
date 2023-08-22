package learn.kotlinsb.KotlinHello

//
// SPRING BOOT
//
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//
// SERVICE
//
import org.springframework.stereotype.Service

//
// JBBC
//
import org.springframework.jdbc.core.JdbcTemplate

//
// REST CONTROLLER
//
import org.springframework.web.bind.annotation.RestController

//
// ROUTE
//
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

//
// PARAM
//
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody

//
// MODEL
//

// data class
data class Message(val id: Int?, val text: String)

// Service class
@Service
class MessageService(val db: JdbcTemplate) {
  fun create(message: Message) {
    val insertQuery = "INSERT INTO messages VALUES (?, ?)"
    db.update(insertQuery, message.id, message.text)
  }
}

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
class MessagesController(val service: MessageService) {
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

  @PostMapping("/api/v1/messages")
  fun create(@RequestBody message: Message) : Message {
    service.create(message)

    return message
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
