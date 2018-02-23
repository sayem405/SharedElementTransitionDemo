package com.example.sayem.sharedelementtransitiondemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sayem.sharedelementtransitiondemo.fragment.GridFragment

class MainActivity : AppCompatActivity() {
    companion object {
        var currentPosition: Int = 0
        const val KEY_CURRENT_POSITION = "com.google.samples.gridtopager.key.currentPosition"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
            return
        }
        val fragmentManager = supportFragmentManager
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, GridFragment(), GridFragment::class.java.getSimpleName())
                .commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putInt(KEY_CURRENT_POSITION, currentPosition)
    }
}
