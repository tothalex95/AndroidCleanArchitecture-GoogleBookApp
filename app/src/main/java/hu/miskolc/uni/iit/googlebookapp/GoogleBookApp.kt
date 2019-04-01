package hu.miskolc.uni.iit.googlebookapp

import android.app.Application
import hu.miskolc.uni.iit.googlebookapp.api.GoogleBookService
import hu.miskolc.uni.iit.googlebookapp.data.repository.BookRepository
import hu.miskolc.uni.iit.googlebookapp.data.repository.BookRepositoryImpl
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel.SearchResultViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GoogleBookApp : Application() {

    var searchResult = SearchResult(0, mutableListOf())

    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { GoogleBookService.create() }
            single<BookRepository> { BookRepositoryImpl(get()) }
            single { GetBooks(get()) }
            factory { SearchResultViewModel(get()) }
        }

        startKoin {
            androidLogger()
            androidContext(this@GoogleBookApp)
            modules(appModule)
        }
    }

}