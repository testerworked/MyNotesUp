package com.homework.module15

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PicturePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val pictureList: List<Picture>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return pictureList.size
    }

    override fun createFragment(position: Int): Fragment {
        return PictureFragment.newInstance(pictureList[position])
    }
}


