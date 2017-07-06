package com.kotlin1


import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

/**
 * Created by alan on 17-7-6.
 */
class ListFragment : BaseFragment() {


    private var mDatas: ArrayList<String>? = null

     private var views: View? = null

    private var mListView: RecyclerView? = null

    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    internal var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun fetchData() {
        /**
         * doNetWork();
         */
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        views = inflater!!.inflate(R.layout.list_layout, container, false)
        initView()
        return views
    }

    private fun initView() {
        mSwipeRefreshLayout = views!!.findViewById(R.id.swipeLayout) as SwipeRefreshLayout
        mListView = views!!.findViewById(R.id.list_view) as RecyclerView
        mListView!!.layoutManager = LinearLayoutManager(mContext)
        mListView!!.adapter = ListAdapter()
        mSwipeRefreshLayout!!.setOnRefreshListener { mSwipeRefreshLayout!!.isRefreshing = false }
    }


    fun initData(a: Char, b: Char) {
        mDatas = ArrayList<String>()
        var i = a.toInt()
        while (i < b.toInt()) {
            mDatas!!.add("" + i.toChar())
            i++
        }
    }

    internal inner class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val holder = MyViewHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.item, parent,
                    false))
            return holder
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tv.text = mDatas!![position]
        }

        override fun getItemCount(): Int {
            return mDatas!!.size
        }

        internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            var tv: TextView

            init {
                tv = view.findViewById(R.id.id_num) as TextView
            }
        }
    }


}