package com.personal.themovieproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.personal.themovieproject.R
import com.personal.themovieproject.databinding.SelectdirectorBinding
import com.personal.themovieproject.viewModel.MainViewModel

class FirstFragment: Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var selectdirectorBinding: SelectdirectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel=ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

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
        selectdirectorBinding.executePendingBindings()
    }
}