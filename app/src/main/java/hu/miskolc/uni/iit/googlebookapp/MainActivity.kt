package hu.miskolc.uni.iit.googlebookapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import hu.miskolc.uni.iit.googlebookapp.data.repository.BookRepositoryImpl
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel.SearchResultViewModel
import hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel.SearchResultViewModelFactory

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private lateinit var viewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = SearchResultViewModelFactory(GetBooks(BookRepositoryImpl())).create(SearchResultViewModel::class.java)

        viewModel.getSearchResult("Metro 2033")
            .observe(this, Observer { it.items.forEach { b -> Log.i("bookdata", b.toString()) } })
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}
