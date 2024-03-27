package me.androidbox.busbytravelmate.userValidation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
    onSignUpClicked: () -> Unit,
    onLoginSuccess: () -> Unit,
    userValidationState: UserValidationState<Unit>,
    userValidationEvents: (userValidationEvent: UserValidationEvents) -> Unit,
) {

    val context = LocalContext.current

    DisposableEffect(key1 = userValidationState.isLoginSuccess, key2 = userValidationState.errorMessage) {
        if (userValidationState.isLoginSuccess) {
            onLoginSuccess()
        }

        if (!userValidationState.isLoginSuccess && userValidationState.errorMessage.isNotBlank()) {
            Toast.makeText(context, userValidationState.errorMessage, Toast.LENGTH_LONG).show()
        }

       onDispose {
           userValidationEvents(UserValidationEvents.OnErrorMessageSeen)
       }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
             Image(
                 modifier = Modifier.fillMaxWidth(),
                 contentScale = ContentScale.FillWidth,
                 painter = painterResource(id = R.drawable.login_screen_header), contentDescription = "background image")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.email_password), contentDescription = "background image")
            
            Column(
                modifier = Modifier.matchParentSize()
            ) {

                Spacer(modifier = Modifier.height(32.dp))

                CredentialInput(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    actionButtonName = "Login",
                    email = userValidationState.email,
                    password = userValidationState.password,
                    isPasswordVisible = userValidationState.isPasswordVisible,
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
            }
        }
    }

/*
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
            isLoading = userValidationState.isLoading == true,
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
                if(email.isBlank() || password.isBlank()) {
                    userValidationEvents(UserValidationEvents.OnLoginFailure("No username or password"))
                }
                else {
                    userValidationEvents(UserValidationEvents.OnLoginClicked(email, password))
                }
            })

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable {
                    onSignUpClicked()
                },
            text = signupAnnotatedString()
        )
    }*/
}

private fun signupAnnotatedString(): AnnotatedString {
    return buildAnnotatedString {
        this.withStyle(
            SpanStyle(color = Color.Black)
        ) {
            this.append("Don't have an account")
        }

        this.withStyle(
            SpanStyle(color = Color.Blue)
        ) {
            this.append(" ")
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
            onSignUpClicked = {

            },
            onLoginSuccess = {},
            userValidationState = UserValidationState(),
            userValidationEvents = {

            },
            )
    }
}