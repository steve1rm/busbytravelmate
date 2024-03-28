package me.androidbox.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    label: String,
    currentValue: String,
    leadingIcon: ImageVector,
    keyboardActions: KeyboardActions,
    onEmailChange: (String) -> Unit) {

    BasicTextField2(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(Color.DarkGray, Color.Black)
                ),
                shape = RoundedCornerShape(size = 8.dp)
            )
            .border(width = 0.1.dp, color = Color.LightGray, shape = RoundedCornerShape(size = 8.dp)),
        value = currentValue,
        onValueChange = onEmailChange,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = TextStyle(color = Color.LightGray, fontSize = 16.sp),
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
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

                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = "",
                        tint = Color.Green,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp))

                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewEmailInput() {
    EmailInput(
        modifier = Modifier.fillMaxWidth(),
        label = "Email",
        leadingIcon = Icons.Default.Email,
        currentValue = "steve@gmail.com",
        keyboardActions = KeyboardActions.Default,
        onEmailChange = {}
    )
}

