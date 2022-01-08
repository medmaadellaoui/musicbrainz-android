package com.artists.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.artists.R
import com.artists.data.model.Artist
import com.artists.ui.component.ArtistImage
import com.artists.ui.component.createPainter
import org.koin.androidx.compose.getViewModel

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */

@Composable
fun ArtistDetailsScreen(id: String, viewModel: DetailsViewModel = getViewModel()) {

    val artist by viewModel.getArtistDetails(id).observeAsState()

    artist?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                painter = createPainter(it.imageUrl ?: "", blurred = true),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(Color.Transparent, MaterialTheme.colors.background),
                            Offset(0f, 0f),
                            Offset(0f, 900f),
                            TileMode.Clamp
                        )
                    ),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    DetailsHeader(it, artist, viewModel)
                }

                items(it.recordings) { recording ->
                    Card(
                        Modifier.padding(4.dp),
                        backgroundColor = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(32.dp),
                        elevation = 4.dp
                    ) {
                        Row(Modifier.padding(16.dp)) {
                            Icon(
                                painterResource(R.drawable.ic_baseline_album_24),
                                "",
                                tint = Color.LightGray
                            )

                            Spacer(Modifier.size(16.dp))

                            Text(
                                recording, overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.primary,
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailsHeader(
    it: Artist,
    artist: Artist?,
    viewModel: DetailsViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ArtistImage(
            imageUrl = it.imageUrl ?: "",
            contentDescription = it.name,
            modifier = Modifier
                .size(250.dp)
                .padding(32.dp)
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = it.name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .wrapContentSize()
            )

            Spacer(Modifier.size(16.dp))
            FavoriteBtn(artist, viewModel, it)
        }
        Spacer(Modifier.size(16.dp))
    }
}

@Composable
private fun FavoriteBtn(
    artist: Artist?,
    viewModel: DetailsViewModel,
    it: Artist
) {
    val isFavorite : Boolean? by artist?.let {
        viewModel.isFavorite(it).observeAsState(false)
    } ?: remember {
        mutableStateOf(false)
    }

    FloatingActionButton(
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = when (isFavorite) {
            false, true -> MaterialTheme.colors.secondaryVariant
            else -> MaterialTheme.colors.error
        },
        onClick = {
            if (isFavorite == true)
                viewModel.removeFavorite(it)
            else
                viewModel.saveAsFavorite(it)
        }
    ) {
        Icon(
            when (isFavorite) {
                null, false -> Icons.Rounded.FavoriteBorder
                else -> Icons.Filled.Favorite
            },
            "favorite icon"
        )
    }
}
