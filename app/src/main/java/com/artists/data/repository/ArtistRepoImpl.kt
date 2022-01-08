package com.artists.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx3.Rx3Apollo
import com.artists.data.database.FavArtistDao
import com.artists.data.graphql.GetArtistDetailQuery
import com.artists.data.model.Artist
import com.artists.data.remote.RemotePagingSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow

class ArtistRepoImpl(
    private val remotePagingSource: RemotePagingSource,
    private val favoriteDao: FavArtistDao,
    private val apolloClient: ApolloClient
) : ArtistRepo {

    fun <M: Any> bindSchedulers(): SingleTransformer<M, M> {
        return SingleTransformer<M, M> { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun loadAll(): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
                prefetchDistance = 5,
                initialLoadSize = 15,
            ),
            pagingSourceFactory = { remotePagingSource }
        ).flow
    }

    override fun getFavorites(): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
                prefetchDistance = 5,
                initialLoadSize = 15,
            ),
            pagingSourceFactory = { favoriteDao.getAll() }
        ).flow
    }

    override fun getArtistDetails(id: String): Single<Artist> {
        val query = apolloClient.query(GetArtistDetailQuery(id))
        return Rx3Apollo.from(query)
            .firstOrError()
            .compose(bindSchedulers())
            .map { response ->
                response.data?.node?.fragments?.artistDetailsFragment?.let {
                    Artist(
                        it.id,
                        it.name ?: "",
                        it.fanArt?.thumbnails?.firstOrNull()?.url.toString(),
                    ).also { artist ->
                        artist.recordings = it.recordings?.nodes?.map { node ->
                            node?.title ?: ""
                        } ?: emptyList()
                    }
                } ?: throw Exception("error getting data from remote response")
            }
    }

    override fun saveFavorite(artist: Artist): Single<Boolean> {
        return favoriteDao.insert(artist)
            .compose(bindSchedulers())
            .map { it > 0 }
    }

    override fun exists(id: String): Single<Boolean> {
        return favoriteDao.count(id)
            .map { it > 0}
            .compose(bindSchedulers())
    }

    override fun deleteFavorite(artist: Artist): Single<Boolean> {
        return favoriteDao.delete(artist)
            .flatMap { exists(artist.id) }
            .compose(bindSchedulers())
    }
}
