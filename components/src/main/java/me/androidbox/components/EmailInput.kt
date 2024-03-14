package me.androidbox.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    label: String,
    currentValue: String,
    icon: ImageVector,
    keyboardActions: KeyboardActions,
    focusRequester: FocusRequester? = null,
    onValueChange: (String) -> Unit) {
    TextField(
        modifier = modifier
            .focusRequester(focusRequester ?: FocusRequester()),
        value = currentValue,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label
            )
        },
        singleLine = true,
        shape = ShapeDefaults.Medium,
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewEmailInput() {
    EmailInput(
        modifier = Modifier.fillMaxWidth(),
        label = "Email",
        icon = Icons.Default.Email,
        currentValue = "steve@gmail.com",
        keyboardActions = KeyboardActions.Default,
        focusRequester = FocusRequester(),
        onValueChange = {}
    )
}


