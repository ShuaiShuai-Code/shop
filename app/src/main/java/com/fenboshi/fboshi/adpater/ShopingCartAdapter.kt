package com.fenboshi.fboshi.adpater

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.activity.TaoBaoDetailActivity
import com.fenboshi.fboshi.bean.NineGooldsBean
import com.fenboshi.fboshi.taobao.Constants
import com.fenboshi.fboshi.util.GlideRoundTransformTop
import com.fenboshi.fboshi.util.MoreCheckError
import com.fenboshi.fboshi.util.OnItemClick

class ShopingCartAdapter(
    private val context: FragmentActivity,
    var listItem: List<NineGooldsBean>,
    var cid: String,
    var cname: String,
    var curl: String,
    var cnumber: String,
    var pid: String,
    var onItemClick: OnItemClick
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_shop_cart, null)
            //我猜这个函数的作用是指定这个类所对应的小毛驴文件
            holder = ViewHolder()
            holder.title = view.findViewById(R.id.tv_title)
            holder.money = view.findViewById(R.id.tv_money)
            holder.tv_pay = view.findViewById(R.id.tv_pay)
            holder.pic = view.findViewById(R.id.imageView)
            holder.checkbox = view.findViewById(R.id.checkbox)
            view.tag = holder
        } else {
            holder = (view?.tag) as ViewHolder
        }
        var goolist = listItem.get(position)

        holder.title.text = goolist.title
        holder.money.text = Constants.MONEY + goolist.originalPrice
        val requestOptions = RequestOptions()
            .circleCrop()
            .transform(GlideRoundTransformTop(5))

        Glide.with(context).load(goolist.mainPic)
            .into(holder.pic)
        holder.checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                listItem.get(position).setCheckBox(true)
                onItemClick.onClick(position)
            }
        })
        if (listItem.get(position).checkBox) {
            holder.checkbox.isSelected = false
        }
        view!!.setOnClickListener {
            if (MoreCheckError.isFastClick()) {
                val intent = Intent()
                intent.setClass(context, TaoBaoDetailActivity::class.java)
                intent.putExtra("pic", goolist.marketingMainPic)
                intent.putExtra("title", goolist.title)
                intent.putExtra("money", goolist.originalPrice)
                intent.putExtra("id", goolist.id)
                context.startActivityForResult(intent, 0)

            }


        }
        return view!!
    }

    override fun getItem(position: Int): Any = listItem[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = listItem.size


    inner class ViewHolder {
        lateinit var title: TextView
        lateinit var money: TextView
        lateinit var tv_pay: TextView
        lateinit var pic: ImageView
        lateinit var checkbox: CheckBox
    }


}
