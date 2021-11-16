package com.fenboshi.fboshi.activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONException
import com.alibaba.fastjson.JSONObject
import com.fenboshi.fboshi.MainActivity
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.adpater.HomeRecyAdpater
import com.fenboshi.fboshi.bean.GooldsTypeBean
import com.fenboshi.fboshi.bean.UserBean
import com.fenboshi.fboshi.fragment.HomeTabFragment
import com.fenboshi.fboshi.fragment.OrderTabFragment
import com.fenboshi.fboshi.fragment.TabActivityPagerAdapter
import com.fenboshi.fboshi.util.OnItemClick
import kotlinx.android.synthetic.main.home_new_fragment.*
import kotlinx.android.synthetic.main.order_activity.myViewPager
import kotlinx.android.synthetic.main.order_activity.recyclerView
import java.util.*
import javax.inject.Inject


class OrderListActivity : AppCompatActivity() {
    @Inject
    lateinit var userBean: UserBean
    private var goodsList: List<GooldsTypeBean>? = null
    lateinit var homeRecyAdpater: HomeRecyAdpater
    private var listFragment: MutableList<Fragment>? = null
    private var adapterTabFragment: TabActivityPagerAdapter? = null
    lateinit var onItemClick: OnItemClick
    private val jsonCopy =
        "{ \"data\": [ { \"id\": 0, \"name\": \"待发货 \" },{ \"id\": 0, \"name\": \"待收货 \" },  { \"id\": 0, \"name\": \"已完成\" }, { \"id\": 0, \"name\": \"退款/售后\" } ] }"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_activity)
        listFragment = ArrayList()
        goodsList = ArrayList()
        onItemClick = OnItemClick {
            homeRecyAdpater!!.setSecled(it)
            recyclerView.smoothScrollToPosition(it)
            myViewPager.setCurrentItem(it)
        }

//        lin_fra_ask_search.setOnClickListener {
//            startActivity(Intent(this, SearchListActivity::class.java))
//        }
        try {

            val jsonObjectCopy =
                JSONObject.parseObject(jsonCopy)

            val jsonArrayCopy =
                jsonObjectCopy["data"] as JSONArray?

            goodsList = JSONArray.parseArray(
                jsonArrayCopy.toString(),
                GooldsTypeBean::class.java
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (goodsList != null) {
            updataUI(goodsList!!)

        }

    }


    fun updataUI(gooldsTypeBean: List<GooldsTypeBean>) {
        goodsList = gooldsTypeBean
        homeRecyAdpater = HomeRecyAdpater(this, goodsList!!, onItemClick)
        var heardView = LinearLayout.inflate(this, R.layout.home_heaher_view, null)
        var footerView = LinearLayout.inflate(this, R.layout.home_footview_view, null)

        recyclerView.layoutManager = LinearLayoutManager(this)
        (recyclerView.layoutManager as LinearLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL)
        recyclerView.adapter = homeRecyAdpater
//        homeRecyAdpater!!.setSecled(2)
        setTabFragment(gooldsTypeBean)
        homeRecyAdpater!!.setSecled(0)


    }

    private fun setTabFragment(newsListCopy: List<GooldsTypeBean>) {
        newsListCopy.forEach {
            val homeTabFragment = OrderTabFragment()
            //        fragment1.setViewPager(myViewPager);
            val bundle = Bundle()
            bundle.putString("keyword", it.name)
            homeTabFragment.arguments = bundle
            listFragment?.add(homeTabFragment)
        }

        adapterTabFragment = TabActivityPagerAdapter(supportFragmentManager, listFragment)
//        var myViewPager = view!!.findViewById(R.id.myViewPager) as ViewPager
        myViewPager.setOnPageChangeListener(MyPagerChangeListener())
        myViewPager.setAdapter(adapterTabFragment)

    }

    inner class MyPagerChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            homeRecyAdpater!!.setSecled(position)
            recyclerView.smoothScrollToPosition(position)
        }

    }
}