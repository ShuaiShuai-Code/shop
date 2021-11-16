package com.fenboshi.fboshi.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.adpater.HomeBeeShopAdapter
import com.fenboshi.fboshi.adpater.ShopingCartAdapter
import com.fenboshi.fboshi.bean.NineGooldsBean
import com.fenboshi.fboshi.bean.UserBean
import com.fenboshi.fboshi.dialog.DialogWait
import com.fenboshi.fboshi.network.ApiConstant
import com.fenboshi.fboshi.network.api.ApiCreator
import com.fenboshi.fboshi.network.rx.BaseObserver
import com.fenboshi.fboshi.network.rx.RxTransformer
import com.fenboshi.fboshi.util.*
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.chat_fragment.*
import kotlinx.android.synthetic.main.look_fragment.*
import kotlinx.android.synthetic.main.look_fragment.gridView
import kotlinx.android.synthetic.main.look_fragment.tv_null
import java.util.*
import kotlin.collections.ArrayList

class ChatFragment : Fragment() {
    lateinit var dialogWait: DialogWait
    var homeNewFragment: ShopingCartAdapter? = null
    lateinit var nineGoodlsList: MutableList<NineGooldsBean>
    lateinit var nineGoodlsList3: MutableList<NineGooldsBean>
    lateinit var nineGoodlsList2: MutableList<NineGooldsBean>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)
//        val paraMap = TreeMap<String, String>()
//        paraMap["version"] = ApiConstant.version
//        paraMap["appKey"] = ApiConstant.appkey
//        paraMap["pageId"] = "1"
//        paraMap["pageSize"] = "20"
//        paraMap["cid"] = "1,2,3,4,5,6,7,8,9,10,11,12,13,14"
//        val sign = SignMD5Util.getSignStr(paraMap, ApiConstant.appSecret)
//        ApiCreator.getApi(0).getCollectionList(
//            ApiConstant.appkey,
//            paraMap.get("version"),
//            paraMap.get("pageId"),
//            paraMap.get("pageSize"),
//            paraMap.get("cid"), sign
//        )
//            .compose(RxTransformer.errorHandle())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : BaseObserver<NineGooldsBean>() {
//                override fun onSuccess(data: NineGooldsBean?) {
//                    Log.i("LookFragment", "" + data!!.list!!.size)
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
        nineGoodlsList2 = ArrayList()
        nineGoodlsList3 = ArrayList()
        var listDataSave = ListDataSave(activity, "gouwuche")
        nineGoodlsList = listDataSave.getDataList<NineGooldsBean>("gouwuche")
        var money = 0.0
        homeNewFragment =
            activity?.let {
                ShopingCartAdapter(
                    it,
                    nineGoodlsList,
                    "",
                    "",
                    "",
                    "",
                    "",
                    OnItemClick {
                        ll_money.visibility = View.VISIBLE
                        money = money + nineGoodlsList.get(it).originalPrice.toDouble()
                        tv_zhifu_money.text = Constants.MONEY + money


                    }
                )
            }
        tv_submit.setOnClickListener {
            dialogWait = DialogWait(activity)
            dialogWait.show()
            Thread {
                Thread.sleep(5000)
                handler.sendEmptyMessage(1)
            }.start()
        }
        gridView.adapter = homeNewFragment

        tv_null.visibility = View.GONE
        gridView.visibility = View.VISIBLE
        if (nineGoodlsList!!.size == 0) {
            tv_null.visibility = View.VISIBLE
            gridView.visibility = View.GONE
        }
    }

    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                dialogWait.dismiss()
                ToastUtil.showToast("支付成功")
                var listDataSave = ListDataSave(activity, "gouwuche")
                nineGoodlsList.forEach {
                    if (!it.checkBox) {
                        nineGoodlsList2.add(it)
                    }
                }
                listDataSave.setDataList("gouwuche", nineGoodlsList2)
                if (nineGoodlsList.size > 0)
                    nineGoodlsList.clear()
                nineGoodlsList3 = listDataSave.getDataList<NineGooldsBean>("gouwuche")
                nineGoodlsList.addAll(nineGoodlsList3)
                homeNewFragment?.notifyDataSetChanged()

            }
        }
    }

}

