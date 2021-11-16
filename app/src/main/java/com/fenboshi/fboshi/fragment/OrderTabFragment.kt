package com.fenboshi.fboshi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.adpater.HomeBeeShopAdapter
import com.fenboshi.fboshi.adpater.OrderListAdapter
import com.fenboshi.fboshi.bean.NineGooldsBean
import com.fenboshi.fboshi.network.ApiConstant
import com.fenboshi.fboshi.network.api.ApiCreator
import com.fenboshi.fboshi.network.rx.BaseObserver
import com.fenboshi.fboshi.network.rx.RxTransformer
import com.fenboshi.fboshi.util.ListDataSave
import com.fenboshi.fboshi.util.SignMD5Util
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.look_fragment.*
import java.util.*

class OrderTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.order_fragment, container, false)

//        var searchkey: String? = arguments!!.getString("keyword")
//        val paraMap = TreeMap<String, String>()
//        paraMap["version"] = ApiConstant.version
//        paraMap["appKey"] = ApiConstant.appkey
//        paraMap["pageId"] = "1"
//        paraMap["pageSize"] = "20"
//        paraMap["keyWords"] = searchkey!!
//        val sign = SignMD5Util.getSignStr(paraMap, ApiConstant.appSecret)
//        ApiCreator.getApi(0).getSearchList(
//            ApiConstant.appkey,
//            paraMap.get("version"),
//            paraMap.get("pageId"),
//            paraMap.get("pageSize"),
//            paraMap.get("keyWords"),
//            sign
//        )
//            .compose(RxTransformer.errorHandle())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : BaseObserver<NineGooldsBean>() {
//                override fun onSuccess(data: NineGooldsBean?) {
//                    Log.i("LookFragment", "" + data!!.list!!.size)
//
//
//                }
//
//                override fun onError(e: Throwable) {
//                    super.onError(e)
//                }
//            })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var listDataSave = ListDataSave(activity, "dingdan")
        var nineGoodlsList = listDataSave.getDataList<NineGooldsBean>("dingdan")
        if (nineGoodlsList.size > 0) {
            tv_null?.visibility = View.GONE
            gridView?.visibility = View.VISIBLE
        }
        val homeNewFragment =
            activity?.let {
                OrderListAdapter(
                    it,
                    nineGoodlsList,
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            }
        try {
            gridView.adapter = homeNewFragment
        }catch (e:Exception){

        }
    }
}