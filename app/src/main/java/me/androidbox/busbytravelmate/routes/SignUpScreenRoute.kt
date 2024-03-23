package me.androidbox.busbytravelmate.routes

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbytravelmate.screens.SignupScreen
import me.androidbox.busbytravelmate.userValidation.viewmodels.UserValidationViewModel
import org.koin.androidx.compose.koinViewModel

object SignUpScreenRoute : Screen {

    @Composable
    override fun Content() {

        val userValidationViewModel = koinViewModel<UserValidationViewModel>()
        val userValidationState = userValidationViewModel.userValidationState.collectAsStateWithLifecycle()

        SignupScreen(
            userValidationState = userValidationState.value) { userValidationEvent ->
            userValidationViewModel.validationEvents(userValidationEvent)
        }
    }
}