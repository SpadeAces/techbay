package com.ansar.techbay

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.ansar.techbay.data.db.AppDatabase
import com.ansar.techbay.data.network.MyApi
import com.ansar.techbay.data.network.NetworkConnectionInterceptor
import com.ansar.techbay.data.preferences.PreferenceProvider
import com.ansar.techbay.data.repostiory.PostsRepository
import com.ansar.techbay.ui.Posts.PostsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    @RequiresApi(Build.VERSION_CODES.O)
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { PostsRepository(instance(), instance(), instance()) }
        bind() from provider{ PostsViewModelFactory(instance()) }

    }
}