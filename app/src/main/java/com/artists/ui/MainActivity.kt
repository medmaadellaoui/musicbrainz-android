package com.artists.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.artists.ui.home.HomeScreen
import com.artists.ui.theme.ArtistsTheme

class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ArtistsTheme {
                val navController = rememberNavController()
                HomeScreen(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtistsTheme {
    }
}
