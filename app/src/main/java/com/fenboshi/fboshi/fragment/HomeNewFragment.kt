package com.fenboshi.fboshi.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONException
import com.alibaba.fastjson.JSONObject
import com.fenboshi.fboshi.MainActivity
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.activity.SearchListActivity
import com.fenboshi.fboshi.adpater.HomeRecyAdpater
import com.fenboshi.fboshi.base.BaseFragment
import com.fenboshi.fboshi.bean.GooldsTypeBean
import com.fenboshi.fboshi.bean.UserBean
import com.fenboshi.fboshi.network.ApiConstant
import com.fenboshi.fboshi.network.api.ApiCreator
import com.fenboshi.fboshi.network.rx.BaseObserver
import com.fenboshi.fboshi.network.rx.RxTransformer
import com.fenboshi.fboshi.util.OnItemClick
import com.fenboshi.fboshi.util.SignMD5Util
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.home_new_fragment.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class HomeNewFragment : BaseFragment() {

    @Inject
    lateinit var userBean: UserBean

    private var goodsList: List<GooldsTypeBean>? = null
    lateinit var homeRecyAdpater: HomeRecyAdpater
    private var listFragment: MutableList<Fragment>? = null
    private var adapterTabFragment: TabFragmentPagerAdapter? = null
    lateinit var onItemClick: OnItemClick
    private val jsonCopy =
        "{ \"data\": [ { \"id\": 0, \"name\": \"女装 \" },{ \"id\": 0, \"name\": \"母婴 \" },  { \"id\": 0, \"name\": \"美妆\" }, { \"id\": 0, \"name\": \"居家日用\" }, { \"id\": 0, \"name\": \"居家日用\" }, { \"id\": 0, \"name\": \"鞋\" }, { \"id\": 0, \"name\": \"居家日用\" }, { \"id\": 0, \"name\": \"美食\" }, { \"id\": 0, \"name\": \"汽车用品\" }, { \"id\": 0, \"name\": \"数码\" }, { \"id\": 0, \"name\": \"男装\" } ] }"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.home_new_fragment,
            container, false
        )
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listFragment = ArrayList()
        goodsList = ArrayList()
        onItemClick = OnItemClick {
            homeRecyAdpater!!.setSecled(it)
            recyclerView.smoothScrollToPosition(it)
            myViewPager.setCurrentItem(it)
        }

        lin_fra_ask_search.setOnClickListener {
            startActivity(Intent(activity, SearchListActivity::class.java))
        }
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
        homeRecyAdpater = HomeRecyAdpater(activity as MainActivity, goodsList!!, onItemClick)
        var heardView = LinearLayout.inflate(context, R.layout.home_heaher_view, null)
        var footerView = LinearLayout.inflate(context, R.layout.home_footview_view, null)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        (recyclerView.layoutManager as LinearLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL)
        recyclerView.adapter = homeRecyAdpater
//        homeRecyAdpater!!.setSecled(2)
        setTabFragment(gooldsTypeBean)
        homeRecyAdpater!!.setSecled(0)


    }

    private fun setTabFragment(newsListCopy: List<GooldsTypeBean>) {
        newsListCopy.forEach {
            val homeTabFragment = HomeTabFragment()
            //        fragment1.setViewPager(myViewPager);
            val bundle = Bundle()
            bundle.putString("keyword", it.name)
            homeTabFragment.arguments = bundle
            listFragment?.add(homeTabFragment)
        }

        adapterTabFragment = TabFragmentPagerAdapter(childFragmentManager, listFragment)
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













