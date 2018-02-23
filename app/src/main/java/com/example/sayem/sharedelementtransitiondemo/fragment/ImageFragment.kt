package com.example.sayem.sharedelementtransitiondemo.fragment

import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.sayem.sharedelementtransitiondemo.R
import com.example.sayem.sharedelementtransitiondemo.databinding.FragmentImageBinding

/**
 * Created by sayem on 2/22/2018.
 */
class ImageFragment : Fragment() {

    private lateinit var itemView : FragmentImageBinding

    companion object {

        val KEY_IMAGE_RES = "com.google.samples.gridtopager.key.imageRes"

        fun newInstance(@DrawableRes drawableRes: Int): ImageFragment {
            val fragment = ImageFragment()
            val argument = Bundle()
            argument.putInt(KEY_IMAGE_RES, drawableRes)
            fragment.arguments = argument
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        itemView = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false)
        val arguments = arguments
        @DrawableRes val imageRes = arguments!!.getInt(KEY_IMAGE_RES)
        itemView.image.transitionName = imageRes.toString()
        Glide.with(this)
                .load(imageRes)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        parentFragment!!.startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        parentFragment!!.startPostponedEnterTransition()
                        return false
                    }

                }).into(itemView.image)
        return itemView.root
    }


}