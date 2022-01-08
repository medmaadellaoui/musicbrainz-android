package com.artists.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */
sealed class Tab(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Splash: Tab("Splash Screen", Icons.Filled.Star, "splash_screen")
    object Artists : Tab("Artists", Icons.Filled.Home, "artists")
    object Favorite : Tab("Favorite", Icons.Filled.Favorite, "favorite")
    object ArtistDetails : Tab("Artist Details", Icons.Filled.Star, "artist")
}