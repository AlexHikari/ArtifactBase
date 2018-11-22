package com.alex.domain.interactor.article

import com.alex.domain.executor.Executor
import com.alex.domain.interactor.SingleInteractor
import com.alex.domain.models.ArticleOverview
import com.alex.domain.repository.INewsRepository

class GetArticleUseCase(private val repository: INewsRepository, executor: Executor) : SingleInteractor<ArticleOverview>(executor = executor) {

    fun execute(url: String, onSuccess: (ArticleOverview) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess = onSuccess, onError = onError, single = repository.retrieveArticle(url))
    }
}