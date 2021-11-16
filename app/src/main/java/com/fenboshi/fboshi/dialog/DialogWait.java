package com.fenboshi.fboshi.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fenboshi.fboshi.R;


public class DialogWait extends Dialog {


    public DialogWait(Context context) {
        super(context, R.style.dialog);
        setContentView(R.layout.dialog_wait);
        setCanceledOnTouchOutside(false);
        if (null == context) {
            return;
        }
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

    }


}
