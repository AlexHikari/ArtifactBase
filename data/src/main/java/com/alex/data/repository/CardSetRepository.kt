package com.alex.data.repository

import com.alex.data.datasource.cardset.CardSetApiSource
import com.alex.data.datasource.cardset.CardSetRealmSource
import com.alex.domain.models.CardSet
import com.alex.domain.repository.ICardSetRepository
import io.reactivex.Flowable

class CardSetRepository(private val localSource: CardSetRealmSource, private val remoteSource: CardSetApiSource) : ICardSetRepository {

    override fun retrieveAllCards(): Flowable<CardSet> {
        var CardSetIndex: Long = 1
        if (localSource.isCardSetsEmpty()) {
            return remoteSource.retrieveEndPoint(url = "https://playartifact.com/cardset/00").flatMapPublisher { endpointOne ->
                return@flatMapPublisher remoteSource.retrieveEndPoint(url = "https://playartifact.com/cardset/01").flatMapPublisher { endpointTwo ->
                    remoteSource.retrieveCards(endpointOne.cdnRoot + endpointOne.url.drop(1), endpointTwo.cdnRoot + endpointTwo.url.drop(1)).map {
                        localSource.addCardSet(it, CardSetIndex)
                        CardSetIndex++
                        return@map it
                    }
                }
            }
        } else {
            return localSource.retrieveCardSets()
        }
    }

}