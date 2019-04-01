package hu.miskolc.uni.iit.googlebookapp.api

import hu.miskolc.uni.iit.googlebookapp.domain.model.SearchResult
import hu.miskolc.uni.iit.googlebookapp.domain.usecase.GetBooks
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBookService {

    @GET("/books/v1/volumes?maxResults=40")
    fun search(@Query("q") query: String, @Query("startIndex") startIndex:Int=0): Observable<SearchResult>
    companion object {
        fun create(): GoogleBookService {
            return Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GoogleBookService::class.java)
        }
    }

}