// Application.kt
import io.javalin.Javalin
import person.PersonController
import person.personRepository

fun main() {
    JavalinApp(7000).init()
}

class JavalinApp(private val port: Int) {

    fun init(): Javalin {

        val app = Javalin.create().apply {
            port(port)
            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        }.start()

        val personController = PersonController(personRepository)

        app.get("/api/person/:id", personController::getPerson)

        return app
    }
}