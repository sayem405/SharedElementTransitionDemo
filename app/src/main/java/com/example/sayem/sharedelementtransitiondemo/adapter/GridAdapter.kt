package com.example.sayem.sharedelementtransitiondemo.adapter

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.sayem.sharedelementtransitiondemo.R
import com.example.sayem.sharedelementtransitiondemo.data.ImageData
import com.example.sayem.sharedelementtransitiondemo.databinding.ImageCardBinding
import com.example.sayem.sharedelementtransitiondemo.listener.ViewHolderListenerImpl
import com.example.sayem.sharedelementtransitiondemo.viewholder.ImageViewHolder

/**
 * Created by sayem on 2/22/2018.
 */
class GridAdapter(fragment : Fragment) : RecyclerView.Adapter<ImageViewHolder>() {

    private val requestManager : RequestManager = Glide.with(fragment)
    private val viewHolderListener = ViewHolderListenerImpl(fragment)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ImageViewHolder {
        val itemView = DataBindingUtil.inflate<ImageCardBinding>(LayoutInflater.from(parent?.context)
                , R.layout.image_card, parent, false)
        return ImageViewHolder(itemView, requestManager, viewHolderListener)
    }

    override fun getItemCount(): Int {
        return ImageData.IMAGE_DRAWABLES.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {
        holder?.onBind(ImageData.IMAGE_DRAWABLES[position])
    }
}
