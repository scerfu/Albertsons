package com.example.acronymjorge.remote

import com.example.acronymjorge.service.AcronymAPI
import com.example.acronymjorge.utils.UIEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Repository {

    suspend fun getAcronym(
        acronym: String,
        function: (UIEvents) -> Unit
    )
}

class AcronymRepositoryImpl @Inject constructor(
    private val acronymAPI: AcronymAPI
) : Repository {

    override suspend fun getAcronym(acronym: String, function: (UIEvents) -> Unit) {
        function.invoke(UIEvents.LOADING)
        try {
            val response = acronymAPI.getAcronymMeaning(acronym)
            if (response.isSuccessful) {
                response.body()?.let {
                    function.invoke(UIEvents.SUCCESS(it))
                } ?: throw Exception("Response is null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            function.invoke(UIEvents.ERROR(e))
        }
    }

}