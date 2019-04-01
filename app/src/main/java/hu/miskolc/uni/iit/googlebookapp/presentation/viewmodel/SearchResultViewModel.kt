package hu.miskolc.uni.iit.googlebookapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks

class SearchResultViewModel(
    private val getBooks: GetBooks
) : BaseViewModel(getBooks) {

    private val searchResultLiveData = MutableLiveData<SearchResult>()

    fun getSearchResult(query: GetBooks.Params): LiveData<SearchResult> {
        getBooks(
            onSuccess = { searchResultLiveData.value = it},
            onError = {},
            params = query
        )
        return searchResultLiveData
    }

}