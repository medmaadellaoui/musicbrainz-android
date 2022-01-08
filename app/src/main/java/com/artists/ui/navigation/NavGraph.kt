package com.artists.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artists.ui.artists.ArtistListScreen
import com.artists.ui.details.ArtistDetailsScreen
import com.artists.ui.favorite.FavoriteListScreen
import com.artists.ui.splash.SplashScreen

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Tab.Splash.route) {
        composable(Tab.Splash.route, ) { SplashScreen(navController) }
        composable(Tab.Artists.route) { ArtistListScreen(navController) }
        composable(Tab.Favorite.route) { FavoriteListScreen(navController) }
        composable(
            "${Tab.ArtistDetails.route}/{artistId}",
            arguments = listOf(
                navArgument("artistId") { type = NavType.StringType }
            ),
        ) {
            ArtistDetailsScreen(it.arguments?.getString("artistId") ?: "")
        }
    }
}
