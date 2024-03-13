package me.androidbox.busbytravelmate.userValidation.screens

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.busbytravelmate.ui.theme.BusbyTravelMateTheme
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationEvents
import me.androidbox.busbytravelmate.userValidation.viewstate.UserValidationState

@Composable
fun LoginScreen(
    userValidationState: UserValidationState<Unit>,
    userValidationEvents: (event: UserValidationEvents) -> Unit
) {
    Text(text = "Login User")

    TextButton(onClick = { /*TODO*/ }) {

    }

    TextButton(onClick = { /*TODO*/ }) {
        Text(text = "Login")
    }


}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewLoginScreen() {
    BusbyTravelMateTheme {
        LoginScreen(
            userValidationState = UserValidationState(userToken = "kakdkeajdjdkekad"),
            userValidationEvents = {
                UserValidationEvents.OnUserTokenRequest("hygtfrtjuygfdew")
            }
        )
    }
}