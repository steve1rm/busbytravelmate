package me.androidbox.data.remote.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import me.androidbox.data.remote.dto.TokenRequest
import me.androidbox.data.remote.dto.TokenResponse
import me.androidbox.data.remote.service.BusbyTravelMateService
import me.androidbox.domain.APIResponse
import org.junit.Assert
import org.junit.Test
import java.util.UUID
import kotlin.random.Random
import kotlin.random.nextInt

class BusbyTravelMateServiceImpTest {

    private lateinit var busbyTravelMateService: BusbyTravelMateService

    private fun createMockEngine(content: String): HttpClient {
        val mockEngine = MockEngine {
            this.respond(
                content = content, // kotlinx.serialization.json.Json.encodeToString(TokenResponse(accessToken = "example_token")),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, Json.toString())
            )
        }

        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(kotlinx.serialization.json.Json)
            }
        }
    }

    @Test
    fun `should make request to get token`() = runTest {
        // Arrange
        val tokenResponse = TokenResponse(
            accessToken = UUID.randomUUID().toString(),
            applicationName = UUID.randomUUID().toString(),
            clientId = UUID.randomUUID().toString(),
            expiresIn = Random.nextInt(10..1000),
            scope = UUID.randomUUID().toString(),
            state = UUID.randomUUID().toString(),
            tokenType = UUID.randomUUID().toString(),
            username = UUID.randomUUID().toString(),
            type = UUID.randomUUID().toString(),
        )

        val tokenResponseString = kotlinx.serialization.json.Json.encodeToString(tokenResponse)

        val client = createMockEngine(tokenResponseString)
        busbyTravelMateService = BusbyTravelMateServiceImp(client)

        val tokenRequest = TokenRequest(
            grantType = UUID.randomUUID().toString(),
            clientId = UUID.randomUUID().toString(),
            clientSecret = UUID.randomUUID().toString())

        // Act
        val actual = busbyTravelMateService.requestToken(tokenRequest) as APIResponse.Success

        // Assert
        Assert.assertEquals(tokenResponse, actual.data)
    }

    @Test
    fun dataIsHelloWorld() = runTest {
        val data = BusbyTravelMateServiceImp(createMockEngine("")).fetchData()

        Assert.assertEquals("Hello World!", data)
    }
}