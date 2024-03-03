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
import me.androidbox.data.remote.service.UserTokenRemoteDataSource
import me.androidbox.repository.userTokenRepository.usecases.FetchUserTokenUseCase
import me.androidbox.repository.userTokenRepository.usecases.RequestUserTokenUseCase
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

/*
    private val userTokenRemoteDataSource by inject<UserTokenRemoteDataSource>()
    private val fetchUserTokenUseCase by inject<FetchUserTokenUseCase>()
*/
    private val requestUserTokenUseCase by inject<RequestUserTokenUseCase>()

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
                        requestUserTokenUseCase.execute(UserTokenRequest(
                            grantType = "client_credentials",
                            clientId = "p8ioeKrMrtQkeOD8yuUjqtxaYG4Nt2KB",
                            clientSecret = "PGDukHIYKweKbYob"
                        ).toUserTokenRequestModel())

                     /*   val apiResponse = userTokenRemoteDataSource
                            .requestToken(UserTokenRequestDto(
                                grantType = "client_credentials",
                                clientId = "p8ioeKrMrtQkeOD8yuUjqtxaYG4Nt2KB",
                                clientSecret = "PGDukHIYKweKbYob"
                            ))

                        when(apiResponse) {
                            is APIResponse.Success -> {
                                Timber.d(apiResponse.data.accessToken)
                                // Maybe show success message box
                            }
                            is APIResponse.Failure -> {
                                Timber.e(apiResponse.error)
                                // Maybe show a message box related to error
                            }
                        } */
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