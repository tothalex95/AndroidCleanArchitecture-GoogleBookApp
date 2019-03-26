package hu.miskolc.uni.iit.googlebookapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
            viewModel.getSearchResult(search_edit_text.text.toString())
                .observe(this, Observer {
                    (application as GoogleBookApp).searchResult = it
                    adapter.searchResult = it
                    adapter.notifyDataSetChanged()
                    progress_bar.visibility = View.GONE
                })
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}
