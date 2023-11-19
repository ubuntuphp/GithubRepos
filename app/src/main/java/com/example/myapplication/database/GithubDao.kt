package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.GitReposModel

@Dao
interface GithubDao  {
    @Insert
    fun addGithubRepos(dao: List<GitReposModel>)

    @Query("SELECT * FROM githubRepos")
    fun getAllRepos(): List<GitReposModel>
}