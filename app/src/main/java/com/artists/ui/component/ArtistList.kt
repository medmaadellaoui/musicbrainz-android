package com.artists.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.artists.data.model.Artist

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */

@ExperimentalFoundationApi
@Composable
fun ArtistList(artists: LazyPagingItems<Artist>, onArtistClick: (String) -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(MaterialTheme.colors.background),
            contentPadding = PaddingValues(4.dp),
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

        if (artists.loadState.source.refresh is LoadState.Loading) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .weight(0.2f)
            ) {

                CircularProgressIndicator(
                    Modifier
                        .padding(16.dp)
                        .align(Alignment.TopCenter),
                    color = MaterialTheme.colors.secondaryVariant,
                    strokeWidth = 8.dp,
                )
            }
        }
    }
}
