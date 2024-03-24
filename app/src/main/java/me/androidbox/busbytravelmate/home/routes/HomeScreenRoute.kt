package me.androidbox.busbytravelmate.home.routes

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbytravelmate.home.screens.HomeScreen

object HomeScreenRoute : Screen {

    @Composable
    override fun Content() {
        HomeScreen()
    }
}
