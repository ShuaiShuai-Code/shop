package com.fenboshi.fboshi.activity

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.adpater.HomeSearchBeeShopAdapter
import com.fenboshi.fboshi.bean.NineGooldsBean
import com.fenboshi.fboshi.network.ApiConstant
import com.fenboshi.fboshi.network.api.ApiCreator
import com.fenboshi.fboshi.network.rx.BaseObserver
import com.fenboshi.fboshi.network.rx.RxTransformer
import com.fenboshi.fboshi.util.SignMD5Util
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.look_fragment.*
import kotlinx.android.synthetic.main.look_fragment.gridView
import kotlinx.android.synthetic.main.search_fragment.*
import java.util.*

class SearchListActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_fragment)

        tv_seatch.setOnClickListener {
            search(edit_search.text.toString())
        }


    }

    fun search(keyword: String) {
        if (TextUtils.isEmpty(keyword))
            return
        val paraMap = TreeMap<String, String>()
        paraMap["version"] = ApiConstant.version
        paraMap["appKey"] = ApiConstant.appkey
        paraMap["pageId"] = "1"
        paraMap["pageSize"] = "20"
        paraMap["keyWords"] = keyword
        val sign = SignMD5Util.getSignStr(paraMap, ApiConstant.appSecret)
        ApiCreator.getApi(0).getSearchList(
            ApiConstant.appkey,
            paraMap.get("version"),
            paraMap.get("pageId"),
            paraMap.get("pageSize"),
            paraMap.get("keyWords"),
            sign
        )
            .compose(RxTransformer.errorHandle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<NineGooldsBean>() {
                override fun onSuccess(data: NineGooldsBean?) {
                    Log.i("LookFragment", "" + data!!.list!!.size)
                    val homeNewFragment =
                        this?.let {
                            HomeSearchBeeShopAdapter(
                                this@SearchListActivity,
                                data!!.list,
                                "",
                                "",
                                "",
                                "",
                                "",
                                ""
                            )
                        }
                    gridView.adapter = homeNewFragment
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                }
            })
    }
}