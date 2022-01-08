package com.artists.ui.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.artists.data.model.Artist
import com.artists.data.repository.ArtistRepo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */
class FavoriteViewModel(private val repository: ArtistRepo) : ViewModel() {

    private var flow: Flow<PagingData<Artist>>? = null

    fun getFavorites(): Flow<PagingData<Artist>> {
        return flow ?: repository.getFavorites()
            .cachedIn(viewModelScope)
            .also { flow = it }
    }

}