package hu.miskolc.uni.iit.googlebookapp

import android.app.Application
import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult

class GoogleBookApp : Application() {

    var searchResult = SearchResult(0, listOf())

}