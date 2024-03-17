package me.androidbox.busbytravelmate.screens

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
import me.androidbox.components.CredentialInput

@Composable
fun SignupScreen(modifier: Modifier = Modifier,) {
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
            actionButtonName = "Sign up",
            onEmailChanged = { /*TODO*/ },
            onPasswordChanged = { /*TODO*/ },
            onVisibilityChanged = { /*TODO*/ },
            onActionClicked = { /*TODO*/ })

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = "Don't have account? Signup"
        )
    }
}

@Composable
@Preview
fun LoginScreen() {
    BusbyTravelMateTheme {
        SignupScreen()
    }
}