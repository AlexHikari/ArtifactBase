package com.alex.data.datasource.cardset

import com.alex.data.models.CardSetDAO
import com.alex.data.models.mapper.toCardSet
import com.alex.data.models.mapper.toCardSetDAO
import com.alex.domain.models.CardSet
import com.alex.domain.repository.CardSetLocalSource
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

class CardSetRealmSource : CardSetLocalSource {


    private var config = RealmConfiguration.Builder().build()
    override fun retrieveCardSets(): Flowable<CardSet> {
        val results: RealmResults<CardSetDAO> = Realm.getInstance(config).where(CardSetDAO::class.java).findAll()
        return Flowable
                .just(results)
                .flatMap { list -> Flowable.fromIterable(list) }
                .map { cardSet -> cardSet.toCardSet() }

    }

    override fun isCardSetsEmpty(): Boolean {
        return Realm.getInstance(config).where(CardSetDAO::class.java).findAll().isEmpty()
    }

    override fun addCardSet(cardSet: CardSet) {
        Realm.getInstance(config).executeTransaction {
            it.insertOrUpdate(cardSet.toCardSetDAO())
        }
    }
}