package me.androidbox.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    label: String,
    currentValue: String,
    leadingIcon: ImageVector = Icons.Default.Lock,
    visibilityIcon: ImageVector = Icons.Default.Visibility,
    visibilityOffIcon: ImageVector = Icons.Default.VisibilityOff,
    keyboardActions: KeyboardActions,
    focusRequester: FocusRequester? = null,
    onValueChange: (String) -> Unit,
    onForgotPassword: () -> Unit
) {

    var isPasswordVisbileState by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicSecureTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester ?: FocusRequester())
                .background(
                    brush = Brush.linearGradient(
                        listOf(Color.DarkGray, Color.Black)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(width = 0.1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
            value = currentValue,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = Color.LightGray, fontSize = 16.sp),
            decorator = { textFieldDecorator ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp)
                        )

                        textFieldDecorator()
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier

                    ) {
                        IconButton(onClick = {
                            isPasswordVisbileState = !isPasswordVisbileState
                        }) {
                            Icon(
                                imageVector =
                                if (isPasswordVisbileState) {
                                    visibilityIcon
                                } else {
                                    visibilityOffIcon
                                },
                                tint = Color.White,
                                contentDescription = label
                            )
                        }
                    }
                }
            },
            textObfuscationMode =
            if (isPasswordVisbileState) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.Hidden
            }
        )

        TextButton(
            onClick = onForgotPassword,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                textAlign = TextAlign.End,
                text = "Forgot password?",
                style = LocalTextStyle.current.copy(
                    color = Color.Gray,
                    fontSize = 16.sp
                ))
        }
    }
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
        keyboardActions = KeyboardActions.Default,
        onForgotPassword = {}
    )
}
