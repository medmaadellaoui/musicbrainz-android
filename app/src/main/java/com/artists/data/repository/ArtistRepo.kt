package com.artists.data.repository

import androidx.paging.PagingData
import com.artists.data.model.Artist
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammed MAADELLAOUI on 04/01/2022.
 */
interface ArtistRepo {

    fun loadAll(): Flow<PagingData<Artist>>
    fun getFavorites(): Flow<PagingData<Artist>>
    fun getArtistDetails(id: String): Single<Artist>
    fun saveFavorite(artist: Artist): Single<Boolean>
    fun exists(id: String): Single<Boolean>
    fun deleteFavorite(artist: Artist): Single<Boolean>
}
