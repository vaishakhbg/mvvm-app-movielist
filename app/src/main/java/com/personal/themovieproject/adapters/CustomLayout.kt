package com.personal.themovieproject.adapters

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


public class CustomLayout(context: Context,attributeSet: AttributeSet,defStyleAttr:Int) : LinearLayout(context,attributeSet,defStyleAttr), Target{
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        Log.d("gailure","picasso")
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        background = BitmapDrawable(resources, bitmap)
    }

}