package com.alex.data.datasource.cardset

import com.alex.data.models.CardSetDAO
import com.alex.data.models.EndPointDAO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface CardSetApi {

    @GET()
    fun retrieveEndPoint(@Url targetUrl: String): Single<EndPointDAO>

    @GET()
    fun retrieveCardSet(@Url targeturl: String): Single<CardSetDAO>
}