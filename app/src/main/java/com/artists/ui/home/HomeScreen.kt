package com.artists.ui.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.artists.ui.component.BottomBar
import com.artists.ui.navigation.MainNavHost
import com.artists.ui.navigation.Tab
import com.artists.ui.theme.TopBar

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavHostController) {
    val tabs = arrayOf(
        Tab.Artists,
        Tab.Favorite
    )
    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in tabs.map { it.route }

    Scaffold(
        topBar = { if(showBottomBar) TopBar() },
        bottomBar = {
            if (showBottomBar) {
                BottomBar(
                    tabs = tabs,
                    currentRoute = Tab.Artists.route,
                    navigateToRoute = {
                        navController.navigate(it)
                    }
                )
            }
        },
        content = {
            MainNavHost(navController)
        }
    )
}
