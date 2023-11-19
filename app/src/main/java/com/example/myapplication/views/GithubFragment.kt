package com.example.myapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.GitReposModel
import com.example.myapplication.viewmodel.GithubViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GithubFragment : Fragment() {

    private lateinit var githubViewModel : GithubViewModel
    private lateinit var githubRv : RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var githubRvAdaptor: GithubRvAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        githubViewModel = ViewModelProvider(this)[GithubViewModel::class.java]
        githubViewModel.fetchGithubData()
        githubViewModel.githubLiveData.observe(viewLifecycleOwner){
            setupGithubRv(it)
        }
        setupSearch()
    }

    private fun initViews(view: View){
        githubRv = view.findViewById(R.id.githubRv)
        searchBar = view.findViewById(R.id.searchTxt)
    }

    private fun setupGithubRv(data : List<GitReposModel>){
        githubRvAdaptor = GithubRvAdaptor(data)
        githubRv.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = githubRvAdaptor
        }
    }

    private fun setupSearch(){
        searchBar.addTextChangedListener {
            if(it?.isNotEmpty() == true) {
                if(::githubRvAdaptor.isInitialized)githubRvAdaptor.showData(githubViewModel.getFilteredDta(it.toString()))
            }else{
                if(::githubRvAdaptor.isInitialized)githubRvAdaptor.clearFilter()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GithubFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}