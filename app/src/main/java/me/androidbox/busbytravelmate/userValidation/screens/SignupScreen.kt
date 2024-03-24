package me.androidbox.busbytravelmate.userValidation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.androidbox.busbytravelmate.R
import me.androidbox.busbytravelmate.ui.theme.BusbyTravelMateTheme
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationEvents
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationState
import me.androidbox.components.CredentialInput

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    userValidationState: UserValidationState<Unit>,
    userValidationEvents: (userValidationEvent: UserValidationEvents) -> Unit,
) {
    Box(
        modifier = modifier.padding(top = 100.dp, start = 16.dp, end = 16.dp, bottom = 30.dp),
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Logo")

        CredentialInput(
            modifier = Modifier.align(Alignment.Center),
            email = userValidationState.email,
            password = userValidationState.password,
            isPasswordVisible = userValidationState.isPasswordVisible,
            actionButtonName = "Sign up",
            onEmailChanged = { /*TODO*/ },
            onPasswordChanged = { /*TODO*/ },
            onVisibilityChanged = { /*TODO*/ },
            onActionClicked = { _, _ ->

            })
    }
}

@Composable
@Preview
fun PreviewSignupScreen() {
    BusbyTravelMateTheme {
    //    SignupScreen()
    }
}