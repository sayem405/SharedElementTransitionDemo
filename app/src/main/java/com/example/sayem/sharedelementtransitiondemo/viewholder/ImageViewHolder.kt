package com.example.sayem.sharedelementtransitiondemo.viewholder

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.sayem.sharedelementtransitiondemo.data.ImageData
import com.example.sayem.sharedelementtransitiondemo.databinding.ImageCardBinding
import com.example.sayem.sharedelementtransitiondemo.listener.ViewHolderListener
import kotlinx.android.synthetic.main.image_card.view.*

/**
 * Created by sayem on 2/22/2018.
 */
class ImageViewHolder(itemView: ImageCardBinding, private val requestManager: RequestManager,
                      private val viewHolderListener: ViewHolderListener) :
        RecyclerView.ViewHolder(itemView.root), View.OnClickListener {

    init {
        itemView.cardView.setOnClickListener(this)
    }

    fun onBind(@DrawableRes imageRes : Int  ) {
        val adapterPosition = adapterPosition
        setImage(adapterPosition, imageRes)
        itemView.card_image.transitionName = ImageData.IMAGE_DRAWABLES[adapterPosition].toString()
    }

    private fun setImage(adapterPosition: Int,@DrawableRes imageRes: Int) {
        requestManager.load(imageRes).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                viewHolderListener.onLoadCompleted(itemView.card_image, adapterPosition)
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                viewHolderListener.onLoadCompleted(itemView.card_image, adapterPosition)
                return false
            }
        }).into(itemView.card_image)
    }

    override fun onClick(v: View) {
        viewHolderListener.onItemClicked(v, adapterPosition)
    }
}