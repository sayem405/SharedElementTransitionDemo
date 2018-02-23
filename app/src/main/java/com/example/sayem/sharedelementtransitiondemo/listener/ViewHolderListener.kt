package com.example.sayem.sharedelementtransitiondemo.listener

import android.view.View
import android.widget.ImageView

/**
 * Created by sayem on 2/22/2018.
 */
open interface ViewHolderListener {
    fun onLoadCompleted(view: ImageView, adapterPosition: Int)
    fun onItemClicked(view: View, adapterPosition: Int)

}