package com.alex.phom.di

import android.content.Context
import com.alex.data.datasource.HMTLSource
import com.alex.data.datasource.RealmSource
import com.alex.data.repository.NewsRepository
import com.alex.domain.executor.Executor
import com.alex.domain.interactor.home.GetNewsUseCase
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
}

val dataModule = Kodein.Module {

    //DataSources
    bind<HMTLSource>() with singleton { HMTLSource() }
    bind<RealmSource>() with singleton { RealmSource() }

    //Repositories

    bind<NewsRepository>() with singleton { NewsRepository(htmlSource = instance(), realmSource = instance()) }
}