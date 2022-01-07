package com.artists.ui.artists

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.artists.data.model.Artist
import com.artists.data.repository.ArtistRepo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammed MAADELLAOUI on 06/01/2022.
 */
class ArtistsViewModel(private val repository: ArtistRepo) : ViewModel() {

    private var flow: Flow<PagingData<Artist>>? = null

    fun getArtists(): Flow<PagingData<Artist>> {
        Log.d("artists", "request data")
        return flow ?: repository.loadAll()
            .cachedIn(viewModelScope)
            .also {
                flow = it
            }
    }
}
