// person.Person.kt
package person

import io.javalin.Context

class PersonController(private val data: Map<Int, Person>) {

    fun getPerson(ctx: Context) {
        ctx.pathParam("id").toInt().let {
            data[it]?.let { item ->
                ctx.json(item)
                return
            }
            ctx.status(404)
        }
    }
}

data class Person(val name: String, val age: Int)

val personRepository = hashMapOf(
        0 to Person("Dmitry", 37),
        1 to Person("Yvonne", 29),
        2 to Person("Peter", 52)
)
