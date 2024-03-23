package me.androidbox.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CredentialInput(
    modifier: Modifier = Modifier,
    actionButtonName: String,
    email: String,
    password: String,
    isPasswordVisible: Boolean,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onVisibilityChanged: () -> Unit,
    onActionClicked: (email: String, password: String) -> Unit
) {
    Column(
        modifier = modifier
    )
    {
        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            label = "Email",
            currentValue = email,
            icon = Icons.Default.Email,
            keyboardActions = KeyboardActions.Default
        ) {
            onEmailChanged(it)
        }

        Spacer(modifier = Modifier.height(8.dp))

        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            label = "Password",
            currentValue = password,
            isPasswordVisible = isPasswordVisible,
            keyboardActions = KeyboardActions.Default,
            onValueChange = onPasswordChanged,
            onVisibilityChange = onVisibilityChanged)

        Spacer(modifier = Modifier.height(16.dp))

        ActionButton(
            modifier = Modifier.fillMaxWidth(),
            label = actionButtonName) {
            onActionClicked(email, password)
        }
    }
}

@Composable
@Preview
fun PreviewCredentialInput() {
    CredentialInput(
        actionButtonName = "Login",
        email = "email",
        password = "password",
        isPasswordVisible = true,
        onVisibilityChanged = {},
        onPasswordChanged = {},
        onEmailChanged = {},
        onActionClicked = { _, _ ->

        }
    )
}