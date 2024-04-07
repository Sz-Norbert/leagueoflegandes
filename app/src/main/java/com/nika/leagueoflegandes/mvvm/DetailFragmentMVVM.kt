package com.nika.leagueoflegandes.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nika.leagueoflegandes.mvvm.view_states.DetailFragmentViewState
import com.nika.leagueoflegandes.other.Resource
import com.nika.leagueoflegandes.remote.models.match.details.MatchDetailsResponse
import com.nika.leagueoflegandes.remote.models.match.details.Participant
import com.nika.leagueoflegandes.remote.models.summoner.SummonerResponse
import com.nika.leagueoflegandes.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentMVVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _sumDetailsLiveData = MutableLiveData<DetailFragmentViewState>()
    var sumDetailsLiveData: LiveData<DetailFragmentViewState> = _sumDetailsLiveData

    private val _matchDetailsLiveData = MutableLiveData<MatchDetailsResponse?>()
    var matchdetailsLiveData: LiveData<MatchDetailsResponse?> = _matchDetailsLiveData

    private val _particpiantLiveData = MutableLiveData<List<Participant>>()
     val participantLiveData: LiveData<List<Participant>> = _particpiantLiveData

    fun getUserFromdb(summonerName: String)= repository.getUserFromDb(summonerName)


    fun getSummonerDetail(id: String) = viewModelScope.launch {
        val summonerResource = repository.getSumDetails(id)
        val viewState = if (summonerResource is Resource.Success && summonerResource.data != null) {
            DetailFragmentViewState.SummonerLoaded(summonerResource.data)
        } else {
            DetailFragmentViewState.Error(summonerResource.message.toString())
        }
        _sumDetailsLiveData.value = viewState
    }

    fun getSumMatchList(puuid: String, summonerName: String) = viewModelScope.launch {
        val resource = repository.getMatches(puuid)

        if (resource is Resource.Success && resource.data != null) {

           val participants =  resource.data.mapNotNull {
                getMatchDetails(it,summonerName)
            }
            Log.d("***", "getSumMatchList: $participants")

            _particpiantLiveData.value = participants

           /* val matchIds = resource.data

            for (matchId in matchIds) {
                    getMatchDetails(matchId, summonerName)
            }*/
        }
    }

    private suspend fun getMatchDetails(matchId: String, summonerName: String): Participant? {
        val resource = repository.getMatchDetails(matchId)
        return when {
            resource is Resource.Success && resource.data != null -> {
              resource.data.info.participants.find { it.summonerName == summonerName }
            }
            else -> null
        }
    }




}