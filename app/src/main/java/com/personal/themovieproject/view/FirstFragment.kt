package com.personal.themovieproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.personal.themovieproject.R
import com.personal.themovieproject.adapters.NameListAdapter
import com.personal.themovieproject.databinding.SelectdirectorBinding
import com.personal.themovieproject.viewModel.MainViewModel
import com.personal.themovieproject.viewModel.VMFactory

class FirstFragment: Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var selectdirectorBinding: SelectdirectorBinding

    /*val listener = object  : NameListAdapter.CustomViewListener {
        override fun onCustomItemClicked(x: String) {
            Log.d("here","atleast");
            mainViewModel.loadProfile(x)
        }

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory = VMFactory(requireActivity().application)

        mainViewModel= ViewModelProvider(requireActivity(),viewModelFactory).get(MainViewModel::class.java)

    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        selectdirectorBinding=DataBindingUtil.inflate(inflater,
            R.layout.selectdirector,container,false)
        selectdirectorBinding.viewModel=mainViewModel
        selectdirectorBinding.lifecycleOwner=this.activity
        return selectdirectorBinding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val theList = mainViewModel.directorList

        Log.d("vaishak",theList.size.toString());



        val listener = object  : NameListAdapter.CustomViewListener {
            override fun onCustomItemClicked(x: String) {
                Log.d("here","atleast");
                mainViewModel.loadProfile(x)
            }

        }

        val nameListAdapter = NameListAdapter(theList,listener)


        val layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.name_recycler).apply {
            this.layoutManager=layoutManager
            this.adapter=nameListAdapter }

        nameListAdapter.notifyDataSetChanged()
        selectdirectorBinding.executePendingBindings()
        subscribe(mainViewModel)
    }

    private fun subscribe(mainViewModel: MainViewModel) {
        mainViewModel._isNewData.observe(requireActivity(), Observer { new_profile ->
            if(new_profile) {
                mainViewModel._isNewData.postValue(false)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmenthere, PersonFragment())
                    .addToBackStack(null)
                    .commit()
            }

        })
    }
}