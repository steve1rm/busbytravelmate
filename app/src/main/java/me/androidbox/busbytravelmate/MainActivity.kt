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
import me.androidbox.busbytravelmate.mappers.toUserTokenRequestModel
import me.androidbox.busbytravelmate.model.UserTokenRequest
import me.androidbox.busbytravelmate.ui.theme.BusbyTravelMateTheme
import me.androidbox.repository.userTokenRepository.usecases.GetUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.RequestUserTokenUseCase
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val requestUserTokenUseCase by inject<RequestUserTokenUseCase>()
    private val getUserTokenUseCase by inject<GetUserTokenUseCase>()

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
                        requestUserTokenUseCase
                            .execute(
                                UserTokenRequest(
                                        grantType = "client_credentials",
                                        clientId = "p8ioeKrMrtQkeOD8yuUjqtxaYG4Nt2KB",
                                        clientSecret = "PGDukHIYKweKbYob"
                                    )
                                    .toUserTokenRequestModel()
                            )
                            .onSuccess { userTokenModel ->
                                Timber.d(userTokenModel.toString(), null)
                                Timber.d(getUserTokenUseCase.execute())
                            }
                            .onFailure { Timber.e(it.message) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusbyTravelMateTheme { Greeting("Android") }
}
