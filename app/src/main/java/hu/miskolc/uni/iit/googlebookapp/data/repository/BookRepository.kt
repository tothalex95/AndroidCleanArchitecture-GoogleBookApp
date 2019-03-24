package hu.miskolc.uni.iit.googlebookapp.data.repository

import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import io.reactivex.Observable

interface BookRepository {

    fun getBooks(query: String): Observable<SearchResult>

}