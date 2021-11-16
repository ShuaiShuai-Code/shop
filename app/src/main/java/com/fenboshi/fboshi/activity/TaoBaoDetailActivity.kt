package com.fenboshi.fboshi.activity

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.bean.NineGooldsBean
import com.fenboshi.fboshi.network.ApiConstant
import com.fenboshi.fboshi.network.api.ApiCreator
import com.fenboshi.fboshi.network.rx.BaseObserver
import com.fenboshi.fboshi.network.rx.RxTransformer
import com.fenboshi.fboshi.util.ListDataSave
import com.fenboshi.fboshi.util.SignMD5Util
import com.fenboshi.fboshi.util.ToastUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.content_tao_bao_detail.*
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

class TaoBaoDetailActivity : AppCompatActivity() {

    var nineGooldsBean: NineGooldsBean? = null
    var nineGooldsBeanList: MutableList<NineGooldsBean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_tao_bao_detail)
        initdata()
        initListener()
    }


    private var outside_url: String = ""
    private var pic: String = ""
    private var pid: String = ""
    private var cname: String = ""
    private lateinit var money: BigDecimal
    private var title: String = ""
    private var id: String = ""
    private lateinit var coupons_money: BigDecimal


    private fun initdata() {
        money = BigDecimal(intent.getStringExtra("money"))
        title = intent.getStringExtra("title")
        pic = intent.getStringExtra("pic")
        id = intent.getStringExtra("id")
        Glide.with(this@TaoBaoDetailActivity).load(pic).into(img_my_shop)
        tv_title.setText(title)
        tv_money.setText(money.toString())
        nineGooldsBeanList = ArrayList()
        initgetData()
    }

    private fun initListener() {

        bt_toPay.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    bt_toPay.background =
                        ContextCompat.getDrawable(this, R.drawable.background_orger_pay_5)
                    // 记录点下去的点（起点）
                    val intent = Intent()
                    intent.setClass(this, TaoBaoPayActivity::class.java)
                    intent.putExtra("pic", pic)
                    intent.putExtra("id", id)
                    intent.putExtra("title", title)
                    intent.putExtra("money", money.toString())
                    startActivityForResult(intent, 0)
                    return@setOnTouchListener false
                }

            }

            return@setOnTouchListener false
        }
        //
        bt_toPay2.setOnClickListener {

            var listDataSave = ListDataSave(this, "gouwuche")
            nineGooldsBeanList = listDataSave.getDataList<NineGooldsBean>("gouwuche")
            nineGooldsBeanList?.forEach {
                if (it.id.equals(id)) {
                    ToastUtil.showToast("购物车已存在 ")
                    return@setOnClickListener
                }
            }
            nineGooldsBean = NineGooldsBean()
            nineGooldsBean?.marketingMainPic = pic
            nineGooldsBean?.mainPic = pic
            nineGooldsBean?.title = title
            nineGooldsBean?.id = id
            nineGooldsBean?.originalPrice = money.toString()

            nineGooldsBeanList?.add(nineGooldsBean!!)
            ToastUtil.showToast("已加入购物车")
            listDataSave.setDataList("gouwuche", nineGooldsBeanList)
//            ToTaobaoUtils.toTaoBao(this, outside_url, "mm_55212074_38988192_153818895")
        }
//        img_back.setOnClickListener {
//            finish()
//        }
//        rr_back.setOnClickListener {
//            finish()
//        }
//        try {
//            var cid = intent.getStringExtra("cid")
//            var gid = intent.getStringExtra("gid")
//
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    }
//备注
    private fun initgetData() {
        val paraMap = TreeMap<String, String>()
        paraMap["version"] = ApiConstant.version
        paraMap["appKey"] = ApiConstant.appkey
        paraMap["id"] = id!!
        val sign = SignMD5Util.getSignStr(paraMap, ApiConstant.appSecret)
        ApiCreator.getApi(0).getDetails(
            ApiConstant.appkey,
            paraMap.get("version"),
            paraMap.get("id"),
            sign
        )
            .compose(RxTransformer.errorHandle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<NineGooldsBean>() {
                override fun onSuccess(data: NineGooldsBean?) {
                    try {
                        tv_desc.setText(data!!.desc)
                        Glide.with(this@TaoBaoDetailActivity).load(data!!.imgs).into(img_my_shop)
                        var htmlData =
                            "<div><p style=\"text-align: center;\">铁头娃儿童袜尔特瑞特而羊肉汤有图图与体育体育体育图与体育台与体育体育体育体育图</p><p><img src=\"http://vod.hibeeok.com/image/default/66677E04F8F8474E80C2FD974BA56FC1-6-2.jpg\" title=\"1622094112360385.jpg\" alt=\"u=4180115,3626769320&amp;fm=15&amp;gp=0.jpg\" style=\"width:100%\"/><img src=\"http://vod.hibeeok.com/image/default/723A109404B84674950F49103B44B983-6-2.jpg\" title=\"1622094119519601.jpg\" alt=\"v2-ba9cdeb6ba816dfe6e3a097701a79ae3_720w.jpg\" style=\"width:100%\"/></p></div>"
                       // webview.loadData(htmlData, "text/html; charset=UTF-8", null)
                        //webview.loadDataWithBaseURL(null,htmlData, "text/html" , "utf-8", null);//加载html数据
                        webview.loadData(
                            htmlData.replace(
                                "<div",
                                "<div word-wrap:break-word;font-family:Arial;"
                            ), "text/html; charset=UTF-8", null
                        )

                    } catch (e: Exception) {

                    }


                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                }
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 200) {
            setResult(200)
            finish()
        }
    }

}
