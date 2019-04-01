package hu.miskolc.uni.iit.googlebookapp.domain.usecase

import hu.miskolc.uni.iit.googlebookapp.data.repository.BookRepository
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult

class GetBooks(
    private val bookRepository: BookRepository
) : ObservableUseCase<SearchResult, GetBooks.Params>() {

    data class Params(val query: String, val startIndex:Int)
    override fun execute(params: Params) = bookRepository.getBooks(params.query, params.startIndex)

}