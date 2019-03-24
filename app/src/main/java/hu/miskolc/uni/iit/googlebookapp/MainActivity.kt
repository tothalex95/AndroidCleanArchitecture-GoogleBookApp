package hu.miskolc.uni.iit.googlebookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hu.miskolc.uni.iit.googlebookapp.api.GoogleBookService
import hu.miskolc.uni.iit.googlebookapp.data.repository.BookRepositoryImpl
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getBooks = GetBooks(BookRepositoryImpl())
        getBooks({
            it.items.forEach { book -> Log.i("bookData", book.toString()) }
        }, {
            Log.i("bookData", it.message)
        }, {

        }, "Artyom")
    }
}
