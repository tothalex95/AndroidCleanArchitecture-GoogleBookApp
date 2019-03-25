package hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import java.lang.IllegalArgumentException

class SearchResultViewModelFactory(
    private val getBooks: GetBooks
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchResultViewModel::class.java)) {
            return SearchResultViewModel(getBooks) as T
        }
        throw IllegalArgumentException()
    }

}