package com.alex.phom.di

import android.content.Context
import com.alex.data.datasource.cardset.CardSetApiSource
import com.alex.data.datasource.cardset.CardSetRealmSource
import com.alex.data.datasource.news.NewsHTMLSource
import com.alex.data.datasource.news.NewsRealmSource
import com.alex.data.repository.CardSetRepository
import com.alex.data.repository.NewsRepository
import com.alex.domain.executor.Executor
import com.alex.domain.interactor.article.GetArticleUseCase
import com.alex.domain.interactor.cards.GetCardSetsUseCase
import com.alex.domain.interactor.home.GetNewsUseCase
import com.alex.domain.repository.ICardSetRepository
import com.alex.domain.repository.INewsRepository
import com.alex.phom.error.AndroidErrorHandler
import com.alex.phom.error.ErrorHandler
import com.alex.phom.executor.RxExecutor
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

/**
 * Modules
 */
fun appModule(context: Context) = Kodein.Module {
    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { RxExecutor() }
    bind<ErrorHandler>() with singleton { AndroidErrorHandler(context = context) }
}

val domainModule = Kodein.Module {
    bind<GetNewsUseCase>() with singleton { GetNewsUseCase(repository = instance(), executor = instance()) }
    bind<GetArticleUseCase>() with singleton { GetArticleUseCase(repository = instance(), executor = instance()) }
    bind<GetCardSetsUseCase>() with singleton { GetCardSetsUseCase(repository = instance(), executor = instance()) }
}

val dataModule = Kodein.Module {

    //DataSources
    bind<NewsHTMLSource>() with singleton { NewsHTMLSource() }
    bind<NewsRealmSource>() with singleton { NewsRealmSource() }
    bind<CardSetApiSource>() with singleton { CardSetApiSource() }
    bind<CardSetRealmSource>() with singleton { CardSetRealmSource() }

    //Repositories

    bind<INewsRepository>() with singleton { NewsRepository(localSource = instance(), remoteSource = instance()) }
    bind<ICardSetRepository>() with singleton { CardSetRepository(localSource = instance(), remoteSource = instance()) }
}