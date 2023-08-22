package learn.kotlinsb.KotlinHello

//
// UTIL
//
// Must import for CrudRepository
import java.util.*

// dotenv()
import io.github.cdimascio.dotenv.dotenv

//
// SPRING BOOT
//
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//
// SPRING DATA
//
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository

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
@Table("MESSAGES")
data class Message(@Id var id: String?, val text: String)

// Repository
interface MessageRepositiry : CrudRepository<Message, String>

// Service class
@Service
class MessageService(val db: MessageRepositiry) {
  fun all() : List<Message> {
    return db.findAll().toList()
  }

  // create if message.id == null
  // update if message.id exists in db
  fun create(message: Message) : Message {
    db.save(message)
    return message
  }

  fun find(id: String) : Message {
    return db.findById(id).toList().first()
  }

  // Must import for CrudRepository
  fun <T : Any> Optional<out T>.toList(): List<T> = if (isPresent) listOf(get()) else emptyList()
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
class EnvsController {
  @GetMapping("/envs")
  fun index() : String {
    val envMysqlDatatabse = dotenv()["MYSQL_DATABASE"]
    var envs = """
      ENVs:<br>
      - MYSQL_DATABASE: $envMysqlDatatabse<br>
    """
    return envs
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
  fun show(@PathVariable id: String) : Message {
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
