package com.artists.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.artists.data.model.Artist
import com.artists.data.repository.ArtistRepo

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */
class DetailsViewModel(private val repository: ArtistRepo) : ViewModel() {

    fun getArtistDetails(id: String) : LiveData<Artist> {
        return LiveDataReactiveStreams.fromPublisher(repository.getArtistDetails(id).toFlowable())
    }

}