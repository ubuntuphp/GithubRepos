package com.example.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "githubRepos")
data class GitReposModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("author")
    val account: String,
    @SerializedName("name")
    val repoName: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("profileImg")
    val profileImg: String
)