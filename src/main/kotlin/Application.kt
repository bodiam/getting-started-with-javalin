// Application.kt
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
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

        app.routes {
            path("api") {
                path("person") {
                    path(":id") {
                        get { ctx -> personController.getPerson(ctx) }
                    }
                }
            }
        }

        return app
    }
}