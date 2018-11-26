package com.alex.data.repository

import com.alex.data.datasource.cardset.CardSetApiSource
import com.alex.data.datasource.cardset.CardSetRealmSource
import com.alex.domain.models.Card
import com.alex.domain.models.CardSet
import com.alex.domain.repository.ICardSetRepository
import io.reactivex.Flowable
import io.reactivex.Single

class CardSetRepository(private val localSource: CardSetRealmSource, private val remoteSource: CardSetApiSource) : ICardSetRepository {

    override fun retrieveAllCards(): Flowable<CardSet> {
        return remoteSource.retrieveEndPoint(url = "https://playartifact.com/cardset/00").flatMapPublisher { endpointOne ->
            return@flatMapPublisher remoteSource.retrieveEndPoint(url = "https://playartifact.com/cardset/01").flatMapPublisher { endpointTwo ->
                return@flatMapPublisher remoteSource.retrieveCards(endpointOne.cdnRoot + endpointOne.url, endpointTwo.cdnRoot + endpointTwo.cdnRoot)
            }
        }
    }

    override fun retrieveSingleCard(cardId: Long): Single<Card> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}