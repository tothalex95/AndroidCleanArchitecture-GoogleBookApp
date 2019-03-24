package hu.miskolc.uni.iit.googlebookapp.domain.usecase

import hu.miskolc.uni.iit.googlebookapp.data.repository.BookRepository
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult

class GetBooks(
    private val bookRepository: BookRepository
) : ObservableUseCase<SearchResult, String>() {

    override fun execute(params: String) = bookRepository.getBooks(params)

}