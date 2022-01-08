package com.artists.data.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import com.artists.data.model.Artist
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */
@Dao
interface FavArtistDao {

    @Query("SELECT * FROM artist")
    fun getAll() : PagingSource<Int, Artist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artist: Artist) : Single<Long>

    @Query("SELECT count() FROM artist WHERE id = :id")
    fun count(id: String): Single<Int>

    @Delete
    fun delete(artist: Artist): Single<Int>

}