package hu.miskolc.uni.iit.googlebookapp.data.repository

import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import io.reactivex.Observable

interface BookRepository {

    fun getBooks(query: String, startIndex:Int): Observable<SearchResult>

}