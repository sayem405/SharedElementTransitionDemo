package com.example.sayem.sharedelementtransitiondemo.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sayem.sharedelementtransitiondemo.MainActivity
import com.example.sayem.sharedelementtransitiondemo.R
import com.example.sayem.sharedelementtransitiondemo.adapter.GridAdapter
import com.example.sayem.sharedelementtransitiondemo.databinding.FragmentGridBinding

/**
 * Created by sayem on 2/22/2018.
 */
class GridFragment : Fragment() {

    private lateinit var viewItem : FragmentGridBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewItem = DataBindingUtil.inflate(inflater, R.layout.fragment_grid,container, false)
        viewItem.recyclerView.adapter = GridAdapter(this)

        prepareTransitions()
        postponeEnterTransition()
        return viewItem.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollToPosition()
    }

    private fun scrollToPosition() {
        viewItem.recyclerView.addOnLayoutChangeListener(object : View.OnLayoutChangeListener{
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                viewItem.recyclerView.removeOnLayoutChangeListener(this)
                val layoutManager = viewItem.recyclerView.layoutManager
                val viewAtPosition = layoutManager.findViewByPosition(MainActivity.currentPosition)
                if (viewAtPosition == null || layoutManager.isViewPartiallyVisible(viewAtPosition,false, true)) {
                    viewItem.recyclerView.post { layoutManager.scrollToPosition(MainActivity.currentPosition) }
                }
            }
        })

    }

    private fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context).inflateTransition(R.transition.grid_exit_transition)
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val viewHolder = viewItem.recyclerView.findViewHolderForAdapterPosition(MainActivity.currentPosition)
                if (viewHolder?.itemView == null) return
                sharedElements?.put(names!!.get(0), viewHolder.itemView.findViewById(R.id.card_image))
            }
        })

    }
}