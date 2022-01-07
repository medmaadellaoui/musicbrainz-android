package com.artists.data.database

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.artists.data.model.Artist
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */
@Dao
interface FavArtistDao {

    @Query("SELECT * FROM artist")
    fun getAll() : PagingSource<Int, Artist>

}