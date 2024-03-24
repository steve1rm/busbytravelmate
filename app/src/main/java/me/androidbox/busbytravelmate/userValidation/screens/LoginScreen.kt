package me.androidbox.busbytravelmate.userValidation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
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
    onLoginSuccess: () -> Unit,
    userValidationState: UserValidationState<Unit>,
    userValidationEvents: (userValidationEvent: UserValidationEvents) -> Unit,
) {

    val loginState by remember(key1 = userValidationState.isLoginSuccess) {
        derivedStateOf {
            userValidationState.isLoginSuccess
        }
    }


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
                .align(Alignment.BottomCenter)
                .clickable {
                    onSignUpClicked()
                },
            text = signupAnnotatedString()
        )

        if(loginState) {
            onLoginSuccess()
        }
        else {
            Toast.makeText(LocalContext.current, "Failed to login", Toast.LENGTH_LONG).show()
        }
    }
}

private fun signupAnnotatedString(): AnnotatedString {
    return buildAnnotatedString {
        this.withStyle(
            SpanStyle(color = Color.Black)
        ) {
            this.append("Don't have an account")
        }

        this.append(" ")

        this.withStyle(
            SpanStyle(color = Color.Blue)
        ) {
            this.append("Sign up")
        }
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
            onLoginSuccess = {},
            userValidationState = UserValidationState(),
            userValidationEvents = {

            },
            )
    }
}