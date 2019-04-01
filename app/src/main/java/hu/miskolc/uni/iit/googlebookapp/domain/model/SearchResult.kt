package hu.miskolc.uni.iit.googlebookapp.domain.model

data class SearchResult(
    val totalItems: Int,
    val items: MutableList<Book>
)