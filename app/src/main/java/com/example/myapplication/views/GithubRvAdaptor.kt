package com.example.myapplication.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.models.GitReposModel

class GithubRvAdaptor(private val githubData: List<GitReposModel>) : RecyclerView.Adapter<GithubVH>() {
    private val filterData: ArrayList<GitReposModel> = ArrayList(githubData)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubVH {
        return GithubVH(LayoutInflater.from(parent.context).inflate(R.layout.layout_githubvh,parent,false))
    }

    override fun getItemCount(): Int {
        return filterData.size
    }

    override fun onBindViewHolder(holder: GithubVH, position: Int) {
        holder.setData(filterData[position])
    }

    fun showData(data: List<GitReposModel>){
        filterData.clear()
        filterData.addAll(data)
        notifyDataSetChanged()
    }

    fun clearFilter(){
        filterData.clear()
        filterData.addAll(githubData)
        notifyDataSetChanged()
    }
}

class GithubVH(itemView: View) : ViewHolder(itemView) {
    private val accountTxt : TextView = itemView.findViewById(R.id.accountNameTv)
    private val githubRepoTxt : TextView = itemView.findViewById(R.id.gitRepoTv)
    private val profileImg : ImageView = itemView.findViewById(R.id.profileImg)

    fun setData(gitReposModel: GitReposModel){
        Glide.with(itemView).load(gitReposModel.profileImg).into(profileImg)
        accountTxt.text = gitReposModel.account
        githubRepoTxt.text = gitReposModel.repoName
    }
}