package com.nika.leagueoflegandes.repository

import com.nika.leagueoflegandes.other.Resource
import com.nika.leagueoflegandes.remote.SummonerResponse
import com.nika.leagueoflegandes.retrofit.LolApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor (
    val summonerApi : LolApi
) {


    suspend fun getSummInfo(summonerName : String):Resource<SummonerResponse>{
        return safeCall {
            summonerApi.getSum(summonerName)
        }
    }


    suspend fun <T> safeCall(apiCall:suspend ()-> Response<T>) :Resource<T>{

        try {
            val response=apiCall.invoke();
            if (response.isSuccessful){
                response.body()?.let {
                    return Resource.Success(it)
                } ?: return Resource.Error("An unknown error occurred")
            }else{
                return Resource.Error("Problems with network call")
            }
        }catch (e:Exception){
            return Resource.Error(e.message ?: "Couldn't reach the server")
        }
    }

}