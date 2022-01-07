package com.artists.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.artists.ui.navigation.Tab

/**
 * Created by Mohammed MAADELLAOUI on 02/01/2022.
 */

@Composable
fun BottomBar(
    tabs: Array<Tab>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primaryVariant
    ) {
        tabs.forEach { tab ->
            BottomNavigationItem(
                icon = { Icon(tab.icon, contentDescription = tab.title) },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.primary,
                alwaysShowLabel = true,
                selected = currentRoute == tab.route,
                onClick = {
                    navigateToRoute(tab.route)
                }
            )
        }
    }
}
