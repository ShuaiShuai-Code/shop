package com.fenboshi.fboshi.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fenboshi.fboshi.R
import com.fenboshi.fboshi.activity.OrderListActivity
import com.fenboshi.fboshi.bean.UserBean
import com.fenboshi.fboshi.db.DaoManager
import kotlinx.android.synthetic.main.activity_setting.*

class MineFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_setting, container, false)
        //数据库的使用 测试数据
        var userBean = UserBean()
        userBean.nickName = "默默-个人中心"
        DaoManager.getInstance().saveUserBean(userBean)
        var user = DaoManager.getInstance().userBean
//        Toast.makeText(context, user.nickName, Toast.LENGTH_SHORT).show()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_all_order.setOnClickListener {
            startActivityForResult(Intent(activity, OrderListActivity::class.java), 0)
        }
    }
}