package com.example.myapplication.repos

import com.example.myapplication.models.GitReposModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface ApiInterface {
    @GET("/p68846/github")
    fun getRepo() : Call<List<GitReposModel>>
}