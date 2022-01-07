package com.artists.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by Mohammed MAADELLAOUI on 01/01/2022.
 */
@Entity
data class Artist(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
) {

    @Ignore
    var recordings: List<String> = emptyList()
}
