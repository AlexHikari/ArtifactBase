package com.alex.phom.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class HeroCardPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return super.getPageTitle(position)
    }
}