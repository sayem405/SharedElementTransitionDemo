package com.example.sayem.sharedelementtransitiondemo.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.ViewPager
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sayem.sharedelementtransitiondemo.MainActivity
import com.example.sayem.sharedelementtransitiondemo.R
import com.example.sayem.sharedelementtransitiondemo.adapter.ImagePagerAdapter
import com.example.sayem.sharedelementtransitiondemo.databinding.FragmentPagerBinding

/**
 * Created by sayem on 2/22/2018.
 */
class ImagePagerFragment : Fragment() {

    private lateinit var itemView : FragmentPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        itemView = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false)
        itemView.viewPager.adapter = ImagePagerAdapter(this)
        itemView.viewPager.currentItem = MainActivity.currentPosition
        itemView.viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                MainActivity.currentPosition = position
            }
        })
        prepareSharedElementTransition()

        if (savedInstanceState == null) {
            postponeEnterTransition()
        }
        return itemView.root
    }

    private fun prepareSharedElementTransition() {
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition
        setEnterSharedElementCallback(object : SharedElementCallback(){
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val fragment = itemView.viewPager.adapter?.instantiateItem(itemView.viewPager, MainActivity.currentPosition) as Fragment
                val view = fragment.view ?: return
                sharedElements!!.put(names!!.get(0), view.findViewById(R.id.image))
            }
        })
    }
}