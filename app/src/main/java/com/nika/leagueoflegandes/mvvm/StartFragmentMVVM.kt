package com.nika.leagueoflegandes.mvvm

import android.util.Log
import androidx.lifecycle.*
import com.nika.leagueoflegandes.other.Resource
import com.nika.leagueoflegandes.remote.SummonerResponse
import com.nika.leagueoflegandes.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class StartFragmentMVVM @Inject constructor(private val repository: Repository):ViewModel() {

    private val _summonerLiveData= MutableLiveData<Resource<SummonerResponse>>()
    var summonerLiveData : LiveData<Resource<SummonerResponse>> = _summonerLiveData


    fun executeCall(summonerName:String)=viewModelScope.launch {
        val summonerResource =repository.getSummInfo(summonerName)
        _summonerLiveData.value=summonerResource
    }
}