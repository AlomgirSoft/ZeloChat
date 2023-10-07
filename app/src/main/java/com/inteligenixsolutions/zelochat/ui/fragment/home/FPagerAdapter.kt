package com.inteligenixsolutions.zelochat.ui.fragment.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.inteligenixsolutions.zelochat.ui.fragment.profile.ProfileFragment
import com.inteligenixsolutions.zelochat.ui.fragment.user.UserFragment


class FPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var names = arrayOf("User",  "Profile")


    override fun getCount(): Int {
        return names.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UserFragment()
            1 -> ProfileFragment()
            else -> ProfileFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return names[position]
    }
}