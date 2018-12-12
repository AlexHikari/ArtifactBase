package com.alex.domain.interactor.cards

import com.alex.domain.executor.Executor
import com.alex.domain.interactor.FlowableInteractor
import com.alex.domain.models.CardSet
import com.alex.domain.repository.ICardSetRepository

class GetCardSetsUseCase(private val repository: ICardSetRepository, executor: Executor) : FlowableInteractor<CardSet>(executor = executor) {

    fun execute(onNext: (CardSet) -> Unit, onError: (Throwable) -> Unit, onComplete: () -> Unit) {
        super.execute(onNext = onNext, onError = onError, flowable = repository.retrieveAllCards(), onComplete = onComplete)
    }
}