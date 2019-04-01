// PersonIntegrationTest.kt
package person

import JavalinApp
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.jackson.responseObject
import io.javalin.Javalin
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Person API")
class PersonIntegrationTest  {

    private lateinit var app: Javalin

    @BeforeAll
    fun setUp() {
        app = JavalinApp(8000).init()
        // Inject the base path to no have repeat the whole URL
        FuelManager.instance.basePath = "http://localhost:${app.port()}/"
    }

    @AfterAll
    fun tearDown() {
        // Stops the application when the tests have completed
        app.stop()
    }

    @Test
    fun `should get result for existing person`() {
        // Deconstructs the ResponseResult
        val (_, _, result) = "api/person/0".httpGet().responseObject<Person>()

        // Get the actual value from the result object
        assertEquals(personRepository[0], result.get())
    }

    @Test
    fun `should get error for non-existing person`() {
        val (_, _, result) = "api/person/-1".httpGet().responseObject<Person>()
        // Deconstructs the ResponseResult to get the FuelError
        val (_, error) = result

        assertEquals(error!!.response.statusCode, 404)
    }
}