package hu.miskolc.uni.iit.googlebookapp.presentation.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.miskolc.uni.iit.googlebookapp.GoogleBookApp
import hu.miskolc.uni.iit.googlebookapp.R
import hu.miskolc.uni.iit.googlebookapp.domain.model.Book
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import hu.miskolc.uni.iit.googlebookapp.presentation.activity.BookDetailsActivity
import hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_search_result.view.*

class SearchResultAdapter(private val searchResultViewModel: SearchResultViewModel, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    var searchResult = SearchResult(0, mutableListOf())

    override fun getItemCount(): Int {
        return searchResult.items.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        if(position+1==itemCount && itemCount<searchResult.totalItems){
            searchResultViewModel.getSearchResult(GetBooks.Params("ss"/*search_edit_text.text.toString()*/,itemCount))
                .observe(lifecycleOwner, Observer {
                    searchResult.items.addAll(it.items)
                    notifyDataSetChanged()
                })
        }

        holder.bind(searchResult.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_search_result,
                parent,
                false
            )
        ) {
            val intent = Intent(parent.context, BookDetailsActivity::class.java)
            intent.putExtra("BOOK_POSITION", it)
            parent.context.startActivity(intent)
        }
    }

    class SearchResultViewHolder(
        view: View,
        private val listener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(book: Book) {
            itemView.book_title_textview.text = book.volumeInfo.title
            itemView.book_authors_textview.text = book.volumeInfo.authors?.toString()
            itemView.book_publisher_textview.text = book.volumeInfo.publisher
            Picasso.get().load(book.volumeInfo.imageLinks.thumbnail).into(itemView.book_thumbnail_imageview)
            itemView.setOnClickListener {
                listener(adapterPosition)
            }
        }

    }

}