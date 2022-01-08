package com.artists.ui.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.artists.ui.component.ArtistList
import com.artists.ui.navigation.Tab
import org.koin.androidx.compose.getViewModel

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */

@ExperimentalFoundationApi
@Composable
fun FavoriteListScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = getViewModel()
) {

    val favorites = viewModel.getFavorites().collectAsLazyPagingItems()
    ArtistList(artists = favorites, onArtistClick = {
        navController.navigate("${Tab.ArtistDetails.route}/$it")
    })
}
