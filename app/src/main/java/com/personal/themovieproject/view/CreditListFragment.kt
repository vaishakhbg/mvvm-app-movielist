package com.personal.themovieproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.personal.themovieproject.R
import com.personal.themovieproject.adapters.CustomRecyclerViewAdapter
import com.personal.themovieproject.databinding.CreditlistBinding
import com.personal.themovieproject.model.Project
import com.personal.themovieproject.viewModel.MainViewModel

class CreditListFragment:Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var creditlistBinding: CreditlistBinding

    private  var projectList:ArrayList<Project> = ArrayList<Project>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        for (projects in mainViewModel.all_credits.value!!.crew){
            if (projects.department.equals("Directing") and (projects.popularity>=4.0)){
                this.projectList.add(projects)
            }
        }
        this.projectList.sortByDescending { it.popularity }
        Log.d("project",this.projectList.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        creditlistBinding=DataBindingUtil.inflate(inflater, R.layout.creditlist,container,false)
        populateData()
        creditlistBinding.executePendingBindings()


        return creditlistBinding.root
    }

    private fun populateData() {

        val customRecyclerViewAdapter=CustomRecyclerViewAdapter(this.projectList,requireContext())
        creditlistBinding.myadapter=customRecyclerViewAdapter

    }


}