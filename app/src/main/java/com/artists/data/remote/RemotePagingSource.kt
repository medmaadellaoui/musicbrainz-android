package com.artists.data.remote

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.rx3.Rx3Apollo
import com.artists.data.graphql.GetArtistsQuery
import com.artists.data.model.Artist
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Mohammed MAADELLAOUI on 04/01/2022.
 */
class RemotePagingSource(private val apolloClient: ApolloClient) : RxPagingSource<String, Artist>() {

    private var previousKey: String? = null

    override fun getRefreshKey(state: PagingState<String, Artist>): String? = null

    override fun loadSingle(params: LoadParams<String>): Single<LoadResult<String, Artist>> {
        Log.d("artists","request data from remote api")
        val currentKey = params.key

        val query = apolloClient.query(GetArtistsQuery(params.loadSize, Input.fromNullable(currentKey)))
        return Rx3Apollo.from(query)
            .firstOrError()
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it.data, currentKey) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: GetArtistsQuery.Data?, currentKey: String?): LoadResult<String, Artist> {
        val artists = data?.search?.artists?.nodes?.map {
            Log.d("logos", ""+it)
            Artist(it?.id ?: "-1", it?.name ?: "", it?.fanArt?.thumbnails?.firstOrNull()?.url.toString())
        } ?: emptyList()

        return LoadResult.Page(
            data = artists,
            prevKey = previousKey,
            nextKey = data?.search?.artists?.edges?.lastOrNull()?.cursor
        ).also {
            previousKey = currentKey
        }
    }
}
