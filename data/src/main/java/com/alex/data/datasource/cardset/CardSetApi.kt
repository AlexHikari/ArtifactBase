package com.alex.data.datasource.cardset

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface CardSetApi {

    @GET(value = "/cardset/")
    fun retrieveEndPoint(@Url() targetDataSet: String): Single<String>
}