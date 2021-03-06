package hu.miskolc.uni.iit.googlebookapp.data.repository

import hu.miskolc.uni.iit.googlebookapp.api.GoogleBookService

class BookRepositoryImpl(
    private val googleBookService : GoogleBookService
) : BookRepository {

    override fun getBooks(
        query: String,
        maxResults: Int,
        startIndex: Int
    ) = googleBookService.search(query, maxResults, startIndex)

}