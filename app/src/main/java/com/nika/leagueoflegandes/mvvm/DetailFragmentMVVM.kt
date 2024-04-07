package com.nika.leagueoflegandes.mvvm

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
    private val participantLiveData: LiveData<List<Participant>> = _particpiantLiveData

    fun getUserFromdb(summonerName: String)= repository.getUserFromDb(summonerName)


    fun getSummonerDetail(id: String) = viewModelScope.launch {
        _sumDetailsLiveData.value = DetailFragmentViewState.Loading
        val summonerResource = repository.getSumDetails(id)
        val viewState = if (summonerResource is Resource.Success && summonerResource.data != null) {
            DetailFragmentViewState.SummonerLoaded(summonerResource.data)
        } else {
            DetailFragmentViewState.Error(summonerResource.message.toString())
        }
        _sumDetailsLiveData.value = viewState
    }

    fun getSumMatchList(puuid: String,summonerName: String) = viewModelScope.launch {
        val resource = repository.getMatches(puuid)
        if (resource is Resource.Success && resource.data != null) {
            for (matchId in resource.data) {
                getMatchDetails(matchId,summonerName)
            }
        }
    }

    private fun getMatchDetails(matchId: String,summonerName: String) = viewModelScope.launch {
        val resource = repository.getMatchDetails(matchId)
        if (resource is Resource.Success && resource.data != null) {
            _matchDetailsLiveData.postValue(resource.data!!)
            getMatchDetailsByPlayerName(summonerName,resource.data.info.participants)
        } else {
            _matchDetailsLiveData.value = null
        }
    }

   private fun getMatchDetailsByPlayerName(name: String,particpantList:List<Participant>) {
       var summonerMatches = mutableListOf<Participant>()
        for (participant in particpantList){
            if (participant.summonerName==name){
                summonerMatches.add(participant)

            }
        }
       _particpiantLiveData.value=summonerMatches
       println(summonerMatches)
    }


}