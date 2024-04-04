package com.nika.leagueoflegandes.mvvm.view_states

import com.nika.leagueoflegandes.remote.models.summoner.SummonerDetailResponse


sealed interface DetailFragmentViewState {
    object Loading: DetailFragmentViewState
    data class Error(val message: String): DetailFragmentViewState

    data class SummonerLoaded(val summoner: SummonerDetailResponse) : DetailFragmentViewState
}