package me.androidbox.busbytravelmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import me.androidbox.busbytravelmate.ui.theme.BusbyTravelMateTheme
import me.androidbox.data.remote.dto.TokenRequest
import me.androidbox.data.remote.service.BusbyTravelMateService
import me.androidbox.repository.userTokenRepository.FetchUserTokenUseCase
import me.androidbox.repository.userTokenRepository.SaveUserTokenUseCase
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val busbyTravelMateService by inject<BusbyTravelMateService>()
    private val saveUserTokenUseCase by inject<SaveUserTokenUseCase>()
    private val fetchUserTokenUseCase by inject<FetchUserTokenUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            BusbyTravelMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    lifecycleScope.launch {
                        busbyTravelMateService
                            .requestToken(TokenRequest(
                                grantType = "client_credentials",
                                clientId = "p8ioeKrMrtQkeOD8yuUjqtxaYG4Nt2KB",
                                clientSecret = "PGDukHIYKweKbYob"
                            ))
                            .onSuccess { tokenResponse ->
                                println(tokenResponse)
                                lifecycleScope.launch {
                                    saveUserTokenUseCase.execute(tokenResponse.accessToken)
                                    println(fetchUserTokenUseCase.execute())
                                }
                            }
                            .onFailure {
                                println(it)
                            }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusbyTravelMateTheme {
        Greeting("Android")
    }
}