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
import java.util.UUID
import kotlin.random.Random
import kotlin.random.nextInt
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import me.androidbox.APIResponse
import me.androidbox.data.remote.dto.UserTokenDto
import me.androidbox.data.remote.dto.UserTokenRequestDto
import me.androidbox.data.remote.service.UserTokenRemoteDataSource
import org.junit.Assert
import org.junit.Test

class UserTokenRemoteDataSourceImpTest {

    private lateinit var userTokenRemoteDataSource: UserTokenRemoteDataSource

    private fun createMockEngine(content: String): HttpClient {
        val mockEngine = MockEngine {
            this.respond(
                content = content,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, Json.toString())
            )
        }

        return HttpClient(mockEngine) {
            install(ContentNegotiation) { json(kotlinx.serialization.json.Json) }
        }
    }

    @Test
    fun `should make request to get token`() = runTest {
        // Arrange
        val tokenResponse =
            UserTokenDto(
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
        userTokenRemoteDataSource = UserTokenRemoteDataSourceImp(client)

        val tokenRequest =
            UserTokenRequestDto(
                grantType = UUID.randomUUID().toString(),
                clientId = UUID.randomUUID().toString(),
                clientSecret = UUID.randomUUID().toString()
            )

        // Act
        val actual = userTokenRemoteDataSource.requestUserToken(tokenRequest) as APIResponse.Success

        // Assert
        Assert.assertEquals(tokenResponse, actual.data)
    }

    @Test
    fun dataIsHelloWorld() = runTest {
        val data = UserTokenRemoteDataSourceImp(createMockEngine("")).fetchData()

        Assert.assertEquals("Hello World!", data)
    }
}
