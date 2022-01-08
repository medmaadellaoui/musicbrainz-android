package com.artists.ui.artists

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.artists.ui.component.ArtistList
import com.artists.ui.navigation.Tab
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

/**
 * Created by Mohammed MAADELLAOUI on 24/12/2021.
 */

@ExperimentalFoundationApi
@Composable
fun ArtistListScreen(navController: NavController, viewModel: ArtistsViewModel = getViewModel()) {
    val artists = viewModel.getArtists().collectAsLazyPagingItems()

    ArtistList(artists) { artistId ->
        navController.navigate("${Tab.ArtistDetails.route}/$artistId")
    }
}



@Composable
fun CustomSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colors.background,
    contentColor: Color = Color.Red,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .background(
                color = color,
                shape = shape
            )
            .clip(shape)
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
    }
}
