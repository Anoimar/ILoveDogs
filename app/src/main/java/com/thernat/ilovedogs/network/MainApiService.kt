package com.thernat.ilovedogs.network

import com.thernat.ilovedogs.breed.Breed
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by m.rafalski@matsuu.com on 10/03/2019.
 */


interface MainApiService {

    @GET("breeds")
    fun getBreeds(@Header("x-api-key")apiKey: String): Observable<List<Breed>>
}