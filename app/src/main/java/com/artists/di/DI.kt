package com.artists.di

import androidx.room.Room
import com.artists.data.database.Database
import com.artists.data.remote.ApolloClientProvider
import com.artists.data.remote.RemotePagingSource
import com.artists.data.repository.ArtistRepo
import com.artists.data.repository.ArtistRepoImpl
import com.artists.ui.artists.ArtistsViewModel
import com.artists.ui.details.DetailsViewModel
import com.artists.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Mohammed MAADELLAOUI on 05/01/2022.
 */
val mainModule = module {

    single {
        Room.databaseBuilder(
            get(),
            Database::class.java,
            "main-database"
        ).build()
    }
    single { ApolloClientProvider().provide("https://graphbrainz.herokuapp.com/") }
    single { RemotePagingSource(get()) }
    single<ArtistRepo> { ArtistRepoImpl(get(), get<Database>().favArtistDao(), get()) }
    viewModel { ArtistsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}
