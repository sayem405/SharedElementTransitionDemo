package com.example.sayem.sharedelementtransitiondemo.listener

import android.support.v4.app.Fragment
import android.transition.TransitionSet
import android.view.View
import android.widget.ImageView
import com.example.sayem.sharedelementtransitiondemo.MainActivity
import com.example.sayem.sharedelementtransitiondemo.R
import com.example.sayem.sharedelementtransitiondemo.fragment.ImagePagerFragment
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by sayem on 2/22/2018.
 */
class ViewHolderListenerImpl(private val fragment: Fragment) : ViewHolderListener {
    private var enterTransitionStarted = AtomicBoolean()

    override fun onLoadCompleted(view: ImageView, adapterPosition: Int) {
        if (MainActivity.currentPosition != adapterPosition) return
        if (enterTransitionStarted.getAndSet(true)) return
        fragment.startPostponedEnterTransition()
    }

    override fun onItemClicked(view: View, adapterPosition: Int) {
        MainActivity.currentPosition = adapterPosition
        (fragment.exitTransition as TransitionSet).excludeTarget(view, true)
        val transitioningView: View = view.findViewById(R.id.card_image)
        fragment.fragmentManager?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.addSharedElement(transitioningView, transitioningView.transitionName)
                ?.replace(R.id.fragment_container, ImagePagerFragment(), ImagePagerFragment::class.java
                        .simpleName)
                ?.addToBackStack(null)
                ?.commit()
    }
}