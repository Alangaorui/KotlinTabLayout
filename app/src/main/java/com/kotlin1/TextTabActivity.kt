package com.kotlin1

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class TextTabActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    private var mFragment1: ListFragment? = null
    private var mFragment2: ListFragment? = null
    private var mFragment3: ListFragment? = null
    private var mPagerAdapter: PagerAdapter? = null
    private var mTabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_tab)
        initView(savedInstanceState)


    }

    private fun initView(savedInstanceState: Bundle?) {
        mViewPager = findViewById(R.id.view_pager) as ViewPager
        mViewPager!!.offscreenPageLimit = 2
        mTabLayout = findViewById(R.id.toolbar_tab) as TabLayout
        if(savedInstanceState == null) {
           mFragment1 = ListFragment()
            mFragment1!!.initData('a', 'z')
            mFragment2 = ListFragment()
            mFragment2!!.initData('A', 'Z')
            mFragment3 = ListFragment()
            mFragment3!!.initData('c', 'x')
        }

        mPagerAdapter = PagerAdapter(supportFragmentManager)
        mViewPager!!.adapter = mPagerAdapter
        mViewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout!!.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))

    }

    inner class  PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            if (position == 0) {
                return mFragment1
            } else if (position == 1) {
                return mFragment2
            } else if (position == 2) {
                return mFragment3
            }

            return null
        }

        override fun getCount(): Int {
            return 3
        }
    }
}
