package com.artists.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.artists.R

/**
 * Created by Mohammed MAADELLAOUI on 02/01/2022.
 */


@Composable
fun TopBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary,
        elevation = 4.dp,
        content = {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(R.drawable.ic_splash_logo),
                    "logo",
                    tint = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(Modifier.size(16.dp))
                Text("Artists App")
            }
        }
    )
}