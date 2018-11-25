package com.alex.data.datasource.cardset

import com.alex.data.models.mapper.toCardSet
import com.alex.data.models.mapper.toEndPoint
import com.alex.domain.models.CardSet
import com.alex.domain.models.EndPoint
import com.alex.domain.repository.CardSetRemoteSource
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class CardSetApiSource : CardSetRemoteSource {
    private var api: CardSetApi

    init {
        val retrofitforDataSet = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        api = retrofitforDataSet.create(CardSetApi::class.java)
    }

    override fun retrieveEndPoint(url: String): Single<EndPoint> {
        return api.retrieveEndPoint(targetUrl = url).map { return@map it.toEndPoint() }
    }

    override fun retrieveCards(firstEndpoint: String, secondEndpoint: String): Flowable<List<CardSet>> {
        return api.retrieveCardSet(firstEndpoint).map { it.toCardSet() }.mergeWith(api.retrieveCardSet(secondEndpoint).map { it.toCardSet() })
    }
}