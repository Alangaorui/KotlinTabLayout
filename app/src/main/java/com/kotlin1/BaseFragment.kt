package com.kotlin1

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by alan on 17-7-5.
 */
abstract class BaseFragment : Fragment() {


    /**
     * Fragment是否已经绑定
     */
    protected var isViewInitiated: Boolean = false
    /**
     * 用户是否可见
     */
    protected var isVisibleToUser: Boolean = false
    /**
     * 是否绑定数据
     */
    protected var isDataInitiated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareFetchData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareFetchData()
    }

    /**
     * 是时候绑定数据了
     */
    abstract fun fetchData()

    /**
     * 是时候准备数据了

     * @return
     */
    fun prepareFetchData(): Boolean {
        return prepareFetchData(false)
    }

    fun prepareFetchData(forceUpdate: Boolean): Boolean {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData()
            isDataInitiated = true
            return true
        }
        return false
    }

}