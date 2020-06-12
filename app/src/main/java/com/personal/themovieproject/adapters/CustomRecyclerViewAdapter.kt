package com.personal.themovieproject.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.personal.themovieproject.BR
import com.personal.themovieproject.R
import com.personal.themovieproject.databinding.MoviecardBinding
import com.personal.themovieproject.model.AllCredits
import com.personal.themovieproject.model.Project
import com.squareup.picasso.Picasso

class CustomRecyclerViewAdapter(private val allcreditslist:ArrayList<Project>,private val context: Context): RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    val projectList=allcreditslist

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val moviecardBinding:MoviecardBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.moviecard,parent,false)
        return ViewHolder(moviecardBinding,projectList)
    }

    override fun getItemCount()=allcreditslist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project=allcreditslist.get(position)
        holder.bind(project)
        //Picasso.get().load(allcreditslist.get(position).poster_path).resize(120,60).into(holder.img_place)
    }


    class ViewHolder(moviecardBinding: MoviecardBinding,project_List: ArrayList<Project>):RecyclerView.ViewHolder(moviecardBinding.root),View.OnClickListener{
        private var view: View = moviecardBinding.root
        private var moviecardBinding1:MoviecardBinding = moviecardBinding
        private val project_List1=project_List

        init {
            view.setOnClickListener(this)
            //this.img_place=view.findViewById<ImageView>(R.id.postersmall)
        }

        fun bind(obj:Project){
            moviecardBinding1.setVariable(BR.model,obj)
            moviecardBinding1.executePendingBindings()
            var img_place:ImageView=view.findViewById<ImageView>(R.id.postersmall)
            Picasso.get().load("https://image.tmdb.org/t/p/original"+obj.poster_path).resize(180,270).into(img_place)
            if(!(obj.backdrop.isNullOrBlank())){
                var img_place:ImageView=view.findViewById<ImageView>(R.id.backdrop)
                Picasso.get().load("https://image.tmdb.org/t/p/w780"+obj.backdrop).into(img_place);



            }
        }

        //4
        override fun onClick(v: View) {

            Log.d("RV",(project_List1.get(layoutPosition)).title)
            val snackbar = Snackbar.make(view,(project_List1.get(layoutPosition)).title,Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

        companion object {
            //5
            private val CREDITS_KEY = "CREDITS"
        }
    }

}