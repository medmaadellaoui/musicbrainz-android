package com.artists.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.artists.R
import com.artists.data.model.Artist
import com.artists.ui.artists.CustomSurface

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */

@Composable
fun ArtistItem(
    artist: Artist,
    onArtistClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier
            .height(210.dp)
            .padding(bottom = 8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                painter = createPainter(artist.imageUrl ?: "", blurred = true),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .clickable(onClick = { onArtistClick(artist.id) })
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(Color.Transparent, MaterialTheme.colors.surface),
                            Offset(0f, 0f),
                            Offset(0f, 350f),
                            TileMode.Clamp
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    ArtistImage(
                        imageUrl = artist.imageUrl ?: "",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Text(
                    text = artist.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun ArtistImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    contentDescription: String?
) {
    CustomSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {

        Image(
            painter = createPainter(imageUrl),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun createPainter(
    imageUrl: String,
    blurred: Boolean = false
) = rememberImagePainter(
    data = imageUrl,
    builder = {
        crossfade(true)
        placeholder(drawableResId = R.drawable.ic_splash_logo)
        if (blurred) transformations(
            BlurTransformation(
                LocalContext.current,
                25f,
                1f
            ),
        )
    },
)
