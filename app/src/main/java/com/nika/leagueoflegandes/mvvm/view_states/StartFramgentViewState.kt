package com.nika.leagueoflegandes.mvvm.view_states

import com.nika.leagueoflegandes.remote.models.summoner.SummonerResponse

sealed interface StartFramgentViewState {
    object Loading : StartFramgentViewState
    object NoSummerFound : StartFramgentViewState

    data class SummonerFound(val summoner: SummonerResponse) : StartFramgentViewState

}
