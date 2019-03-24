package hu.miskolc.uni.iit.googlebookapp.domain.model

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val description: String,
    val pageCount: Int,
    val imageLinks: ImageLinks
)