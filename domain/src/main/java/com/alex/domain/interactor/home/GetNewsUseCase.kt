package com.alex.domain.interactor.home

import com.alex.domain.executor.Executor
import com.alex.domain.interactor.SingleInteractor
import com.alex.domain.models.NewsOverview
import com.alex.domain.repository.INewsRepository

/**
 * Get all News to be shown in the home page (app->presentation) module
 * @property repository INewsRepository
 * @constructor
 */
class GetNewsUseCase(private val repository: INewsRepository, executor: Executor) : SingleInteractor<List<NewsOverview>>(executor = executor) {

    /**
     * Exec of the useCase with OnSuccess and orError being lambdas
     * @param onSuccess (MutableList<NewsOverview>) -> Unit
     * @param onError (Throwable) -> Unit
     */
    fun execute(onSuccess: (List<NewsOverview>) -> Unit, onError: (Throwable) -> Unit, networkInfo: Boolean) {
        super.execute(onSuccess = onSuccess, onError = onError, single = repository.retrieveNews(networkInfo))
    }
}