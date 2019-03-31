import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

fun main() {
    JavalinApp(7000).init()
}

class JavalinApp(private val port: Int) {

    fun init(): Javalin {

        val app = Javalin.create().apply {
            port(port)
            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        }.start()

        val controller = FoodItemController(foodItems)

        app.routes {
            app.get("/") { ctx -> ctx.result("Hello World") }
            app.get("/bye") { ctx -> ctx.result("Bye World") }

            path("api") {
                path("food") {
                    path(":id") {
                        get { ctx -> controller.getFoodItem(ctx) }
                    }
                }
            }
        }

        return app
    }
}