package com.nika.leagueoflegandes.repository

import androidx.lifecycle.LiveData
import com.nika.leagueoflegandes.db.UserDao
import com.nika.leagueoflegandes.other.Resource
import com.nika.leagueoflegandes.remote.models.match.MatchListResponse
import com.nika.leagueoflegandes.remote.models.match.details.MatchDetailsResponse
import com.nika.leagueoflegandes.remote.models.summoner.SummonerDetailResponse
import com.nika.leagueoflegandes.remote.models.summoner.SummonerResponse
import com.nika.leagueoflegandes.retrofit.LolApi
import com.nika.leagueoflegandes.retrofit.MatchApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor (
   private val api : LolApi,
   private val matchApi: MatchApi,
   private val userDao: UserDao
) {


    suspend fun getSummInfo(summonerName : String):Resource<SummonerResponse>{
        return safeCall {
            api.getSum(summonerName)
        }
    }

    suspend fun getMatchDetails(matchId:String) : Resource<MatchDetailsResponse>{
        return safeCall {
            matchApi.getMatchDetails(matchId)
        }
    }
    suspend fun getSumDetails(encryptedSummonerId : String):Resource<SummonerDetailResponse>{
        return safeCall {
            api.getSumDetail(encryptedSummonerId)
        }
    }

    suspend fun getMatches(puuid:String):Resource<MatchListResponse>{
        return safeCall {
            matchApi.getMatches(puuid)
        }
    }



    fun getUserFromDb(summonerName: String):LiveData<SummonerResponse?>{
        return userDao.getSummoner(summonerName)
    }

    suspend fun insertUser(user: SummonerResponse){
        userDao.upsertUser(user)
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