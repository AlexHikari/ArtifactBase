package com.alex.data.datasource.cardset

import com.alex.domain.repository.CardSetRemoteSource
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class CardSetApiSource : CardSetRemoteSource {
    private var api: CardSetApi

    init {
        val retrofitforDataSet = Retrofit.Builder()
                .baseUrl("https://playartifact.com/cardset/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        api = retrofitforDataSet.create(CardSetApi::class.java)
    }

    override fun retrieveEndPoints(): List<Single<String>> {
        val endpointlist: MutableList<String> = mutableListOf()
        endpointlist.add(api.retrieveEndPoint(targetDataSet = "00"))
        endpointlist.add(api.retrieveEndPoint(targetDataSet = "01"))
    }

    override fun retrieveCards() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}