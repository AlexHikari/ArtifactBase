package com.alex.data.datasource.cardset

import com.alex.data.models.CardSetVo
import com.alex.data.models.CardVo
import com.alex.data.models.mapper.toCardSet
import com.alex.data.models.mapper.toCardSetVo
import com.alex.domain.models.CardSet
import com.alex.domain.repository.CardSetLocalSource
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

class CardSetRealmSource : CardSetLocalSource {


    private var config = RealmConfiguration.Builder().build()
    override fun retrieveCardSets(): Flowable<CardSet> {
        val results: RealmResults<CardSetVo> = Realm.getInstance(config).where(CardSetVo::class.java).findAll()
        val returnedResults = mutableListOf<CardSet>()
        results.forEach {
            returnedResults.add(it.toCardSet())
        }
        return Flowable
                .just(returnedResults)
                .flatMap { list -> Flowable.fromIterable(list) }
    }

    override fun isCardSetsEmpty(): Boolean {
        return Realm.getInstance(config).where(CardSetVo::class.java).findAll().isEmpty()
    }

    override fun addCardSet(cardSet: CardSet, cardSetId: Long) {
        Realm.getInstance(config).executeTransaction {
            it.copyToRealmOrUpdate(cardSet.toCardSetVo(cardSetId))
        }
    }
}