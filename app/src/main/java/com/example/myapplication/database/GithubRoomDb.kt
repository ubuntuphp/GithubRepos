package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.models.GitReposModel

@Database(entities = [GitReposModel::class], version = 1)
abstract class GithubRoomDb : RoomDatabase() {
    abstract fun getGithubRepos(): GithubDao

    companion object {
        private lateinit var instance: GithubRoomDb
        fun getInstance(context: Context): GithubRoomDb {
            if (::instance.isInitialized) return instance
            instance =
                Room.databaseBuilder(context, GithubRoomDb::class.java, "github_repo_db").build()
            return instance
        }
    }

}