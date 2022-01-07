package com.artists.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.artists.data.model.Artist

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */

@ExperimentalFoundationApi
@Composable
fun ArtistList(artists: LazyPagingItems<Artist>, onArtistClick: (String) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(artists.itemCount) { index ->
            artists[index]?.let {
                ArtistItem(
                    artist = it,
                    onArtistClick,
                    modifier = Modifier.padding(
                        PaddingValues(top = 8.dp, start = 8.dp, end = 8.dp)
                    )
                )
            }
        }
    }
}