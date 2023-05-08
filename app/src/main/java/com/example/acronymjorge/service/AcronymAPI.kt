package com.example.acronymjorge.service

import com.example.acronymjorge.model.AcronymResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymAPI {
    @GET(PATH)
    suspend fun getAcronymMeaning(
        @Query("sf") acronym: String? = null
    ): Response<List<AcronymResponseItem>>

    companion object {
        const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"
        private const val PATH = "dictionary.py"
    }
}