package com.fenboshi.fboshi.adpater

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.bean.GooldsTypeBean
import com.fenboshi.fboshi.util.OnItemClick
import java.awt.font.TextAttribute
import java.time.format.TextStyle

class OrderRecyAdpater(
    private var mContext: Context,
    private var data: List<GooldsTypeBean>,
    var onItemClick: OnItemClick
) :
    RecyclerView.Adapter<OrderRecyAdpater.MyHolder>() {

    fun setSecled(position: Int) {
        data.forEach {
            it.selected = 0
        }
        if (data.size != 0) {
            data.get(position).selected = 1
            notifyDataSetChanged()
        }

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        var gooldsTypeBean: GooldsTypeBean = data.get(position)
        holder.text_news?.text = gooldsTypeBean.name
        if (gooldsTypeBean.selected == 1) {
            holder.img_news?.visibility = View.VISIBLE
            holder.text_news?.textSize = 19f
            holder.text_news?.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
        } else {
            holder.img_news?.visibility = View.GONE
            holder.text_news?.textSize = 15f
            holder.text_news?.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        }

        holder.text_news!!.setOnClickListener {
            onItemClick.onClick(position)
        }


    }

    override fun getItemCount(): Int {

        return data.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var View =
            LayoutInflater.from(mContext).inflate(R.layout.order_new_header_item, parent, false)
        return MyHolder("", View)
    }

    inner class MyHolder(myView: View) : RecyclerView.ViewHolder(myView) {
        var text_news: TextView? = null
        var img_news: View? = null

        constructor (str: String, myView: View) : this(myView) {
            text_news = myView.findViewById(R.id.text_news)
            img_news = myView.findViewById(R.id.img_news)

        }
    }


}


