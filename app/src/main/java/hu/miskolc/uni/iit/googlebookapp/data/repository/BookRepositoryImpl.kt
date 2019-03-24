package hu.miskolc.uni.iit.googlebookapp.data.repository

import hu.miskolc.uni.iit.googlebookapp.api.GoogleBookService
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import io.reactivex.Observable

class BookRepositoryImpl : BookRepository {

    private val googleBookService = GoogleBookService.create()

    override fun getBooks(query: String) = googleBookService.search(query)

}