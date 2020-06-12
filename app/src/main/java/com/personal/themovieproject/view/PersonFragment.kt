package com.personal.themovieproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.personal.themovieproject.R
import com.personal.themovieproject.databinding.PersonfragmentBinding
import com.personal.themovieproject.viewModel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.personfragment.*

class PersonFragment:Fragment() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var personfragmentBinding: PersonfragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel= ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        personfragmentBinding=DataBindingUtil.inflate(inflater,R.layout.personfragment,container,false)
        personfragmentBinding.viewModel=mainViewModel

        personfragmentBinding.lifecycleOwner=this.activity
        return personfragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        personfragmentBinding.executePendingBindings()
        val imageView: ImageView=requireActivity().findViewById(R.id.imageView)
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+mainViewModel.director.value!!.img_path).into(imageView)

    }
}