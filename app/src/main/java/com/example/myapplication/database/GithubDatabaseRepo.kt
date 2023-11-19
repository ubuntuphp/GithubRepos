package com.example.myapplication.database

import android.content.Context
import com.example.myapplication.models.GitReposModel

object GithubDatabaseRepo {
    fun getGithubRepos(context: Context) : List<GitReposModel>{
        return GithubRoomDb.getInstance(context).getGithubRepos().getAllRepos()
    }

    fun addGithubRepos(context: Context,data : List<GitReposModel>){
        GithubRoomDb.getInstance(context).getGithubRepos().addGithubRepos(data)
    }
}