package com.artists.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FloatSpringSpec
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artists.R
import com.artists.ui.navigation.Tab
import kotlinx.coroutines.delay

/**
 * Created by Mohammed MAADELLAOUI on 22/12/2021.
 */

@Composable
fun SplashScreen(navHostController: NavHostController) {

    val animatedSize = remember { Animatable(0f) }
    LaunchedEffect(animatedSize) {
        delay(300)
        animatedSize.animateTo(targetValue = 96f, animationSpec = FloatSpringSpec(
            Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ))
        delay(2000)
        navHostController.popBackStack()
        navHostController.navigate(Tab.Artists.route)
    }

    Splash(animatedSize.value)
}

@Composable
private fun Splash(iconSize: Float) {
    Box(
        Modifier
            .background(Color.Black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painterResource(id = R.drawable.ic_splash_logo),
            contentDescription = "logo",
            tint = Color.White,
            modifier = Modifier.size(iconSize.dp)
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
fun SplashScreenPreview() {
    Splash(96f)
}