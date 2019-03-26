package hu.miskolc.uni.iit.googlebookapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hu.miskolc.uni.iit.googlebookapp.R
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import kotlinx.android.synthetic.main.row_search_result.view.*

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    var searchResult = SearchResult(0, mutableListOf())

    override fun getItemCount(): Int {
        return searchResult.totalItems
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val book = searchResult.items[position]
        holder.itemView.book_title_textview.text = book.volumeInfo.title
        holder.itemView.book_authors_textview.text = book.volumeInfo.authors?.toString()
        holder.itemView.book_publisher_textview.text = book.volumeInfo.publisher
        Picasso.get().load(book.volumeInfo.imageLinks.thumbnail).into(holder.itemView.book_thumbnail_imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_search_result,
                parent,
                false
            )
        )
    }

    class SearchResultViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view)

}