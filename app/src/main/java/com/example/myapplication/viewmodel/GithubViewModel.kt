package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.database.GithubDatabaseRepo
import com.example.myapplication.models.GitReposModel
import com.example.myapplication.repos.RetrofitModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel(application: Application) : AndroidViewModel(application) {
    private val apiInterface = RetrofitModule.getApi()
    val githubLiveData: MutableLiveData<List<GitReposModel>>
        get() = _githubLiveData
    private val _githubLiveData = MutableLiveData<List<GitReposModel>>()

    fun fetchGithubData() {
        apiInterface.getRepo().enqueue(object : Callback<List<GitReposModel>> {
            override fun onResponse(
                call: Call<List<GitReposModel>>,
                response: Response<List<GitReposModel>>
            ) {
                if (response.isSuccessful) {
                    _githubLiveData.postValue(response.body())
                    response.body()?.let { saveDataLocally(it) }
                }
            }

            override fun onFailure(call: Call<List<GitReposModel>>, t: Throwable) {
                t.printStackTrace()
                CoroutineScope(Dispatchers.IO).launch {
                    val localData = getDataFromDb()
                    if (localData.isNotEmpty()) {
                        _githubLiveData.postValue(localData)
                    }
                }
            }

        })
    }

    private fun saveDataLocally(data: List<GitReposModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            GithubDatabaseRepo.addGithubRepos(getApplication<Application>(), data)
        }
    }

    private suspend fun getDataFromDb(): List<GitReposModel> {
        return withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            GithubDatabaseRepo.getGithubRepos(getApplication<Application>())
        }
    }

    fun getFilteredDta(filter: String): List<GitReposModel> {
        return _githubLiveData.value?.filter {
            it.account.contains(filter) || it.repoName.contains(filter) || it.url.contains(filter)
        } ?: listOf()
    }

}