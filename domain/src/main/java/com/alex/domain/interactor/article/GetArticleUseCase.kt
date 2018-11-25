package com.alex.domain.interactor.article

import com.alex.domain.executor.Executor
import com.alex.domain.interactor.SingleInteractor
import com.alex.domain.models.Article
import com.alex.domain.repository.INewsRepository

class GetArticleUseCase(private val repository: INewsRepository, executor: Executor) : SingleInteractor<Article>(executor = executor) {

    fun execute(url: String, onSuccess: (Article) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, single = repository.retrieveArticle(url))
    }
}