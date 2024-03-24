package me.androidbox.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    label: String,
    showLoading: Boolean = false,
    onButtonClicked: () -> Unit
) {

    Button(
        modifier = modifier,
        onClick = {
        onButtonClicked()
    },
        content = {
            if(showLoading) {
                CircularProgressIndicator(
                    color = Color.White
                )
            }
            else {
                Text(text = label)
            }
        }
    )
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true)
fun PreviewActionButtonLoading() {
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        label = "Login",
        showLoading = true,
        onButtonClicked = {}
    )
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true)
fun PreviewActionButton() {
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        label = "Login",
        showLoading = false,
        onButtonClicked = {}
    )
}