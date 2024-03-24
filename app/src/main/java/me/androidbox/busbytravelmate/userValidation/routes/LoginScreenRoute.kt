package me.androidbox.busbytravelmate.userValidation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.busbytravelmate.home.routes.HomeScreenRoute
import me.androidbox.busbytravelmate.userValidation.screens.LoginScreen
import me.androidbox.busbytravelmate.userValidation.viewmodels.UserValidationViewModel
import org.koin.androidx.compose.koinViewModel

data class LoginScreenRoute(private val userToken: String) : Screen {

    @Composable
    override fun Content() {
        val viewModel: UserValidationViewModel = koinViewModel()
        val userState = viewModel.userValidationState.collectAsStateWithLifecycle()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = true) {
            viewModel.requestUserToken()
        }

        LoginScreen(
            userToken = userState.value.userToken,
            userValidationState = userState.value,
            onLoginSuccess = {
                navigator.push(HomeScreenRoute)
            },
            onSignUpClicked = {
                navigator.push(SignUpScreenRoute)
            },
            userValidationEvents = { event ->
                viewModel.validationEvents(event)
            }
        )
    }
}