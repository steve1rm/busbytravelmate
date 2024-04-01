package me.androidbox.busbytravelmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import me.androidbox.busbytravelmate.ui.theme.BusbyTravelMateTheme
import me.androidbox.busbytravelmate.userValidation.routes.LoginScreenRoute
import me.androidbox.busbytravelmate.userValidation.viewmodels.UserValidationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val userRepositoryViewModel by viewModel<UserValidationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val userValidationState by userRepositoryViewModel.userValidationState.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = false) {
                userRepositoryViewModel.requestUserToken()
            }

            BusbyTravelMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    tonalElevation = 5.dp,
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator(LoginScreenRoute(userValidationState.userToken))

                    /*Text(text = "Hello [ ${userValidationState.userToken} ]", modifier = Modifier.clickable {
                        userRepositoryViewModel.login()
                    })*/
                }
            }
        }
    }
}
