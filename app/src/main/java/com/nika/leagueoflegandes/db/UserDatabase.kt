package com.nika.leagueoflegandes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nika.leagueoflegandes.remote.models.summoner.SummonerResponse

@Database(entities = [SummonerResponse::class] , version = 1)
abstract class UserDatabase : RoomDatabase() {


    abstract fun  userDao() : UserDao
}