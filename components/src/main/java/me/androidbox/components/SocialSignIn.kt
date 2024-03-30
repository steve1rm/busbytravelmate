package me.androidbox.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SocialSignIn(
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            modifier = Modifier
                .size(width = 56.dp, height = 44.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .border(width = 0.1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Transparent
            ),
            onClick = { /*TODO*/ }) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "google"
            )
        }

        IconButton(
            modifier = Modifier
                .size(width = 56.dp, height = 44.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .border(width = 0.1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
            onClick = { /*TODO*/ }) {
           Image(
                modifier = Modifier.size(20.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.apple_icon),
                contentDescription = "apple"
            )
        }

        IconButton(
            modifier = Modifier
                .size(width = 56.dp, height = 44.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .border(width = 0.1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
            onClick = {  }) {
            Image(
                modifier = Modifier.size(20.dp),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.facebook_icon),
                contentDescription ="facebook"
            )
        }
    }
}

@Composable
@Preview
fun PreviewSocialSignIn() {
    SocialSignIn(modifier = Modifier.fillMaxWidth())
}