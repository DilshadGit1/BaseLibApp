package com.example.municipal.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.baselibv1.BuildConfig
import com.example.baselibv1.R

class LoadImageUtil {
    companion object{
        fun loadImage(imageView: ImageView,url:String){
            if(url==null || url.isEmpty()){
                imageView.setImageResource(R.drawable.ic_baseline_person_outline_24)
                return
            }
            Glide.with(imageView.context).load(url).apply( RequestOptions()
                .placeholder(R.drawable.ic_baseline_person_outline_24)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                // here you add some value , if the next time you add the same value then it will load from cache otherwise if you put new value you will download , then save in cache
            ).into(imageView)
        }

    }
}