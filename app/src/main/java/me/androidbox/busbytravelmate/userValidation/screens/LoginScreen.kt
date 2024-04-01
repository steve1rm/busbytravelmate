package me.androidbox.busbytravelmate.userValidation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.busbytravelmate.R
import me.androidbox.busbytravelmate.ui.theme.BusbyTravelMateTheme
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationEvents
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationState
import me.androidbox.components.CredentialInput
import me.androidbox.components.SocialSignIn

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
            .background(color = MaterialTheme.colorScheme.background)) {

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
                modifier = Modifier.matchParentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Welcome Back!",
                    style = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Welcome back we missed you",
                    style = LocalTextStyle.current.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                CredentialInput(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    actionButtonName = "Login",
                    email = userValidationState.email,
                    password = userValidationState.password,
                    onEmailChanged = { email ->
                        userValidationEvents(UserValidationEvents.OnEmailChanged(email))
                    },
                    onPasswordChanged = { password ->
                        userValidationEvents(UserValidationEvents.OnPasswordChanged(password))
                    },
                    onActionClicked = { email, password ->
                        userValidationEvents(UserValidationEvents.OnLoginClicked(email, password))
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier.fillMaxWidth().padding(horizontal = 32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier
                        .height(2.dp)
                        .weight(0.70F)
                        .background(brush = Brush.linearGradient(listOf(Color.Transparent, Color.DarkGray))))

                    Text(
                        modifier = Modifier.weight(1F),
                        textAlign = TextAlign.Center,
                        text = "Or continue with",
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 16.sp
                        ))

                    Spacer(modifier = Modifier
                        .height(2.dp)
                        .weight(0.70F)
                        .background(brush = Brush.linearGradient(listOf(Color.DarkGray, Color.Transparent))))
                }

                Spacer(modifier = Modifier.height(6.dp))

                SocialSignIn(modifier = Modifier.fillMaxWidth())
            }

            Text(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                text = signupAnnotatedString(textColor = MaterialTheme.colorScheme.secondary, signupColor = MaterialTheme.colorScheme.primary))
        }
    }
}

private fun signupAnnotatedString(textColor: Color, signupColor: Color): AnnotatedString {
    return buildAnnotatedString {
        this.withStyle(
            SpanStyle(color = textColor)
        ) {
            this.append("Don't have an account")
        }

        this.withStyle(
            SpanStyle(color = signupColor)
        ) {
            this.append(" ")
            this.append("Sign up")
        }
    }
}

@Composable
@PreviewLightDark
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