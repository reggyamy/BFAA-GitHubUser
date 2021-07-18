package com.dicodingsubmission.githupuser.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicodingsubmission.githupuser.R
import com.dicodingsubmission.githupuser.activity.detailUser.FollowersFragment
import com.dicodingsubmission.githupuser.activity.detailUser.FollowingFragment

class ProfilePagerAdapter(private val context: Context, fragmentManager: FragmentManager, dataBundle: Bundle): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.tab_follower,
                R.string.tab_following
        )
    }

    private  var fragmentBundle :Bundle = dataBundle


    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position){
            0-> fragment = FollowersFragment()
            1-> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? = context.getString(TAB_TITLES[position])
}