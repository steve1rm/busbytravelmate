package me.androidbox.busbytravelmate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import java.util.UUID

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    userToken: String,
    onSignUpClicked: () -> Unit,
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
            actionButtonName = "Login",
            onEmailChanged = { email ->
                userValidationEvents(UserValidationEvents.OnEmailChanged(email))
            },
            onPasswordChanged = { password ->
                userValidationEvents(UserValidationEvents.OnPasswordChanged(password))
            },
            onVisibilityChanged = {
               userValidationEvents(UserValidationEvents.OnPasswordVisibilityChanged)
            },
            onActionClicked = { email, password ->
                userValidationEvents(UserValidationEvents.OnLoginClicked(email, password))
            })

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter).
            clickable {
                onSignUpClicked()
            },
            text = "Don't have an account, sign up"
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true)
fun PreviewLoginScreen() {
    BusbyTravelMateTheme {
        LoginScreen(
        userToken = UUID.randomUUID().toString(),
        onSignUpClicked = {

        },
        userValidationState = UserValidationState(),
        userValidationEvents = {

        },)
    }
}