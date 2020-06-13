package com.personal.themovieproject.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.personal.themovieproject.R
import com.personal.themovieproject.model.Project

class NameListAdapter(private val namelist:ArrayList<String>, private val listener: CustomViewListener) : RecyclerView.Adapter<NameListAdapter.MyViewHolder>() {


   // private val _namelist = namelist


    interface  CustomViewListener{
        fun onCustomItemClicked(x : String)
    }

    class MyViewHolder(view : View, namelist: ArrayList<String>): RecyclerView.ViewHolder(view)/*,View.OnClickListener*/{

        private val _view = view
        private val namlist=namelist
        /*init {
            view.setOnClickListener(this)
        }*/

        companion object {
            //5
            private val BUTTON_NAME = "BTNNAME"
        }


        /*override fun onClick(v: View?) {
            Log.d("Rec", namlist.get(layoutPosition))
            listen
        }*/

        fun bind(obj:String,listener: CustomViewListener){
            var button : Button = _view.findViewById<Button>(R.id.buttonname)
            button.setOnClickListener{
                listener.onCustomItemClicked(obj)
            }
            button.setText(obj)
            /*Picasso.get().load("https://image.tmdb.org/t/p/original"+obj.poster_path).resize(180,270).into(img_place)
            if(!(obj.backdrop.isNullOrBlank())){
                var img_place:ImageView=view.findViewById<ImageView>(R.id.backdrop)
                Picasso.get().load("https://image.tmdb.org/t/p/w780"+obj.backdrop).into(img_place);*/
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NameListAdapter.MyViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.card_row,parent, false)
        return MyViewHolder(inflatedView,namelist)
    }

    override fun getItemCount() = namelist.size

    override fun onBindViewHolder(holder: NameListAdapter.MyViewHolder, position: Int) {
        val name=namelist.get(position)
        holder.bind(name,listener)
        /*holder.itemView.setOnClickListener{
            listener.onCustomItemClicked(namelist.get(position))
        }*///To change body of created functions use File | Settings | File Templates.

    }

}