package me.androidbox.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
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

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Password",
            style = LocalTextStyle.current.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 14.sp))

        Spacer(modifier = Modifier.height(2.dp))

        BasicSecureTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .focusRequester(focusRequester ?: FocusRequester())
                .background(
                    brush = Brush.linearGradient(
                        listOf(MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.colorScheme.onSecondary)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(width = 0.1.dp, color = MaterialTheme.colorScheme.onPrimaryContainer, shape = RoundedCornerShape(8.dp)),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimaryContainer),
            value = currentValue,
            onValueChange = onValueChange,
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 20.sp),
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
                                .size(32.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
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
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
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
            modifier = Modifier
                .align(Alignment.End),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                modifier = Modifier.padding(0.dp),
                textAlign = TextAlign.End,
                text = "Forgot password?",
                style = LocalTextStyle.current.copy(
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 12.sp
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

    PasswordInput(
        modifier = Modifier.fillMaxWidth(),
        label = "Password",
        leadingIcon = Icons.Default.Lock,
        currentValue = textInput,
        onValueChange = {
            textInput = it
        },
        onForgotPassword = {}
    )
}
