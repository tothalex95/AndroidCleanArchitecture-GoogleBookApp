package hu.miskolc.uni.iit.googlebookapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import hu.miskolc.uni.iit.googlebookapp.presentation.adapter.SearchResultAdapter
import hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    private val viewModel by inject<SearchResultViewModel>()
    private val adapter = SearchResultAdapter()

    private var searchButtonClicked = false
    private var isLoading = false
    private val searchResultObserver = Observer<SearchResult> {
        if (searchButtonClicked) {
            (application as GoogleBookApp).searchResult.items.clear()
            adapter.searchResult.items.clear()
        }

        (application as GoogleBookApp).searchResult.items.addAll(it.items)
        adapter.searchResult.items.addAll(it.items)

        adapter.notifyDataSetChanged()

        isLoading = false
        progress_bar.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_results_recycler_view.layoutManager = LinearLayoutManager(this)
        search_results_recycler_view.adapter = adapter

        viewModel.searchResultLiveData.observe(this, searchResultObserver)

        search_button.setOnClickListener {
            searchButtonClicked = true
            isLoading = true
            progress_bar.visibility = View.VISIBLE
            viewModel.search(
                GetBooks.Params(
                    search_edit_text.text.toString(),
                    10
                )
            )
        }

        search_results_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val fetchedItemCount = recyclerView.layoutManager?.itemCount
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (dy > 0
                    && fetchedItemCount == lastVisibleItemPosition + 1
                    && !isLoading
                ) {
                    searchButtonClicked = false
                    isLoading = true
                    progress_bar.visibility = View.VISIBLE

                    viewModel.search(
                        GetBooks.Params(
                            search_edit_text.text.toString(),
                            maxResults = 10,
                            startIndex = fetchedItemCount
                        )
                    )
                }
            }
        })
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}
