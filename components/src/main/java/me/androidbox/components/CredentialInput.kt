package me.androidbox.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CredentialInput(
    modifier: Modifier = Modifier,
    actionButtonName: String,
    email: String,
    password: String,
    isLoading: Boolean = false,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onActionClicked: (email: String, password: String) -> Unit
) {
    Column(
        modifier = modifier
    ) {

        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            label = "Email",
            currentValue = email,
            leadingIcon = Icons.Default.Email,
            keyboardActions = KeyboardActions.Default
        ) {
            onEmailChanged(it)
        }

        Spacer(modifier = Modifier.height(14.dp))

        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            label = "Password",
            currentValue = password,
            onValueChange = onPasswordChanged,
            onForgotPassword = {})

        Spacer(modifier = Modifier.height(8.dp))

        ActionButton(
            modifier = Modifier.fillMaxWidth(),
            label = actionButtonName,
            showLoading = isLoading,
            onButtonClicked = {
                onActionClicked(email, password)
            })
    }
}

@Composable
@Preview
fun PreviewCredentialInput() {
    CredentialInput(
        actionButtonName = "Login",
        email = "email",
        password = "password",
        onPasswordChanged = {},
        onEmailChanged = {},
        onActionClicked = { _, _ ->
        }
    )
}