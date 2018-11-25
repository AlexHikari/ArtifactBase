package com.alex.data.repository

import com.alex.data.datasource.cardset.CardSetApiSource
import com.alex.data.datasource.cardset.CardSetRealmSource
import com.alex.data.models.CardSet
import com.alex.domain.models.CardList
import com.alex.domain.repository.ICardSetRepository
import io.reactivex.Single

class CardSetRepository(private val localSource: CardSetRealmSource, private val remoteSource: CardSetApiSource) : ICardSetRepository {

    override fun retrieveAllCards(): Single<List<CardSet>> {
        remoteSource.retrieveEndPoint(url = "https://playartifact.com/cardset/00").map { endpointOne ->
            remoteSource.retrieveEndPoint(url = "https://playartifact.com/cardset/01").map { endpointTwo ->
                return@map remoteSource.retrieveCards(endpointOne, endpointTwo)
            }
        }
    }

    override fun retrieveSingleCard(cardId: Long): Single<CardList> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}