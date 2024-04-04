package com.nika.leagueoflegandes.mvvm


import androidx.lifecycle.*
import com.nika.leagueoflegandes.other.Resource
import com.nika.leagueoflegandes.remote.models.SummonerResponse
import com.nika.leagueoflegandes.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import retrofit2.Response

import javax.inject.Inject

@HiltViewModel
class StartFragmentMVVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _summonerLiveData = MutableLiveData<StartFramgentViewState>()
    var summonerLiveData: LiveData<StartFramgentViewState> = _summonerLiveData


    fun getPlayer(summonerName: String) = viewModelScope.launch {
        _summonerLiveData.value = StartFramgentViewState.Loading
        val summonerResource = repository.getSummInfo(summonerName)
        val viewState = if (summonerResource is Resource.Success && summonerResource.data != null) {
            repository.insertUser(summonerResource.data)
            StartFramgentViewState.SummonerFound(summonerResource.data)
        } else {
            StartFramgentViewState.NoSummerFound
        }
        _summonerLiveData.value = viewState

    }


    sealed interface StartFramgentViewState {
        object Loading : StartFramgentViewState
        object NoSummerFound : StartFramgentViewState

        data class SummonerFound(val summoner: SummonerResponse) : StartFramgentViewState

    }
}
