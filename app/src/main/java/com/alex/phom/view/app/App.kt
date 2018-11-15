package com.alex.phom.view.app

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import com.alex.phom.di.appModule
import com.alex.phom.di.dataModule
import com.alex.phom.di.domainModule

/**
 * App.
 */
class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(domainModule)
        import(dataModule)
    }

}