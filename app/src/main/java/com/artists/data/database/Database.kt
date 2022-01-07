package com.artists.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artists.data.model.Artist

/**
 * Created by Mohammed MAADELLAOUI on 07/01/2022.
 */
@Database(entities = [Artist::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun favArtistDao(): FavArtistDao
}
