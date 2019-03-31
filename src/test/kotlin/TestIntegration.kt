import io.javalin.Javalin
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestIntegration  {

    private lateinit var app: Javalin
    private val url = "http://localhost:8000/"

    @BeforeAll
    fun setUp() {
        app = JavalinApp(8000).init()
    }

    @AfterAll
    fun tearDown() {
        app.stop()
    }

    @Test
    fun testDummy() {
        assertEquals(1, 1)
    }
}