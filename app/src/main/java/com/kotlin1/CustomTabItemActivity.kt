package com.kotlin1

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class CustomTabItemActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    private var mFragment1: ListFragment? = null
    private var mFragment2: ListFragment? = null
    private var mTabLayout: TabLayout? = null
    private var mPagerAdapter: PagerAdapter? = null
    private var titles: ArrayList<String> = ArrayList()
    private var isSelected: Boolean = false

    private val tabIcons = intArrayOf(
            R.mipmap.icon_down,
            R.mipmap.icon_down)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_tab_item)
        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) {
        mViewPager = findViewById(R.id.view_pager) as ViewPager
        mViewPager!!.offscreenPageLimit = 2
        mTabLayout = findViewById(R.id.toolbar_tab) as TabLayout

        if (savedInstanceState == null) {
            mFragment1 = ListFragment()
            mFragment1!!.initData('a','z')
            mFragment2 = ListFragment()
            mFragment2!!.initData('A','Z')
        }
        titles.add("第一个")
        titles.add("第二个")

        mPagerAdapter = PagerAdapter(supportFragmentManager)
        mViewPager!!.setAdapter(mPagerAdapter)
        mViewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout!!.setupWithViewPager(mViewPager)
        mTabLayout!!.getTabAt(0)!!.customView = getTabView(0)
        mTabLayout!!.getTabAt(1)!!.customView = getTabView(1)
        mTabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                changeTabStatus(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                changeTabStatus(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


    private fun changeTabStatus(tab: TabLayout.Tab, selected: Boolean) {
        val view = tab.customView
        val imgTitle = view!!.findViewById(R.id.img_title) as ImageView
        val txtTitle = view.findViewById(R.id.txt_title) as TextView
        imgTitle.visibility = View.VISIBLE
        if (selected) {
            txtTitle.setTextColor(Color.parseColor("#0EA73C"))
            startPropertyAnim(imgTitle)
        } else {
            txtTitle.setTextColor(Color.parseColor("#7f7f7f"))
            imgTitle.visibility = View.INVISIBLE
        }
    }

    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(this).inflate(R.layout.item_tab, null)
        val txtTitle = view.findViewById(R.id.txt_title) as TextView
        val imgTitle = view.findViewById(R.id.img_title) as ImageView
        imgTitle.setImageResource(tabIcons[position])
        txtTitle.text = titles[position]
        if (position == 0) {
            txtTitle.setTextColor(Color.parseColor("#057523"))
        } else {
            imgTitle.visibility = View.INVISIBLE
            txtTitle.setTextColor(Color.parseColor("#ced0d3"))
        }

        view.setOnClickListener {
            startPropertyAnim(imgTitle)
            mViewPager!!.setCurrentItem(position)
        }
        return view
    }

    private fun startPropertyAnim(v: ImageView) {
        var anim: ObjectAnimator? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            anim = ObjectAnimator.ofFloat(v, "rotation", 180f, 360f)
            anim!!.duration = 500
            anim.start()
        }

    }

    inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            if (position == 0) {
                return mFragment1
            } else if (position == 1) {
                return mFragment2
            }

            return null
        }

        override fun getCount(): Int {
            return 2
        }

    }
}
