package com.artists.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artists.data.model.Artist
import com.artists.data.repository.ArtistRepo
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */
class DetailsViewModel(private val repository: ArtistRepo) : ViewModel() {

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData(null)
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getArtistDetails(id: String): LiveData<Artist> {
        return LiveDataReactiveStreams.fromPublisher(repository.getArtistDetails(id).toFlowable())
    }

    fun saveAsFavorite(artist: Artist): LiveData<Boolean> {
        return repository.saveFavorite(artist)
            .toFavoriteLiveData()
    }

    fun isFavorite(artist: Artist): LiveData<Boolean> {
        return repository.exists(artist.id)
            .toFavoriteLiveData()
    }

    fun removeFavorite(artist: Artist): LiveData<Boolean> {
        return repository.deleteFavorite(artist)
            .toFavoriteLiveData()
    }

    private fun  Single<Boolean>.toFavoriteLiveData(): LiveData<Boolean> {
        subscribe({
            _isFavorite.value = it
        }, {
            _isFavorite.value = null
        })
        return isFavorite
    }

}


