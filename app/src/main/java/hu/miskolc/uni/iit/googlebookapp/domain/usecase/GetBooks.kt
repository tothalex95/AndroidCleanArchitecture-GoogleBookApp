package hu.miskolc.uni.iit.googlebookapp.domain.usecase

import hu.miskolc.uni.iit.googlebookapp.data.repository.BookRepository
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult

class GetBooks(
    private val bookRepository: BookRepository
) : ObservableUseCase<SearchResult, GetBooks.Params>() {

    override fun execute(params: Params) = bookRepository.getBooks(params.query, params.maxResults, params.startIndex)

    data class Params(
        val query: String,
        val maxResults: Int = 40,
        val startIndex: Int = 0
    )

}