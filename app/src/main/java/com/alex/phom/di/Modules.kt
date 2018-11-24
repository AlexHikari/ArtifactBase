package com.alex.phom.di

import android.content.Context
import com.alex.data.datasource.news.HMTLSource
import com.alex.data.datasource.news.RealmSource
import com.alex.data.repository.NewsRepository
import com.alex.domain.executor.Executor
import com.alex.domain.interactor.article.GetArticleUseCase
import com.alex.domain.interactor.home.GetNewsUseCase
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
}

val dataModule = Kodein.Module {

    //DataSources
    bind<HMTLSource>() with singleton { HMTLSource() }
    bind<RealmSource>() with singleton { RealmSource() }

    //Repositories

    bind<INewsRepository>() with singleton { NewsRepository(localSource = instance(), remoteSource = instance()) }
}