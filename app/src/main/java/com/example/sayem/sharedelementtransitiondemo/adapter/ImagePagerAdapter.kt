package com.example.sayem.sharedelementtransitiondemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.sayem.sharedelementtransitiondemo.data.ImageData
import com.example.sayem.sharedelementtransitiondemo.fragment.ImageFragment

/**
 * Created by sayem on 2/22/2018.
 */
class ImagePagerAdapter(fragment : Fragment) : FragmentStatePagerAdapter(fragment.childFragmentManager){
    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(ImageData.IMAGE_DRAWABLES[position])
    }

    override fun getCount(): Int {
        return ImageData.IMAGE_DRAWABLES.size
    }
}