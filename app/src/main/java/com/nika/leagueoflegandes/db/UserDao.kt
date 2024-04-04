package com.nika.leagueoflegandes.db

import androidx.room.Dao
import androidx.room.Upsert
import com.nika.leagueoflegandes.remote.models.SummonerResponse

@Dao
interface UserDao {


    @Upsert
    suspend fun upsertUser(user:SummonerResponse)



}