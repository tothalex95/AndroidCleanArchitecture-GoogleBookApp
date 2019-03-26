package hu.miskolc.uni.iit.googlebookapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import hu.miskolc.uni.iit.googlebookapp.GoogleBookApp
import hu.miskolc.uni.iit.googlebookapp.R
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailsActivity : AppCompatActivity() {

    private var bookPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        show_previous_book_button.setOnClickListener {
            --bookPosition
            bind()
        }

        show_next_book_button.setOnClickListener {
            ++bookPosition
            bind()
        }

        bookPosition = intent.getIntExtra("BOOK_POSITION", 0)
        bind()
    }

    private fun bind() {
        val book = (application as GoogleBookApp).searchResult.items[bookPosition]

        Picasso.get().load(book.volumeInfo.imageLinks.thumbnail).into(book_thumbnail_imageview)
        book_title_textview.text = book.volumeInfo.title
        book_authors_textview.text = book.volumeInfo.authors?.toString()
        book_publisher_textview.text = book.volumeInfo.publisher
        book_description.text = book.volumeInfo.description

        show_previous_book_button.isEnabled = bookPosition > 0
    }

}