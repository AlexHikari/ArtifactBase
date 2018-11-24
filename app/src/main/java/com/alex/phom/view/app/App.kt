package com.alex.phom.view.app

import android.app.Application
import com.alex.phom.di.appModule
import com.alex.phom.di.dataModule
import com.alex.phom.di.domainModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * App.
 */
class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(domainModule)
        import(dataModule)
    }

    override fun onCreate() {
        super.onCreate()
        initializeRealm()
    }

    private fun initializeRealm() {

        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.deleteRealm(realmConfiguration)
        Realm.setDefaultConfiguration(realmConfiguration)
    }

}