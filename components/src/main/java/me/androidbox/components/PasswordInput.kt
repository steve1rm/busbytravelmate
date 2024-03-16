package me.androidbox.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    label: String,
    currentValue: String,
    leadingIcon: ImageVector = Icons.Default.Lock,
    isPasswordVisible: Boolean,
    visibilityIcon: ImageVector = Icons.Default.Visibility,
    visibilityOffIcon: ImageVector = Icons.Default.VisibilityOff,
    keyboardActions: KeyboardActions,
    focusRequester: FocusRequester? = null,
    onValueChange: (String) -> Unit,
    onVisibilityChange: () -> Unit
    ) {

    TextField(
        modifier = modifier.focusRequester(focusRequester ?: FocusRequester()),
        value = currentValue,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = label
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                onVisibilityChange()
            }) {
                Icon(
                    imageVector = if (isPasswordVisible) {
                        visibilityIcon }
                    else { visibilityOffIcon
                    },
                    contentDescription = label
                )
            }
        },
        singleLine = true,
        shape = ShapeDefaults.Medium,
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        }
        else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewPasswordInput() {

    var textInput by remember {
        mutableStateOf("")
    }

    var isVisibilityState by remember {
        mutableStateOf(false)
    }

    PasswordInput(
        modifier = Modifier.fillMaxWidth(),
        label = "Password",
        leadingIcon = Icons.Default.Lock,
        currentValue = textInput,
        onValueChange = {
            textInput = it
        },
        onVisibilityChange = {
            isVisibilityState = !isVisibilityState
        },
        keyboardActions = KeyboardActions.Default,
        isPasswordVisible = isVisibilityState
    )
}
