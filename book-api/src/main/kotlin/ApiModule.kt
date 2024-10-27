import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.books


fun Application.module(){
    install(ContentNegotiation){
        json()
    }
    routing {
        get("/books/author/{author}") {
            val author = call.parameters["author"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Параметор авто указан не правильно")
            val filteredBooks = filterBooksByAuthor(books, author)
            call.respond(filteredBooks)
        }
        get("/books/rating/{rating}") {
            val rating = call.parameters["rating"] ?. toDoubleOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest, "Неправильный рейтинг")
            val filteredBooks = filterBooksByRating(books, rating)
            call.respond(filteredBooks)
        }
    }
}