package com.fenboshi.fboshi.activity

import android.app.Activity
import android.app.Dialog
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.bean.NineGooldsBean
import com.fenboshi.fboshi.dialog.DialogWait
import com.fenboshi.fboshi.util.ListDataSave
import com.fenboshi.fboshi.util.ToTaobaoUtils
import com.fenboshi.fboshi.util.ToastUtil
import kotlinx.android.synthetic.main.activity_shop_order_off.*
import kotlinx.android.synthetic.main.view_new_header_white.*

import java.math.BigDecimal
import java.util.*

class TaoBaoPayActivity : AppCompatActivity() {

    lateinit var dialogWait: DialogWait
    var nineGooldsBean: NineGooldsBean? = null
    var nineGooldsBeanList: MutableList<NineGooldsBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_order_off)
        initdata()
        initListener()
    }


    private var outside_url: String = ""
    private var pic: String = ""
    private var id: String = ""
    private var pid: String = ""
    private var cname: String = ""
    private lateinit var money: String
    private var title: String = ""
    private lateinit var coupons_money: BigDecimal


    private fun initdata() {
        money = intent.getStringExtra("money")
        title = intent.getStringExtra("title")
        pic = intent.getStringExtra("pic")
        id = intent.getStringExtra("id")
        Glide.with(this@TaoBaoPayActivity).load(pic).into(img_photo)
        text_header_title.setText("订单详情")
        tv_title.setText(title)
        tv_money.setText(money.toString())
        id_money.setText(money.toString())
        tv_to_money.setText(money.toString())
        tv_pay_money.setText(money.toString())
    }

    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 1) {
                dialogWait.dismiss()
                var listDataSave = ListDataSave(this@TaoBaoPayActivity, "dingdan")
                nineGooldsBeanList = listDataSave.getDataList<NineGooldsBean>("dingdan")

                nineGooldsBean = NineGooldsBean()
                nineGooldsBean?.marketingMainPic = pic
                nineGooldsBean?.mainPic = pic
                nineGooldsBean?.title = title
                nineGooldsBean?.id = id
                nineGooldsBean?.originalPrice = money.toString()

                nineGooldsBeanList?.add(nineGooldsBean!!)
                listDataSave.setDataList("dingdan", nineGooldsBeanList)
                ToastUtil.showToast("支付成功")
                setResult(200)
                finish()
            }
        }
    }

    private fun initListener() {

        tv_submit.setOnClickListener {
            dialogWait = DialogWait(this)
            dialogWait.show()
            Thread {
                Thread.sleep(5000)
                handler.sendEmptyMessage(1)
            }.start()

        }

    }

}
