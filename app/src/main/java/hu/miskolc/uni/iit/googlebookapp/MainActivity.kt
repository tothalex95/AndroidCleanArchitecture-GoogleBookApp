package hu.miskolc.uni.iit.googlebookapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import hu.miskolc.uni.iit.googlebookapp.presentation.adapter.SearchResultAdapter
import hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private val viewModel by inject<SearchResultViewModel>()
    private val adapter = SearchResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_results_recycler_view.layoutManager = LinearLayoutManager(this)
        search_results_recycler_view.adapter = adapter

        search_button.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            search_results_recycler_view.visibility = View.GONE
            viewModel.getSearchResult(
                GetBooks.Params(
                    search_edit_text.text.toString(),
                    10
                )
            )
                .observe(this, Observer {
                    (application as GoogleBookApp).searchResult.items.clear()
                    (application as GoogleBookApp).searchResult.items.addAll(it.items)

                    adapter.searchResult.items.clear()
                    adapter.searchResult.items.addAll(it.items)

                    adapter.notifyDataSetChanged()

                    progress_bar.visibility = View.GONE
                    search_results_recycler_view.visibility = View.VISIBLE
                })
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}
