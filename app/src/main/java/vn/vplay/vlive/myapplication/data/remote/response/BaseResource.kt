package vn.vplay.vlive.myapplication.data.repository

import retrofit2.Response
import vn.vplay.vlive.myapplication.data.remote.response.Resource

interface BaseResource {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                Resource.success(response.body())
            } else {
                Resource.error(statusCode = response.code(), msg = listOf(response.message()))
            }
        } catch (e: Exception) {
            Resource.error(listOf("Exception: ${e.message}"))
        }
    }
}