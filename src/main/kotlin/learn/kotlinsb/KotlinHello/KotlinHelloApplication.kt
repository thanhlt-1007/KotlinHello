package learn.kotlinsb.KotlinHello

//
// UTIL
//
import java.util.UUID

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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody

//
// MODEL
//

// data class
data class Message(val id: String?, val text: String)

// Service class
@Service
class MessageService(val db: JdbcTemplate) {
  fun all() : List<Message> {
    var mMessagesList: MutableList<Message> = mutableListOf()

    val selectQuery = "SELECT * FROM messages"
    db.query(selectQuery) { response, _ ->
      var id = response.getString("id")
      var text = response.getString("text")
      var message = Message(id, text)
      mMessagesList.add(message)
    }
    return mMessagesList.toList()
  }

  fun create(messageParam: Message) : Message {
    val id = messageParam.id ?: UUID.randomUUID().toString()
    val text = messageParam.text

    val insertQuery = "INSERT INTO messages VALUES (?, ?)"
    db.update(insertQuery, id, text)

    return Message(id, text)
  }

  fun find(id: String) : Message? {
    var message: Message? = null

    val selectQuery = "SELECT * FROM messages WHERE id = $id"
    db.query(selectQuery) { response, _ ->
      var text = response.getString("text")
      message = Message(id, text)
    }

    return message
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
  fun index() : List<Message> {
    return service.all()
  }

  @PostMapping("/api/v1/messages")
  fun create(@RequestBody message: Message) : Message {
    return service.create(message)
  }

  @GetMapping("/api/v1/messages/{id}")
  fun show(@PathVariable id: String) : Message? {
    return service.find(id)
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
